package com.example.app.error.advice;
import com.example.app.error.exception.TrainNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TrainNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(TrainNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String trainNotFoundHandler(TrainNotFoundException ex) {
        return ex.getMessage();
    }
}
