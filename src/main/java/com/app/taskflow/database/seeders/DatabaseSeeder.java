package com.app.taskflow.database.seeders;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder {

    private final UserSeeder userSeeder;
    private final RoleSeeder roleSeeder;

    @PostConstruct
    public void init() {
        userSeeder.seed();
        roleSeeder.seed();
    }
}
