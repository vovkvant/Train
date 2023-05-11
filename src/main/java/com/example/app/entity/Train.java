package com.example.app.entity;

import com.example.app.repository.TrainRepository;
import jakarta.persistence.*;

import java.util.List;

@Entity

public class Train {

    private  @Id  @GeneratedValue Long id;
    private String number;
    private int placesNumber;
    public Train() {
    }

    public Train(Long id, String number, int placesNumber) {
        this.id = id;
        this.number = number;
        this.placesNumber = placesNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getPlacesNumber() {
        return placesNumber;
    }

    public void setPlacesNumber(int placesNumber) {
        this.placesNumber = placesNumber;
    }

}

