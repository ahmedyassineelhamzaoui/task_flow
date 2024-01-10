package com.app.taskflow.models.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String accessToken;
    private String refreshToken;

}
