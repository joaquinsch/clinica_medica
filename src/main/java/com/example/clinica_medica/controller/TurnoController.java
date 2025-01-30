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

import com.example.clinica_medica.model.Turno;
import com.example.clinica_medica.services.TurnoService;

@RestController
@RequestMapping("/api/turnos")
public class TurnoController {
	/*
	 * Es necesario para
	 * agregar turnos a un medico.
	 * Se debe tener al medico creado, y luego crear el turno
	 * asociandolo al medico.
	 */

	@Autowired
    private TurnoService turnoService;


    @PostMapping("/crear")
    public ResponseEntity<Turno> crearTurno(@RequestBody Turno turno) {
    	Turno turnoGuardado = turnoService.guardarTurno(turno);
    	return new ResponseEntity<>(turnoGuardado,HttpStatus.CREATED);
    }
   
    @GetMapping("/buscar/{id_turno}")
    public ResponseEntity<?> buscarTurno(@PathVariable Long id_turno) {
        try {
            Turno turnoBuscado = turnoService.buscarTurno(id_turno);
            return ResponseEntity.status(HttpStatus.OK).body(turnoBuscado);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el turno");
        }
    }
    
    @PutMapping("/editar")
    public ResponseEntity<?> editarTurno(@RequestBody Turno turno) {
        try {
            Turno turnoEditado = turnoService.editarMedico(turno);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(turnoEditado);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el turno");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo salió mal");
        }
    }
    
    @DeleteMapping("/eliminar/{id_turno}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id_turno) {
        try {
            turnoService.eliminarTurno(id_turno);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Turno eliminado");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el turno");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo salió mal");
        }
    }

}
