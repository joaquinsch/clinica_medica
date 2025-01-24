package com.example.clinica_medica.controller;

import java.util.NoSuchElementException;

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

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;
	// private ObjectMapper objectMapper;

	@PostMapping("/crear")
	public ResponseEntity<Paciente> crearPaciente(@RequestBody Paciente paciente) {
		try {
			pacienteService.guardarPaciente(paciente);
			return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/buscar/{id_paciente}")
	public ResponseEntity<?> buscarPaciente(@PathVariable Long id_paciente) {
		try {
			Paciente buscado = pacienteService.buscarPaciente(id_paciente);
			return ResponseEntity.status(HttpStatus.OK).body(buscado);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el paciente");
		}
	}

	@PutMapping("/editar")
	public ResponseEntity<?> editarPaciente(@RequestBody Paciente paciente) {
		try {
			Paciente aEditar = pacienteService.editarPaciente(paciente);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(aEditar);

		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el paciente");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo salió mal");
		}
	}

	@DeleteMapping("/eliminar/{id_paciente}")
	public ResponseEntity<?> eliminarPaciente(@PathVariable Long id_paciente) {
		try {
			pacienteService.eliminarPaciente(id_paciente);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Se ha eliminado");

		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El paciente no existe");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo salió mal");
		}
	}

}
