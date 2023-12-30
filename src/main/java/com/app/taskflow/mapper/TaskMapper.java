package com.app.taskflow.mapper;

import com.app.taskflow.models.dto.TaskDTO;
import com.app.taskflow.models.dto.UserTableDTO;
import com.app.taskflow.models.entity.Task;
import com.app.taskflow.models.entity.UserTable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(source = "user", target = "user")
    Task toEntity(TaskDTO taskDTO);

    @Mapping(source = "user", target = "user")
    TaskDTO toDTO(Task task);
}
