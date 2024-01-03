package com.app.taskflow.mapper;

import com.app.taskflow.models.dto.TaskDTO;
import com.app.taskflow.models.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface TaskMapper {
    TaskDTO toDTO(Task task);

    Task toEntity(TaskDTO taskDTO);

}
