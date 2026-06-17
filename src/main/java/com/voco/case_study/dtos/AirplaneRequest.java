package com.voco.case_study.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AirplaneRequest {

    @NotBlank
    private String model;

    @NotBlank
    private String tailNumber;

    @NotNull
    private Integer capacity;

    @NotBlank
    private String airline;

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getTailNumber() { return tailNumber; }
    public void setTailNumber(String tailNumber) { this.tailNumber = tailNumber; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
}
