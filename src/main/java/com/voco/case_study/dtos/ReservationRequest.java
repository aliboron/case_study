package com.voco.case_study.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record ReservationRequest(
        @NotNull Long airplaneId,
        @NotNull Long departureAirportId,
        @NotNull Long arrivalAirportId,
        @NotNull @FutureOrPresent LocalDate flightDate,
        @NotNull @Min(value = 1) Integer seatNumber
) {}