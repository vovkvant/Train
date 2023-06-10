package com.example.app.service;

import com.example.app.dto.PassengerDto;
import com.example.app.entity.*;
import com.example.app.repository.PassengerRepository;
import com.example.app.repository.ScheduleDBRepository;
import com.example.app.repository.ScheduleRepository;
import com.example.app.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TicketService {

    @Autowired
    private TicketRepository repository;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ScheduleDBRepository scheduleDBRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private PassengerService passengerService;
    Ticket ticket;
    public TicketService() {};

    public Iterable<Ticket> getAllRegisteredPassengers() {

        return  repository.getAllRegisteredPassengers();
    }
    public void buyTicket(PassengerDto passengerDto, Integer scheduleId,
                          Integer end_station_id, CharSequence arrivalTime) {

        Optional<Schedule> schedule = scheduleRepository.findById(scheduleId);

        Schedule scheduleItem = schedule.get();

        // exception if not found
        Integer places_left = scheduleItem.getPlaces_left();
        Passenger passenger;
        if (places_left == 0) {
            // exception
        }
        Integer train_id = scheduleItem.getTrain().getId();
        Integer station_id = scheduleItem.getStation().getId();
        List <Passenger> passengers = passengerRepository.findPassengerByNameAndSurnameAndBirthDate(
                passengerDto.getName(),
                passengerDto.getSurname(),
                passengerDto.getBirthDate());

        // exception if passengers.size() > 0

        if (!scheduleService.checkMinutesLeftBeforeTrainTime(10, train_id, station_id)) {
            // exception
        }

        if (passengers.size() == 0) {
            passenger = passengerService.addNewPassenger(passengerDto);
        } else {
            passenger = passengers.get(1);
        }

        LocalDateTime arrivalTimeDB = LocalDateTime.parse(arrivalTime, DateTimeFormatter.ofPattern("yyyy-MM-dd@HH:mm:ss"));

        Ticket ticket = Ticket.builder()
                .trainId(scheduleItem.getTrain().getId())
                .passengerId(passenger.getId())
                .startStationId(scheduleItem.getStation().getId())
                .endStationId(end_station_id)
                .departureTime(scheduleItem.getDepartureTime())
                .arrivalTime(arrivalTimeDB)
                .build();

        repository.save(ticket);

        scheduleItem.setPlaces_left(places_left - 1);

    }

}
