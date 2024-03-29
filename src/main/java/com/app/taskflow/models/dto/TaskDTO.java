package com.app.taskflow.models.dto;


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
public class TaskDTO {

    private UUID id;
    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotBlank(message = "Description is mandatory")
    private String description;

    private String status = "TODO";

    @NotNull(message = "End Date cannot be null")
    @Future(message = "Start Date must be in the future")
    private Date startDate;

    @NotNull(message = "Start Date cannot be null")
    @Future(message = "End Date must be in the future")
    private Date endDate;

    @NotEmpty(message = "tags is required")
    private List<TagDTO> tags;

    private UserTableDTO createdBy;

    private UserTableDTO assignedTo;


}
