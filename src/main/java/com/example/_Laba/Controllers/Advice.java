package com.example._Laba.Controllers;

import com.example._Laba.exception.ExceptionResponse;
import com.example._Laba.exception.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class Advice {
    private final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionResponse> handleException(ResponseStatusException e) {
        Error error = new Error("Bad Request");
        logger.error(error.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(), e.hashCode(),
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleException(RuntimeException e) {
        Error error = new Error("Internal Server Error");
        logger.error(error.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(), e.hashCode(),
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(ServerException ex) {
        Error error = new Error("My Bad Request");
        logger.error(error.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), ex.hashCode(),
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
