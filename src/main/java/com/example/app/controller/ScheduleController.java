package com.example.app.controller;

import com.example.app.entity.Passenger;
import com.example.app.entity.Schedule;
import com.example.app.repository.ScheduleRepository;
import com.example.app.service.ScheduleService;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScheduleController {

    private final ScheduleRepository repository;
    private ScheduleService scheduleService;

    public ScheduleController(ScheduleRepository repository, ScheduleService scheduleService) {
        this.repository = repository;
        this.scheduleService = scheduleService;
    }

    @GetMapping("/schedule")
    List<Object> getSchedule() {
        return scheduleService.getSchedule();
    }

    @GetMapping("/schedule/station/{station_id}")
    List<Object> getScheduleByStation(Integer station_id) {

        return scheduleService.getScheduleByStation(station_id);
    }
}
