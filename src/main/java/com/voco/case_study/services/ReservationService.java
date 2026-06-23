package com.voco.case_study.services;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.voco.case_study.dtos.ReservationRequest;
import com.voco.case_study.enums.ReservationStatus;
import com.voco.case_study.models.Airplane;
import com.voco.case_study.models.Airport;
import com.voco.case_study.models.Reservation;
import com.voco.case_study.models.User;
import com.voco.case_study.repositories.AirplaneRepository;
import com.voco.case_study.repositories.AirportRepository;
import com.voco.case_study.repositories.ReservationRepository;
import com.voco.case_study.repositories.UserRepository;
import com.voco.case_study.models.QReservation;
import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final AirplaneRepository airplaneRepository;
    private final AirportRepository airportRepository;
    private final MailService mailService;

    public ReservationService(ReservationRepository reservationRepository,
                              UserRepository userRepository,
                              AirplaneRepository airplaneRepository,
                              AirportRepository airportRepository,
                              MailService mailService){
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.airplaneRepository = airplaneRepository;
        this.airportRepository = airportRepository;
        this.mailService = mailService;
    }

    public List<Reservation> getUserReservations(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with email: " + email));
        return reservationRepository.findByUser(user);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Iterable<Reservation> searchReservations(String email, ReservationStatus status) {
        QReservation qReservation = QReservation.reservation;
        BooleanBuilder builder = new BooleanBuilder();

        if (email != null && !email.isEmpty()) {
            builder.and(qReservation.user.email.eq(email));
        }
        if (status != null) {
            builder.and(qReservation.status.eq(status));
        }

        return reservationRepository.findAll(builder);
    }


    public Reservation create(ReservationRequest request, String userEmail) {


        boolean isSeatTaken = reservationRepository.existsByAirplaneIdAndSeatNumberAndFlightDateAndStatus(
                request.airplaneId(),
                request.seatNumber(),
                request.flightDate(),
                ReservationStatus.CONFIRMED
        );

        if (isSeatTaken) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Seat already taken!");
        }

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with email: " + userEmail));

        Airplane airplane = airplaneRepository.findById(request.airplaneId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Airplane not found with id: " + request.airplaneId()));

        if (request.seatNumber() > airplane.getCapacity()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seat number cant be greater than current airplane's capacity: " + airplane.getCapacity());
        }

        Airport depAirport = airportRepository.findById(request.departureAirportId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport not found with id: " + request.departureAirportId()));

        Airport arrAirport = airportRepository.findById(request.arrivalAirportId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport not found with id: " + request.arrivalAirportId()));

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setAirplane(airplane);
        reservation.setDepartureAirport(depAirport);
        reservation.setArrivalAirport(arrAirport);
        reservation.setFlightDate(request.flightDate());
        reservation.setSeatNumber(request.seatNumber());

        reservation.setStatus(ReservationStatus.CONFIRMED);

        try {
            mailService.sendPlainText(userEmail, "Reservation successful!", "Thank you for using our service");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return reservationRepository.save(reservation);

    }

    public Optional<Reservation> cancel(UUID reservationId){
        return reservationRepository.findById(reservationId).map(reservation -> {
            reservation.setStatus(ReservationStatus.CANCELLED);
            return reservationRepository.save(reservation);
        });
    }
}