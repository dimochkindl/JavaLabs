package com.example._Laba.enities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EmbeddableResponse {
    @Column(nullable = false)
    private String response;

    @Column(nullable = false)
    private int days;


    public EmbeddableResponse(String response, int days) {
        this.response = response;
        this.days = days;
    }

    public EmbeddableResponse(EmbeddableResponse other){
        response = other.getResponse();
        days = other.getDays();
    }

    public EmbeddableResponse() {
        this("",0);
    }

    public String getResponse() {
        return response;
    }

    public int getDays() {
        return days;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
