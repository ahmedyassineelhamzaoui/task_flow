package com.app.taskflow.services.facade;

import com.app.taskflow.models.entity.UserTable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {
    UserDetailsService userDetailsService();

    UserTable findByEmail(String email);
}
