package com.example.note2service.Exceptions.module;

import com.example.note2service.shared.ApiBasedException;
import org.springframework.http.HttpStatus;

public class ModuleListIsEmptyException extends ApiBasedException {
    public ModuleListIsEmptyException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }
}
