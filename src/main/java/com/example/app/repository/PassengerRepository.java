package com.example.app.repository;

import com.example.app.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
   Passenger findPassengerById(int id);
   Passenger findPassengerByBirthDate(LocalDate birthDate);
   @Query("SELECT p.name, p.surname, p.id, p.birthDate FROM Passenger AS p " +
   "WHERE p.name = :name AND p.surname = :surname")
   List<Object> findPassengerByNameAndSurname(@Param("name") String name,
                                              @Param("surname") String surname);

   @Query("SELECT p.name, p.surname, p.id FROM Passenger AS p " +
           "WHERE p.birthDate=?1")
   Passenger findPassengerByBirthDateQuery(String birthDate);
}
