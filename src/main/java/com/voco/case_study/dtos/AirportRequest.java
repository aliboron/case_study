package com.voco.case_study.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AirportRequest {

    @NotBlank
    @Size(min = 3, max = 3)
    private String iataCode;

    @NotBlank
    private String name;

    @NotBlank
    private String city;

    @NotBlank
    private String country;


    public String getIataCode() { return iataCode; }
    public void setIataCode(String iataCode) { this.iataCode = iataCode; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}