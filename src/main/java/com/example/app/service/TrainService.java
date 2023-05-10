package com.example.app.service;


//import com.example.app.entity.Train;
import com.example.app.repository.TrainRepository;
import com.example.app.service.StationService;
//import jakarta.persistence.*;
//import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class TrainService {
    private StationService stationService;

    @Autowired
    private TrainRepository trainRepository;

}
