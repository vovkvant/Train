package com.example.app.controller;

import com.example.app.repository.TicketRepository;
import com.example.app.service.TicketService;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/registered")
    List<Object> getAllRegisteredPassengers() {
       return ticketService.getAllRegisteredPassengers();
    }
}
