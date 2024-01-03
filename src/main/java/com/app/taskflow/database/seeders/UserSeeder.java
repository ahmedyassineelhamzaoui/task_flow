package com.app.taskflow.database.seeders;

import com.app.taskflow.models.entity.UserTable;
import com.app.taskflow.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserSeeder {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public void seed() {
     if(userRepository.count() == 0){
         List<UserTable> users = List.of(

           UserTable.builder().firstName("yassine").lastName("ahmed").modificationCredit(2L).deletionCredit(1L).email("yassine@gmail.com").password(passwordEncoder.encode("!Password12@")).enabled(true).credentialsNonExpired(true).accountNonExpired(true).accountNonLocked(true).build(),
           UserTable.builder().firstName("mohamed").lastName("saadi").modificationCredit(2L).deletionCredit(1L).email("mohamed@gmail.com").password(passwordEncoder.encode("!Password12@")).enabled(true).credentialsNonExpired(true).accountNonExpired(true).accountNonLocked(true).build(),
           UserTable.builder().firstName("adil").lastName("hajji").modificationCredit(2L).deletionCredit(1L).email("adil@gmail.com").password(passwordEncoder.encode("!Password12@")).enabled(true).credentialsNonExpired(true).accountNonExpired(true).accountNonLocked(true).build(),
           UserTable.builder().firstName("bassem").modificationCredit(2L).deletionCredit(1L).lastName("amin").email("bassem@gmail.com").password(passwordEncoder.encode("!Password12@")).enabled(true).credentialsNonExpired(true).accountNonExpired(true).accountNonLocked(true).build()
         );
         userRepository.saveAll(users);
     }
    }
}
