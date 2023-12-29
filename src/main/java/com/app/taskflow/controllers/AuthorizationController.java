package com.app.taskflow.controllers;

import com.app.taskflow.common.response.ResponseWithDetails;
import com.app.taskflow.services.facade.AuthenticationService;
import com.app.taskflow.services.facade.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthorizationController {

    private final UserService userService;
    private final ResponseWithDetails responseWithDetails;
    @GetMapping("users/{email}")
    public ResponseEntity<ResponseWithDetails> getUserByEmail(@PathVariable String email) {
        Map<String,Object> response = new HashMap<>();
        responseWithDetails.setTimestamp(LocalDateTime.now());
        responseWithDetails.setStatus("200");
        responseWithDetails.setMessage("User found");
        response.put("user",userService.findByEmail(email));
        responseWithDetails.setDetails(response);
        return ResponseEntity.ok(responseWithDetails);
    }
}
