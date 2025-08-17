package com.pm.patientservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        //Gives you the BindingResult that holds all validation results.
        //Returns a List<FieldError>—one item per field-level validation failure (e.g., email, name)
        //Iterates through each FieldError. The lambda receives a single FieldError called error.
        //error.getField()-->The field name that failed validation (e.g., "email").
        //error.getDefaultMessage()-->Comes from the constraint’s message attribute (e.g., @NotNull(message="Registered date is required"))
        //errors.put(key, value)-->Adds an entry to your Map<String, String> errors, mapping field → message.

        return  ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex){

        log.warn("Email address already exist {}",ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("message","Email address Already Exist");
        return ResponseEntity.badRequest().body(errors);
    }
}
