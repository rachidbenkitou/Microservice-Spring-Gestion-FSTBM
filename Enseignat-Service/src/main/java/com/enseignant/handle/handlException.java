package com.enseignant.handle;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.enseignant.exeption.DepartementAlreadyExist;
import com.enseignant.exeption.DepartementNotFoundException;



@ControllerAdvice
public class handlException {
	
	@ExceptionHandler(DepartementNotFoundException.class)
	public ResponseEntity<MessageError> handleNotFoundDeparement(DepartementNotFoundException   ex, WebRequest request){
		
		MessageError errorB = new MessageError();
		errorB.setTimestamp(new Date());
		errorB.setStatus(HttpStatus.BAD_REQUEST.value());
		errorB.setError(HttpStatus.BAD_REQUEST.name());
		errorB.setMessage(ex.getMessage());
		
		return new ResponseEntity<MessageError>(errorB,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(DepartementAlreadyExist.class)
	public ResponseEntity<MessageError> handlAlreadyExistDepartement(DepartementAlreadyExist   ex, WebRequest request){
		
		MessageError errorB = new MessageError();
		errorB.setTimestamp(new Date());
		errorB.setStatus(HttpStatus.BAD_REQUEST.value());
		errorB.setError(HttpStatus.BAD_REQUEST.name());
		errorB.setMessage(ex.getMessage());
		
		return new ResponseEntity<MessageError>(errorB,HttpStatus.BAD_REQUEST);
		
	}

}
