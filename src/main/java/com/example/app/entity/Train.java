package com.example.app.entity;

import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;

@Entity
@DynamicUpdate
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
}

