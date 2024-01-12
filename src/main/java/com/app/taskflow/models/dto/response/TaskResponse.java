package com.app.taskflow.models.dto.response;

import com.app.taskflow.models.dto.TagDTO;
import com.app.taskflow.models.dto.TaskHasTagsDTO;
import com.app.taskflow.models.dto.UserTableDTO;
import com.app.taskflow.models.entity.TaskHasTags;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {

    private UUID id;
    private String title;
    private String description;
    private String status ;
    private Date startDate;
    private Date endDate;
    private List<TaskHasTagsDTO> taskTagsDTO;

    private UserTableDTO createdBy;

    private UserTableDTO assignedTo;
}
