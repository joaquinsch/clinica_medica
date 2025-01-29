package com.example.clinica_medica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PacienteNoEncontradoError extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PacienteNoEncontradoError(String mensaje) {
		super(mensaje);
	}
	
	public PacienteNoEncontradoError(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
	
	
}
