package com.example.clinica_medica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clinica_medica.exception.ConsultaMedicaConServicioYPaqueteError;
import com.example.clinica_medica.exception.ConsultaMedicaNoEncontradaError;
import com.example.clinica_medica.exception.PaqueteNoDisponibleError;
import com.example.clinica_medica.exception.TurnoNoDisponibleError;
import com.example.clinica_medica.model.ConsultaMedica;
import com.example.clinica_medica.model.PaqueteServicio;
import com.example.clinica_medica.model.ServicioMedico;
import com.example.clinica_medica.model.Turno;
import com.example.clinica_medica.repository.ConsultaMedicaRepository;
import com.example.clinica_medica.repository.TurnoRepository;

@Service
public class ConsultaMedicaService {

	private static final Double DESCUENTO_OBRA_SOCIAL = 0.2;

	@Autowired
	private ConsultaMedicaRepository consultaMedicaRepo;

	@Autowired
	private TurnoRepository turnoRepo;

	@Autowired
	private TurnoService turnoService;

	@Autowired
	private ServicioMedicoService servicioMedicoService;

	@Autowired
	private PaqueteServicioService paqueteService;

	@Autowired
	private PacienteService pacienteService;

	public ConsultaMedica guardarConsultaMedica(ConsultaMedica consulta) {
		Turno turnoBuscado = turnoService.buscarTurnoPorFecha(consulta.getUn_medico(), consulta.getFecha_consulta(),
				consulta.getHora_consulta());
		if (turnoBuscado == null || !turnoBuscado.getDisponibilidad()) {
			throw new TurnoNoDisponibleError("No hay turnos disponibles en el horario o fecha elegidos");
		}

		if (consultaConPaquete(consulta)) {
			PaqueteServicio paqueteServicio = paqueteService
					.buscarPaqueteServicio(consulta.getUn_paquete_servicio().getCodigo_paquete());
			// si tiene obra social aplica descuento de 20%
			if (pacienteService.buscarPaciente(consulta.getUn_paciente().getId_paciente()).getTiene_obra_social()) {
				Double precioFinal = paqueteServicio.obtenerPrecioConDescuento(paqueteServicio.getPrecio_paquete(),
						DESCUENTO_OBRA_SOCIAL);
				paqueteServicio.setPrecio_paquete(precioFinal);
			}
			consulta.setMonto_total(paqueteServicio.getPrecio_paquete());
		} else {
			ServicioMedico servicioMedico = servicioMedicoService
					.buscarServicioMedico(consulta.getUn_servicio_medico().getCodigo_servicio());
			consulta.setMonto_total(servicioMedico.getPrecio());
		}
		/*
		 * ESTO GUARDA BIEN LA DISPONIBILIDAD, SIN TENER QUE LLAMAR AL REPO DE TURNO Y
		 * GUARDARLO... (NO SE POR Q)
		 */
		turnoBuscado.setDisponibilidad(false);

		return consultaMedicaRepo.save(consulta);
	}

	public ConsultaMedica buscarConsultaMedica(Long id_consulta) {
		return consultaMedicaRepo.findById(id_consulta).orElseThrow(
				() -> new ConsultaMedicaNoEncontradaError("La consulta médica con id: " + id_consulta + " no existe"));

	}

	public ConsultaMedica editarConsultaMedica(ConsultaMedica consulta) {
		ConsultaMedica consultaRecuperada = buscarConsultaMedica(consulta.getId_consulta_medica());
		consultaRecuperada.setId_consulta_medica(consulta.getId_consulta_medica());
		editarTurno(consultaRecuperada, consulta);

		consultaRecuperada.setUn_paciente(consulta.getUn_paciente());
		consultaRecuperada.setUn_medico(consulta.getUn_medico());
		if (consultaConPaquete(consulta)) {
			PaqueteServicio paqueteBuscado = paqueteService
					.buscarPaqueteServicio(consulta.getUn_paquete_servicio().getCodigo_paquete());

			if (paqueteValido(paqueteBuscado, consultaRecuperada)) {
				if (!obtenerTipoDeConsulta(consultaRecuperada)) {
					consultaRecuperada.setUn_servicio_medico(null);
				}
				consultaRecuperada.setUn_paquete_servicio(consulta.getUn_paquete_servicio());
			} else {
				throw new PaqueteNoDisponibleError("El paquete ya se encuentra asociado a otra consulta médica");
			}
		} else {
			@SuppressWarnings("unused")
			ServicioMedico servicioMedicoBuscado = servicioMedicoService
					.buscarServicioMedico(consulta.getUn_servicio_medico().getCodigo_servicio());
			if (obtenerTipoDeConsulta(consultaRecuperada)) {
				consultaRecuperada.setUn_paquete_servicio(null);
			}
			consultaRecuperada.setUn_servicio_medico(consulta.getUn_servicio_medico());

		}
		consultaRecuperada.setMonto_total(consulta.getMonto_total());
		consultaRecuperada.setPagado_o_no(consulta.getPagado_o_no());

		return consultaMedicaRepo.save(consultaRecuperada);
	}

