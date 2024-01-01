package com.app.taskflow.models.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Collate;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleTable implements GrantedAuthority {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Column(unique = true)
    private String authority;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserTable> users = new ArrayList<>();
}
