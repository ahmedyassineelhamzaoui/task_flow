package com.app.taskflow.services.impl;

import com.app.taskflow.common.response.JwtAuthenticationResponse;
import com.app.taskflow.models.dto.request.LoginRequest;
import com.app.taskflow.models.dto.request.SignUpRequest;
import com.app.taskflow.models.entity.UserTable;
import com.app.taskflow.repositories.UserRepository;
import com.app.taskflow.services.facade.AuthenticationService;
import com.app.taskflow.services.facade.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = UserTable.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .authorities(new ArrayList<>()).accountNonExpired(true).accountNonLocked(true).credentialsNonExpired(true).enabled(true).build();
        userRepository.save(user);
        var jwt = jwtService.generateAccessToken(user);
        return JwtAuthenticationResponse.builder().accessToken(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateAccessToken(user);
        return JwtAuthenticationResponse.builder().accessToken(jwt).build();
    }
}
