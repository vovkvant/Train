package com.example.app.repository;
import com.example.app.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrainRepository extends JpaRepository<Train, Integer> {
    Optional<Train> findTrainById(int id);
    List<Train> findTrainByNumber(String number);
}