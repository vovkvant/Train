package com.example.app.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "train_id")
    private Integer trainId;
    @Column(name = "passenger_id")
    private Integer passengerId;
    @Column(name = "start_station_id")
    private Integer startStationId;
    @Column(name = "end_station_id")
    private Integer endStationId;

}