	public void eliminarConsultaMedica(Long id_consulta) {
		// busca, si no existe tira excepcion
		ConsultaMedica consultaMedica = buscarConsultaMedica(id_consulta);
		Turno turnoBuscado = turnoService.buscarTurnoPorFecha(consultaMedica.getUn_medico(),
				consultaMedica.getFecha_consulta(), consultaMedica.getHora_consulta());
		consultaMedicaRepo.deleteById(id_consulta);
		turnoBuscado.setDisponibilidad(true);
		turnoRepo.save(turnoBuscado);
	}

	/**
	 * VALIDA QUE SOLO SEA DE UN TIPO
	 * 
	 * @return false si es por servicio medico / true si es por paquete.
	 * @throws ConsultaMedicaConServicioYPaqueteError si la consulta tiene los dos
	 *                                                tipos
	 */
	

	private boolean consultaConPaquete(ConsultaMedica consulta) {
		if (consulta.getUn_servicio_medico() == null ^ consulta.getUn_paquete_servicio() == null) {
			if (consulta.getUn_paquete_servicio() == null) {
				return false;
			}
		} else {
			throw new ConsultaMedicaConServicioYPaqueteError(
					"La consulta debe estar asociada a un único servicio médico o a un único paquete");
		}
		return true;
	}

	/**
	 * NO VALIDA NADA
	 * @return true si es por paquete, false si es por servicio medico.
	 * 
	 */
	private boolean obtenerTipoDeConsulta(ConsultaMedica consulta) {
		return consulta.getUn_paquete_servicio() != null;
	}

	/**
	 * Valida que al editar codigo_paquete, no haya otra consulta que ya tenga ese
	 * codigo_paquete asociado.
	 */
	private boolean paqueteValido(PaqueteServicio paquete, ConsultaMedica consulta) {
		for (ConsultaMedica unaConsulta : this.consultaMedicaRepo.findAll()) {
			if (unaConsulta.getUn_paquete_servicio() != null) {
				if (unaConsulta.getUn_paquete_servicio().getCodigo_paquete() == paquete.getCodigo_paquete()) {
					if (unaConsulta.getUn_paquete_servicio().getCodigo_paquete() != consulta.getUn_paquete_servicio()
							.getCodigo_paquete()) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Valida si el turno de la consulta se quiere editar, o se deja el que ya
	 * tiene. Si no hay turnos en la fecha y hora ingresadas tira error.
	 * 
	 * @param original
	 * @param nueva
	 * @throws TurnoNoDisponibleError
	 */

	private void editarTurno(ConsultaMedica original, ConsultaMedica nueva) {
		Turno turnoOriginal = turnoService.buscarTurnoPorFecha(original.getUn_medico(), original.getFecha_consulta(),
				original.getHora_consulta());
		Turno turnoIngresado = turnoService.buscarTurnoPorFecha(nueva.getUn_medico(), nueva.getFecha_consulta(),
				nueva.getHora_consulta());
		// si existe el turno y está disponible, o si deja el mismo turno que ya estaba
		if (turnoIngresado != null && turnoIngresado.getDisponibilidad()) {
			original.setFecha_consulta(nueva.getFecha_consulta());
			original.setHora_consulta(nueva.getHora_consulta());
			turnoIngresado.setDisponibilidad(false);
			turnoRepo.save(turnoIngresado);
			turnoOriginal.setDisponibilidad(true);
			turnoRepo.save(turnoOriginal);
		} else if (turnoIngresado != null && turnoService.coincidenFechaYHorario(turnoIngresado,
				nueva.getFecha_consulta(), nueva.getHora_consulta())) {
			original.setFecha_consulta(nueva.getFecha_consulta());
			original.setHora_consulta(nueva.getHora_consulta());
		} else {
			throw new TurnoNoDisponibleError("No hay turnos disponibles en el horario o fecha elegidos");
		}
	}

}
