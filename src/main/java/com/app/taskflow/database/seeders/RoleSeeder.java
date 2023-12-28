package com.app.taskflow.database.seeders;

import com.app.taskflow.models.entity.RoleTable;
import com.app.taskflow.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleSeeder {
    private final RoleRepository roleRepository;

    public void seed() {
        if(roleRepository.count() == 0) {
            List<RoleTable>  roles = List.of(
                  RoleTable.builder().authority("USER").build(),
                  RoleTable.builder().authority("ADMIN").build(),
                  RoleTable.builder().authority("MANAGER").build()
            );
            roleRepository.saveAll(roles);
        }

    }
}
