package com.example.app.entity;

import com.example.app.repository.TrainRepository;
import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;

@Entity
@Table
@ToString
public class Train {

      @Id
      @GeneratedValue(strategy=GenerationType.IDENTITY)
      private Integer id;

    private String number;
    @Column(name = "places_number")
    private int placesNumber;
    public Train() {
    }

    public Train(int id, String number, int placesNumber) {
        this.id = id;
        this.number = number;
        this.placesNumber = placesNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

