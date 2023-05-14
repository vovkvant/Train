package com.example.app.repository;
import com.example.app.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Integer> {
    Train findTrainById(int id);
}