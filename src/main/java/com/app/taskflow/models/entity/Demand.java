package com.app.taskflow.models.entity;


import com.app.taskflow.enums.OperationType;
import com.app.taskflow.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Demand {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @ManyToOne
    private UserTable demandBy;

    @OneToOne
    private Task task;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
