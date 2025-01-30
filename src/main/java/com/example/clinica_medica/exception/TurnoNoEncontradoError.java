package com.example.clinica_medica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TurnoNoEncontradoError extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TurnoNoEncontradoError(String mensaje) {
		super(mensaje);
	}

	public TurnoNoEncontradoError(String mensaje, Throwable throwable) {
		super(mensaje, throwable);
	}
}
