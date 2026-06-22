package com.voco.case_study.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AirplaneRequest(
        @NotBlank String model,
        @NotBlank String tailNumber,
        @NotNull Integer capacity,
        @NotBlank String airline
) {}
