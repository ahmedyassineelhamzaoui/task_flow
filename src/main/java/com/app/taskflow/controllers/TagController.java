package com.app.taskflow.controllers;

import com.app.taskflow.common.response.ResponseWithDetails;
import com.app.taskflow.common.response.ResponseWithoutDetails;
import com.app.taskflow.models.dto.TagDTO;
import com.app.taskflow.services.facade.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TagController {

    private final TagService tagService;
    private final ResponseWithDetails responseWithDetails;
    private final ResponseWithoutDetails responseWithoutDetails;

    @PostMapping("tag")
    public ResponseEntity<ResponseWithDetails> addTag(@RequestBody @Valid TagDTO tagDTO){
        Map<String,Object> response = new HashMap<>();
        response.put("tag",tagService.addTag(tagDTO));
        responseWithDetails.setTimestamp(LocalDateTime.now());
        responseWithDetails.setStatus("200");
        responseWithDetails.setMessage("tag added successfully");
        responseWithDetails.setDetails(response);
        return ResponseEntity.ok(responseWithDetails);
    }

    @DeleteMapping("/tags/{id}")
    public ResponseEntity<ResponseWithoutDetails> deleteTag(@PathVariable UUID id){
        tagService.deleteTag(id);
        responseWithoutDetails.setTimestamp(LocalDateTime.now());
        responseWithoutDetails.setStatus("200");
        responseWithoutDetails.setMessage("tag deleted successfully");
        return ResponseEntity.ok(responseWithoutDetails);
    }


}
