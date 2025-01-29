package com.example.clinica_medica.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clinica_medica.model.Medico;
import com.example.clinica_medica.model.Turno;
import com.example.clinica_medica.repository.MedicoRepository;
import com.example.clinica_medica.repository.TurnoRepository;

@Service
public class TurnoService {
	@Autowired
	private TurnoRepository turnoRepo;

	@Autowired
	private MedicoRepository medicoRepo;

	public Turno guardarTurno(Turno turno) {
		return turnoRepo.save(turno);
	}

	public Turno buscarTurno(Long id_turno) {
		return turnoRepo.findById(id_turno).orElseThrow();
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

	public boolean hayTurnoDisponible(Medico medico, LocalDate fecha, LocalTime hora) {
		Optional<Medico> medicoBuscado = medicoRepo.findById(medico.getId_medico());

		if (medicoBuscado.isPresent()) {
			List<Turno> turnosDelMedico = medicoBuscado.get().getTurnos_disponibles();
			for (Turno turno : turnosDelMedico) {
				if (turno.getDisponibilidad()) {
					if (turno.getFecha_turno().equals(fecha)) {
						if (turno.getHora_turno().equals(hora)) {
							return true;
						}
					}
				}
			}
		} else {
			throw new IllegalArgumentException("El médico ingresado no existe");
		}
		return false;
	}

	public Turno buscarTurnoPorFecha(Medico medico, LocalDate fecha, LocalTime hora) {
		Optional<Medico> medicoBuscado = medicoRepo.findById(medico.getId_medico());

		if (medicoBuscado.isPresent()) {
			List<Turno> turnosDelMedico = medicoBuscado.get().getTurnos_disponibles();
			for (Turno turno : turnosDelMedico) {
				if (turno.getDisponibilidad()) {
					if (turno.getFecha_turno().equals(fecha)) {
						if (turno.getHora_turno().equals(hora)) {
							return turno;
						}
					}
				}
			}
		} 
		return null;
	}

}
