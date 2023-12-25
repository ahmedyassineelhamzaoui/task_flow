package com.app.taskflow.models.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;


@Getter
@Setter
@Builder
public class UserDTO {

    private UUID id;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    private String email;


}
