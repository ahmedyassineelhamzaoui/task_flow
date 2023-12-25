package com.app.taskflow.mapper;

import com.app.taskflow.models.dto.TaskDTO;
import com.app.taskflow.models.entity.Task;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskMapper {

    private final ModelMapper modelMapper;
    public Task  mapToTask(TaskDTO taskDTO){
        return modelMapper.map(taskDTO,Task.class);
    }
    public TaskDTO mapToDTO(Task task){
        return modelMapper.map(task,TaskDTO.class);
    }
}
