package com.app.taskflow.models.dto.request;


import com.app.taskflow.models.entity.RoleTable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRolesRequest {

        @NotBlank(message = "Email is required")
        private String email;

        @NotEmpty(message = "Roles are required")
        private List<RoleTable> roles;
}
