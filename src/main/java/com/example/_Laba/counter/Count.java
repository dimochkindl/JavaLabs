package com.example._Laba.counter;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Count {
    public static AtomicInteger counter = new AtomicInteger(0);
    public void add() {
        counter.incrementAndGet();
    }

    public static AtomicInteger getCounter() {
        return counter;
    }
}
