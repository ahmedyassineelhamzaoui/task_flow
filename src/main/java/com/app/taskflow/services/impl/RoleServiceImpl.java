package com.app.taskflow.services.impl;

import com.app.taskflow.models.entity.RoleTable;
import com.app.taskflow.repositories.RoleRepository;
import com.app.taskflow.services.facade.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleTable findByAuthority(String authority) {
        return  roleRepository.findByAuthority(authority).orElseThrow(()-> new NoSuchElementException("Role not found"));
    }

    @Override
    public RoleTable findById(UUID id) {
        return  roleRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Role not found"));
    }
}
