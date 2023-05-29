package com.example.app.repository;

import com.example.app.entity.Schedule;
import com.example.app.entity.ScheduleDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    Schedule findScheduleById(int id);

    @Query("Select s.name as station_name, " +
           "t.number as train_name, sc.time as time, s.id as id " +
           "from  Schedule as sc join Station as s " +
          " on  sc.stationId = s.id  " +
          "join Train as t on t.id = sc.trainId ")
    List<ScheduleDB> getSchedule();

    @Query("Select s.name as station_name,\n" +
            "t.number as train_name, sc.time\n" +
            "from  Schedule as sc join Station as s\n" +
            "on  sc.stationId = s.id\n" +
            "join Train as t on t.id = sc.trainId\n" +
            "WHERE sc.stationId = :id")
    Iterable<Schedule> getScheduleByStation(@Param("id") Integer station_id);

    @Query("Select s.name as station_name,\n" +
            "       t.number as train_name, sc.time\n" +
            "        from  Schedule as sc join Station as s\n" +
            "        on  sc.stationId = s.id\n" +
            "        join Train as t on t.id = sc.trainId\n" +
            "        WHERE sc.stationId = :fromStation_id\n" +
            "        AND sc.time >= :fromTime\n" +
            "        AND sc.time <= :toTime")
    List <ScheduleDB> getTrainsFromStation(@Param("fromTime") LocalTime fromTime,
                          @Param("toTime") LocalTime toTime,
                          @Param("fromStation_id") Integer fromStation_id);

    @Query("Select s.name as station_name,\n" +
            "       t.number as train_name, sc.time\n" +
            "from  Schedule as sc join Station as s\n" +
            "    on  sc.stationId = s.id\n" +
            "    join Train as t on t.id = sc.trainId\n" +
            "WHERE sc.stationId = :toStation_id\n" +
            "  AND sc.time > :trainTime")
    Iterable<ScheduleDB> getTrainToStation(@Param("toStation_id") Integer toStation_id,
                                           @Param("trainTime") LocalTime fromTime);
}
