package com.example.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table
public class ScheduleDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column
    String train_name;
    @Column
    String station_name;
    @JsonFormat(pattern = "HH:mm:ss")
    @Column
    LocalTime time;

    public ScheduleDB() {
    }
}
