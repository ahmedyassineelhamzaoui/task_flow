package com.app.taskflow.models.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Task> tasks;
}
