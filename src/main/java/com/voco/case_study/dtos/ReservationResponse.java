package com.voco.case_study.dtos;

import com.voco.case_study.enums.ReservationStatus;
import com.voco.case_study.models.Reservation;

import java.time.LocalDate;

public class ReservationResponse {

    private Long id;
    private LocalDate flightDate;
    private Integer seatNumber;
    private ReservationStatus status;

    private String passengerName;
    private String passengerEmail;

    private String airplaneTailNumber;

    private String departureAirportCode;
    private String arrivalAirportCode;


    public ReservationResponse() {}

    public static ReservationResponse fromEntity(Reservation reservation) {
        ReservationResponse dto = new ReservationResponse();
        dto.setId(reservation.getId());
        dto.setFlightDate(reservation.getFlightDate());
        dto.setSeatNumber(reservation.getSeatNumber());
        dto.setStatus(reservation.getStatus());

        if (reservation.getUser() != null) {
            dto.setPassengerName(reservation.getUser().getName() + " " + reservation.getUser().getSurname());
            dto.setPassengerEmail(reservation.getUser().getEmail());
        }

        if (reservation.getAirplane() != null) {
            dto.setAirplaneTailNumber(reservation.getAirplane().getTailNumber());
        }

        if (reservation.getDepartureAirport() != null) {
            dto.setDepartureAirportCode(reservation.getDepartureAirport().getIataCode());
        }

        if (reservation.getArrivalAirport() != null) {
            dto.setArrivalAirportCode(reservation.getArrivalAirport().getIataCode());
        }

        return dto;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFlightDate() { return flightDate; }
    public void setFlightDate(LocalDate flightDate) { this.flightDate = flightDate; }

    public Integer getSeatNumber() { return seatNumber; }
    public void setSeatNumber(Integer seatNumber) { this.seatNumber = seatNumber; }

    public ReservationStatus getStatus() { return status; }
    public void setStatus(ReservationStatus status) { this.status = status; }

    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }

    public String getPassengerEmail() { return passengerEmail; }
    public void setPassengerEmail(String passengerEmail) { this.passengerEmail = passengerEmail; }

    public String getAirplaneTailNumber() { return airplaneTailNumber; }
    public void setAirplaneTailNumber(String airplaneTailNumber) { this.airplaneTailNumber = airplaneTailNumber; }

    public String getDepartureAirportCode() { return departureAirportCode; }
    public void setDepartureAirportCode(String departureAirportCode) { this.departureAirportCode = departureAirportCode; }

    public String getArrivalAirportCode() { return arrivalAirportCode; }
    public void setArrivalAirportCode(String arrivalAirportCode) { this.arrivalAirportCode = arrivalAirportCode; }
}