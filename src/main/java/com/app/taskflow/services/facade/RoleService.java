package com.app.taskflow.services.facade;

import com.app.taskflow.models.entity.RoleTable;

import java.util.UUID;

public interface RoleService {

    RoleTable findByAuthority(String authority);
    RoleTable findById(UUID id);
}
