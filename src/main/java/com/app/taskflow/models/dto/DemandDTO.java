package com.app.taskflow.models.dto;

import com.app.taskflow.enums.OperationType;
import com.app.taskflow.enums.OrderStatus;
import com.app.taskflow.models.entity.Task;
import com.app.taskflow.models.entity.UserTable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemandDTO {

    private UUID id;

    @NotBlank(message ="operation type is required ")
    private String operationType;

    private UserTableDTO demandBy;

    @NotNull(message =" task that you want to change or delete is required")
    private TaskDTO task;

    private String status = "PENDING";
}
