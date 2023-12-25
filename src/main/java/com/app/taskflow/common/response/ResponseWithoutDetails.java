package com.app.taskflow.common.response;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class ResponseWithoutDetails {

    private LocalDateTime timestamp;
    private String message;
    private String status;
}
