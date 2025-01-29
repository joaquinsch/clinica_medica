package com.example.clinica_medica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ConsultaMedicaConServicioYPaqueteError extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConsultaMedicaConServicioYPaqueteError(String mensaje) {
		super(mensaje);
	}
	public ConsultaMedicaConServicioYPaqueteError(String mensaje, Throwable throwable) {
		super(mensaje, throwable);
	}
}
