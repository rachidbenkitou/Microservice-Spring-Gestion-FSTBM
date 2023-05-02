package com.example.securityservice.advice;

import com.example.securityservice.exceptions.EntityAlreadyExistException;
import com.example.securityservice.exceptions.EntityNotFoundException;
import com.example.securityservice.exceptions.InvalidEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleInvalidArguments(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            errors.put(error.getField(),error.getDefaultMessage());
        });
        return  errors;
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public Map<String,String> handleNotFoundException(EntityNotFoundException ex, WebRequest request){
        Map<String,String> errors = new HashMap<>();
        errors.put("message",ex.getMessage());
        errors.put("description", request.getDescription(false));
        errors.put("status", HttpStatus.NOT_FOUND.toString());
        return errors;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InvalidEntityException.class)
    public Map<String,String> handleInvalidEntityException(InvalidEntityException ex, WebRequest request){
        Map<String,String> errors = new HashMap<>();
        errors.put("message",ex.getMessage());
        errors.put("description", request.getDescription(false));
        errors.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return errors;
    }
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EntityAlreadyExistException.class)
    public Map<String,String> handleEntityAlreadyExistException(EntityAlreadyExistException ex, WebRequest request){
        Map<String,String> errors = new HashMap<>();
        errors.put("message",ex.getMessage());
        errors.put("description", request.getDescription(false));
        errors.put("status", HttpStatus.CONFLICT.toString());
        return errors;
    }
}

