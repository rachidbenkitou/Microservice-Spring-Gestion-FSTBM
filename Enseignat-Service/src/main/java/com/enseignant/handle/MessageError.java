package com.enseignant.handle;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageError {
	
	private int status;
	private String error;
	private String message;
	private Date timestamp;
	
}
