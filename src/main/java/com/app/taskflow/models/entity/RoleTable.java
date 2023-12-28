package com.app.taskflow.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import java.util.UUID;

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
    private String authority;
}
