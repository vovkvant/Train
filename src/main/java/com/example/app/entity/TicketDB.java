package com.example.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table
public class TicketDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column
    Integer train_id;
    @Column
    Integer start_station_id;
    @Column
    Integer end_station_id;
    @JsonFormat(pattern = "HH:mm:ss")
    @Column
    LocalTime time;

    public TicketDB() {

    }
}
