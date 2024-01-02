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
import java.util.HashMap;
import java.util.Map;

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
    @GetMapping("/demands")
    public  ResponseEntity<ResponseWithDetails> getAllDemands(){
        Map<String,Object> response = new HashMap<>();
        response.put("demands",orderService.getAllOrders());
        responseWithDetails.setTimestamp(LocalDateTime.now());
        responseWithDetails.setStatus("200");
        responseWithDetails.setMessage("All demands");
        responseWithDetails.setDetails(response);
        return ResponseEntity.ok(responseWithDetails);
    }

}
