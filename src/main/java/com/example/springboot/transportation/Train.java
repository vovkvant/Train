package com.example.springboot.transportation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class Train {
    private String number;
    private String placesNumber;
    public String getNumber() {
        return number;
    }
    public Train( String number, String placesNumber) {
        this.number = number;
        this.placesNumber = placesNumber;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPlacesNumber() {
        return placesNumber;
    }

    public void setPlacesNumber(String placesNumber) {
        this.placesNumber = placesNumber;
    }

    @Override
    public String toString() {
        return "Train{" +
                "number='" + number + '\'' +
                ", placesNumber=" + placesNumber +
                '}';
    }
}
