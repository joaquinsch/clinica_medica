package com.example.clinica_medica.exception;

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
