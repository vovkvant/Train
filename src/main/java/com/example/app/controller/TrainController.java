package com.example.app.controller;
import java.util.List;
import com.example.app.entity.Train;
import com.example.app.repository.TrainRepository;
import com.example.app.service.TrainService;
import org.springframework.web.bind.annotation.*;

@RestController
public class TrainController {

    private final TrainRepository repository;

    private final TrainService trainService;

    TrainController(TrainRepository repository, TrainService trainService) {
        this.repository = repository;
        this.trainService = trainService;
    }

    @GetMapping("/train/all")
    List<Train> all() {
        return trainService.findAllTrains();
    }

    @GetMapping("/train/{id}")
    public Train findTrainById(@PathVariable int id) {
        return trainService.findTrainById(id);
    }

    @PostMapping(path="/train") // Map ONLY POST Requests
    public @ResponseBody Train addNewTrain(@RequestBody Train train) {
        return trainService.addNewTrain(train);
    }

    @DeleteMapping("/train/{id}")
    void deleteTrain(@PathVariable int id) {
        trainService.deleteTrain(id);
    }
}