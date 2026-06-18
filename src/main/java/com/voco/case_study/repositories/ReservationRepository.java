package com.voco.case_study.repositories;

import com.voco.case_study.enums.ReservationStatus;
import com.voco.case_study.models.Reservation;
import com.voco.case_study.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);
    boolean existsByAirplaneIdAndSeatNumberAndFlightDateAndStatus(
            Long airplaneId,
            Integer seatNumber,
            LocalDate flightDate,
            ReservationStatus status
    );}
