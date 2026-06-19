package com.voco.case_study.controllers;

import com.voco.case_study.BaseIntegrationTest;
import com.voco.case_study.dtos.AirplaneRequest;
import com.voco.case_study.repositories.AirplaneRepository;
import com.voco.case_study.services.AirplaneService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class AirplaneControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private AirplaneService airplaneService;

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Test
    @WithMockUser(username = "john.doe@email.com", roles = "PASSENGER")
    void createAirplane_byPassenger() throws Exception{
        AirplaneRequest airplane = new AirplaneRequest();
        airplane.setAirline("Turkish Airlines");
        airplane.setCapacity(300);
        airplane.setModel("Airbus A320neo");
        airplane.setTailNumber("TK-301");

        mockMvc.perform(post("/airplanes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(airplane)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin1@airlines.com", roles = "ADMIN")
    void createAirplane_byAdmin() throws Exception{
        AirplaneRequest airplane = new AirplaneRequest();
        airplane.setAirline("Turkish Airlines");
        airplane.setCapacity(300);
        airplane.setModel("Airbus A320neo");
        airplane.setTailNumber("TK-301");

        mockMvc.perform(post("/airplanes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(airplane)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "admin1@airlines.com", roles = "ADMIN")
    void createAirplane_byAdmin_EmptyField() throws Exception{
        AirplaneRequest airplane = new AirplaneRequest();
        airplane.setAirline("");
        airplane.setCapacity(100);
        airplane.setModel("Airbus A320neo");
        airplane.setTailNumber("TK-301");

        mockMvc.perform(post("/airplanes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(airplane)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "john.doe@email.com", roles = "PASSENGER")
    void getAirplanes_All() throws Exception{

        airplaneRepository.deleteAll();

        AirplaneRequest airplane = new AirplaneRequest();
        airplane.setAirline("Pegasus");
        airplane.setCapacity(180);
        airplane.setModel("Boeing 737");
        airplane.setTailNumber("PG-101");
        airplaneService.create(airplane);

        mockMvc.perform(get("/airplanes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].model").value("Boeing 737"))
                .andExpect(jsonPath("$[0].tailNumber").value("PG-101"));
    }

    @Test
    @WithMockUser(username = "john.doe@email.com", roles = "PASSENGER")
    void getAirplane_byId() throws Exception {
        airplaneRepository.deleteAll();

        AirplaneRequest airplane = new AirplaneRequest();
        airplane.setAirline("Pegasus");
        airplane.setCapacity(180);
        airplane.setModel("Boeing 737");
        airplane.setTailNumber("PG-101");
        airplaneService.create(airplane);

        mockMvc.perform(get("/airplanes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model").value("Boeing 737"))
                .andExpect(jsonPath("$.tailNumber").value("PG-101"));
    }

    @Test
    @WithMockUser(username = "john.doe@email.com", roles = "PASSENGER")
    void getAirplane_byId_NotFound() throws Exception {
        airplaneRepository.deleteAll();

        mockMvc.perform(get("/airplanes/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteAirplane_byId() throws Exception {
        airplaneRepository.deleteAll();

        AirplaneRequest airplane = new AirplaneRequest();
        airplane.setAirline("Pegasus");
        airplane.setCapacity(180);
        airplane.setModel("Boeing 737");
        airplane.setTailNumber("PG-101");
        airplaneService.create(airplane);

        mockMvc.perform(delete("/airplanes/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteAirplane_byId_NonExistent() throws Exception {
        airplaneRepository.deleteAll();

        mockMvc.perform(delete("/airplanes/1"))
                .andExpect(status().isNotFound());
    }
}
