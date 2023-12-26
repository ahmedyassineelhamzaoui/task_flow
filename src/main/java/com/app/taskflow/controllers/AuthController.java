package com.app.taskflow.controllers;

import com.app.taskflow.common.response.ResponseWithDetails;
import com.app.taskflow.common.response.ResponseWithoutDetails;
import com.app.taskflow.models.dto.UserDTO;
import com.app.taskflow.models.dto.request.LoginRequest;
import com.app.taskflow.models.entity.User;
import com.app.taskflow.services.facade.UserService;
import com.app.taskflow.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/rest/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final ResponseWithDetails responseWithDetails;
    private final ResponseWithoutDetails responseWithoutDetails;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<ResponseWithDetails> login(@RequestBody @Valid LoginRequest loginReq)  {

        Map<String,Object> response = new HashMap<>();
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));
            String username = authentication.getName();
            User user = new User(username,"");
            String token = jwtUtil.createToken(user);
            responseWithDetails.setTimestamp(LocalDateTime.now());
            responseWithDetails.setStatus("200");
            responseWithDetails.setMessage("Login Successful");
            response.put("user",user);
            response.put("token",token);

            responseWithDetails.setDetails(response);
            return ResponseEntity.ok(responseWithDetails);
        }catch (BadCredentialsException e){
            responseWithDetails.setTimestamp(LocalDateTime.now());
            responseWithDetails.setStatus("400");
            responseWithDetails.setMessage("Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseWithDetails);
        }catch (Exception e){
            responseWithDetails.setTimestamp(LocalDateTime.now());
            responseWithDetails.setStatus("400");
            responseWithDetails.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseWithDetails);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseWithDetails> register(@RequestBody @Valid UserDTO userDTO){

        Map<String,Object> response = new HashMap<>();
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        responseWithDetails.setTimestamp(LocalDateTime.now());
        responseWithDetails.setStatus("200");
        responseWithDetails.setMessage("Login Successful");
        response.put("user",userService.register(userDTO));
        responseWithDetails.setDetails(response);

        return ResponseEntity.ok(responseWithDetails);
    }
}
