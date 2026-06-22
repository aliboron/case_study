package com.voco.case_study.dtos;

import java.util.UUID;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record ReservationRequest(
        @NotNull UUID airplaneId,
        @NotNull UUID departureAirportId,
        @NotNull UUID arrivalAirportId,
        @NotNull @FutureOrPresent LocalDate flightDate,
        @NotNull @Min(value = 1) Integer seatNumber
) {}