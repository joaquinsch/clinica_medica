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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/consultasmedicas")
public class ConsultaMedicaController {

	/* 
	 * - (hecho)VERIFICAR QUE SI UN TURNO YA FUE USADO, NO PUEDA VOLVER A USARSE. 
	 * - (hecho)VERIFICAR FECHA Y HORA DE TURNO AL EDITAR CONSULTA MEDICA, DEBE HABER PARA EL TURNO INGRESADO.
	 * - (hecho)VERIFICAR TURNO QUE NO HAYA TURNOS DE UN MEDICO CON MISMA FECHA Y HORA.
	 * - (hecho)VERIFICAR QUE SI SE EDITA CONSULTA MEDICA PASANDOLE SERVICIO Y PAQUETE DE ERROR.
	 * - (hecho)VERIFICAR QUE SI SE QUIERE EDITAR EL CODIGO_PAQUETE, SEA UN PAQUETE EXISTENTE, Y QUE NO 
	 * ESTÉ ASOCIADO A OTRA CONSULTA MEDICA. 
	 * -(definir)VERIFICAR MONTOTOTAL DE LA CONSULTA CUANDO SE LE EDITA PAQUETE O SERVICIO 
	 * - (hecho)VERIFICAR QUE SI SE QUIERE EDITAR CODIGO_SERVICIO_MEDICO, EL SERVICIO EXISTA
	 * 
	 *  - (hecho)VERIFICAR QUE SI SE QUIERE EDITAR ID-MEDICO, EXISTA EL MEDICO
	 *  - (hecho)VERIFICAR QUE SI SE QUIERE EDITAR ID-PACIENTE, EXISTA EL PACIENTE
	 *  - (hecho)VERIFICAR RECURSIVIDAD INFINITA EN ENDPOINTS GETS
	 *  -(no se) VERIFICAR QUE NO SE VEAN DATOS SENSIBLES AL HACER GET DE ALGO. POR EJEMPLO
	 *  AL LISTAR UNA CONSULTAMEDICA, SE VEN DATOS DEL MEDICO COMO LOS TURNOS Y EL SUELDO.
	 * -HACER REGEX PARA RUTAS INVALIDAS COMO /buscar/asd
	 * -CAMBIAR FORMATO FECHA TURNOS AL MISMO FORMATO DE TODAS LAS OTRAS FECHAS
	 * -----------------------------------------------------------
	 * - (hecho)VERIFICAR TURNO AL ELIMINAR CONSULTA MEDICA, DEBE REESTABLECERSE EL TURNO.
	 * - (hecho)VERIFICAR QUE SI SE ELIMINA CONSULTA MEDICA, DEBE ELIMINARSE EL PAQUETE ASOCIADO A ELLA.
	 * -----------------------------------------------------------------
	 * 
	 * **********************************
	 * 
	 * -REVISAR RELACION N a N DE PAQUETES Y SERVICIOS (NO SE SI ES NECESARIO GUARDAR
	 * ESTO) 

	 */

	@Autowired
	private ConsultaMedicaService consultaMedicaService;

	@PostMapping("/crear")
	public ResponseEntity<ConsultaMedica> crearConsultaMedica(@Valid @RequestBody ConsultaMedica consulta) {
		ConsultaMedica consultaGuardada = consultaMedicaService.guardarConsultaMedica(consulta);
		return new ResponseEntity<>(consultaGuardada, HttpStatus.CREATED);
	}
	
	@GetMapping("/buscar/{id_consulta_medica}")
	public ResponseEntity<ConsultaMedica> buscarConsultaMedica(@PathVariable Long id_consulta_medica){
		ConsultaMedica consultaBuscada = consultaMedicaService.buscarConsultaMedica(id_consulta_medica);
		return new ResponseEntity<>(consultaBuscada, HttpStatus.OK);
	}
	
	@PutMapping("/editar")
	public ResponseEntity<ConsultaMedica> editarConsultaMedica(@Valid @RequestBody ConsultaMedica consultaMedica){
		ConsultaMedica consultaBuscada = consultaMedicaService.editarConsultaMedica(consultaMedica);
		return new ResponseEntity<>(consultaBuscada, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/eliminar/{id_consulta_medica}")
	public ResponseEntity<ConsultaMedica> eliminarConsultaMedica(@PathVariable Long id_consulta_medica){
		consultaMedicaService.eliminarConsultaMedica(id_consulta_medica);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
