package com.example._Laba.Controllers;

import com.example._Laba.Year.*;
import com.example._Laba.cache.MyCash;
import com.example._Laba.collections.YearRequestCollection;
import com.example._Laba.collections.YearResponseCollection;
import com.example._Laba.counter.Count;
import com.example._Laba.enities.EmbeddableResponse;
import com.example._Laba.enities.ResponseEntityData;
import com.example._Laba.repositories.ResponseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class Controller {

    private final Logger logger = LoggerFactory.getLogger(Controller.class);
    private final MyCash<Integer, Response> cache;
    @Autowired
    private final Count count;

    @Autowired
    private AsyncOperation asyncOperation;

    @Autowired
    private ResponseRepository responseRepository;

    public Controller(MyCash<Integer, Response> cache, Count count) {
        this.cache = cache;
        this.count = count;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/check")
    public ResponseEntity<Response> check(@RequestParam int year) {
        count.add();
        Counter counter = new Counter(new Year(year));

        if (cache.get(year) != null) return new ResponseEntity(cache.get(year), HttpStatus.OK);
        logger.info(String.format("Input: " + "%s", year));
        if (!counter.validCheck())
            return new ResponseEntity(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Year is below 1"), HttpStatus.BAD_REQUEST);
        logger.info("Checked for valid input");
        Response response = new Response(counter.checkForLeap(), counter.countDays());
        cache.push(year, response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/counter")
    public AtomicInteger counter() {
        count.add();
        return Count.getCounter();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/set_data")
    public void setData() {
        Counter counter = new Counter(new Year(2000));
        EmbeddableResponse response = new EmbeddableResponse(counter.checkForLeap(), counter.countDays());
        ResponseEntityData responseEntity = new ResponseEntityData(response, 2000);
        //responseRepository.save(responseEntity);
        responseRepository.deleteAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save", consumes = "application/json", produces = "application/json")
    public ResponseEntityData saveData(@RequestBody RequestYear requestYear) {
        ResponseEntityData responseEntityData = new ResponseEntityData(requestYear.getResponse(), requestYear.getYear());
        logger.info("Created new responseEntityData");
        Optional<ResponseEntityData> dataOptional = responseRepository.findOne(Example.of(responseEntityData));
        if (!dataOptional.isPresent()) responseRepository.saveAndFlush(responseEntityData);
        logger.info("Checked for uniqueness new response");
        return responseEntityData;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/bulk", consumes = "application/json", produces = "application/json")
    public YearResponseCollection bulkOperations(@RequestBody YearRequestCollection collection) {
        var entities = collection.requestYears.stream().map(year -> {
            var yearEntity = new ResponseEntityData(year.getResponse(), year.getYear());
            return yearEntity;
        }).collect(Collectors.toList());

        var dataOptional = entities.stream().map(entity -> {
            var data = responseRepository.findOne(Example.of(entity));
            return data;
        }).collect(Collectors.toList());

        IntStream.range(0, dataOptional.size()).forEach(index -> {
            var optional = dataOptional.get(index);
            var data = entities.get(index);
            if (!optional.isPresent()) {
                responseRepository.saveAndFlush(data);
            }
        });

        YearResponseCollection responseCollection = new YearResponseCollection(entities.stream().map(yearEntity -> {
            var response = new EmbeddableResponse(yearEntity.getResponse());
            return response;
        }).collect(Collectors.toList()));


        Aggregator aggregator = new Aggregator();
        aggregator.setMax(entities);
        aggregator.setMin(entities);
        aggregator.setAverage(entities);
        responseCollection.setStat(aggregator.getStatistics());

        return responseCollection;
    }
    
    @RequestMapping(method = RequestMethod.GET, path = "/async")
    public HttpStatus async(@RequestParam int year) throws ExecutionException, InterruptedException {
        Counter count = new Counter(new Year(year));
        CompletableFuture<HttpStatus> future = asyncOperation.compute(count, year);
        HttpStatus status = future.get();
        return status;
    }

}