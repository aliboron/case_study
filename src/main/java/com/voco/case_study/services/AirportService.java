package com.voco.case_study.services;

import com.voco.case_study.dtos.AirportRequest;
import com.voco.case_study.models.Airport;
import com.voco.case_study.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    public List<Airport> getAll() {
        return airportRepository.findAll();
    }

    public Optional<Airport> getById(Long id) {
        return airportRepository.findById(id);
    }

    public Airport create(AirportRequest request) {
        Airport airport = new Airport();
        airport.setIataCode(request.getIataCode());
        airport.setName(request.getName());
        airport.setCity(request.getCity());
        airport.setCountry(request.getCountry());
        return airportRepository.save(airport);
    }

    public Optional<Airport> update(Long id, AirportRequest request) {
        return airportRepository.findById(id).map(airport -> {
            airport.setIataCode(request.getIataCode());
            airport.setName(request.getName());
            airport.setCity(request.getCity());
            airport.setCountry(request.getCountry());
            return airportRepository.save(airport);
        });
    }

    public boolean delete(Long id) {
        if (airportRepository.existsById(id)) {
            airportRepository.deleteById(id);
            return true;
        }
        return false;
    }
}