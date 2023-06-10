package com.example.app.service;

import com.example.app.entity.Station;
import com.example.app.entity.Train;
import com.example.app.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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
    public Optional<Station> findStationById(int id) {
        return repository.findStationById(id);
    }
    public Station addNewStation(Station station) {

        List<Station> stations = repository.findStationByName(station.getName());
        if (stations.size() == 0) {
            return repository.save(station);
        } else {
            // исключение
            return stations.get(1);
        }
    }
    public void deleteStation(int id) {
        repository.deleteById(id);
    }

}
