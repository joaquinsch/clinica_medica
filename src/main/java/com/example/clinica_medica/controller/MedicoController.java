package com.example.clinica_medica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.clinica_medica.model.Medico;

import com.example.clinica_medica.services.MedicoService;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoService medicoService;
	
	@PostMapping("/crear")
	public ResponseEntity<?> crearPaciente(@RequestBody Medico medico) {
		try {
			medicoService.guardarMedico(medico);
			return ResponseEntity.status(HttpStatus.CREATED).body(medico);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo salió mal");
		}
	}
	
	

}
