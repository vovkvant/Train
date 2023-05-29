package com.example.app.controller;

import com.example.app.entity.Passenger;
import com.example.app.entity.Ticket;
import com.example.app.entity.TicketDB;
import com.example.app.repository.TicketRepository;
import com.example.app.service.TicketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketController {

    private final TicketRepository repository;
    private TicketService ticketService;

    public TicketController(TicketRepository repository, TicketService ticketService) {
        this.repository = repository;
        this.ticketService = ticketService;
    }
    @GetMapping("/ticket/all")
    Iterable<Ticket> getAllRegisteredPassengers() {
       return ticketService.getAllRegisteredPassengers();
    }

    @GetMapping("/ticket/buy")
    void buyTicket(@PathVariable Integer station_id,
                                 @PathVariable Integer train_id,
                                 @PathVariable CharSequence time) {
        ticketService.buyTicket(station_id, train_id, time);
    }
}
