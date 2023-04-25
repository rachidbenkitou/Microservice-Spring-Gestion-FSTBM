package com.example.note2service.Exceptions.module;

import com.example.note2service.shared.ApiBasedException;
import org.springframework.http.HttpStatus;

public class ModuleAlreadyExistsException extends ApiBasedException {

    public ModuleAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }
}
