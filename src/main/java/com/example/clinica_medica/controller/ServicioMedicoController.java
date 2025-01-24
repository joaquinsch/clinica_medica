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

import com.example.clinica_medica.model.ServicioMedico;
import com.example.clinica_medica.services.ServicioMedicoService;

@RestController
@RequestMapping("/api/serviciosmedicos")
public class ServicioMedicoController {
	@Autowired
	private ServicioMedicoService servicioMedicoService;

	// Crear un nuevo servicio médico
	@PostMapping("/crear")
	public ResponseEntity<?> crearServicioMedico(@RequestBody ServicioMedico servicioMedico) {
		try {
			servicioMedicoService.guardarServicioMedico(servicioMedico);
			return ResponseEntity.status(HttpStatus.CREATED).body(servicioMedico);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo salió mal");
		}
	}

	@GetMapping("/buscar/{id_servicio}")
	public ResponseEntity<?> buscarServicioMedico(@PathVariable Long id_servicio) {
		try {
			ServicioMedico servicioBuscado = servicioMedicoService.buscarServicioMedico(id_servicio);
			return ResponseEntity.status(HttpStatus.OK).body(servicioBuscado);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el servicio médico");
		}
	}


	@PutMapping("/editar")
	public ResponseEntity<?> editarServicioMedico(@RequestBody ServicioMedico servicioMedico) {
		try {
			ServicioMedico servicioEditado = servicioMedicoService.editarServicioMedico(servicioMedico);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicioEditado);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el servicio médico");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo salió mal");
		}
	}

	@DeleteMapping("/eliminar/{id_servicio}")
	public ResponseEntity<?> eliminarServicioMedico(@PathVariable Long id_servicio) {
		try {
			servicioMedicoService.eliminarServicioMedico(id_servicio);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Se ha eliminado el servicio médico");
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El servicio médico no existe");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo salió mal");
		}
	}
	

}
