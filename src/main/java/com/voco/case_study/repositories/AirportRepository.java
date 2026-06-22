package com.voco.case_study.repositories;

import java.util.UUID;

import com.voco.case_study.models.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, UUID> {
}
