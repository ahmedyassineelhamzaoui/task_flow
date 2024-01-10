package com.app.taskflow.services.impl;

import com.app.taskflow.common.response.JwtAuthenticationResponse;
import com.app.taskflow.models.dto.request.LoginRequest;
import com.app.taskflow.models.dto.request.SignUpRequest;
import com.app.taskflow.models.dto.response.AuthResponse;
import com.app.taskflow.models.entity.UserTable;
import com.app.taskflow.repositories.UserRepository;
import com.app.taskflow.services.facade.AuthenticationService;
import com.app.taskflow.services.facade.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
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
    public AuthResponse signup(SignUpRequest request) {
        var user = UserTable.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .authorities(new ArrayList<>()).accountNonExpired(true).accountNonLocked(true).credentialsNonExpired(true).enabled(true).build();
        UserTable userResponse = userRepository.save(user);
        var jwtAccess = jwtService.generateAccessToken(user);
        var jwtRefresh = jwtService.generateRefreshToken(user);

        return AuthResponse.builder().firstName(userResponse.getFirstName()).lastName(userResponse.getLastName()).email(userResponse.getEmail()).accessToken(jwtAccess).refreshToken(jwtRefresh).build();
    }

    @Override
    public AuthResponse signin(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (AuthenticationException e) {
            throw new IllegalArgumentException("Invalid email or password.");
        }
        UserTable user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwtAccess = jwtService.generateAccessToken(user);
        var jwtRefresh = jwtService.generateRefreshToken(user);

        return AuthResponse.builder().email(user.getEmail()).firstName(user.getEmail()).lastName(user.getLastName()).accessToken(jwtAccess).refreshToken(jwtRefresh).build();
    }
}
