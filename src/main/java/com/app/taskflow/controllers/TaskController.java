package com.app.taskflow.controllers;

import com.app.taskflow.common.response.ResponseWithDetails;
import com.app.taskflow.common.response.ResponseWithoutDetails;
import com.app.taskflow.models.dto.TaskDTO;
import com.app.taskflow.services.facade.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TaskController {

    private final TaskService taskService;
    private final ResponseWithoutDetails responseWithoutDetails;

    @PostMapping("task")
    public ResponseEntity<ResponseWithoutDetails> addTask(@RequestBody @Valid TaskDTO taskDTO){
        taskService.addTask(taskDTO);
        responseWithoutDetails.setTimestamp(LocalDateTime.now());
        responseWithoutDetails.setStatus("200");
        responseWithoutDetails.setMessage("task added successfully");

        return ResponseEntity.ok(responseWithoutDetails);
    }

    @PutMapping("task/{id}")
    public  ResponseEntity<ResponseWithoutDetails> assignTaskToUser(@PathVariable UUID id, @RequestParam UUID userId){
        responseWithoutDetails.setTimestamp(LocalDateTime.now());
        responseWithoutDetails.setStatus("200");
        responseWithoutDetails.setMessage("task assigned successfully");
        return ResponseEntity.ok(responseWithoutDetails);
    }
}
