package com.example.app.repository;

import com.example.app.entity.Schedule;
import com.example.app.entity.ScheduleDB;
import com.example.app.entity.Station;
import com.example.app.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    List<Schedule> findScheduleByStationAndTrainAndDepartureTime(Station station, Train train, LocalDateTime departureTime);

    List<Schedule> findScheduleByStation(Station station);

    List<Schedule> findScheduleByTrainAndStation(Optional<Train> train, Optional<Station> station);

    Optional<Schedule> findScheduleById(Integer id);


}
