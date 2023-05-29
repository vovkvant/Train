package com.example.app.repository;

import com.example.app.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
   Passenger findPassengerById(int id);
   Passenger findPassengerByBirthDate(LocalDateTime birthDate);
   @Query("SELECT p.name, p.surname, p.id, p.birthDate FROM Passenger AS p " +
   "WHERE p.name = :name AND p.surname = :surname")
   Iterable<Passenger> findPassengerByNameAndSurname(@Param("name") String name,
                                              @Param("surname") String surname);

   @Query("SELECT p.name, p.surname, p.id FROM Passenger AS p " +
           "WHERE p.birthDate=?1")
   Iterable<Passenger> findPassengerByBirthDateQuery(LocalDateTime birthDate);
}
