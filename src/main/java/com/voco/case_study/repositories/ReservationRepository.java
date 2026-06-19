package com.voco.case_study.repositories;

import com.voco.case_study.enums.ReservationStatus;
import com.voco.case_study.models.Reservation;
import com.voco.case_study.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long>, QuerydslPredicateExecutor<Reservation> {
    List<Reservation> findByUser(User user);
    boolean existsByAirplaneIdAndSeatNumberAndFlightDateAndStatus(
            Long airplaneId,
            Integer seatNumber,
            LocalDate flightDate,
            ReservationStatus status
    );}
