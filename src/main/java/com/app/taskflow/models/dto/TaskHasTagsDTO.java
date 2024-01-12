package com.app.taskflow.models.dto;

import com.app.taskflow.models.entity.Tag;
import com.app.taskflow.models.entity.Task;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

public class TaskHasTagsDTO {

    private UUID id;

    private Task task;

    private Tag tag;
}
