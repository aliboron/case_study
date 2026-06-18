package com.voco.case_study.controllers;

import com.voco.case_study.dtos.AirplaneRequest;
import com.voco.case_study.exceptions.ResourceNotFoundException;
import com.voco.case_study.models.Airplane;
import com.voco.case_study.services.AirplaneService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airplanes")
@Tag(name = "airplanes")
public class AirplaneController {

    @Autowired
    private AirplaneService airplaneService;

    @GetMapping
    public ResponseEntity<List<Airplane>> getAll() {
        return ResponseEntity.ok(airplaneService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airplane> getById(@PathVariable Long id) {
        return airplaneService.getById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Airplane", "id", id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Airplane> create(@RequestBody @Valid AirplaneRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(airplaneService.create(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Airplane> update(@PathVariable Long id, @RequestBody @Valid AirplaneRequest request) {
        return airplaneService.update(id, request)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Airplane", "id", id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (airplaneService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        throw new ResourceNotFoundException("Airplane", "id", id);
    }
}
