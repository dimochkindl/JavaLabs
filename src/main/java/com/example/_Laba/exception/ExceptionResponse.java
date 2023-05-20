package com.example._Laba.exception;

import java.time.ZonedDateTime;

public class ExceptionResponse {
    private String message;

    private ZonedDateTime timestamp;

    private int code;

    public ExceptionResponse(String message, int code, ZonedDateTime time) {
        this.message = message;
        this.code = code;
        this.timestamp= time;
    }

    public ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
