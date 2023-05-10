package com.example.app.repository;

import com.example.app.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {

}