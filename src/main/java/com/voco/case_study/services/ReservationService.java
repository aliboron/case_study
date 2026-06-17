package com.voco.case_study.services;

import com.voco.case_study.dtos.ReservationRequest;
import com.voco.case_study.enums.ReservationStatus;
import com.voco.case_study.exceptions.ResourceNotFoundException;
import com.voco.case_study.models.Airplane;
import com.voco.case_study.models.Airport;
import com.voco.case_study.models.Reservation;
import com.voco.case_study.models.User;
import com.voco.case_study.repositories.AirplaneRepository;
import com.voco.case_study.repositories.AirportRepository;
import com.voco.case_study.repositories.ReservationRepository;
import com.voco.case_study.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired private ReservationRepository reservationRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private AirplaneRepository airplaneRepository;
    @Autowired private AirportRepository airportRepository;

    public Reservation create(ReservationRequest request, String userEmail) {

        // 1. İş Kuralı: Koltuk dolu mu kontrolü (Çakışma engelleme)
        boolean isSeatTaken = reservationRepository.existsByAirplaneIdAndSeatNumberAndFlightDate(
                request.getAirplaneId(),
                request.getSeatNumber(),
                request.getFlightDate()
        );

        if (isSeatTaken) {
            throw new RuntimeException("Seat already taken!");
        }

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", userEmail));

        Airplane airplane = airplaneRepository.findById(request.getAirplaneId())
                .orElseThrow(() -> new ResourceNotFoundException("Airplane", "id", request.getAirplaneId()));

        Airport depAirport = airportRepository.findById(request.getDepartureAirportId())
                .orElseThrow(() -> new ResourceNotFoundException("Airport", "id", request.getDepartureAirportId()));

        Airport arrAirport = airportRepository.findById(request.getArrivalAirportId())
                .orElseThrow(() -> new ResourceNotFoundException("Airport", "id", request.getArrivalAirportId()));

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setAirplane(airplane);
        reservation.setDepartureAirport(depAirport);
        reservation.setArrivalAirport(arrAirport);
        reservation.setFlightDate(request.getFlightDate());
        reservation.setSeatNumber(request.getSeatNumber());

        reservation.setStatus(ReservationStatus.CONFIRMED);
        return reservationRepository.save(reservation);
    }
}