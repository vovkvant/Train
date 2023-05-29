package com.example.app.controller;

import com.example.app.entity.Passenger;
import com.example.app.entity.Train;
import com.example.app.repository.PassengerRepository;
import com.example.app.service.PassengerService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class PassengerController {

    private final PassengerRepository repository;
    private PassengerService passengerService;

    public PassengerController(PassengerRepository repository, PassengerService passengerService) {
        this.repository = repository;
        this.passengerService = passengerService;
    }

    @GetMapping("/passenger/all")
    List<Passenger> all() {
        return passengerService.findAllPassengers();
    }

    @GetMapping("/passenger/{id}")
    public Passenger findPassengerById(@PathVariable int id) {
        return passengerService.findPassengerById(id);
    }

    @GetMapping("/passenger/?birthDate={birthDate}")
    public Iterable<Passenger> findPassengerByBirthDate(@PathVariable CharSequence birthDate) {
        return passengerService.findPassengerByBirthDateQuery(birthDate);
    }

    // как сейчас не работает
    // и так тоже
//    @GetMapping("/passenger/?name={name}&surname={surname}")
    @GetMapping("/passenger/?name=name&surname=surname")
    public Iterable<Passenger> findPassengersByNameAndSurname(@PathVariable String name, @PathVariable String surname) {
        return passengerService.findPassengersByNameAndSurname(name, surname);
    }

    @PostMapping(path="/passenger") // Map ONLY POST Requests
    public @ResponseBody Passenger addNewTrain(@RequestBody Passenger passenger) {
        return passengerService.addNewPassenger(passenger);
    }

    @DeleteMapping("/passenger/{id}")
    void deletePassenger(@PathVariable int id) {
        passengerService.deletePassenger(id);
    }

}
