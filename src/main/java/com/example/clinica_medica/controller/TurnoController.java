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

import com.example.clinica_medica.model.Turno;
import com.example.clinica_medica.services.TurnoService;

@RestController
@RequestMapping("/api/turnos")
public class TurnoController {
	/*
	 * Es necesario para agregar turnos a un medico. Se debe tener al medico creado,
	 * y luego crear el turno asociandolo al medico.
	 */

	@Autowired
	private TurnoService turnoService;

	@PostMapping("/crear")
	public ResponseEntity<Turno> crearTurno(@RequestBody Turno turno) {
		Turno turnoGuardado = turnoService.guardarTurno(turno);
		return new ResponseEntity<>(turnoGuardado, HttpStatus.CREATED);
	}

	@GetMapping("/buscar/{id_turno}")
	public ResponseEntity<Turno> buscarTurno(@PathVariable Long id_turno) {

		Turno turnoBuscado = turnoService.buscarTurno(id_turno);
		return new ResponseEntity<>(turnoBuscado, HttpStatus.OK);
	}

	@PutMapping("/editar")
	public ResponseEntity<Turno> editarTurno(@RequestBody Turno turno) {

		Turno turnoEditado = turnoService.editarTurno(turno);
		return new ResponseEntity<>(turnoEditado, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/eliminar/{id_turno}")
	public ResponseEntity<Turno> eliminarTurno(@PathVariable Long id_turno) {
		turnoService.eliminarTurno(id_turno);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
