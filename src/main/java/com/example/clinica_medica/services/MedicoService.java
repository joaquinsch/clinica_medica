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

	public Medico buscarMedico(Long id_medico) {
		return medicoRepo.findById(id_medico).orElseThrow();

	}

	public Medico editarMedico(Medico medico) {
		Medico medicoBuscado = buscarMedico(medico.getId_medico());
		medicoBuscado.setApellido(medico.getApellido());
		medicoBuscado.setDireccion(medico.getDireccion());
		medicoBuscado.setDni(medico.getDni());
		medicoBuscado.setEmail(medico.getEmail());
		medicoBuscado.setFecha_nac(medico.getFecha_nac());
		medicoBuscado.setId_medico(medico.getId_medico());
		medicoBuscado.setNombre(medico.getNombre());
		medicoBuscado.setTelefono(medico.getTelefono());
		medicoBuscado.setEspecialidad_medica(medico.getEspecialidad_medica());
		medicoBuscado.setTurnos_disponibles(medico.getTurnos_disponibles());
		medicoBuscado.setSueldo(medico.getSueldo());
		return medicoRepo.save(medicoBuscado);

	}
	
	public void eliminarMedico(Long id_medico) {
	    @SuppressWarnings("unused")
		Medico medico = buscarMedico(id_medico);
	    medicoRepo.deleteById(id_medico);
	}

}
