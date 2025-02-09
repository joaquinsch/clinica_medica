package com.example.clinica_medica.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ApiError {
	private final String mensaje;
	private final HttpStatus httpStatus;

	public ApiError(String mensaje, HttpStatus httpStatus) {
		super();
		this.mensaje = mensaje;
		this.httpStatus = httpStatus;
	}

}
