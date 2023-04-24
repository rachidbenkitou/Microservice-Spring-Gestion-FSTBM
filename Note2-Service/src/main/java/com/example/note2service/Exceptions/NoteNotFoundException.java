package com.example.note2service.Exceptions;

public class NoteNotFoundException extends  Exception{
    public NoteNotFoundException(String message) {
        super(message);
    }
}
