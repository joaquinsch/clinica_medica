package com.example.clinica_medica.exception;

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
