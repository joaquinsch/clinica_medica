package com.example.clinica_medica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.clinica_medica.model.PaqueteServicio;
import com.example.clinica_medica.services.PaqueteServicioService;

@RestController
@RequestMapping("/api/paquetesservicios")
public class PaqueteServicioController {

	@Autowired
	private PaqueteServicioService paqueteServicioService;

	@PostMapping("/crear")
	public ResponseEntity<?> crearPaqueteServicio(@RequestBody PaqueteServicio paqueteServicio) {
		try {
			PaqueteServicio nuevoPaquete = paqueteServicioService.guardarPaqueteServicio(paqueteServicio);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPaquete);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo salió mal");
		}
	}

}
