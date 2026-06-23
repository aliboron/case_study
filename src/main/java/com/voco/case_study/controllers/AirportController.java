package com.voco.case_study.controllers;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import com.voco.case_study.dtos.AirportRequest;
import com.voco.case_study.models.Airport;
import com.voco.case_study.services.AirportService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
@Tag(name = "airports")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService){
        this.airportService = airportService;
    }

    @GetMapping
    public ResponseEntity<List<Airport>> getAll() {
        return ResponseEntity.ok(airportService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airport> getById(@PathVariable UUID id) {
        return airportService.getById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport not found with id: " + id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Airport> create(@RequestBody @Valid AirportRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(airportService.create(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Airport> update(@PathVariable UUID id, @RequestBody @Valid AirportRequest request) {
        return airportService.update(id, request)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport not found with id: " + id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (airportService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport not found with id: " + id);
    }
}
