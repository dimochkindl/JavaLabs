package com.example._Laba.enities;

import jakarta.persistence.*;
import com.example._Laba.Year.Response;

@Entity
@Table(name = "response")
public class ResponseEntityData {

    @Basic
    @Column(name = "year", nullable = false)
    private int year;

    @Embedded
    @Column(name = "response", nullable = false)
    private EmbeddableResponse response;
    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public ResponseEntityData(EmbeddableResponse response, int year) {
        this.response = response;
        this.year = year;
    }

    public ResponseEntityData(Response response, Integer id, Integer year) {
        this.id = id;
        this.year = year;
    }

    public ResponseEntityData() {
        response = new EmbeddableResponse();
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setResponse(EmbeddableResponse response) {
        this.response = response;
    }

    public EmbeddableResponse getResponse() {
        return response;
    }
}
