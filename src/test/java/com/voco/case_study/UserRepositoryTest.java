package com.voco.case_study;

import com.voco.case_study.enums.Role;
import com.voco.case_study.models.Airport;
import com.voco.case_study.models.User;
import com.voco.case_study.repositories.AirportRepository;
import com.voco.case_study.repositories.UserRepository;
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
class UserRepositoryTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveAndFindUser() {
        User u1 = new User();
        u1.setAddress("adana");
        u1.setName("horoz");
        u1.setSurname("horozz");
        u1.setEmail("horoz@mail.com");
        u1.setRole(Role.PASSENGER);
        u1.setPasswordHash(String.valueOf(u1.hashCode()));

        User saved = userRepository.saveAndFlush(u1);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("horoz");
    }

}
