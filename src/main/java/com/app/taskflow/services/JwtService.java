package com.app.taskflow.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String extractEmail(String token);
    boolean isTokenValid(String token, UserDetails userDetails);

    }
