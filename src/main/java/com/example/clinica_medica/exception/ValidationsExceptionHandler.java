package com.example.clinica_medica.exception;

import java.time.format.DateTimeParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ValidationsExceptionHandler {

	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
		ApiError apiError = new ApiError(e.getBindingResult().getFieldError().getDefaultMessage(), e.getCause(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = {DateTimeParseException.class})
	public ResponseEntity<ApiError> handleDateTimeParseException(DateTimeParseException e){
		String mensaje = "Debes ingresar la fecha y la hora con los formatos: 'dd/mm/yyyy' y 'hh:mm' respectivamente";
		ApiError apiError = new ApiError(mensaje, e.getCause(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
	}
	
}
