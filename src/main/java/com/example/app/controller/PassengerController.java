package com.example.app.controller;

import com.example.app.dto.PassengerDto;
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

    @GetMapping("/passenger/find")
    public List<Passenger> findPassengerByNameAndSurnameAndBirthDate(@RequestParam String name,
                                                                     @RequestParam String surname,
                                                                     @RequestParam CharSequence birthDate) {
        return passengerService.findPassengerByNameAndSurnameAndBirthDate(name, surname, birthDate);
    }

    @PostMapping(path="/passenger")
    public @ResponseBody Passenger addNewPassenger(@RequestBody PassengerDto passengerDto) {
        return passengerService.addNewPassenger(passengerDto);
    }

    @DeleteMapping("/passenger/{id}")
    void deletePassenger(@PathVariable int id) {
        passengerService.deletePassenger(id);
    }

}
