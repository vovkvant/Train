package com.example.app.service;

import com.example.app.entity.Station;
import com.example.app.entity.Train;
import com.example.app.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StationService {

    @Autowired
    private StationRepository repository;
    Station station;
    public StationService() {}
    public List<Station> findAllStations() {
        return repository.findAll();
    }
    public Station findStationById(int id) {
        return repository.findStationById(id);
    }
    public Station addNewStation(Station station) {
        Station s = repository.save(station);
        return s;
    }
    public void deleteStation(int id) {
        repository.deleteById(id);
    }

}
