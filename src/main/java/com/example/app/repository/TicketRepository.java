package com.example.app.repository;

import com.example.app.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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

}