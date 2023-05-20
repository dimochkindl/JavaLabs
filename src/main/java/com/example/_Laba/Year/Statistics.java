package com.example._Laba.Year;

public class Statistics {

    private int min, max;
    private double average;

    public Statistics(int min, int max, double middle) {
        this.min = min;
        this.max = max;
        this.average = middle;
    }

    public Statistics() {
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public double getAverage() {
        return average;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
