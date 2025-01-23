package com.example.clinica_medica.tests;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.clinica_medica.model.Paciente;
import com.example.clinica_medica.repository.PacienteRepository;
import com.example.clinica_medica.services.PacienteService;

@ExtendWith(MockitoExtension.class)
public class PacienteServiceTests {

	@Mock
	private PacienteRepository pacienteRepo;
	
	@InjectMocks
	private PacienteService pacienteService;
	
	@Test
	public void deberiaGuardarUnPaciente() {
		Paciente paciente = new Paciente();
		paciente.setNombre("carlos");
		Mockito.when(pacienteRepo.save(paciente)).thenReturn(paciente);
		
		Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);
		
		Assertions.assertEquals("carlos", paciente.getNombre());
		
		
		
	}
}
