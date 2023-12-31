package com.app.taskflow.mapper;

import com.app.taskflow.models.dto.TagDTO;
import com.app.taskflow.models.dto.TaskDTO;
import com.app.taskflow.models.dto.UserTableDTO;
import com.app.taskflow.models.entity.Tag;
import com.app.taskflow.models.entity.Task;
import com.app.taskflow.models.entity.TaskHasTags;
import com.app.taskflow.models.entity.UserTable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface TaskMapper {
    TaskDTO toDTO(Task task);

    Task toEntity(TaskDTO taskDTO);

}
