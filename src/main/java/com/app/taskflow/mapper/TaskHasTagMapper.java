package com.app.taskflow.mapper;

import com.app.taskflow.models.dto.TagDTO;
import com.app.taskflow.models.dto.TaskHasTagsDTO;
import com.app.taskflow.models.entity.Tag;
import com.app.taskflow.models.entity.TaskHasTags;
import org.mapstruct.Mapper;

@Mapper(componentModel ="spring")

public interface TaskHasTagMapper {
    TaskHasTagsDTO toDTO(TaskHasTags taskHasTags);

    TaskHasTags toEntity(TaskHasTagsDTO taskHasTagsDTO);
}
