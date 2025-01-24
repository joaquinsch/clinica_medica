package com.example.clinica_medica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clinica_medica.model.Turno;
import com.example.clinica_medica.repository.TurnoRepository;

@Service
public class TurnoService {
	@Autowired
	private TurnoRepository turnoRepo;

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

		return turnoRepo.save(turnoBuscado);
	}

	public void eliminarTurno(Long id_turno) {
		@SuppressWarnings("unused")
		Turno turno = buscarTurno(id_turno);
		turnoRepo.deleteById(id_turno);
	}

}
