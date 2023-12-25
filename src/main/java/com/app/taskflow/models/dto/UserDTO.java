package com.app.taskflow.models.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @Size(min= 5,max=20,message="Username mast be between 5 and 20 characters")
    private String userName;

    @NotBlank(message = "password is required")
    @Size(min=8,max=30,message = "password  must be between 8 and 30 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).*$",message = "Password must contain at least one lowercase letter,one uppercase letter,and one special character")
    private String password;


}
