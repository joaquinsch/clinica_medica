package com.example.clinica_medica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.clinica_medica.model.ConsultaMedica;

import com.example.clinica_medica.services.ConsultaMedicaService;

@RestController
@RequestMapping("/api/consultasmedicas")
public class ConsultaMedicaController {

	/*
	 * 
	 * - TERMINAR CRUD CONSULTAMEDICA
	 * - VERIFICAR QUE SI UN TURNO YA FUE USADO, NO PUEDA VOLVER A USARSE.
	 * -REFACTOREAR HAYTURNODISPONIBLE PARA Q LLAME A SERVICES SOLO
	 * 
	 * **********************************
	 * -REVISAR RELACION N a N DE PAQUETES Y SERVICIOS (NO SE SI ES NECESARIO GUARDAR
	 * ESTO) 
	 * - PREGUNTAR POR CONSULTA MEDICA CON PAQUETE. YA QUE SE ESTÁ ASUMIENDO
	 * QUE UN SOLO MÉDICO SE HACE CARGO DE LA CONSULTA CON LOS SERVICIOS DEL
	 * PAQUETE... 
	 * - FECHA Y HORA DE UNA CONSULTA CON PAQUETE, SE ESTÁ ASUMIENDO QUE
	 * EN ESA FECHA Y HORA DE LA CONSULTA SE HACE TODo LO QUE TRAE EL PAQUETE
	 * DENTRO...
	 * 
	 * 
	 */

	@Autowired
	private ConsultaMedicaService consultaMedicaService;

	@PostMapping("/crear")
	public ResponseEntity<ConsultaMedica> crearConsultaMedica(@RequestBody ConsultaMedica consulta) {
		
		consultaMedicaService.guardarConsultaMedica(consulta);
		return ResponseEntity.status(HttpStatus.CREATED).body(consulta);
	}
	
	@GetMapping("/buscar/{id_consulta_medica}")
	public ResponseEntity<ConsultaMedica> buscarConsultaMedica(@PathVariable Long id_consulta_medica){
		ConsultaMedica consultaBuscada = consultaMedicaService.buscarConsultaMedica(id_consulta_medica);
		return new ResponseEntity<ConsultaMedica>(consultaBuscada, HttpStatus.OK);
	}
}
