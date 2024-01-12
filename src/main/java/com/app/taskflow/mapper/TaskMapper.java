package com.app.taskflow.mapper;

import com.app.taskflow.models.dto.TagDTO;
import com.app.taskflow.models.dto.TaskDTO;
import com.app.taskflow.models.dto.UserTableDTO;
import com.app.taskflow.models.dto.response.TaskResponse;
import com.app.taskflow.models.entity.Tag;
import com.app.taskflow.models.entity.Task;
import com.app.taskflow.models.entity.TaskHasTags;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TaskMapper {


    TaskDTO toDTO(Task task);
    @Mapping(source = "taskHasTags", target = "tags")
    TaskResponse toResponse(Task task);
    Task toEntity(TaskDTO taskDTO);
    Task toEntity(TaskResponse taskResponse);

    default List<TagDTO> toTagDTOList(List<TaskHasTags> taskHasTags) {
        if (taskHasTags == null) {
            return null;
        } else {
            return taskHasTags.stream()
                    .map(taskHasTag -> new TagDTO(taskHasTag.getTag().getId(), taskHasTag.getTag().getName()))
                    .collect(Collectors.toList());
        }
    }

}
