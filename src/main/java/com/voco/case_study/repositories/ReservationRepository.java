package com.voco.case_study.repositories;

import com.voco.case_study.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserId(Long userId);
    boolean existsByAirplaneIdAndSeatNumberAndFlightDate(Long airplaneId, Integer seatNumber, LocalDate flightDate);
}
