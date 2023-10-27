package com.caiobruno.helpdesk.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.caiobruno.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.caiobruno.helpdesk.services.exceptions.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler  {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandartError> objectNotFoundException(ObjectNotFoundException ex , HttpServletRequest request){	  
		StandartError error = new StandartError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value() , "Object not found", ex.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandartError> dataIntegrityViolationException(DataIntegrityViolationException ex , HttpServletRequest request){	  
		StandartError error = new StandartError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value() , "Violação  de dados", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandartError> validationErros(MethodArgumentNotValidException ex , HttpServletRequest request){	  
		ValidationError errors = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Validation error ", "Erro na validação do codigo", request.getRequestURI());
		
		for(FieldError x : ex.getBindingResult().getFieldErrors()) {
			errors.addErros(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		
	}
}
