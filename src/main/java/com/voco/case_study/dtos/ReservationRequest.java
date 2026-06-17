package com.voco.case_study.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class ReservationRequest {

    @NotNull
    private Long airplaneId;

    @NotNull
    private Long departureAirportId;

    @NotNull
    private Long arrivalAirportId;

    @NotNull
    @FutureOrPresent
    private LocalDate flightDate;

    @NotNull
    @Min(value = 1)
    private Integer seatNumber;

    public ReservationRequest() {}

    public Long getAirplaneId() { return airplaneId; }
    public void setAirplaneId(Long airplaneId) { this.airplaneId = airplaneId; }

    public Long getDepartureAirportId() { return departureAirportId; }
    public void setDepartureAirportId(Long departureAirportId) { this.departureAirportId = departureAirportId; }

    public Long getArrivalAirportId() { return arrivalAirportId; }
    public void setArrivalAirportId(Long arrivalAirportId) { this.arrivalAirportId = arrivalAirportId; }

    public LocalDate getFlightDate() { return flightDate; }
    public void setFlightDate(LocalDate flightDate) { this.flightDate = flightDate; }

    public Integer getSeatNumber() { return seatNumber; }
    public void setSeatNumber(Integer seatNumber) { this.seatNumber = seatNumber; }
}