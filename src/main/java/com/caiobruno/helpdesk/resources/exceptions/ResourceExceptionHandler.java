package com.caiobruno.helpdesk.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.caiobruno.helpdesk.services.exceptions.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler  {

	@ExceptionHandler
	public ResponseEntity<StandartError> objectNotFoundException(ObjectNotFoundException ex , HttpServletRequest request){	  
		StandartError error = new StandartError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value() , "Object not found", ex.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	
}
