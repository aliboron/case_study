package com.voco.case_study.controllers;

import com.voco.case_study.dtos.AirportRequest;
import com.voco.case_study.models.Airport;
import com.voco.case_study.repositories.AirportRepository;
import com.voco.case_study.services.AirportService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.json.GsonEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
@Tag(name = "Airport Endpoints")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping
    public ResponseEntity<List<Airport>> getAll() {
        return ResponseEntity.ok(airportService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airport> getById(@PathVariable Long id) {
        return airportService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Airport> create(@RequestBody @Valid AirportRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(airportService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Airport> update(@PathVariable Long id, @RequestBody @Valid AirportRequest request) {
        return airportService.update(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (airportService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
