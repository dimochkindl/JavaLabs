package com.example._Laba.Year;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CounterTest {

    @ParameterizedTest
    @ValueSource(ints = {2022, 2023})
    void checkForLeap(int year) {
        Counter counter = new Counter(new Year(year));
        String actual = counter.checkForLeap();
        assertEquals("This year is not leap", actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {2024, 2020})
    void checkForLeap_2(int year) {
        Counter counter = new Counter(new Year(year));
        String actual = counter.checkForLeap();
        assertEquals("This year is leap", actual);
    }

}