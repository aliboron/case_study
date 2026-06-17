package com.voco.case_study;

import com.voco.case_study.dtos.AirportRequest;
import com.voco.case_study.models.Airport;
import com.voco.case_study.repositories.AirportRepository;
import com.voco.case_study.services.AirportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AirportServiceTest {

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private AirportService airportService;

    @Test
    void shouldReturnAllAirports() {
        Airport a1 = new Airport();
        a1.setIataCode("ESB");
        a1.setName("Esenboğa");
        a1.setCity("Ankara");
        a1.setCountry("Turkey");

        when(airportRepository.findAll()).thenReturn(List.of(a1));

        List<Airport> result = airportService.getAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getIataCode()).isEqualTo("ESB");
        verify(airportRepository, times(1)).findAll();
    }

    @Test
    void shouldCreateAirport() {
        AirportRequest request = new AirportRequest();
        request.setIataCode("IST");
        request.setName("Sabiha");
        request.setCity("Istanbul");
        request.setCountry("Turkey");

        Airport saved = new Airport();
        saved.setId(1L);
        saved.setIataCode("IST");

        when(airportRepository.save(any(Airport.class))).thenReturn(saved);

        Airport result = airportService.create(request);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getIataCode()).isEqualTo("IST");
        verify(airportRepository, times(1)).save(any(Airport.class));
    }

    @Test
    void shouldReturnEmptyWhenAirportNotFound() {
        when(airportRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Airport> result = airportService.getById(99L);

        assertThat(result).isEmpty();
    }

    @Test
    void shouldDeleteAirport() {
        when(airportRepository.existsById(1L)).thenReturn(true);

        boolean result = airportService.delete(1L);

        assertThat(result).isTrue();
        verify(airportRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldReturnFalseWhenDeletingNonExistentAirport() {
        when(airportRepository.existsById(99L)).thenReturn(false);

        boolean result = airportService.delete(99L);

        assertThat(result).isFalse();
        verify(airportRepository, never()).deleteById(any());
    }
}