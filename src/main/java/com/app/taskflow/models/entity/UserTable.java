package com.app.taskflow.models.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTable implements UserDetails {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;

    private boolean accountNonExpired=true;

    private boolean accountNonLocked=true;

    private boolean credentialsNonExpired=true;

    private boolean enabled=true;

    @OneToMany(mappedBy = "createdBy")
    private List<Task> createdtasks;

    @OneToMany(mappedBy = "assignedTo")
    private List<Task> assignedtasks;
    
    @OneToMany(mappedBy = "user")
    private List<Jeton> jetons;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<RoleTable> authorities= new ArrayList<>();


    @Override
    public String getUsername() {
        return email;
    }


}
