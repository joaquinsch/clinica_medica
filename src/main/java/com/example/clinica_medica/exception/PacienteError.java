package com.example.clinica_medica.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;


@Getter
public class PacienteError {
	private final String mensaje;
	private final Throwable throwable;
	private final HttpStatus httpStatus;

	public PacienteError(String mensaje,Throwable throwable, HttpStatus httpStatus) {
		super();
		this.mensaje = mensaje;
		this.throwable = throwable;
		this.httpStatus = httpStatus;
	}

}
