package com.app.taskflow.models.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


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

    private Long modificationCredit;
    private Long deletionCredit;

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

    @OneToMany(mappedBy = "demandBy")
    private List<Demand> demands;

    @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private List<RoleTable> authorities = new ArrayList<>();

    @Override
    public String getUsername() {
        return email;
    }


}
