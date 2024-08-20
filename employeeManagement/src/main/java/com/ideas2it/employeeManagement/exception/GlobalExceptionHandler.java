package com.ideas2it.employeeManagement.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException elementException){
        return new ResponseEntity<>(elementException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<String> handleEmployeeException(EmployeeException employeeException, HttpServletRequest request){
        return new ResponseEntity<>(employeeException.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException notValidException, HttpServletRequest request){
        Map<String, String> errorMap = new HashMap<>();
        for (FieldError error : notValidException.getFieldErrors()) {
            errorMap.put(error.getField(), error.getDefaultMessage());
        }
        errorMap.put("url", String.valueOf(request.getRequestURL()));
        return new ResponseEntity<>(errorMap, HttpStatus.CONFLICT);
    }
}