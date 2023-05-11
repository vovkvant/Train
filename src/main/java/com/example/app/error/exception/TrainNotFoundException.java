package com.example.app.error.exception;

public class TrainNotFoundException extends RuntimeException{
    public TrainNotFoundException(Long id) {
        super("Could not find train " + id);
    }
}
