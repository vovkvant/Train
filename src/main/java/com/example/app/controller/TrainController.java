package com.example.app.controller;

import java.util.List;

import com.example.app.db.ListBase;
import com.example.app.entity.Train;
import com.example.app.error.exception.TrainNotFoundException;
import com.example.app.repository.TrainRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TrainController {

    private final TrainRepository repository;

    TrainController(TrainRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/trains")
    List<Train> all() {
//       return repository.findAll();
        return ListBase.createTrainList();
    }

    @PostMapping("/trains")
    Train newTrain(@RequestBody Train newTrain) {
        return repository.save(newTrain);
    }

    // Single item

    @GetMapping("/trains/{id}")
    Train one(@PathVariable Long id) {

//        return repository.findById(id)
//                .orElseThrow(() -> new TrainNotFoundException(id));
        return ListBase.findTrainById(id);
    }

    @PutMapping("/trains/{id}")
    Train replaceTrain(@RequestBody Train newTrain, @PathVariable Long id) {

        return repository.findById(id)
                .map(train -> {
                    train.setNumber(newTrain.getNumber());
                    return repository.save(train);
                })
                .orElseGet(() -> {
                    newTrain.setId(id);
                    return repository.save(newTrain);
                });
    }
    @DeleteMapping("/trains/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}