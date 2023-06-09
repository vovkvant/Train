package com.example.app.repository;

import com.example.app.entity.Station;
import com.example.app.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StationRepository  extends JpaRepository<Station, Integer> {
    Optional<Station> findStationById(int id);
}
