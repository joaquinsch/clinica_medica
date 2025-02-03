package com.example.clinica_medica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ServicioMedicoNoEncontradoError extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ServicioMedicoNoEncontradoError(String mensaje) {
		super(mensaje);
	}

	public ServicioMedicoNoEncontradoError(String mensaje, Throwable throwable) {
		super(mensaje, throwable);
	}

}
