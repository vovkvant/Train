package com.example.app.service;

import com.example.app.entity.Passenger;
import com.example.app.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PassengerService {

    @Autowired
    private PassengerRepository repository;
    private Passenger passenger;
    public PassengerService() {}
    public List<Passenger> findAllPassengers() { return repository.findAll(); }
    public Passenger findPassengerById(int id) {
        return repository.findPassengerById(id);
    }
    public Passenger addNewPassenger(Passenger passenger) {
        Passenger p = repository.save(passenger);
        return p;
    }
    public Passenger findPassengerByBirthDate(LocalDate birthDate) {
        return repository.findPassengerByBirthDate(birthDate);
    }
    public Passenger findPassengerByBirthDateQuery(String birthDate) {
      return  repository.findPassengerByBirthDateQuery(birthDate);
    }
    public List<Object> findPassengersByNameAfterAndSurname(String name, String surname) {
        return repository.findPassengerByNameAndSurname(name, surname);
    }
    public void deletePassenger(int id) {
        repository.deleteById(id);
    }
}
