package com.example.clinica_medica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = { PacienteNoEncontradoError.class })
	public ResponseEntity<PacienteError> handlePacienteNoEncontradoError(PacienteNoEncontradoError e) {

		PacienteError pacienteError = new 
				PacienteError(e.getMessage(), e.getCause(), HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(pacienteError, HttpStatus.NOT_FOUND);
	}

}
