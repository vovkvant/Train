package com.example.app.repository;

import com.example.app.entity.Station;
import com.example.app.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository  extends JpaRepository<Station, Integer> {
    Station findStationById(int id);
}
