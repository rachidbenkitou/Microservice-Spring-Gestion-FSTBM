package com.example.securityservice.exceptions;

public class IncorrectPasswordException extends RuntimeException{
        public IncorrectPasswordException(String message) {
            super(message);
        }
}
