package com.voco.case_study.exceptions;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}