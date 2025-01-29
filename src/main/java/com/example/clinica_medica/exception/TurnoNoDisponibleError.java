package com.example.clinica_medica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TurnoNoDisponibleError extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TurnoNoDisponibleError(String mensaje) {
		super(mensaje);
	}

	public TurnoNoDisponibleError(String mensaje, Throwable throwable) {
		super(mensaje, throwable);
	}

}
