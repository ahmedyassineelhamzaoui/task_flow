package com.app.taskflow.services.impl;

import com.app.taskflow.mapper.UserTableMapper;
import com.app.taskflow.models.dto.UserTableDTO;
import com.app.taskflow.models.entity.RoleTable;
import com.app.taskflow.models.entity.UserTable;
import com.app.taskflow.repositories.RoleRepository;
import com.app.taskflow.repositories.UserRepository;
import com.app.taskflow.services.facade.RoleService;
import com.app.taskflow.services.facade.UserService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserTableMapper userTableMapper;
    private final RoleRepository roleRepository;

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
    @Transactional
    public void AddRoleToUser(String email, String roleName) {
        UserTable user= userRepository.findByEmail(email).orElseThrow(()-> new NoSuchElementException("User not found"));
        RoleTable role =  roleService.findByAuthority(roleName);

        if (user.getAuthorities() != null ) {
            user.getAuthorities().add(role);
            role.getUsers().add(user);
        }

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
