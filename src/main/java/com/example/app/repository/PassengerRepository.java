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
   List<Passenger> findPassengerByNameAndSurnameAndBirthDate(String name, String surname, LocalDateTime birth_date);

}
