package com.app.taskflow.common.exception;


import com.app.taskflow.common.exception.custom.*;
import com.app.taskflow.common.response.ResponseWithDetails;
import com.app.taskflow.common.response.ResponseWithoutDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.management.OperationsException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@RequiredArgsConstructor
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
        responseWithDetails.setDetails(errors);
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
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseWithDetails> handleDataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException) {
        responseWithDetails.setTimestamp(LocalDateTime.now());
        responseWithDetails.setMessage("Data integrity violation");
        responseWithDetails.setStatus("409");
        Map<String,Object> errors = new HashMap<>();
        String exceptionMessage = dataIntegrityViolationException.getRootCause().getMessage();
        errors.put("duplicate key", exceptionMessage);
        responseWithDetails.setDetails(errors);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseWithDetails);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseWithDetails> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        responseWithDetails.setTimestamp(LocalDateTime.now());
        responseWithDetails.setMessage("Illegal argument");
        responseWithDetails.setStatus("400");
        Map<String,Object> errors = new HashMap<>();
        errors.put("Error", illegalArgumentException.getMessage());
        responseWithDetails.setDetails(errors);
        return ResponseEntity.badRequest().body(responseWithDetails);
    }
    @ExceptionHandler(TaskTagsSizeException.class)
    public ResponseEntity<ResponseWithoutDetails> handleTaskTagsSizeException(TaskTagsSizeException taskTagsSizeException){
        responseWithoutDetails.setTimestamp(LocalDateTime.now());
        responseWithoutDetails.setMessage(taskTagsSizeException.getMessage());
        responseWithoutDetails.setStatus("400");
        return ResponseEntity.badRequest().body(responseWithoutDetails);
    }
    @ExceptionHandler(TaskTimeException.class)
    public ResponseEntity<ResponseWithoutDetails> handleTaskTimeException(TaskTimeException taskTimeException){
        responseWithoutDetails.setTimestamp(LocalDateTime.now());
        responseWithoutDetails.setMessage(taskTimeException.getMessage());
        responseWithoutDetails.setStatus("400");
        return ResponseEntity.badRequest().body(responseWithoutDetails);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseWithoutDetails> handleHttpMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException){
        responseWithoutDetails.setTimestamp(LocalDateTime.now());
        responseWithoutDetails.setMessage(httpMessageNotReadableException.getMessage());
        responseWithoutDetails.setStatus("400");
        return ResponseEntity.badRequest().body(responseWithoutDetails);
    }
    @ExceptionHandler(UserAssignTaskException.class)
    public ResponseEntity<ResponseWithoutDetails> handleUserAssignTaskException(UserAssignTaskException userAssignTaskException){
        responseWithoutDetails.setTimestamp(LocalDateTime.now());
        responseWithoutDetails.setMessage(userAssignTaskException.getMessage());
        responseWithoutDetails.setStatus("400");
        return ResponseEntity.badRequest().body(responseWithoutDetails);
    }
    @ExceptionHandler(OperationException.class)
    public ResponseEntity<ResponseWithoutDetails> handleOperationException(OperationException operationException){
        responseWithoutDetails.setTimestamp(LocalDateTime.now());
        responseWithoutDetails.setMessage(operationException.getMessage());
        responseWithoutDetails.setStatus("400");
        return ResponseEntity.badRequest().body(responseWithoutDetails);
    }
    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ResponseWithoutDetails> handleOrderException(OrderException orderException){
        responseWithoutDetails.setTimestamp(LocalDateTime.now());
        responseWithoutDetails.setMessage(orderException.getMessage());
        responseWithoutDetails.setStatus("400");
        return ResponseEntity.badRequest().body(responseWithoutDetails);
    }
}
