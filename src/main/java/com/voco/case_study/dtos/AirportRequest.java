package com.voco.case_study.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AirportRequest(
        @NotBlank @Size(min = 3, max = 3) String iataCode,
        @NotBlank String name,
        @NotBlank String city,
        @NotBlank String country
) {}