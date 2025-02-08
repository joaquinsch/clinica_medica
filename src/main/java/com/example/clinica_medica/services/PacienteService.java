package com.example.clinica_medica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clinica_medica.exception.PacienteNoEncontradoError;

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
		return pacienteRepo.findById(id_paciente)
				.orElseThrow(() -> new PacienteNoEncontradoError("El paciente con id: " + id_paciente + " no existe"));
	}

	public Paciente editarPaciente(Paciente paciente) {
		Paciente pacienteBuscado = buscarPaciente(paciente.getId_paciente());
		pacienteBuscado.setApellido(paciente.getApellido());
		pacienteBuscado.setConsultas(paciente.getConsultas());
		pacienteBuscado.setDireccion(paciente.getDireccion());
		pacienteBuscado.setDni(paciente.getDni());
		pacienteBuscado.setEmail(paciente.getEmail());
		pacienteBuscado.setFecha_nac(paciente.getFecha_nac());
		pacienteBuscado.setId_paciente(paciente.getId_paciente());
		pacienteBuscado.setNombre(paciente.getNombre());
		pacienteBuscado.setTelefono(paciente.getTelefono());
		pacienteBuscado.setTiene_obra_social(paciente.getTiene_obra_social());
		return pacienteRepo.save(pacienteBuscado);

	}

	public void eliminarPaciente(Long id_paciente) {
		// busca el paciente, si no existe tira excepcion
		buscarPaciente(id_paciente);
		pacienteRepo.deleteById(id_paciente);
	}
}
