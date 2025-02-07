package com.example.clinica_medica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = { PacienteNoEncontradoError.class })
	public ResponseEntity<ApiError> handlePacienteNoEncontradoError(PacienteNoEncontradoError e) {
		ApiError apiError = new ApiError(e.getMessage(), e.getCause(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { ConsultaMedicaNoEncontradaError.class })
	public ResponseEntity<ApiError> handleConsultaMedicaNoEncontradaError(ConsultaMedicaNoEncontradaError e) {
		ApiError apiError = new ApiError(e.getMessage(), e.getCause(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(value = { TurnoNoDisponibleError.class })
	public ResponseEntity<ApiError> handleConsultaMedicaNoEncontradaError(TurnoNoDisponibleError e) {
		ApiError apiError = new ApiError(e.getMessage(), e.getCause(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(value = { ConsultaMedicaConServicioYPaqueteError.class })
	public ResponseEntity<ApiError> handleConsultaMedicaConServicioYPaqueteError(
			ConsultaMedicaConServicioYPaqueteError e) {
		ApiError apiError = new ApiError(e.getMessage(), e.getCause(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(value = { TurnoNoEncontradoError.class })
	public ResponseEntity<ApiError> handleConsultaMedicaConServicioYPaqueteError(TurnoNoEncontradoError e) {
		ApiError apiError = new ApiError(e.getMessage(), e.getCause(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(value = { PaqueteNoEncontradoError.class })
	public ResponseEntity<ApiError> handlePaqueteNoEncontradoError(PaqueteNoEncontradoError e) {
		ApiError apiError = new ApiError(e.getMessage(), e.getCause(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = { ServicioMedicoNoEncontradoError.class })
	public ResponseEntity<ApiError> handleServicioMedicoNoEncontradoError(ServicioMedicoNoEncontradoError e) {
		ApiError apiError = new ApiError(e.getMessage(), e.getCause(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = { PaqueteNoDisponibleError.class })
	public ResponseEntity<ApiError> handlePaqueteNoDisponibleError(PaqueteNoDisponibleError e) {
		ApiError apiError = new ApiError(e.getMessage(), e.getCause(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = { MedicoNoEncontradoError.class })
	public ResponseEntity<ApiError> handleMedicoNoEncontradoError(MedicoNoEncontradoError e) {
		ApiError apiError = new ApiError(e.getMessage(), e.getCause(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
	}

}
