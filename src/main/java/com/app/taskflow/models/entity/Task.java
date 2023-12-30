package com.app.taskflow.models.entity;


import com.app.taskflow.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Task {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Column(unique = true)
    private String title;

    private String description;

    private  boolean isTaskAlreadyTakeJeton = true;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;


    @OneToMany(mappedBy = "task")
    private List<TaskTag> taskTags;

    @ManyToOne
    private UserTable user;

}
