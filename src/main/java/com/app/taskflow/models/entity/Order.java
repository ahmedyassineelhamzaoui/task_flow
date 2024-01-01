package com.app.taskflow.models.entity;


import com.app.taskflow.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @ManyToOne
    private UserTable user;

    @OneToOne
    private Task task;

    private OrderStatus status;
}
