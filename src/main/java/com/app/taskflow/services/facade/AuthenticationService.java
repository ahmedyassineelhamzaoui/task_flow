package com.app.taskflow.services.facade;

import com.app.taskflow.common.response.JwtAuthenticationResponse;
import com.app.taskflow.models.dto.request.LoginRequest;
import com.app.taskflow.models.dto.request.SignUpRequest;
import com.app.taskflow.models.dto.response.AuthResponse;


public interface AuthenticationService {
    AuthResponse signup(SignUpRequest request);

    AuthResponse signin(LoginRequest request);


}
