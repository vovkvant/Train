package com.example.app.service;

import com.example.app.controller.TicketController;
import com.example.app.entity.Passenger;
import com.example.app.entity.Schedule;
import com.example.app.entity.Ticket;
import com.example.app.entity.TicketDB;
import com.example.app.repository.ScheduleRepository;
import com.example.app.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TicketService {

    @Autowired
    private TicketRepository repository;
    Ticket ticket;
    public TicketService() {};

    public Iterable<Ticket> getAllRegisteredPassengers() {

        return  repository.getAllRegisteredPassengers();
    }
    public void buyTicket(Integer station_id, Integer train_id, CharSequence time) {
      if (checkPlacesLeft(station_id, train_id, time)) {
         updatePlacesLeft(station_id, train_id, time);
      }
    }

    public Boolean checkPlacesLeft(Integer station_id, Integer train_id, CharSequence time) {
        // нужно в какой-то момент еще добавить в ticketdb общее количество мест
        // из полученной далее строки выбрать оставшееся количество мест
        // repository.checkPlacesLeft(station_id, train_id, time);
        // если количество результат = количество мест >= 1

        // исключение, если такой строки не найдено?
        return true;
    }

    public void updatePlacesLeft(Integer station_id, Integer train_id, CharSequence time) {
        repository.updatePlacesLeft(station_id, train_id, time);
    }
}
