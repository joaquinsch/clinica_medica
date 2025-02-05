package com.example.clinica_medica.tests;

import static org.hamcrest.CoreMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.clinica_medica.controller.PacienteController;
import com.example.clinica_medica.model.Paciente;
import com.example.clinica_medica.services.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class PacienteControllerTests {
	
	@Mock
	private PacienteService pacienteService;
	
	@InjectMocks
	private PacienteController pacienteController;
	
	private MockMvc mockMvc;
	
    private ObjectMapper objectMapper;

    private Paciente paciente;

    @BeforeEach
    public void setUp() {
    	this.mockMvc = MockMvcBuilders.standaloneSetup(pacienteController).build();
    	objectMapper = new ObjectMapper();
        paciente = new Paciente();
        paciente.setId_paciente(1L);
        paciente.setNombre("Juan");
        paciente.setApellido("Pérez");
        paciente.setDni("12345678");
        paciente.setEmail("juan.perez@example.com");
        //paciente.setFecha_nac(LocalDate.of(1990, 5, 15));
        paciente.setTelefono("123456789");
        paciente.setTiene_obra_social(true);
    }
	
	@Test
	public void deberiaCrearUnPaciente() throws Exception{
		Mockito.when(pacienteService.guardarPaciente(Mockito.any(Paciente.class))).thenReturn(paciente);
		
		String pacienteJson = objectMapper.writeValueAsString(paciente);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/pacientes/crear")
				.contentType(MediaType.APPLICATION_JSON)
				.content(pacienteJson))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id_paciente").value(1))
				.andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellido").value("Pérez"));
	}
}
