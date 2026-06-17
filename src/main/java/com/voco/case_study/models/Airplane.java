package com.voco.case_study.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "airplanes")
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String model;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String tailNumber;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Integer capacity;

    @NotBlank
    @Column(nullable = false)
    private String airline;

    public Airplane() {}

    public Airplane(Long id, String model, String tailNumber, Integer capacity, String airline) {
        this.id = id;
        this.model = model;
        this.tailNumber = tailNumber;
        this.capacity = capacity;
        this.airline = airline;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getTailNumber() { return tailNumber; }
    public void setTailNumber(String tailNumber) { this.tailNumber = tailNumber; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
}
