package com.example.clinica_medica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.clinica_medica.model.Paciente;
import com.example.clinica_medica.services.PacienteService;


@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;
	//private ObjectMapper objectMapper;

	@PostMapping("/crear")
	public ResponseEntity<String> crearPaciente(@RequestBody Paciente paciente) {
		try {
			pacienteService.guardarPaciente(paciente);
			return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo salió mal");
		}
	}

}
