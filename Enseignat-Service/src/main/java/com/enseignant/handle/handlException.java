package com.enseignant.handle;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.enseignant.exeption.CourNoteFoundException;
import com.enseignant.exeption.DepartementAlreadyExist;
import com.enseignant.exeption.DepartementNotFoundException;
import com.enseignant.exeption.EnseignantNotFound;
import com.enseignant.exeption.ModuleAlrealyHasCour;



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
	
	@ExceptionHandler(ModuleAlrealyHasCour.class)
	public ResponseEntity<MessageError> handlAlreadyModuleHasCour(ModuleAlrealyHasCour   ex, WebRequest request){
		
		MessageError errorB = new MessageError();
		errorB.setTimestamp(new Date());
		errorB.setStatus(HttpStatus.BAD_REQUEST.value());
		errorB.setError(HttpStatus.BAD_REQUEST.name());
		errorB.setMessage(ex.getMessage());
		
		return new ResponseEntity<MessageError>(errorB,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(CourNoteFoundException.class)
	public ResponseEntity<MessageError> handlCourNotFound(CourNoteFoundException   ex, WebRequest request){
		
		MessageError errorB = new MessageError();
		errorB.setTimestamp(new Date());
		errorB.setStatus(HttpStatus.BAD_REQUEST.value());
		errorB.setError(HttpStatus.BAD_REQUEST.name());
		errorB.setMessage(ex.getMessage());
		
		return new ResponseEntity<MessageError>(errorB,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(EnseignantNotFound.class)
	public ResponseEntity<MessageError> handlEnseignantNotFound(EnseignantNotFound   ex, WebRequest request){
		
		MessageError errorB = new MessageError();
		errorB.setTimestamp(new Date());
		errorB.setStatus(HttpStatus.BAD_REQUEST.value());
		errorB.setError(HttpStatus.BAD_REQUEST.name());
		errorB.setMessage(ex.getMessage());
		
		return new ResponseEntity<MessageError>(errorB,HttpStatus.BAD_REQUEST);
		
	}

}
