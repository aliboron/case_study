package com.voco.case_study.services;

import com.voco.case_study.dtos.AirplaneRequest;
import com.voco.case_study.models.Airplane;
import com.voco.case_study.repositories.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirplaneService {

    @Autowired
    private AirplaneRepository airplaneRepository;

    public List<Airplane> getAll() {
        return airplaneRepository.findAll();
    }

    public Optional<Airplane> getById(Long id) {
        return airplaneRepository.findById(id);
    }

    public Airplane create(AirplaneRequest request) {
        Airplane airport = new Airplane();
        airport.setAirline(request.getAirline());
        airport.setCapacity(request.getCapacity());
        airport.setModel(request.getModel());
        airport.setTailNumber(request.getTailNumber());
        return airplaneRepository.save(airport);
    }

    public Optional<Airplane> update(Long id, AirplaneRequest request) {
        return airplaneRepository.findById(id).map(airport -> {
            airport.setAirline(request.getAirline());
            airport.setCapacity(request.getCapacity());
            airport.setModel(request.getModel());
            airport.setTailNumber(request.getTailNumber());
            return airplaneRepository.save(airport);
        });
    }

    public boolean delete(Long id) {
        if (airplaneRepository.existsById(id)) {
            airplaneRepository.deleteById(id);
            return true;
        }
        return false;
    }
}