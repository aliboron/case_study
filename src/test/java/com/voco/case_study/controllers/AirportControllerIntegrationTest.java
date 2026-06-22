package com.voco.case_study.controllers;

import com.voco.case_study.BaseIntegrationTest;
import com.voco.case_study.dtos.AirportRequest;
import com.voco.case_study.models.Airport;
import com.voco.case_study.repositories.AirportRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AirportControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private AirportRepository airportRepository;

    @Test
    @WithMockUser(username = "john.doe@email.com", roles = "PASSENGER")
    void createAirport_byPassenger_ShouldReturn403() throws Exception {
        AirportRequest airport = new AirportRequest("IST", "Istanbul Airport",
                    "Istanbul", "Turkey");

        mockMvc.perform(post("/airports")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(airport)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin1@airlines.com", roles = "ADMIN")
    void createAirport_byAdmin_ShouldReturn201() throws Exception {
        AirportRequest airport = new AirportRequest("IST", "Istanbul Airport", "Istanbul",
                "Turkey");

        mockMvc.perform(post("/airports")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(airport)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "john.doe@email.com", roles = "PASSENGER")
    void getAirports_All_ShouldReturn200() throws Exception {
        airportRepository.deleteAll();

        Airport airport = new Airport();
        airport.setIataCode("SAW");
        airport.setName("Sabiha Gokcen");
        airport.setCity("Istanbul");
        airport.setCountry("Turkey");
        airportRepository.save(airport);

        mockMvc.perform(get("/airports"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].iataCode").value("SAW"));
    }

    @Test
    @WithMockUser(username = "john.doe@email.com", roles = "PASSENGER")
    void getAirport_byId_ShouldReturn200() throws Exception {
        airportRepository.deleteAll();

        Airport airport = new Airport();
        airport.setIataCode("ESB");
        airport.setName("Esenboga");
        airport.setCity("Ankara");
        airport.setCountry("Turkey");
        airport = airportRepository.save(airport);

        mockMvc.perform(get("/airports/" + airport.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.iataCode").value("ESB"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteAirport_byId_ShouldReturn204() throws Exception {
        airportRepository.deleteAll();

        Airport airport = new Airport();
        airport.setIataCode("ADB");
        airport.setName("Adnan Menderes");
        airport.setCity("Izmir");
        airport.setCountry("Turkey");
        airport = airportRepository.save(airport);

        mockMvc.perform(delete("/airports/" + airport.getId()))
                .andExpect(status().isNoContent()); 
    }
}
