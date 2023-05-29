package com.example.app.service;

import com.example.app.entity.Passenger;
import com.example.app.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class PassengerService {

    @Autowired
    private PassengerRepository repository;
    Passenger passenger;
    public PassengerService() {}
    public List<Passenger> findAllPassengers() { return repository.findAll(); }
    public Passenger findPassengerById(int id) {
        return repository.findPassengerById(id);
    }
    public Passenger addNewPassenger(Passenger passenger) {
        Passenger p = repository.save(passenger);
        return p;
    }
    public Passenger findPassengerByBirthDate(LocalDateTime birthDate) {
        return repository.findPassengerByBirthDate(birthDate);
    }
    public Iterable<Passenger> findPassengerByBirthDateQuery(CharSequence birthDate) {
        LocalDate parsedBD = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDateTime birthDateTime = parsedBD.atStartOfDay();
        return  repository.findPassengerByBirthDateQuery(birthDateTime);
    }
    public Iterable<Passenger> findPassengersByNameAndSurname(String name, String surname) {
        return repository.findPassengerByNameAndSurname(name, surname);
    }
    public void deletePassenger(int id) {
        repository.deleteById(id);
    }
}
