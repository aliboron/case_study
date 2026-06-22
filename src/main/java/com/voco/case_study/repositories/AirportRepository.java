package com.voco.case_study.repositories;

import java.util.UUID;

import com.voco.case_study.models.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, UUID> {
}
