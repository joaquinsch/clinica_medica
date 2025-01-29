package com.example.clinica_medica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ConsultaMedicaNoEncontradaError extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConsultaMedicaNoEncontradaError(String mensaje) {
		super(mensaje);
	}

	public ConsultaMedicaNoEncontradaError(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
}
