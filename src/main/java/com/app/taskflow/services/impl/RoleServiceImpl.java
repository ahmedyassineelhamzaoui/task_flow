package com.app.taskflow.services.impl;


import com.app.taskflow.models.entity.Role;
import com.app.taskflow.repositories.RoleRepository;
import com.app.taskflow.services.facade.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        Role loadedRole = roleRepository.findByAuthority(role.getAuthority());
        if(loadedRole == null){
            return roleRepository.save(role);
        }else{
            return loadedRole;
        }
    }

    @Override
    public void save(Collection<Role> roles) {
        if(roles!=null && !roles.isEmpty()){
            for (Role role :roles) {
                Role foundedRole = findByAuthority(role.getAuthority());
                if (foundedRole != null) {
                    role.setId(foundedRole.getId());
                }else{
                    roleRepository.save(role);
                }
            }
        }
    }

    @Override
    public Role findByAuthority(String authority) {
        return roleRepository.findByAuthority(authority);
    }
}
