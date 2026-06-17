package com.voco.case_study;

import com.voco.case_study.models.Airport;
import com.voco.case_study.repositories.AirportRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class AirportRepositoryTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private AirportRepository airportRepository;

    @Test
    void shouldSaveAndFindAirport() {
        Airport airport = new Airport();
        airport.setIataCode("ESB");
        airport.setName("Esenboğa");
        airport.setCity("Ankara");
        airport.setCountry("Turkey");

        Airport saved = airportRepository.save(airport);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getIataCode()).isEqualTo("ESB");
    }

    @Test
    void shouldFailOnDuplicateIataCode() {
        Airport a1 = new Airport();
        a1.setIataCode("IST");
        a1.setName("Sabiha");
        a1.setCity("Istanbul");
        a1.setCountry("Turkey");
        airportRepository.save(a1);

        Airport a2 = new Airport();
        a2.setIataCode("IST"); // duplicate
        a2.setName("Atatürk");
        a2.setCity("Istanbul");
        a2.setCountry("Turkey");

        assertThatThrownBy(() -> airportRepository.saveAndFlush(a2))
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}
