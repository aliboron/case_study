package com.voco.case_study.dtos;

import com.voco.case_study.enums.ReservationStatus;
import com.voco.case_study.models.Reservation;

import java.time.LocalDate;

public record ReservationResponse(
        Long id,
        LocalDate flightDate,
        Integer seatNumber,
        ReservationStatus status,
        String passengerName,
        String passengerEmail,
        String airplaneTailNumber,
        String departureAirportCode,
        String arrivalAirportCode
) {
    public static ReservationResponse fromEntity(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getFlightDate(),
                reservation.getSeatNumber(),
                reservation.getStatus(),
                reservation.getUser() != null ? reservation.getUser().getName() + " " + reservation.getUser().getSurname() : null,
                reservation.getUser() != null ? reservation.getUser().getEmail() : null,
                reservation.getAirplane() != null ? reservation.getAirplane().getTailNumber() : null,
                reservation.getDepartureAirport() != null ? reservation.getDepartureAirport().getIataCode() : null,
                reservation.getArrivalAirport() != null ? reservation.getArrivalAirport().getIataCode() : null
        );
    }
}