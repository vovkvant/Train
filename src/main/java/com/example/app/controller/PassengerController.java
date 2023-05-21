package com.example.app.controller;

import com.example.app.entity.Passenger;
import com.example.app.entity.Train;
import com.example.app.repository.PassengerRepository;
import com.example.app.service.PassengerService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class PassengerController {

    private final PassengerRepository repository;
    private PassengerService passengerService;

    public PassengerController(PassengerRepository repository, PassengerService passengerService) {
        this.repository = repository;
        this.passengerService = passengerService;
    }
    @GetMapping("/passengers")
    List<Passenger> all() {
        return passengerService.findAllPassengers();
    }

    @GetMapping("/passengers/{id}")
    public Passenger findPassengerById(@PathVariable int id) {
        return passengerService.findPassengerById(id);
    }

    @GetMapping("/passengers/birthDate/{birthDate}")
    public Passenger findPassengerByBirthDate(@PathVariable String birthDate) {
//        return passengerService.findPassengerByBirthDate(birthDate);
        return passengerService.findPassengerByBirthDateQuery(birthDate);
    }

    @GetMapping("/passengers/{name}/{surname}")
    public List<Object> findPassengersByNameAfterAndSurname(@PathVariable String name, @PathVariable String surname) {
        return passengerService.findPassengersByNameAfterAndSurname(name, surname);
    }

    @PostMapping(path="/passenger") // Map ONLY POST Requests
    public @ResponseBody Passenger addNewTrain(@RequestBody Passenger passenger) {
        return passengerService.addNewPassenger(passenger);
    }

    @DeleteMapping("/passengers/{id}")
    void deletePassenger(@PathVariable int id) {
        passengerService.deletePassenger(id);
    }

}
