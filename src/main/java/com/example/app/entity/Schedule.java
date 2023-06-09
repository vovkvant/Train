package com.example.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name="station_id", nullable=false)
    private Station station;

    @ManyToOne
    @JoinColumn(name="train_id", nullable=false)
    private Train train;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "time")
    LocalTime time;

    public Integer getId() {
        return id;
    }

    public LocalTime getTime() {
        return time;
    }

    public Station getStation() {
        return station;
    }

    public Train getTrain() {
        return train;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", station=" + station.toString() +
                ", train=" + train.toString() +
                ", time=" + time +
                '}';
    }
}
