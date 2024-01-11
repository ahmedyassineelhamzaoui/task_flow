package com.app.taskflow.controllers;

import com.app.taskflow.common.response.JwtAuthenticationResponse;
import com.app.taskflow.models.dto.request.LoginRequest;
import com.app.taskflow.models.dto.request.SignUpRequest;
import com.app.taskflow.models.dto.request.TokenValidationRequest;
import com.app.taskflow.models.dto.response.AuthResponse;
import com.app.taskflow.services.facade.AuthenticationService;
import com.app.taskflow.services.facade.JwtService;
import com.app.taskflow.services.facade.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final JwtService jwtService;
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody @Valid  SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @PostMapping("/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestBody TokenValidationRequest validationRequest) {
        String token = validationRequest.getToken();

        try {
            UserDetails userDetails = userService.userDetailsService()
                    .loadUserByUsername(jwtService.extractUserName(token));

            if (jwtService.isTokenValid(token, userDetails)) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.ok(false);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.ok(false);
        }
    }
}
