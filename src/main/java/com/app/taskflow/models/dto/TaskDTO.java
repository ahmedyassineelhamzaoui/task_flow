package com.app.taskflow.models.dto;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Time;
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

    private String status;

    @NotNull(message = "Date is mandatory")
    @Future(message = "Date must be in the future")
    private Date date;

    @NotNull(message = "Start time cannot be null")
    private Time startTime;

    @NotNull(message = "End time cannot be null")
    private Time endTime;

    @NotNull(message = "user is required")
    private UserTableDTO userDTO;

    @NotEmpty(message = "tags is required")
    private List<TagDTO> tags;
}
