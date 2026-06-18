package com.voco.case_study.services;

import com.voco.case_study.dtos.AirplaneRequest;
import com.voco.case_study.models.Airplane;
import com.voco.case_study.repositories.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirplaneService {

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Cacheable("airplanes")
    public List<Airplane> getAll() {
        return airplaneRepository.findAll();
    }

    public Optional<Airplane> getById(Long id) {
        return airplaneRepository.findById(id);
    }

    @CacheEvict(value = "airplanes", allEntries = true)
    public Airplane create(AirplaneRequest request) {
        Airplane airplane = new Airplane();
        airplane.setAirline(request.getAirline());
        airplane.setCapacity(request.getCapacity());
        airplane.setModel(request.getModel());
        airplane.setTailNumber(request.getTailNumber());
        return airplaneRepository.save(airplane);
    }

    @CacheEvict(value = "airplanes", allEntries = true)
    public Optional<Airplane> update(Long id, AirplaneRequest request) {
        return airplaneRepository.findById(id).map(airplane -> {
            airplane.setAirline(request.getAirline());
            airplane.setCapacity(request.getCapacity());
            airplane.setModel(request.getModel());
            airplane.setTailNumber(request.getTailNumber());
            return airplaneRepository.save(airplane);
        });
    }

    @CacheEvict(value = "airplanes", allEntries = true)
    public boolean delete(Long id) {
        if (airplaneRepository.existsById(id)) {
            airplaneRepository.deleteById(id);
            return true;
        }
        return false;
    }
}