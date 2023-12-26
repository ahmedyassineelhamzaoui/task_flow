package com.app.taskflow.services.impl;


import com.app.taskflow.mapper.UserMapper;
import com.app.taskflow.models.dto.UserDTO;
import com.app.taskflow.models.entity.User;
import com.app.taskflow.repositories.UserRepository;
import com.app.taskflow.services.facade.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

     private final UserRepository userRepository;
     private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                                           .orElseThrow(() ->
                                                   new UsernameNotFoundException("User not found with username: " + username)
                                           );

    }

    @Override
    public UserDTO register(UserDTO userDTO) {
        User user = userMapper.mapToUser(userDTO);
        return userMapper.mapToDTO(userRepository.save(user));
    }
}
