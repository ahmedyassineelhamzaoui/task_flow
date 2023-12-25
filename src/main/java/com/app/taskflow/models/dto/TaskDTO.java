package com.app.taskflow.models.dto;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.UUID;

@Setter
@Getter
@Builder
public class TaskDTO {

    private UUID id;
    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotBlank(message = "Description is mandatory")
    private String description;

    private String status;

    @NotBlank(message = "Date is mandatory")
    @Future(message = "Date must be in the future")
    private String date;

    private Time startTime;

    private Time endTime;

    private UserDTO userDTO;
}
