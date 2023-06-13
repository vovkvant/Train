package com.example.app.service;

import com.example.app.dto.ScheduleDto;
import com.example.app.entity.*;
import com.example.app.error.ExceptionMessage;
import com.example.app.error.exception.BusinessException;
import com.example.app.repository.ScheduleDBRepository;
import com.example.app.repository.ScheduleRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Optional;


@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ScheduleDBService scheduleDBService;

    @Autowired
    private StationService stationService;

    @Autowired
    private TrainService trainService;

    Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    public ScheduleService() {}

    public ScheduleService(ScheduleRepository scheduleRepository,
                           ScheduleDBService scheduleDBService,
                           StationService stationService,
                           TrainService trainService) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleDBService = scheduleDBService;
        this.stationService = stationService;
        this.trainService = trainService;
    }

    public List<ScheduleDB> getScheduleNative() {
        return scheduleDBService.getScheduleJpql();
    }
    public List<ScheduleDB> getScheduleJpql() {
        return scheduleDBService.getScheduleJpql();
    }
    public List<Schedule> getScheduleJpa() {
        return scheduleRepository.findAll();
    }
    public Optional<Schedule> getScheduleById(Integer id) { return scheduleRepository.findById(id);}
    public Optional<Schedule> findById(Integer scheduleId) {
        return scheduleRepository.findById(scheduleId);
    }
    public List<Schedule> getScheduleByStation(Integer station_id) {

        Station station = stationService.findStationById(station_id)
                .orElseThrow(() -> new BusinessException(ExceptionMessage.OBJECT_NOT_FOUND));

        List<Schedule> schedules = scheduleRepository.findScheduleByStation(station);
        if (schedules.size() == 0) {
            logger.info("Schedule by station " + station.toString() + " is not founud ");
            throw(new BusinessException(ExceptionMessage.OBJECT_NOT_FOUND));
        }
        return schedules;
    }

    public List <Train> getTrainFromToStation(CharSequence fromTime, CharSequence toTime,
                                                  Integer fromStation_id, Integer toStation_id) {

        LocalDateTime localFromTime = LocalDateTime.parse(fromTime, DateTimeFormatter.ofPattern("yyyy-MM-dd@HH:mm:ss"));
        LocalDateTime localToTime = LocalDateTime.parse(toTime, DateTimeFormatter.ofPattern("yyyy-MM-dd@HH:mm:ss"));

        List<Train> trainsFromStation = scheduleDBService.getTrainFromStation(fromStation_id, localFromTime, localToTime);

        List<Train> trainsToStation = scheduleDBService.getTrainToStation(toStation_id, localFromTime);

        trainsFromStation.retainAll(trainsToStation);

        return trainsToStation;

    }

    public Boolean checkMinutesLeftBeforeTrainTime(Integer minutes,
                                                   Integer train_id,
                                                   Integer station_id) {
        Optional<Train> train = Optional.ofNullable(trainService.findTrainById(train_id)
                .orElseThrow(() -> new BusinessException(ExceptionMessage.OBJECT_NOT_FOUND)));
        Optional<Station> station = Optional.ofNullable(stationService.findStationById(station_id)
                .orElseThrow(() -> new BusinessException(ExceptionMessage.OBJECT_NOT_FOUND)));

        boolean response = false;

        List<Schedule> schedule = scheduleRepository.findScheduleByTrainAndStation(train, station);
        for (Schedule elem: schedule) {
            if (elem.getArrivalTime().isAfter(LocalDateTime.now().plusMinutes(minutes))) {
                response = true;
            }
        }
        return response;
    }

    public Schedule addNewScheduleItem(ScheduleDto scheduleDto)  {

        Station station = getStationByDto(scheduleDto);
        Train train = getTrainByDto(scheduleDto);
        List<Schedule> scheduleList = getScheduleListByDto(scheduleDto);

        if (scheduleList.size() == 0) {

            Schedule schedule = Schedule.builder()
                    .train(train)
                    .station(station)
                    .departureTime(scheduleDto.getDepartureTime())
                    .arrivalTime(scheduleDto.getArrivalTime())
                    .places_left(train.getPlacesNumber())
                    .build();

            logger.info("Schedule to save: " + schedule.toString());

            return scheduleRepository.save(schedule);
        } else {
            logger.info("Schedule item: " + scheduleDto.toString() + " already exists");
            throw (new BusinessException(ExceptionMessage.OBJECT_ALREADY_EXISTS));
        }
    }

    public void deleteScheduleItem(Integer id)  {

        getScheduleById(id).orElseThrow(() -> new BusinessException(ExceptionMessage.OBJECT_ALREADY_DELETED));
        scheduleRepository.deleteById(id);
    }

    public List<Schedule> getScheduleListByDto(ScheduleDto scheduleDto) {

       Station station = getStationByDto(scheduleDto);
       Train train = getTrainByDto(scheduleDto);
       List<Schedule> scheduleList = scheduleRepository.findScheduleByStationAndTrainAndDepartureTime(station, train, scheduleDto.getDepartureTime());

       return scheduleList;
    }

    public Station getStationByDto(ScheduleDto scheduleDto) {
        Station station = stationService.findStationById(scheduleDto.getStationId())
                .orElseThrow(() -> new BusinessException(ExceptionMessage.OBJECT_NOT_FOUND));
        return station;
    }

    public Train getTrainByDto(ScheduleDto scheduleDto) {
        Train train = trainService.findTrainById(scheduleDto.getTrainId())
                .orElseThrow(() -> new BusinessException(ExceptionMessage.OBJECT_NOT_FOUND));
        return train;
    }

}
