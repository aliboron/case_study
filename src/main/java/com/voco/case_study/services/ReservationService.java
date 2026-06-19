package com.voco.case_study.services;

import com.voco.case_study.dtos.ReservationRequest;
import com.voco.case_study.enums.ReservationStatus;
import com.voco.case_study.exceptions.BadRequestException;
import com.voco.case_study.exceptions.ConflictException;
import com.voco.case_study.exceptions.ResourceNotFoundException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired private ReservationRepository reservationRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private AirplaneRepository airplaneRepository;
    @Autowired private AirportRepository airportRepository;
    @Autowired private MailService mailService;

    public List<Reservation> getUserReservations(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
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
                request.getAirplaneId(),
                request.getSeatNumber(),
                request.getFlightDate(),
                ReservationStatus.CONFIRMED
        );

        if (isSeatTaken) {
            throw new ConflictException("Seat already taken!");
        }

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", userEmail));

        Airplane airplane = airplaneRepository.findById(request.getAirplaneId())
                .orElseThrow(() -> new ResourceNotFoundException("Airplane", "id", request.getAirplaneId()));

        if (request.getSeatNumber() > airplane.getCapacity()) {
            throw new BadRequestException("Seat number cant be greater than current airplane's capacity: " + airplane.getCapacity());
        }

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
        try {
            mailService.sendPlainText(userEmail, "Reservation successful!", "Thank you for using our service");
        } catch (Exception e) {
            System.out.println("Mail gönderim hatası: " + e.getMessage());
        }
        return reservationRepository.save(reservation);

    }

    public Optional<Reservation> cancel(Long reservationId){
        return reservationRepository.findById(reservationId).map(reservation -> {
            reservation.setStatus(ReservationStatus.CANCELLED);
            return reservationRepository.save(reservation);
        });
    }
}