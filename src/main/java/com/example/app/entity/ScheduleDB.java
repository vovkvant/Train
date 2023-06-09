package com.example.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class ScheduleDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column
    String train_name;
    @Column
    String station_name;
    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "time")
    LocalTime time;

    public ScheduleDB() {
    }

    public ScheduleDB(Integer id, String train_name, String station_name, LocalTime time) {
        this.id = id;
        this.train_name = train_name;
        this.station_name = station_name;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public String getTrain_name() {
        return train_name;
    }

    public String getStation_name() {
        return station_name;
    }
    public LocalTime getTime() {
        return time;
    }
}
