package com.smart_traffic.exceptions;

import com.smart_traffic.exceptions.commons.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResourceExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> notFoundError(NotFoundException ex, HttpServletRequest request) {
        ApiError error = ApiError.createGenericError(LocalDateTime.now(), NotFoundException.DEFAULT_STATUS, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(DateInvalidException.class)
    public ResponseEntity<ApiError> dateInvalidError(DateInvalidException ex, HttpServletRequest request) {
        ApiError error = ApiError.createGenericError(LocalDateTime.now(), DateInvalidException.DEFAULT_STATUS, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ApiError> authorizationError(AuthorizationException ex, HttpServletRequest request) {
        ApiError error = ApiError.createGenericError(LocalDateTime.now(), AuthorizationException.DEFAULT_STATUS, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(ResourceCannotCreateException.class)
    public ResponseEntity<ApiError> authorizationError(ResourceCannotCreateException ex, HttpServletRequest request) {
        ApiError error = ApiError.createGenericError(LocalDateTime.now(), ResourceCannotCreateException.DEFAULT_STATUS, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<ApiError> tokenError(TokenException ex, HttpServletRequest request) {
        ApiError error = ApiError.createGenericError(LocalDateTime.now(), TokenException.DEFAULT_STATUS, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> beanValidationError(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        ValidationError error = ValidationError.createValidationError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                ValidationError.DEFAULT_MESSAGE, request.getRequestURI(), errors);

        return ResponseEntity.status(error.getStatus()).body(error);
    }
}
