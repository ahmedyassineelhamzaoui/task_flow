package com.app.taskflow.database.seeders;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder {

    private final UserSeeder userSeeder;
    private final RoleSeeder roleSeeder;
    private final AddRoleTOUserSeeder addRoleTOUserSeeder;
    private final TagSeeder tagSeeder;

    @PostConstruct
    public void init() {
        tagSeeder.seed();
        userSeeder.seed();
        roleSeeder.seed();
        addRoleTOUserSeeder.seed();
    }
}
