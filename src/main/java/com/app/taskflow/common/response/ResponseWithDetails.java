package com.app.taskflow.common.response;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Component
@Data
public class ResponseWithDetails {

    private LocalDateTime timestamp;
    private String message;
    private String status;
    private Map<String,Object> details;
}
