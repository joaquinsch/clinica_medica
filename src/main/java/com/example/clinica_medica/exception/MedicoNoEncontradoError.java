package com.example.clinica_medica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class MedicoNoEncontradoError extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MedicoNoEncontradoError(String mensaje) {
		super(mensaje);
	}
	public MedicoNoEncontradoError(String mensaje, Throwable throwable) {
		super(mensaje, throwable);
	}
}
