package com.example._Laba.Year;

import com.example._Laba.enities.ResponseEntityData;

import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public class Aggregator {
    private Statistics statistics;

    public Aggregator() {
        statistics = new Statistics();
    }

    public void setMax(List<ResponseEntityData> entities){
        OptionalInt optionalMax = entities.stream().mapToInt(ResponseEntityData::getYear).max();
        if(optionalMax.isPresent())
            statistics.setMax(optionalMax.getAsInt());
    }

    public void setMin(List<ResponseEntityData> entities){
        OptionalInt optionalMin = entities.stream().mapToInt(ResponseEntityData::getYear).min();
        if(optionalMin.isPresent())
            statistics.setMin(optionalMin.getAsInt());
    }

    public void setAverage(List<ResponseEntityData> entities){
        OptionalDouble optionalAverage = entities.stream().mapToDouble(ResponseEntityData::getYear).average();
        if(optionalAverage.isPresent())
            statistics.setAverage(optionalAverage.getAsDouble());
    }

    public Statistics getStatistics() {
        return statistics;
    }
}
