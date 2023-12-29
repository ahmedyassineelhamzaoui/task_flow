package com.app.taskflow.services.facade;

import com.app.taskflow.models.dto.UserTableDTO;
import com.app.taskflow.models.entity.RoleTable;
import com.app.taskflow.models.entity.UserTable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDetailsService userDetailsService();

    UserTableDTO findByEmail(String email);

    void AddRoleToUser(String email,String roleName);

    Long getCount();

    void AddRolesToUser(String email, List<RoleTable> roles);
}
