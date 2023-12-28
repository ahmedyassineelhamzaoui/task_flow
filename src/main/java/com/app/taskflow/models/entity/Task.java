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

    private String title;

    private String description;

    private  boolean isTaskAlreadyTakeJeton = true;

    @Temporal(TemporalType.DATE)
    private Date date;

    private Time startTime;

    private Time endTime;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;


    @ManyToMany
    @JoinTable(
            name = "task_tags",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @ManyToOne
    private UserTable user;

}
