package com.prjt.noteservice.Exceptions;

public class NoteNotFoundException extends  Exception{
    public NoteNotFoundException(String message) {
        super(message);
    }
}
