package com.example.clinica_medica.tests;

import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.clinica_medica.exception.PacienteNoEncontradoError;
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

		Assertions.assertEquals("carlos", pacienteGuardado.getNombre());
	}

	@Test
	public void deberiaRecuperarPacientePorId() {
		Paciente paciente = new Paciente();
		paciente.setId_paciente(1L);
		paciente.setNombre("carlos");

		Mockito.when(pacienteRepo.findById(paciente.getId_paciente())).thenReturn(Optional.of(paciente));

		Paciente pacienteRecuperado = pacienteService.buscarPaciente(paciente.getId_paciente());
		Assertions.assertEquals(1L, pacienteRecuperado.getId_paciente());
		Assertions.assertEquals("carlos", pacienteRecuperado.getNombre());
	}
	
	@Test
	public void deberiaEditarUnPaciente() {
		Paciente paciente = new Paciente();
		paciente.setId_paciente(1L);
		paciente.setNombre("carlos");
		Mockito.when(pacienteRepo.findById(paciente.getId_paciente())).thenReturn(Optional.of(paciente));
		Mockito.when(pacienteRepo.save(paciente)).thenReturn(paciente);
		Paciente paciente2 = new Paciente();
		paciente2.setId_paciente(1L);
		paciente2.setNombre("raul");
		Paciente pacienteEditado = pacienteService.editarPaciente(paciente2);
		Assertions.assertEquals(1L, pacienteEditado.getId_paciente());
		Assertions.assertEquals("raul", pacienteEditado.getNombre());
		
	}
	
	@Test
	public void deberiaDarErrorSiIntentaBuscarPacienteInexistente() {
		Paciente paciente = new Paciente();
		paciente.setId_paciente(1L);
		paciente.setNombre("carlos");
		Mockito.when(pacienteRepo.findById(2L)).thenReturn(Optional.empty());

		Assertions.assertThrows(PacienteNoEncontradoError.class, () ->
		        pacienteService.buscarPaciente(2L)
		   );
	}

	@Test
	public void deberiaDarErrorSiIntentaEditarPacienteInexistente() {
		Paciente paciente = new Paciente();
		paciente.setId_paciente(2L);
		paciente.setNombre("carlos");
		Mockito.when(pacienteRepo.findById(2L)).thenReturn(Optional.of(paciente));
		Mockito.when(pacienteService.buscarPaciente(2L)).thenThrow(new PacienteNoEncontradoError("El paciente con id: 2 no existe"));

		Assertions.assertThrows(PacienteNoEncontradoError.class, () ->
		        pacienteService.editarPaciente(paciente)
		   );
	}

	@Test
	public void deberiaEliminarElPaciente() {
		Paciente paciente = new Paciente();
		paciente.setId_paciente(1L);
		paciente.setNombre("carlos");
		
		Mockito.when(pacienteRepo.findById(1L)).thenReturn(Optional.of(paciente));
		pacienteService.eliminarPaciente(paciente.getId_paciente());
		
		Mockito.verify(pacienteRepo, times(1)).deleteById(paciente.getId_paciente());
		Assertions.assertEquals(0, pacienteRepo.count());
	}
}
