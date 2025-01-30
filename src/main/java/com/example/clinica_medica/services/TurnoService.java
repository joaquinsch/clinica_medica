package com.example.clinica_medica.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clinica_medica.exception.TurnoNoDisponibleError;
import com.example.clinica_medica.exception.TurnoNoEncontradoError;
import com.example.clinica_medica.model.Medico;
import com.example.clinica_medica.model.Turno;
import com.example.clinica_medica.repository.TurnoRepository;

@Service
public class TurnoService {
	@Autowired
	private TurnoRepository turnoRepo;

	@Autowired
	private MedicoService medicoService;

	public Turno guardarTurno(Turno turno) {
		List<Turno> turnosDelMedico = medicoService.buscarMedico(turno.getUn_medico().getId_medico())
				.getTurnos_disponibles();
		for (Turno turnoDelMedico : turnosDelMedico) {
			if (coincidenFechaYHorario(turno, turnoDelMedico.getFecha_turno(), turnoDelMedico.getHora_turno())) {
				throw new TurnoNoDisponibleError("El médico ya tiene un turno en la fecha y horario ingresados");
			}
		}
		return turnoRepo.save(turno);
	}

	public Turno buscarTurno(Long id_turno) {
		return turnoRepo.findById(id_turno).orElseThrow(
				() -> new TurnoNoEncontradoError("El turno con id: " + id_turno + " no existe"));
	}

	public Turno editarMedico(Turno turno) {
		Turno turnoBuscado = buscarTurno(turno.getId_turno());

		turnoBuscado.setFecha_turno(turno.getFecha_turno());
		turnoBuscado.setHora_turno(turno.getHora_turno());
		turnoBuscado.setUn_medico(turno.getUn_medico());
		turnoBuscado.setDisponibilidad(turno.getDisponibilidad());
		return turnoRepo.save(turnoBuscado);
	}

	public void eliminarTurno(Long id_turno) {
		@SuppressWarnings("unused")
		Turno turno = buscarTurno(id_turno);
		turnoRepo.deleteById(id_turno);
	}


	public Turno buscarTurnoPorFecha(Medico medico, LocalDate fecha, LocalTime hora) {
		Medico medicoBuscado = medicoService.buscarMedico(medico.getId_medico());

		List<Turno> turnosDelMedico = medicoBuscado.getTurnos_disponibles();
		for (Turno turno : turnosDelMedico) {
			if (coincidenFechaYHorario(turno, fecha, hora)) {
				return turno;
			}
		}

		return null;
	}

	private boolean coincidenFechaYHorario(Turno turno, LocalDate fecha, LocalTime horario) {
		return turno.getFecha_turno().equals(fecha) && turno.getHora_turno().equals(horario);
	}

}
