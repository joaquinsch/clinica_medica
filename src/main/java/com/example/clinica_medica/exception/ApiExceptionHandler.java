package com.example.clinica_medica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = { PacienteNoEncontradoError.class })
	public ResponseEntity<ApiError> handlePacienteNoEncontradoError(PacienteNoEncontradoError e) {

		ApiError pacienteError = new ApiError(e.getMessage(), e.getCause(), HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(pacienteError, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { ConsultaMedicaNoEncontradaError.class })
	public ResponseEntity<ApiError> handleConsultaMedicaNoEncontradaError(ConsultaMedicaNoEncontradaError e) {
		ApiError consultaMedicaError = new ApiError(e.getMessage(), e.getCause(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(consultaMedicaError, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(value = { TurnoNoDisponibleError.class })
	public ResponseEntity<ApiError> handleConsultaMedicaNoEncontradaError(TurnoNoDisponibleError e) {
		ApiError consultaMedicaError = new ApiError(e.getMessage(), e.getCause(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(consultaMedicaError, HttpStatus.BAD_REQUEST);

	}

}
