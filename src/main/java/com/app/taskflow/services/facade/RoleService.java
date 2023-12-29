package com.app.taskflow.services.facade;

import com.app.taskflow.models.entity.RoleTable;

public interface RoleService {

    RoleTable findByAuthority(String authority);
}
