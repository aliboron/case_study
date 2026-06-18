package com.voco.case_study.services;

import com.voco.case_study.dtos.LoginRequest;
import com.voco.case_study.dtos.SignupRequest;
import com.voco.case_study.enums.Role;
import com.voco.case_study.models.User;
import com.voco.case_study.repositories.UserRepository;
import com.voco.case_study.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtils;

    @Autowired
    public AuthService(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            PasswordEncoder encoder,
            JwtUtil jwtUtils
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    public String authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtils.generateToken(userDetails.getUsername());
    }

    public String registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Error: Email is already in use!");
        }

        User newUser = new User();
        newUser.setName(signUpRequest.getName());
        newUser.setSurname(signUpRequest.getSurname());
        newUser.setEmail(signUpRequest.getEmail());
        newUser.setAddress(signUpRequest.getAddress());
        newUser.setRole(Role.PASSENGER);
        newUser.setPasswordHash(encoder.encode(signUpRequest.getPassword()));

        userRepository.save(newUser);

        return jwtUtils.generateToken(newUser.getEmail());
    }
}