package com.example._Laba.Year;

import com.example._Laba.enities.EmbeddableResponse;
import com.example._Laba.enities.ResponseEntityData;
import com.example._Laba.repositories.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncOperation {

    @Autowired
    private ResponseRepository responseRepository;

    @Async
    public CompletableFuture<HttpStatus> compute(Counter counter, int year) {
        EmbeddableResponse embeddableResponse = new EmbeddableResponse(counter.checkForLeap(), counter.countDays());
        ResponseEntityData responseEntityData = new ResponseEntityData(embeddableResponse, year);
        Optional<ResponseEntityData> dataOptional = responseRepository.findOne(Example.of(responseEntityData));
        if (!dataOptional.isPresent()) responseRepository.saveAndFlush(responseEntityData);
        return CompletableFuture.completedFuture(HttpStatus.OK);
    }
}
