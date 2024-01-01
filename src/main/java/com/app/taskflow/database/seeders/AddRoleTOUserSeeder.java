package com.app.taskflow.database.seeders;


import com.app.taskflow.services.facade.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddRoleTOUserSeeder {
    private final UserService userService;
    public void seed() {
            userService.AddRoleToUser("yassine@gmail.com", "ADMIN");
            userService.AddRoleToUser("yassine@gmail.com", "USER");
            userService.AddRoleToUser("yassine@gmail.com", "MANAGER");

            userService.AddRoleToUser("mohamed@gmail.com","USER");

            userService.AddRoleToUser("adil@gmail.com","MANAGER");
            userService.AddRoleToUser("adil@gmail.com","USER");

            userService.AddRoleToUser("bassem@gmail.com","ADMIN");
            userService.AddRoleToUser("bassem@gmail.com","USER");
        }

}
