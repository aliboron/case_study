package com.voco.case_study;

import com.voco.case_study.enums.Role;
import com.voco.case_study.models.User;
import com.voco.case_study.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CaseStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaseStudyApplication.class, args);
    }

    @Bean
    public CommandLineRunner initSuperAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            String adminEmail = "super@user.com";

            if (!userRepository.existsByEmail(adminEmail)) {
                User admin = new User();
                admin.setName("Admin");
                admin.setSurname("Bey");
                admin.setEmail(adminEmail);
                admin.setAddress("Your Head");
                admin.setRole(Role.ADMIN);
                admin.setPasswordHash(passwordEncoder.encode("nopassword"));

                userRepository.save(admin);
                System.out.println("Sisteme ilk Kurucu Admin başarıyla eklendi!");
            }
        };
    }
}
