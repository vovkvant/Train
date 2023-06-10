package com.example.app.controller;

import com.example.app.dto.ScheduleDto;
import com.example.app.entity.*;
import com.example.app.error.exception.BusinessException;
import com.example.app.repository.ScheduleRepository;
import com.example.app.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/schedule/all/native")
    List<ScheduleDB> getScheduleNative() {
        return scheduleService.getScheduleNative();
    }
    @GetMapping("/schedule/all/jpql")
    List<ScheduleDB> getScheduleJpql() {
        return scheduleService.getScheduleJpql();
    }
    @GetMapping("/schedule/all/jpa")
    List<Schedule> getScheduleJpa() {
        return scheduleService.getScheduleJpa();
    }
    @GetMapping("/schedule/station/{station_id}")
    Iterable<Schedule> getScheduleByStation(@PathVariable Integer station_id) {
        return scheduleService.getScheduleByStation(station_id);
    }
    @GetMapping("/schedule/findRoute")
    List<Train> getTrainFromToStation(@RequestParam CharSequence fromTime,
                                      @RequestParam CharSequence toTime,
                                      @RequestParam Integer fromStation_id,
                                      @RequestParam Integer toStation_id) {
        // http://localhost:8080/schedule/findRoute?fromTime=2023-06-10@05:00:00&toTime=2023-06-11@00:00:00&fromStation_id=1&toStation_id=2
        return scheduleService.getTrainFromToStation(fromTime, toTime, fromStation_id, toStation_id);
    }

    @GetMapping("/schedule/minutesleft")
    Boolean checkMinutesLeftBeforeTrainTime(@RequestParam Integer train_id,
                                            @RequestParam Integer station_id,
                                            @RequestParam Integer minutes) {
        // http://localhost:8080/schedule/minutesleft?train_id=1&station_id=1&minutes=10
        return scheduleService.checkMinutesLeftBeforeTrainTime(minutes, train_id, station_id);
    }

    @PostMapping(path="/schedule")
    public @ResponseBody Schedule addNewScheduleItem(@RequestBody ScheduleDto scheduleDto)  {
        return scheduleService.addNewScheduleItem(scheduleDto);
    }

    @DeleteMapping("/schedule/{id}")
    void deleteScheduleItem(@PathVariable Integer id) {
        scheduleService.deleteScheduleItem(id);
    }
}
