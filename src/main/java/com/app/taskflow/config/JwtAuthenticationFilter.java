package com.app.taskflow.config;

import com.app.taskflow.services.JwtService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request,@Nonnull HttpServletResponse response,@Nonnull FilterChain filterChain) throws ServletException, IOException {

        // header that contains jwt token
        final String authHeader = request.getHeader("Authorization");
        final String jwToken;
        final String userEmail;
        if(authHeader == null  || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        jwToken = authHeader.substring(7);
        userEmail = jwtService.extractEmail(jwToken);
    }
}
