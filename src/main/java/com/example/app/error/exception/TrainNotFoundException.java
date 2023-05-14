package com.example.app.error.exception;

public class TrainNotFoundException extends RuntimeException{
    public TrainNotFoundException(int id) {
        super("Could not find train " + id);
    }
}
