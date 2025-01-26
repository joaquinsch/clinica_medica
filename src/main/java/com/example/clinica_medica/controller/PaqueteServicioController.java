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
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo salió mal");
		}
	}

	@GetMapping("/buscar/{id_paquete}")
	public ResponseEntity<?> buscarPaqueteServicio(@PathVariable Long id_paquete) {
		try {
			PaqueteServicio paqueteBuscado = paqueteServicioService.buscarPaqueteServicio(id_paquete);
			return ResponseEntity.status(HttpStatus.OK).body(paqueteBuscado);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el paquete");
		}
	}

	@PutMapping("/editar")
	public ResponseEntity<?> editarPaqueteServicio(@RequestBody PaqueteServicio paqueteServicio) {
		try {
			PaqueteServicio paqueteEditado = paqueteServicioService.editarPaqueteServicio(paqueteServicio);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(paqueteEditado);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el paquete");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo salió mal");
		}
	}

	@DeleteMapping("/eliminar/{id_paquete}")
	public ResponseEntity<?> eliminarPaqueteServicio(@PathVariable Long id_paquete) {
		try {
			paqueteServicioService.eliminarPaqueteServicio(id_paquete);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Paquete eliminado correctamente");
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El paquete no existe");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo salió mal");
		}
	}
}
