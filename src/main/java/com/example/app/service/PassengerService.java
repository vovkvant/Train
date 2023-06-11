package com.example.app.service;

import com.example.app.dto.PassengerDto;
import com.example.app.entity.Passenger;
import com.example.app.entity.Schedule;
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
    public PassengerService() {}
    public List<Passenger> findAllPassengers() { return repository.findAll(); }
    public Passenger findPassengerById(int id) {
        return repository.findPassengerById(id);
    }
    public Passenger addNewPassenger(PassengerDto passengerDto) {

        String name = passengerDto.getName();
        String surname = passengerDto.getSurname();
        LocalDateTime birthDate = passengerDto.getBirthDate();

        List<Passenger> passengersDB = repository.findPassengerByNameAndSurnameAndBirthDate(name, surname, birthDate);

        if (passengersDB.size() == 0) {

            Passenger passenger = Passenger.builder()
                    .name(name)
                    .surname(surname)
                    .birthDate(birthDate)
                    .build();
            return repository.save(passenger);
        } else {
            // добавить исключение
            return passengersDB.get(1);
        }
    }
    public List<Passenger> findPassengerByNameAndSurnameAndBirthDate(String name, String surname, CharSequence birthDate) {
        LocalDate parsedBD = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDateTime birthDateTime = parsedBD.atStartOfDay();
        return repository.findPassengerByNameAndSurnameAndBirthDate(name, surname, birthDateTime);
    }
    public List<Passenger> findPassengerByNameAndSurnameAndBirthDateAsDate(String name, String surname, LocalDateTime birthDate) {
        return repository.findPassengerByNameAndSurnameAndBirthDate(name, surname, birthDate);
    }
    public void deletePassenger(int id) {
        repository.deleteById(id);
    }
}
