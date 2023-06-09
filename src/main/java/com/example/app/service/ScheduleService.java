package com.example.app.service;

import ch.qos.logback.core.spi.ErrorCodes;
import com.example.app.dto.ScheduleDto;
import com.example.app.entity.*;
import com.example.app.error.ErrorCode;
import com.example.app.error.ExceptionMessage;
import com.example.app.error.exception.BusinessException;
import com.example.app.repository.ScheduleDBRepository;
import com.example.app.repository.ScheduleRepository;
import com.example.app.repository.StationRepository;
import com.example.app.repository.TrainRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ScheduleDBRepository scheduleDBRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private TrainRepository trainRepository;

    Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    public ScheduleService() {}
    public List<ScheduleDB> getScheduleNative() {
        return scheduleDBRepository.findAllNative();
    }
    public List<ScheduleDB> getScheduleJpql() {
        return scheduleDBRepository.findAllJpql();
    }
    public List<Schedule> getScheduleJpa() {
        return scheduleRepository.findAll();
    }
    public Optional<Schedule> getScheduleById(Integer id) { return scheduleRepository.findById(id);}

    public List<Schedule> getScheduleByStation(Integer station_id) {

        Station station = stationRepository.findStationById(station_id)
                .orElseThrow(() -> new BusinessException(ExceptionMessage.OBJECT_NOT_FOUND, ErrorCode.OBJECT_NOT_FOUND));

        List<Schedule> schedules = scheduleRepository.findScheduleByStation(station);
        if (schedules.size() == 0) {
            logger.info("Schedule by station " + station.toString() + " is not founud ");
            throw(new BusinessException(ExceptionMessage.OBJECT_NOT_FOUND, ErrorCode.OBJECT_NOT_FOUND));
        }
        return schedules;
    }

    public List <ScheduleDB> getTrainFromToStation(CharSequence fromTime, CharSequence toTime,
                                                  Integer fromStation_id, Integer toStation_id) {

        LocalTime localFromTime = LocalTime.parse(fromTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalTime localToTime = LocalTime.parse(toTime, DateTimeFormatter.ofPattern("HH:mm:ss"));

        List<ScheduleDB> trainsFromStation = scheduleDBRepository.getTrainToStation(fromStation_id, localFromTime);

        List<ScheduleDB> trainsToStation = new ArrayList<>();

        // поиск поезда до конечной станции после времени первого поезда, пытаемся найти тот же поезд
        for (ScheduleDB scheduleStr : trainsFromStation) {
            LocalTime localTrainTime = scheduleStr.getTime();
            List<ScheduleDB> trains = scheduleDBRepository.findTrainToStation(toStation_id, localTrainTime, scheduleStr.getTrain_name());
            trainsToStation.addAll(trains);
        }

        // это по идее пересадка
        if (trainsToStation.size() == 0) {
            for (ScheduleDB scheduleStr : trainsFromStation) {
                LocalTime localTrainTime = scheduleStr.getTime();
                List<ScheduleDB> trains = scheduleDBRepository.getTrainToStation(toStation_id, localTrainTime);
                trainsToStation.addAll(trains);
            }
        }

        trainsToStation.addAll(trainsFromStation);

        return trainsToStation;

    }


//    public Boolean checkMinutesLeftBeforeTrainTime(Integer minutes,
//                                                   Integer train_id,
//                                                   Integer station_id) {
//        Iterable<ScheduleDB> scheduleStr = repository.getTrainTime(train_id, station_id);
////        return scheduleStr.time.minusMinutes(minutes) >= LocalDateTime.now();
//        return false;
//    }

    public Schedule addNewScheduleItem(ScheduleDto scheduleDto)  {

        Station station = getStationByDto(scheduleDto);
        Train train = getTrainByDto(scheduleDto);
        List<Schedule> scheduleList = getScheduleListByDto(scheduleDto);

        if (scheduleList.size() == 0) {

            Schedule schedule = Schedule.builder()
                    .train(train)
                    .station(station)
                    .time(scheduleDto.getTime())
                    .build();

            logger.info("Schedule to save: " + schedule.toString());

            return scheduleRepository.save(schedule);
        } else {

            logger.info("Schedule item: " + scheduleDto.toString() + " already exists");
            throw (new BusinessException(ExceptionMessage.OBJECT_ALREADY_EXISTS, ErrorCode.OBJECT_ALREADY_EXISTS));
        }

    }

    public void deleteScheduleItem(Integer id)  {

        Optional<Schedule> scheduleToDelete = getScheduleById(id);

        if (scheduleToDelete.isEmpty()) {
            logger.info("Schedule by id " + id + " is not found");
            throw (new BusinessException(ExceptionMessage.OBJECT_NOT_FOUND, ErrorCode.OBJECT_NOT_FOUND));
        } else {
            logger.info("Schedule " + scheduleToDelete.toString() + " will be deleted");
            scheduleRepository.deleteById(id);
        }

    }

    public List<Schedule> getScheduleListByDto(ScheduleDto scheduleDto) {

       Station station = getStationByDto(scheduleDto);
       Train train = getTrainByDto(scheduleDto);
       List<Schedule> scheduleList = scheduleRepository.findScheduleByStationAndTrainAndTime(station, train, scheduleDto.getTime());

        return scheduleList;
    }

    public Station getStationByDto(ScheduleDto scheduleDto) {
        Station station = stationRepository.findStationById(scheduleDto.getStationId())
                .orElseThrow(() -> new BusinessException(ExceptionMessage.OBJECT_NOT_FOUND, ErrorCode.OBJECT_NOT_FOUND));
        return station;
    }

    public Train getTrainByDto(ScheduleDto scheduleDto) {
        Train train = trainRepository.findTrainById(scheduleDto.getTrainId())
                .orElseThrow(() -> new BusinessException(ExceptionMessage.OBJECT_NOT_FOUND, ErrorCode.OBJECT_NOT_FOUND));
        return train;
    }

}
