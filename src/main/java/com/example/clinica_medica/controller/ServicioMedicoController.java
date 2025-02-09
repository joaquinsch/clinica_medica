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

import com.example.clinica_medica.model.ServicioMedico;
import com.example.clinica_medica.services.ServicioMedicoService;

@RestController
@RequestMapping("/api/serviciosmedicos")
public class ServicioMedicoController {
	@Autowired
	private ServicioMedicoService servicioMedicoService;

	@PostMapping("/crear")
	public ResponseEntity<ServicioMedico> crearServicioMedico(@RequestBody ServicioMedico servicioMedico) {
		ServicioMedico servicioMedicoCreado = servicioMedicoService.guardarServicioMedico(servicioMedico);
		return new ResponseEntity<>(servicioMedicoCreado, HttpStatus.CREATED);
	}

	@GetMapping("/buscar/{id_servicio}")
	public ResponseEntity<ServicioMedico> buscarServicioMedico(@PathVariable Long id_servicio) {
		ServicioMedico servicioBuscado = servicioMedicoService.buscarServicioMedico(id_servicio);
		return new ResponseEntity<>(servicioBuscado, HttpStatus.OK);
	}

	@PutMapping("/editar")
	public ResponseEntity<ServicioMedico> editarServicioMedico(@RequestBody ServicioMedico servicioMedico) {
		ServicioMedico servicioBuscado = servicioMedicoService.editarServicioMedico(servicioMedico);
		return new ResponseEntity<>(servicioBuscado, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/eliminar/{id_servicio}")
	public ResponseEntity<?> eliminarServicioMedico(@PathVariable Long id_servicio) {
		servicioMedicoService.eliminarServicioMedico(id_servicio);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
