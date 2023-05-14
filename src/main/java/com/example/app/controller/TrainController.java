package com.example.app.controller;
import java.util.List;
import com.example.app.entity.Train;
import com.example.app.repository.TrainRepository;
import org.springframework.web.bind.annotation.*;

@RestController
class TrainController {

    private final TrainRepository repository;

    TrainController(TrainRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/trains")
    List<Train> all() {
       return repository.findAll();
    }

    @GetMapping("/trains/{id}")
    public Train findOne(@PathVariable int id) {
        return (Train) repository.findTrainById(id);
    }

    @PostMapping(path="/trains/add") // Map ONLY POST Requests
    public @ResponseBody String addNewTrain(@RequestParam String number, @RequestParam int placesNumber) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Train n = new Train();
        n.setNumber(number);
        n.setPlacesNumber(placesNumber);
        repository.save(n);

        return "Saved";
    }

    @PutMapping("/trains/{id}")
    Train replaceTrain(@RequestBody Train newTrain, @PathVariable int id) {

        return repository.findById(id)
                .map(train -> {
                    train.setNumber(newTrain.getNumber());
                    return repository.save(train);
                })
                .orElseGet(() -> {
                    return repository.save(newTrain);
                });
    }
    @DeleteMapping("/trains/{id}")
    void deleteEmployee(@PathVariable int id) {
        repository.deleteById(id);
    }
}