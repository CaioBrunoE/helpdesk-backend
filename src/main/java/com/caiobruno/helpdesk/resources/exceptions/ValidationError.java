package com.caiobruno.helpdesk.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError {
	private static final long serialVersionUID = 1L;

	private List<Fieldmessage> erros = new ArrayList<>();

	public ValidationError() {
		super();
		
	}

	public ValidationError(Long timeStamp, Integer status, String error, String message, String path) {
		super(timeStamp, status, error, message, path);
		
	}

	public List<Fieldmessage> getErros() {
		return erros;
	}

	public void addErros( String fieldName, String message) {
		this.erros.add(new Fieldmessage(fieldName, message));
	}

}
