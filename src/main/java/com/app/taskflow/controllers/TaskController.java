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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TaskController {

    private final TaskService taskService;
    private final ResponseWithoutDetails responseWithoutDetails;
    private final ResponseWithDetails responseWithDetails;


    @GetMapping("tasks")
    public ResponseEntity<ResponseWithDetails> getAllTasks(){
        Map<String,Object> response = new HashMap<>();
        responseWithDetails.setTimestamp(LocalDateTime.now());
        responseWithDetails.setStatus("200");
        responseWithDetails.setMessage("tasks retrieved successfully");
        response.put("tasks",taskService.getAllTasks());
        responseWithDetails.setDetails(response);
        return ResponseEntity.ok(responseWithDetails);
    }
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
        taskService.assignTaskToUser(id,userId);
        responseWithoutDetails.setTimestamp(LocalDateTime.now());
        responseWithoutDetails.setStatus("200");
        responseWithoutDetails.setMessage("task assigned successfully");
        return ResponseEntity.ok(responseWithoutDetails);
    }
    @PutMapping("task/{id}/status")
    public  ResponseEntity<ResponseWithoutDetails> changeTaskStatus(@PathVariable UUID id, @RequestParam String status){
        taskService.changeTaskStatus(id,status);
        responseWithoutDetails.setTimestamp(LocalDateTime.now());
        responseWithoutDetails.setStatus("200");
        responseWithoutDetails.setMessage("task status changed successfully");
        return ResponseEntity.ok(responseWithoutDetails);
    }
}
