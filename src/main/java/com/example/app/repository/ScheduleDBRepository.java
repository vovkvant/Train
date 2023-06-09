package com.example.app.repository;

import com.example.app.entity.ScheduleDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface ScheduleDBRepository extends JpaRepository<ScheduleDB, Integer> {
    @Query(value = "Select new com.example.app.entity.ScheduleDB(sc.id, t.number, s.name, sc.time) " +
            "from  Schedule as sc " +
            "join Station as s on s.id = sc.id " +
            "join Train as t on t.id = sc.id")
    List<ScheduleDB> findAllJpql();

    @Query(value = "Select sc.id, t.number as train_name, s.name as station_name " +
            "from  schedule as sc " +
            "join station as s on s.id = sc.station_id " +
            "join train as t on t.id = sc.train_id", nativeQuery = true)
    List<ScheduleDB> findAllNative();

    @Query(value = "Select new com.example.app.entity.ScheduleDB(sc.id, t.number, s.name, sc.time)" +
            "from Schedule as sc join Station as s\n" +
            "on sc.id = s.id\n" +
            "join Train as t on t.id = sc.id\n" +
            "WHERE sc.id = :toStation_id\n" +
            "AND sc.time > :trainTime")
    List<ScheduleDB> getTrainToStation(@Param("toStation_id") Integer toStation_id,
                                           @Param("trainTime") LocalTime fromTime);

    @Query(value = "Select new com.example.app.entity.ScheduleDB(sc.id, t.number, s.name, sc.time)" +
            "from Schedule as sc join Station as s\n" +
            "on sc.id = s.id\n" +
            "join Train as t on t.id = sc.id\n" +
            "WHERE sc.id = :toStation_id\n" +
            "AND sc.time > :trainTime\n" +
            "AND t.number = :train_name")
    List<ScheduleDB> findTrainToStation(@Param("toStation_id") Integer toStation_id,
                                       @Param("trainTime") LocalTime fromTime,
                                        @Param("train_name") String train_name);


}
