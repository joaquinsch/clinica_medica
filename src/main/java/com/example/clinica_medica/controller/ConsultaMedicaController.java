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

import com.example.clinica_medica.model.ConsultaMedica;

import com.example.clinica_medica.services.ConsultaMedicaService;

@RestController
@RequestMapping("/api/consultasmedicas")
public class ConsultaMedicaController {

	/* 
	 * - (hecho)VERIFICAR QUE SI UN TURNO YA FUE USADO, NO PUEDA VOLVER A USARSE. 
	 * - (hecho)VERIFICAR FECHA Y HORA DE TURNO AL EDITAR CONSULTA MEDICA, DEBE HABER PARA EL TURNO INGRESADO.
	 * - VERIFICAR QUE SI SE EDITA CONSULTA MEDICA PASANDOLE SERVICIO Y PAQUETE DE ERROR.
	 * - VERIFICAR QUE SI SE QUIERE EDITAR EL CODIGO_PAQUETE, SEA UN PAQUETE EXISTENTE, Y QUE NO 
	 * ESTÉ ASOCIADO A OTRA CONSULTA MEDICA.
	 * - VERIFICAR QUE SI SE QUIERE EDITAR CODIGO_SERVICIO_MEDICO, EL SERVICIO EXISTA
	 *  - VERIFICAR QUE SI SE QUIERE EDITAR ID-MEDICO, EXISTA EL MEDICO
	 *  - VERIFICAR QUE SI SE QUIERE EDITAR ID-PACIENTE, EXISTA EL PACIENTE
	 *  - VERIFICAR RECURSIVIDAD INFINITA EN ENDPOINTS GETS
	 * -----------------------------------------------------------
	 * - VERIFICAR TURNO AL ELIMINAR CONSULTA MEDICA, DEBE REESTABLECERSE EL TURNO.
	 * - VERIFICAR QUE SI SE ELIMINA CONSULTA MEDICA, DEBE ELIMINARSE EL PAQUETE ASOCIADO A ELLA.
	 * -----------------------------------------------------------------
	 * - REFACTOREAR HAYTURNODISPONIBLE PARA Q LLAME A SERVICES SOLO
	 * 
	 * **********************************
	 * 
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
		return new ResponseEntity<>(consultaBuscada, HttpStatus.OK);
	}
	
	@PutMapping("/editar")
	public ResponseEntity<ConsultaMedica> editarConsultaMedica(@RequestBody ConsultaMedica consultaMedica){
		ConsultaMedica consultaBuscada = consultaMedicaService.editarConsultaMedica(consultaMedica);
		return new ResponseEntity<>(consultaBuscada, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/eliminar/{id_consulta_medica}")
	public ResponseEntity<ConsultaMedica> eliminarConsultaMedica(@PathVariable Long id_consulta_medica){
		consultaMedicaService.eliminarConsultaMedica(id_consulta_medica);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
