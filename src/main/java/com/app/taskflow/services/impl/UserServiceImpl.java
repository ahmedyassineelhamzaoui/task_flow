package com.app.taskflow.services.impl;

import com.app.taskflow.mapper.UserTableMapper;
import com.app.taskflow.models.dto.UserTableDTO;
import com.app.taskflow.models.entity.RoleTable;
import com.app.taskflow.models.entity.UserTable;
import com.app.taskflow.repositories.RoleRepository;
import com.app.taskflow.repositories.UserRepository;
import com.app.taskflow.services.facade.RoleService;
import com.app.taskflow.services.facade.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserTableMapper userTableMapper;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    @Override
    public UserTableDTO findByEmail(String email) {
        return  userTableMapper.toDTO(userRepository.findByEmail(email).orElseThrow(()-> new NoSuchElementException("User not found"))) ;
    }

    @Override
    public void AddRoleToUser(String email, String roleName) {
        UserTableDTO userTableDto = findByEmail(email);

        RoleTable role =  roleService.findByAuthority(roleName);
       UserTable userTable =  userTableMapper.toEntity(userTableDto);
        userTable.getAuthorities().add(role);
        userRepository.save(userTable);
    }

    @Override
    public Long getCount() {
        return userRepository.count();
    }

    @Override
    public void AddRolesToUser(String email, List<RoleTable> roles) {
        UserTable userTable = userTableMapper.toEntity(findByEmail(email)) ;
        for (RoleTable role : roles) {
            RoleTable roleTable = roleService.findById(role.getId());
            userTable.getAuthorities().add(roleTable);
        }
        userRepository.save(userTable);
    }
}
