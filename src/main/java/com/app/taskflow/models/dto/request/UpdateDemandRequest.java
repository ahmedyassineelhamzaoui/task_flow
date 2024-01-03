package com.app.taskflow.models.dto.request;

import com.app.taskflow.models.dto.TaskDTO;
import com.app.taskflow.models.dto.UserTableDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDemandRequest {

    private UUID id;

    private String operationType;

    @NotNull(message =" when you update an order to ACCEPTED you must specify the user who will do the task")
    private UserTableDTO assignedTo;

    @NotNull(message =" you should assign to user who want to change his task with  a new task")
    private TaskDTO newTask;

    @NotNull(message =" status is required")
    private String status;
}
