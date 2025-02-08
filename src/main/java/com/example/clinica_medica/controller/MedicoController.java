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

import com.example.clinica_medica.model.Medico;

import com.example.clinica_medica.services.MedicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

	@Autowired
	private MedicoService medicoService;

	@PostMapping("/crear")
	public ResponseEntity<Medico> crearMedico(@Valid @RequestBody Medico medico) {
		Medico medicoGuardado = medicoService.guardarMedico(medico);
		return new ResponseEntity<>(medicoGuardado, HttpStatus.CREATED);

	}

	@GetMapping("/buscar/{id_medico}")
	public ResponseEntity<Medico> buscarMedico(@PathVariable Long id_medico) {
		Medico buscado = medicoService.buscarMedico(id_medico);
		return new ResponseEntity<>(buscado, HttpStatus.OK);
	}

	@PutMapping("/editar")
	public ResponseEntity<Medico> editarMedico(@Valid @RequestBody Medico medico) {
		Medico aEditar = medicoService.editarMedico(medico);
		return new ResponseEntity<>(aEditar, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/eliminar/{id_medico}")
	public ResponseEntity<Medico> eliminarMedico(@PathVariable Long id_medico) {
		medicoService.eliminarMedico(id_medico);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
