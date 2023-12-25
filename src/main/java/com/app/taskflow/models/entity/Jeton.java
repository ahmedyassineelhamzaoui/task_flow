package com.app.taskflow.models.entity;


import com.app.taskflow.enums.JetonType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Jeton {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Enumerated(EnumType.STRING)
    private JetonType type;

    private Long quantity;

    @ManyToOne
    private User user;

}
