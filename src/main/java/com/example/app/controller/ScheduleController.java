package com.example.app.controller;

import com.example.app.entity.Passenger;
import com.example.app.entity.Schedule;
import com.example.app.entity.ScheduleDB;
import com.example.app.entity.Station;
import com.example.app.repository.ScheduleRepository;
import com.example.app.service.ScheduleService;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class ScheduleController {

    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/schedule/all")
    List<ScheduleDB> getSchedule() {
        return scheduleService.getSchedule();
    }

    @GetMapping("/schedule/station/{station_id}")
    Iterable<Schedule> getScheduleByStation(@PathVariable Integer station_id) {
        return scheduleService.getScheduleByStation(station_id);
    }

    @GetMapping("/schedule/{fromTime}/{toTime}/{fromStation_id}/{toStation_id}")
    Iterable<ScheduleDB> getTrainFromToStation(@PathVariable CharSequence fromTime,
                                           @PathVariable CharSequence toTime,
                                           @PathVariable Integer fromStation_id,
                                           @PathVariable Integer toStation_id) {
        return scheduleService.getTrainFromToStation(fromTime, toTime, fromStation_id, toStation_id);
    }
    @PostMapping(path="/schedule")
    public @ResponseBody Schedule addNewScheduleItem(@RequestBody Schedule schedule) {
        return scheduleService.addNewScheduleItem(schedule);
    }

    @DeleteMapping("/schedule/{id}")
    void deleteScheduleItem(@PathVariable int id) {
        scheduleService.deleteScheduleItem(id);
    }
}
