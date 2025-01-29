package com.example.clinica_medica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clinica_medica.exception.ConsultaMedicaConServicioYPaqueteError;
import com.example.clinica_medica.exception.ConsultaMedicaNoEncontradaError;
import com.example.clinica_medica.exception.TurnoNoDisponibleError;
import com.example.clinica_medica.model.ConsultaMedica;
import com.example.clinica_medica.model.PaqueteServicio;
import com.example.clinica_medica.model.ServicioMedico;
import com.example.clinica_medica.model.Turno;
import com.example.clinica_medica.repository.ConsultaMedicaRepository;

@Service
public class ConsultaMedicaService {

	private static final Double DESCUENTO_OBRA_SOCIAL = 0.2;

	@Autowired
	private ConsultaMedicaRepository consultaMedicaRepo;

	@Autowired
	private TurnoService turnoService;

	@Autowired
	private ServicioMedicoService servicioMedicoService;

	@Autowired
	private PaqueteServicioService paqueteService;
	
	@Autowired
	private PacienteService pacienteService;

	public ConsultaMedica guardarConsultaMedica(ConsultaMedica consulta) {
		if (!turnoService.hayTurnoDisponible(consulta.getUn_medico(), consulta.getFecha_consulta(),
				consulta.getHora_consulta())) {
			throw new TurnoNoDisponibleError("No hay turnos disponibles en el horario o fecha elegidos");
		}

		if (consulta.getUn_servicio_medico() == null ^ consulta.getUn_paquete_servicio() == null) {
			// pide por servicio
			if (consulta.getUn_servicio_medico() != null) {
				ServicioMedico servicioMedico = servicioMedicoService
						.buscarServicioMedico(consulta.getUn_servicio_medico().getCodigo_servicio());
				consulta.setMonto_total(servicioMedico.getPrecio());
			} else {
				// pide por paquete
				PaqueteServicio paqueteServicio = paqueteService
						.buscarPaqueteServicio(consulta.getUn_paquete_servicio().getCodigo_paquete());
				// si tiene obra social aplica descuento de 20%
				if (pacienteService.buscarPaciente(consulta.getUn_paciente().getId_paciente()).getTiene_obra_social()) {
					Double precioFinal = paqueteServicio.obtenerPrecioConDescuento(paqueteServicio.getPrecio_paquete(), DESCUENTO_OBRA_SOCIAL);
					paqueteServicio.setPrecio_paquete(precioFinal);
				}
				consulta.setMonto_total(paqueteServicio.getPrecio_paquete());
			}
			// REFACTOREAER
			Turno turno = turnoService.buscarTurnoPorFecha(consulta.getUn_medico(), consulta.getFecha_consulta(), consulta.getHora_consulta());
			turnoService.buscarTurno(turno.getId_turno()).setDisponibilidad(false);
			return consultaMedicaRepo.save(consulta);
		} else {
			throw new ConsultaMedicaConServicioYPaqueteError(
					"La consulta debe estar asociada a un único servicio médico o a un único paquete");
		}

	}

	public ConsultaMedica buscarConsultaMedica(Long id_consulta) {
		return consultaMedicaRepo.findById(id_consulta).orElseThrow(
				() -> new ConsultaMedicaNoEncontradaError("La consulta médica con id: " + id_consulta + " no existe"));

	}

	public ConsultaMedica editarConsultaMedica(ConsultaMedica consulta) {
		ConsultaMedica consultaMedica = buscarConsultaMedica(consulta.getId_consulta_medica());
		consultaMedica.setId_consulta_medica(consulta.getId_consulta_medica());
		if (turnoService.hayTurnoDisponible(consulta.getUn_medico(), consulta.getFecha_consulta(), consulta.getHora_consulta())) {
			consultaMedica.setFecha_consulta(consulta.getFecha_consulta());
			consultaMedica.setHora_consulta(consulta.getHora_consulta());		
		} else {
			throw new TurnoNoDisponibleError("No hay turnos disponibles en el horario o fecha elegidos");
		}
		consultaMedica.setUn_paciente(consulta.getUn_paciente());
		consultaMedica.setUn_medico(consulta.getUn_medico());
		consultaMedica.setUn_paquete_servicio(consulta.getUn_paquete_servicio());
		consultaMedica.setUn_servicio_medico(consulta.getUn_servicio_medico());
		consultaMedica.setMonto_total(consulta.getMonto_total());
		consultaMedica.setPagado_o_no(consulta.getPagado_o_no());
		return consultaMedicaRepo.save(consultaMedica);
	}

	public void eliminarConsultaMedica(Long id_consulta) {
		// busca, si no existe tira excepcion
		@SuppressWarnings("unused")
		ConsultaMedica consultaMedica = buscarConsultaMedica(id_consulta);
		consultaMedicaRepo.deleteById(id_consulta);
	}


}
