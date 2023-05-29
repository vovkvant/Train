package com.example.app.service;

import com.example.app.controller.TicketController;
import com.example.app.entity.Passenger;
import com.example.app.entity.Schedule;
import com.example.app.entity.Ticket;
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
}
