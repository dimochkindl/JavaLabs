package com.example._Laba.collections;

import com.example._Laba.Year.Statistics;
import com.example._Laba.enities.EmbeddableResponse;

import java.util.Collection;

public class YearResponseCollection {

    public Collection<EmbeddableResponse> embeddableResponses;
    private Statistics stat;

    public YearResponseCollection(Collection<EmbeddableResponse> embeddableResponses) {
        this.embeddableResponses = embeddableResponses;
    }

    public void setStat(Statistics stat) {
        this.stat = stat;
    }

    public Statistics getStat() {
        return stat;
    }
}
