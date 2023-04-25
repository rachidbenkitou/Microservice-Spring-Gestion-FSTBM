package com.example.note2service.Exceptions.module;

import com.example.note2service.shared.ApiBasedException;
import org.springframework.http.HttpStatus;

public class ModuleRequestIsNull extends ApiBasedException {

    public ModuleRequestIsNull(String message) {
        super(message);
    }
    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.NO_CONTENT;
    }
}
