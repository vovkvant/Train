package com.example.app.controller;

import com.example.app.entity.Station;
import com.example.app.repository.StationRepository;
import com.example.app.service.StationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class StationController {
    private final StationRepository repository;
    private final StationService stationService;

    StationController(StationRepository repository, StationService stationService) {
        this.repository = repository;
        this.stationService = stationService;
    }

    @GetMapping("/station/all")
    List<Station> all() {
        return stationService.findAllStations();
    }

    @GetMapping("/station/{id}")
    public Station findStationById(@PathVariable int id) {
        return stationService.findStationById(id);
    }

    @PostMapping(path="/station")
    public @ResponseBody Station addNewStation(@RequestBody Station station) {
        return stationService.addNewStation(station);
    }

    @DeleteMapping("/station/{id}")
    void deleteStation(@PathVariable int id) {
        stationService.deleteStation(id);
    }
}
