package com.example._Laba.Year;

import com.example._Laba.enities.EmbeddableResponse;

public class RequestYear {
    private EmbeddableResponse response;
    private int year;

    public RequestYear(EmbeddableResponse response, int year) {
        this.response = response;
        this.year = year;
    }

    public RequestYear() {
    }

    public RequestYear(EmbeddableResponse response) {
        this.response = response;
    }

    public RequestYear(int year) {
        this.year = year;
    }

    public EmbeddableResponse getResponse() {
        return response;
    }

    public int getYear() {
        return year;
    }
}
