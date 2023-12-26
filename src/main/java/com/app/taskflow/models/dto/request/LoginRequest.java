package com.app.taskflow.models.dto.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Setter
@Getter
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginRequest {

    private String username;
    private String password;

}
