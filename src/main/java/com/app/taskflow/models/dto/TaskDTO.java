package com.app.taskflow.models.dto;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Time;
import java.util.Date;
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

    private Time startTime;

    private Time endTime;

    private UserTableDTO userTableDTO;
}
