package com.example.clinica_medica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PaqueteNoEncontradoError extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PaqueteNoEncontradoError(String mensaje) {
		super(mensaje);
	}
	
	public PaqueteNoEncontradoError(String mensaje, Throwable throwable) {
		super(mensaje, throwable);
	}


}
