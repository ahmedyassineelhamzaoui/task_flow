package com.app.taskflow.services.facade;

import com.app.taskflow.models.dto.TaskDTO;

import java.util.UUID;

public interface TaskService {

    void addTask(TaskDTO taskDTO);
    void assignTaskToUser(UUID id, UUID userId);

    void changeTaskStatus(UUID id, String status);
}
