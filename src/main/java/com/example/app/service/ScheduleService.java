package com.example.app.service;

import com.example.app.entity.Passenger;
import com.example.app.entity.Schedule;
import com.example.app.entity.ScheduleDB;
import com.example.app.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository repository;
    Schedule schedule;
    public ScheduleService() {}

    public List<ScheduleDB> getSchedule() {
        return repository.getSchedule();
    }

    public Iterable<Schedule> getScheduleByStation(Integer station_id) {

        return repository.getScheduleByStation(station_id);
    }

    public Iterable<ScheduleDB> getTrainFromToStation(CharSequence fromTime, CharSequence toTime,
                                                  Integer fromStation_id, Integer toStation_id) {

        LocalTime localFromTime = LocalTime.parse(fromTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalTime localToTime = LocalTime.parse(toTime, DateTimeFormatter.ofPattern("HH:mm:ss"));

        Iterable<ScheduleDB> trainsFromStation = repository.getTrainsFromStation(localFromTime, localToTime, fromStation_id);

        // Для второго запроса сделала бы обход типа такого (или в идеале передать в запрос2 getTrainToStation
        // внешнюю таблицу из запроса1 getTrainsFromStation, но не поняла как
//        for (ScheduleDB scheduleStr : trainsFromStation) {
//            Iterable<ScheduleDB> resultTrain = repository.getTrainToStation(toStation_id, scheduleStr.getrain_name)
//        }
        return trainsFromStation;
    }
    public Schedule addNewScheduleItem(Schedule schedule) {

        Schedule sc = repository.save(schedule);
        return sc;
    }

    public void deleteScheduleItem(int id)  {
        repository.deleteById(id);
    }

}
