package com.example.clinica_medica.exception;

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
