package com.app.taskflow.common.exception;


import com.app.taskflow.common.response.ResponseWithDetails;
import com.app.taskflow.common.response.ResponseWithoutDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GlobalExceptionHandler {

    private final ResponseWithoutDetails responseWithoutDetails;
    private final ResponseWithDetails responseWithDetails;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseWithDetails> handValidationException(MethodArgumentNotValidException e){
        responseWithDetails.setTimestamp(LocalDateTime.now());
        responseWithDetails.setStatus("422");
        responseWithDetails.setMessage("validation error");

        Map<String,Object> errors = e.getBindingResult()
                                                        .getFieldErrors()
                                                        .stream()
                                                        .collect(
                                                                HashMap::new,
                                                                (map,fieldError) -> map.put(fieldError.getField(),fieldError.getDefaultMessage()),
                                                                (map,map2) -> map.putAll(map2)
                                                        );
        return ResponseEntity.badRequest().body(responseWithDetails);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResponseWithDetails> handleNoSuchElementException(NoSuchElementException e){
        responseWithDetails.setTimestamp(LocalDateTime.now());
        responseWithDetails.setMessage("no such element ");
        responseWithDetails.setStatus("404");
        Map<String,Object> error = new HashMap<>();
        error.put("Error",e.getMessage());
        responseWithDetails.setDetails(error);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseWithDetails);
    }
}
