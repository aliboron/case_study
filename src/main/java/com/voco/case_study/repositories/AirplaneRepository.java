package com.voco.case_study.repositories;


import com.voco.case_study.models.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
    Optional<Airplane> findByTailNumber(String tailNumber);
}