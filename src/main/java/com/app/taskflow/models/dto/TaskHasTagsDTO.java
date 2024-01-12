package com.app.taskflow.models.dto;

import com.app.taskflow.models.entity.Tag;
import com.app.taskflow.models.entity.Task;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TaskHasTagsDTO {

    private UUID id;

    private TaskDTO task;

    private TagDTO tag;
}
