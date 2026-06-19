package com.voco.case_study.controllers;

import com.voco.case_study.BaseIntegrationTest;
import com.voco.case_study.dtos.ReservationRequest;
import com.voco.case_study.enums.Role;
import com.voco.case_study.models.Airplane;
import com.voco.case_study.models.Airport;
import com.voco.case_study.models.User;
import com.voco.case_study.repositories.AirplaneRepository;
import com.voco.case_study.repositories.AirportRepository;
import com.voco.case_study.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;


import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class ReservationControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithMockUser(username = "test@voco.com", roles = "PASSENGER")
    void createReservation_WithValidPassenger_ShouldReturn201() throws Exception {

        User user = new User();
        user.setEmail("test@voco.com");
        user.setPasswordHash("nonexistenthash");
        user.setName("Test");
        user.setSurname("User");
        user.setAddress("Test Address");
        user.setRole(Role.PASSENGER);
        userRepository.save(user);

        Airport departure = new Airport();
        departure.setIataCode("IST");
        departure.setCity("Istanbul");
        departure.setName("Istanbul Airport");
        departure.setCountry("Turkey");
        departure = airportRepository.save(departure);

        Airport arrival = new Airport();
        arrival.setIataCode("ANK");
        arrival.setCity("Ankara");
        arrival.setName("Esenboga Airport");
        arrival.setCountry("Turkey");
        arrival = airportRepository.save(arrival);

        Airplane airplane = new Airplane();
        airplane.setTailNumber("TC-123");
        airplane.setCapacity(150);
        airplane.setAirline("Turkish Airlines");
        airplane.setModel("Boeing 737");
        airplane = airplaneRepository.save(airplane);


        ReservationRequest request = new ReservationRequest();
        request.setAirplaneId(airplane.getId());
        request.setDepartureAirportId(departure.getId());
        request.setArrivalAirportId(arrival.getId());
        request.setFlightDate(LocalDate.now().plusDays(5));
        request.setSeatNumber(12);

        mockMvc.perform(post("/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void createReservation_WithoutLogin_ShouldReturn401() throws Exception {

        ReservationRequest request = new ReservationRequest();
        request.setAirplaneId(1L);
        request.setFlightDate(LocalDate.now().plusDays(5));
        request.setSeatNumber(12);

        mockMvc.perform(post("/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }
}