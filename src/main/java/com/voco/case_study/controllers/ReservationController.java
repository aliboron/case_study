package com.voco.case_study.controllers;

import com.voco.case_study.dtos.ReservationRequest;
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

@RestController
@RequestMapping("/reservations")
@Tag(name = "reservations")
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
}