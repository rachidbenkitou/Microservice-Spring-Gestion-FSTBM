package com.example.etudaintinscriptionfiliereservice.exceptions;

public class EntityAlreadyExistException extends RuntimeException{
    public EntityAlreadyExistException(String message) {
        super(message);
    }
}
