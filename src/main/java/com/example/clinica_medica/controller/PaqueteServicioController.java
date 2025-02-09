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

import com.example.clinica_medica.model.PaqueteServicio;
import com.example.clinica_medica.services.PaqueteServicioService;

@RestController
@RequestMapping("/api/paquetesservicios")
public class PaqueteServicioController {

	@Autowired
	private PaqueteServicioService paqueteServicioService;

	@PostMapping("/crear")
	public ResponseEntity<PaqueteServicio> crearPaqueteServicio(@RequestBody PaqueteServicio paqueteServicio) {
		PaqueteServicio nuevoPaquete = paqueteServicioService.guardarPaqueteServicio(paqueteServicio);
		return new ResponseEntity<>(nuevoPaquete, HttpStatus.CREATED);

	}

	@GetMapping("/buscar/{id_paquete}")
	public ResponseEntity<PaqueteServicio> buscarPaqueteServicio(@PathVariable Long id_paquete) {

		PaqueteServicio paqueteBuscado = paqueteServicioService.buscarPaqueteServicio(id_paquete);
		return new ResponseEntity<>(paqueteBuscado, HttpStatus.OK);
	}

	@PutMapping("/editar")
	public ResponseEntity<PaqueteServicio> editarPaqueteServicio(@RequestBody PaqueteServicio paqueteServicio) {

		PaqueteServicio paqueteEditado = paqueteServicioService.editarPaqueteServicio(paqueteServicio);
		return new ResponseEntity<>(paqueteEditado, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/eliminar/{id_paquete}")
	public ResponseEntity<PaqueteServicio> eliminarPaqueteServicio(@PathVariable Long id_paquete) {
		paqueteServicioService.eliminarPaqueteServicio(id_paquete);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
