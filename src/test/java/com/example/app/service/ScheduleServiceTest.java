package com.example.app.service;

import com.example.app.repository.PassengerRepository;
import com.example.app.repository.ScheduleRepository;
import com.example.app.repository.TrainRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {

    @Mock
    ScheduleRepository repo;
    ScheduleService scheduleService;
    ScheduleDBService scheduleDBService;

    StationService stationService;

    TrainService trainService;
    @Mock TrainRepository trainRepository;


    @BeforeEach
    void setUp() {
        this.trainService = new TrainService(this.trainRepository);
        this.scheduleService = new ScheduleService(this.repo,
                this.scheduleDBService, this.stationService, this.trainService);
    }

//    @Test
//    void checkMinutesLeftBeforeTrainTime() {
//        when(scheduleService.checkMinutesLeftBeforeTrainTime(10, 1, 1))
//                .thenReturn(false);
//    }
}