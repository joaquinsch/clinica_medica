package com.example.clinica_medica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clinica_medica.model.Paciente;
import com.example.clinica_medica.repository.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepo;

	public Paciente guardarPaciente(Paciente paciente) {
		return pacienteRepo.save(paciente);
	}

	public Paciente buscarPaciente(Long id_paciente) {
		return pacienteRepo.findById(id_paciente).orElseThrow();

	}
}
