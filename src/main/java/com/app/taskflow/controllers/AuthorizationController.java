package com.app.taskflow.controllers;

import com.app.taskflow.common.response.ResponseWithDetails;
import com.app.taskflow.common.response.ResponseWithoutDetails;
import com.app.taskflow.models.dto.request.UserRolesRequest;
import com.app.taskflow.services.facade.AuthenticationService;
import com.app.taskflow.services.facade.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthorizationController {

    private final UserService userService;
    private final ResponseWithDetails responseWithDetails;
    private final ResponseWithoutDetails responseWithoutDetails;
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
    @PostMapping("users/addRoles")
    public ResponseEntity<ResponseWithoutDetails> addRolesToUser(@RequestBody @Valid UserRolesRequest request ){
        userService.AddRolesToUser(request.getEmail(),request.getRoles());
        responseWithoutDetails.setTimestamp(LocalDateTime.now());
        responseWithoutDetails.setStatus("200");
        responseWithoutDetails.setMessage("Role added to user");
        return ResponseEntity.ok(responseWithoutDetails);
    }
}
