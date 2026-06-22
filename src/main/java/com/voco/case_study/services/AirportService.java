package com.voco.case_study.services;

import java.util.UUID;

import com.voco.case_study.dtos.AirportRequest;
import com.voco.case_study.models.Airport;
import com.voco.case_study.repositories.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {


    private final AirportRepository airportRepository;

    public AirportService (AirportRepository airportRepository){
        this.airportRepository = airportRepository;
    }

    //@Cacheable("airports")
    public List<Airport> getAll() {
        return airportRepository.findAll();
    }

    public Optional<Airport> getById(UUID id) {
        return airportRepository.findById(id);
    }

    //@CacheEvict(value = "airports", allEntries = true)
    public Airport create(AirportRequest request) {
        Airport airport = new Airport();
        airport.setIataCode(request.iataCode());
        airport.setName(request.name());
        airport.setCity(request.city());
        airport.setCountry(request.country());
        return airportRepository.save(airport);
    }

    //@CacheEvict(value = "airports", allEntries = true)
    public Optional<Airport> update(UUID id, AirportRequest request) {
        return airportRepository.findById(id).map(airport -> {
            airport.setIataCode(request.iataCode());
            airport.setName(request.name());
            airport.setCity(request.city());
            airport.setCountry(request.country());
            return airportRepository.save(airport);
        });
    }

    //@CacheEvict(value = "airports", allEntries = true)
    public boolean delete(UUID id) {
        if (airportRepository.existsById(id)) {
            airportRepository.deleteById(id);
            return true;
        }
        return false;
    }
}