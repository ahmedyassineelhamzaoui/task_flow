package com.app.taskflow.services.facade;

import com.app.taskflow.models.dto.TaskDTO;
import com.app.taskflow.models.dto.response.TaskResponse;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    List<TaskResponse> getAllTasks();
    void addTask(TaskDTO taskDTO);
    void assignTaskToUser(UUID id, UUID userId);
    void changeTaskStatus(UUID id, String status);

    void deleteTask(UUID id);
}
