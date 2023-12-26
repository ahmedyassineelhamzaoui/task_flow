package com.app.taskflow.services.facade;

import com.app.taskflow.models.dto.UserDTO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    UserDTO register(UserDTO userDTO);


}
