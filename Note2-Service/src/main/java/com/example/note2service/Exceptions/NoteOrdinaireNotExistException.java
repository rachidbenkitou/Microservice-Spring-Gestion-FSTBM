package com.example.note2service.Exceptions;

import com.example.note2service.shared.ApiBasedException;
import org.springframework.http.HttpStatus;

public class NoteOrdinaireNotExistException extends ApiBasedException {


    public NoteOrdinaireNotExistException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }
}
