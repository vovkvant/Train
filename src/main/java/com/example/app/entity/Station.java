package com.example.app.entity;

import jakarta.persistence.*;
import lombok.ToString;
import org.springframework.stereotype.Component;
@Entity
@Table
@ToString
public class Station {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    public Station() {
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

