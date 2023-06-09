package com.example.app.dto;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.DataAmount;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
public class ScheduleDto {

    @NotNull
    private Integer stationId;

    @NotNull
    private Integer trainId;

    @NotNull
    private LocalTime time;

}
