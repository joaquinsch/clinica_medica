package com.example.clinica_medica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.clinica_medica.model.Paciente;
import com.example.clinica_medica.services.PacienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;

	@PostMapping("/crear")
	public ResponseEntity<Paciente> crearPaciente(@Valid @RequestBody Paciente paciente) {
		Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);
		return new ResponseEntity<>(pacienteGuardado, HttpStatus.CREATED);

	}

	@GetMapping("/buscar/{id_paciente}")
	public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id_paciente) {
		Paciente buscado = pacienteService.buscarPaciente(id_paciente);
		return new ResponseEntity<>(buscado, HttpStatus.OK);

	}

	@PutMapping("/editar")
	public ResponseEntity<Paciente> editarPaciente(@RequestBody Paciente paciente) {
		Paciente aEditar = pacienteService.editarPaciente(paciente);
		return new ResponseEntity<>(aEditar, HttpStatus.ACCEPTED);
	}

	/*
	 * Si lo elimina devuelve NOCONTENT.
	 */
	@DeleteMapping("/eliminar/{id_paciente}")
	public ResponseEntity<Paciente> eliminarPaciente(@PathVariable Long id_paciente) {
		pacienteService.eliminarPaciente(id_paciente);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
