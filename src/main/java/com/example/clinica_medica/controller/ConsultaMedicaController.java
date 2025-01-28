package com.example.clinica_medica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	 * 
	 * - VERIFICAR PACIENTE Y HACER 20% DE DESCUENTO SI CONTRATA PAQUETE Y TIENE
	 * OBRA SOCIAL(no se si es solo para paquete o tambien para un servicio) 
	 * - VERIFICAR QUE SI UN TURNO YA FUE USADO, NO PUEDA VOLVER A USARSE.
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
}
