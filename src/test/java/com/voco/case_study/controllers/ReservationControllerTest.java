package com.voco.case_study.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voco.case_study.dtos.ReservationRequest;
import com.voco.case_study.models.Reservation;
import com.voco.case_study.services.ReservationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "sprin  g.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    @MockitoBean
    private ReservationService reservationService;

    @Test
    @WithMockUser(username = "test@voco.com", roles = "PASSENGER")
    void createReservation_WithValidPassenger_ShouldReturn201() throws Exception {

        ReservationRequest request = new ReservationRequest();
        request.setAirplaneId(1L);
        request.setDepartureAirportId(2L);
        request.setArrivalAirportId(3L);
        request.setFlightDate(LocalDate.now().plusDays(5));
        request.setSeatNumber(12);

        Reservation fakeReservation = new Reservation();
        fakeReservation.setId(100L);
        Mockito.when(reservationService.create(any(ReservationRequest.class), eq("test@voco.com")))
                .thenReturn(fakeReservation);


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
                .andExpect(status().isUnauthorized()); // 401 dönmesini bekliyoruz
    }

    /*
    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteReservation_WithAdminRole_ShouldReturn204() throws Exception {



        // EYLEM VE DOĞRULAMA: DELETE isteği at ve 204 No Content bekle
        mockMvc.perform(delete("/reservations/1"))
                .andExpect(status().isNoContent());
    }
     */
}