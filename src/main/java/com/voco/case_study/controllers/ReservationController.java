package com.voco.case_study.controllers;

import com.voco.case_study.dtos.ReservationRequest;
import com.voco.case_study.dtos.ReservationResponse;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservations")
@Tag(name = "reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    @PreAuthorize("hasRole('PASSENGER') or hasRole('ADMIN')")
    public ResponseEntity<ReservationResponse> createReservation(
            @Valid @RequestBody ReservationRequest request,
            Authentication authentication
    ) {
        String currentUserEmail = authentication.getName();
        Reservation createdReservation = reservationService.create(request, currentUserEmail);

        ReservationResponse responseDto = ReservationResponse.fromEntity(createdReservation);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('PASSENGER') or hasRole('ADMIN')")
    public ResponseEntity<List<ReservationResponse>> getMyReservations(Authentication authentication) {
        String currentUserEmail = authentication.getName();
        List<Reservation> myReservations = reservationService.getUserReservations(currentUserEmail);

        List<ReservationResponse> responseList = myReservations.stream()
                .map(ReservationResponse::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ReservationResponse>> getAllReservations() {
        List<Reservation> allReservations = reservationService.getAllReservations();

        List<ReservationResponse> responseList = allReservations.stream()
                .map(ReservationResponse::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ReservationResponse>> searchReservations(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) com.voco.case_study.enums.ReservationStatus status) {
        
        Iterable<Reservation> searchResults = reservationService.searchReservations(email, status);
        
        List<ReservationResponse> responseList = new java.util.ArrayList<>();
        searchResults.forEach(res -> responseList.add(ReservationResponse.fromEntity(res)));
        
        return ResponseEntity.ok(responseList);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationService.cancel(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", id));

        return ResponseEntity.noContent().build();
    }
}