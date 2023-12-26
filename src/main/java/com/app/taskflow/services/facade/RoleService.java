package com.app.taskflow.services.facade;


import com.app.taskflow.models.entity.Role;

import java.util.Collection;

public interface RoleService {
    Role save(Role role);

    void save(Collection<Role> roles);

    Role findByAuthority(String authority);
}
