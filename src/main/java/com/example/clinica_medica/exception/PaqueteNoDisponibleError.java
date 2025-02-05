package com.example.clinica_medica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class PaqueteNoDisponibleError extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PaqueteNoDisponibleError(String mensaje) {
		super(mensaje);
	}

	public PaqueteNoDisponibleError(String mensaje, Throwable throwable) {
		super(mensaje, throwable);
	}
}
