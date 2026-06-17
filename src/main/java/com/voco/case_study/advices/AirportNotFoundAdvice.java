package com.voco.case_study.advices;

import com.voco.case_study.exceptions.AirportNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AirportNotFoundAdvice {

    @ExceptionHandler(AirportNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String airportNotFoundHandler(AirportNotFoundException ex){
        return ex.getMessage();
    }
}
