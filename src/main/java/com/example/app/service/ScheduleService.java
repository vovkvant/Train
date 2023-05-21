package com.example.app.service;

import com.example.app.entity.Passenger;
import com.example.app.entity.Schedule;
import com.example.app.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository repository;
    private Schedule schedule;
    public ScheduleService() {}

    public List<Object> getSchedule() {
        return repository.getSchedule();
    }

    public List<Object> getScheduleByStation(Integer station_id) {

        return repository.getScheduleByStation(station_id);
    }

}
