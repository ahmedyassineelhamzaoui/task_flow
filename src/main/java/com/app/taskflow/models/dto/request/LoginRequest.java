package com.app.taskflow.models.dto.request;


import com.app.taskflow.common.validation.interfaces.EmailConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {

    @NotBlank(message = "Email is required")
    @EmailConstraint
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

}
