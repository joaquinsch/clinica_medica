package com.example.clinica_medica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.clinica_medica.model.ConsultaMedica;

import com.example.clinica_medica.services.ConsultaMedicaService;

@RestController
@RequestMapping("/api/consultasmedicas")
public class ConsultaMedicaController {
	
	@Autowired
	private ConsultaMedicaService consultaMedicaService;
	
	@PostMapping("/crear")
	public ResponseEntity<?> crearConsultaMedica(@RequestBody ConsultaMedica consulta) {
		try {
			consultaMedicaService.guardarConsultaMedica(consulta);
			return ResponseEntity.status(HttpStatus.CREATED).body(consulta);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo salió mal");
		}
	}
}
