package com.example._Laba.Year;

import com.example._Laba.interfaces.YearOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class Counter implements YearOperations {
    private Year year;
    private String content = "This year is leap";
    public Counter(Year year) {
        this.year = year;
    }

    @Override
    public String checkForLeap() {
        if (year.getYear() % 400 == 0)
            return content;
        else if (year.getYear() % 100 == 0)
            return "This year is not leap";
        else if (year.getYear() % 4 == 0)
            return content;
        else
            return "This year is not leap";
    }

    @Override
    public boolean validCheck() {
        if (year.getYear() < 1) return false;
        return true;
    }

    public Year getYear() {
        return year;
    }

    @Override
    public int countDays() {
        if(checkForLeap() == content)
            return 364;
        return 365;
    }
}
