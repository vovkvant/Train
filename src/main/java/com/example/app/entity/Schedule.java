package com.example.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "station_id")
    Integer stationId;
    @Column(name = "train_id")
    Integer trainId;
    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "time")
    LocalTime time;

    public Schedule() {
    }

    public Schedule(Integer id, Integer stationId, Integer trainId, LocalTime time) {
        this.id = id;
        this.stationId = stationId;
        this.trainId = trainId;
        this.time = time;
    }
}
