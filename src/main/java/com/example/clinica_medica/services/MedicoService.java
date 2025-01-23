package com.example.clinica_medica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clinica_medica.model.Medico;
import com.example.clinica_medica.repository.MedicoRepository;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository medicoRepo;
	
	public Medico guardarMedico(Medico medico) {
		return medicoRepo.save(medico);
	}

}
