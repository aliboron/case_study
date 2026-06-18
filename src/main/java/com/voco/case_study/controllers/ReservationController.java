package com.voco.case_study.controllers;

import com.voco.case_study.dtos.ReservationRequest;
import com.voco.case_study.exceptions.ResourceNotFoundException;
import com.voco.case_study.models.Reservation;
import com.voco.case_study.services.ReservationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@Tag(name = "Reservation Endpoints")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    @PreAuthorize("hasRole('PASSENGER') or hasRole('ADMIN')")
    public ResponseEntity<Reservation> createReservation(
            @Valid @RequestBody ReservationRequest request,
            Authentication authentication
    ) {
        String currentUserEmail = authentication.getName();
        Reservation createdReservation = reservationService.create(request, currentUserEmail);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReservation);
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('PASSENGER') or hasRole('ADMIN')")
    public ResponseEntity<List<Reservation>> getMyReservations(Authentication authentication) {
        String currentUserEmail = authentication.getName();
        List<Reservation> myReservations = reservationService.getUserReservations(currentUserEmail);
        return ResponseEntity.ok(myReservations);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationService.cancel(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", id));

        return ResponseEntity.noContent().build();
    }
}