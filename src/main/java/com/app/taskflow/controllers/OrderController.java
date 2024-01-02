package com.app.taskflow.controllers;

import com.app.taskflow.common.response.ResponseWithDetails;
import com.app.taskflow.common.response.ResponseWithoutDetails;
import com.app.taskflow.models.dto.DemandDTO;
import com.app.taskflow.services.facade.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OrderController {
    private final OrderService orderService;
    private final ResponseWithoutDetails responseWithoutDetails;
    private final ResponseWithDetails responseWithDetails;

    @PostMapping("/demand")
    public ResponseEntity<ResponseWithoutDetails> addOrder(@RequestBody @Valid DemandDTO demandDTO)  {
        orderService.addOrder(demandDTO);
        responseWithoutDetails.setTimestamp(LocalDateTime.now());
        responseWithoutDetails.setStatus("200");
        responseWithoutDetails.setMessage("Order added successfully");
        return ResponseEntity.ok(responseWithoutDetails);
    }

}
