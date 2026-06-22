package com.voco.case_study.repositories;

import java.util.UUID;


import com.voco.case_study.models.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneRepository extends JpaRepository<Airplane, UUID> {
}