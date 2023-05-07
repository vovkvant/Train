package com.example.springboot.transportation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Schedule {
    private Date date;
    private Train train;
    public void setDate(Date date) {
        this.date = date;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "date=" + date +
                ", train=" + train +
                '}';
    }
}
