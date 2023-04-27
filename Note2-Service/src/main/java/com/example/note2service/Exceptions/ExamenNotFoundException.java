package com.example.note2service.Exceptions;

import com.example.note2service.shared.ApiBasedException;
import org.springframework.http.HttpStatus;

public class ExamenNotFoundException extends ApiBasedException {

    public ExamenNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }
}
