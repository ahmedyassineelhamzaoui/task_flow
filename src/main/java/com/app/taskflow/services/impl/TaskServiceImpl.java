package com.app.taskflow.services.impl;

import com.app.taskflow.mapper.TaskMapper;
import com.app.taskflow.models.dto.TaskDTO;
import com.app.taskflow.repositories.TaskRepository;
import com.app.taskflow.services.facade.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    @Override
    public void addTask(TaskDTO taskDTO) {

        taskRepository.save(taskMapper.toEntity(taskDTO));

    }
}
