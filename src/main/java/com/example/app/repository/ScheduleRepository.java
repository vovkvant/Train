package com.example.app.repository;

import com.example.app.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    Schedule findScheduleById(int id);

    @Query("Select s.name as station_name, " +
           "t.number as train_name, sc.time, s.id " +
           "from  Schedule as sc join Station as s " +
          " on  sc.stationId = s.id  " +
          "join Train as t on t.id = sc.trainId ")
    List<Object> getSchedule();

    @Query("Select s.name as station_name,\n" +
            "t.number as train_name, sc.time\n" +
            "from  Schedule as sc join Station as s\n" +
            "on  sc.stationId = s.id\n" +
            "join Train as t on t.id = sc.trainId\n" +
            "WHERE sc.stationId = :id")
    List<Object> getScheduleByStation(@Param("id") Integer station_id);

}
