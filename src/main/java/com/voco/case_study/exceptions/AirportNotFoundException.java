package com.voco.case_study.exceptions;

public class AirportNotFoundException extends RuntimeException{
    AirportNotFoundException(Long id){
        super("Could not find airport " + id);
    }
}
