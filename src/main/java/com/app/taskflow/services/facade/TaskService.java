package com.app.taskflow.services.facade;

import com.app.taskflow.models.dto.TaskDTO;

public interface TaskService {

    void addTask(TaskDTO taskDTO);
}
