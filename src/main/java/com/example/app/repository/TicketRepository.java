package com.example.app.repository;

import com.example.app.entity.Passenger;
import com.example.app.entity.Ticket;
import com.example.app.entity.TicketDB;
import com.example.app.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("Select tr.number as train_name,\n" +
            "p.name as passenger_name,\n" +
            "p.surname as passenger_surname\n" +
            "from Ticket as t join Train as tr\n" +
            "on  t.trainId = tr.id\n" +
            "join Passenger as p on p.id = t.passengerId\n" +
            "\n")
    Iterable<Ticket> getAllRegisteredPassengers();

    // Iterable<Ticket> buyTicket();
    //
    @Query("SELECT t.id as id, t.train_id as train_id, " +
            "t.start_station_id as start_station_id, " +
            "t.end_station_id as end_station_id, " +
            "t.places_number as places_number FROM ticketdb as t\n" +
            "WHERE t.train_id = :train_id\n" +
            "AND t.start_station_id = :station_id\n" +
            "AND t.time = :time")
    Boolean checkPlacesLeft(@Param("station_id") Integer station_id,
                                       @Param("train_id") Integer train_id,
                                       @Param("time") CharSequence time);


    @Query("UPDATE ticketdb as t\n" +
            "SET t.places_number = t.places_number - 1.0\n" +
            "WHERE t.train_id = :train_id\n" +
            "AND t.start_station_id = :station_id\n" +
            "AND t.time = :time")
    void updatePlacesLeft(@Param("station_id") Integer station_id,
                          @Param("train_id") Integer train_id,
                          @Param("time") CharSequence time);
}