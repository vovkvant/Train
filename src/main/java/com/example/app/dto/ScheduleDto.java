package com.example.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.DataAmount;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@ToString
public class ScheduleDto {

    @NotNull
    private Integer stationId;

    @NotNull
    private Integer trainId;

    @NotNull
    @JsonDeserialize(as = LocalDateTime.class)
    private LocalDateTime arrival_time;

    @NotNull
    @JsonDeserialize(as = LocalDateTime.class)
    private LocalDateTime departure_time;

    public Integer getStationId() {
        return stationId;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public LocalDateTime getArrivalTime() {
        return arrival_time;
    }

    public LocalDateTime getDepartureTime() {
        return departure_time;
    }
}
