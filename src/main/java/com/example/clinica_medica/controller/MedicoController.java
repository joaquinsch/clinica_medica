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

import com.example.clinica_medica.model.Medico;

import com.example.clinica_medica.services.MedicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

	@Autowired
	private MedicoService medicoService;

	@PostMapping("/crear")
	public ResponseEntity<?> crearMedico(@Valid @RequestBody Medico medico) {
		try {
			medicoService.guardarMedico(medico);
			return ResponseEntity.status(HttpStatus.CREATED).body(medico);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo salió mal");
		}
	}

	@GetMapping("/buscar/{id_medico}")
	public ResponseEntity<?> buscarMedico(@PathVariable Long id_medico) {
		try {
			Medico buscado = medicoService.buscarMedico(id_medico);
			return ResponseEntity.status(HttpStatus.OK).body(buscado);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el médico");
		}
	}

	@PutMapping("/editar")
	public ResponseEntity<?> editarMedico(@Valid @RequestBody Medico medico) {
		try {
			Medico aEditar = medicoService.editarMedico(medico);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(aEditar);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el médico");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo salió mal");
		}
	}

	@DeleteMapping("/eliminar/{id_medico}")
	public ResponseEntity<?> eliminarMedico(@PathVariable Long id_medico){
		try {
			medicoService.eliminarMedico(id_medico);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Se ha eliminado");
		} catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el médico");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo salió mal");
		}
	}

}
