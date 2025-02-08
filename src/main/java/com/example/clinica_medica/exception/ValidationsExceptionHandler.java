package com.example.clinica_medica.exception;

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
}
