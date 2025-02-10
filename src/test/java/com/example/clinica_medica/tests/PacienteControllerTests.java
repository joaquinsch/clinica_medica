package com.example.clinica_medica.tests;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withAccepted;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.clinica_medica.controller.PacienteController;
import com.example.clinica_medica.model.Paciente;
import com.example.clinica_medica.services.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

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
    	// necesario para usar LocalDate
    	objectMapper.registerModule(new JavaTimeModule());
        paciente = new Paciente();
        paciente.setId_paciente(1L);
        paciente.setNombre("Juan");
        paciente.setApellido("Pérez");
        paciente.setDni("12345678");
        paciente.setEmail("juan.perez@example.com");
        paciente.setFecha_nac(LocalDate.of(1990, 5, 15));
        paciente.setTelefono("123456789");
        paciente.setDireccion("Calle falsa 123");
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
                .andExpect(jsonPath("$.apellido").value("Pérez"))
                .andExpect(jsonPath("$.dni").value("12345678"))
                .andExpect(jsonPath("$.email").value("juan.perez@example.com"))
				.andExpect(jsonPath("$.fecha_nac").value("15/05/1990"))
				.andExpect(jsonPath("$.telefono").value("123456789"))
				.andExpect(jsonPath("$.direccion").value("Calle falsa 123"))
				.andExpect(jsonPath("$.tiene_obra_social").value(true));
	}
	
	@Test
	public void deberiaEliminarUnPaciente() throws Exception{
		//Mockito.when(pacienteService.buscarPaciente(paciente.getId_paciente())).thenReturn(paciente);
		//String pacienteJson = objectMapper.writeValueAsString(paciente);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/pacientes/eliminar/1")
				.contentType(MediaType.APPLICATION_JSON)
				).andExpect(status().isNoContent());
		
		Mockito.verify(pacienteService, times(1)).eliminarPaciente(paciente.getId_paciente());
	}
	
	@Test
	public void deberiaBuscarUnPaciente() throws Exception{
		Mockito.when(pacienteService.buscarPaciente(paciente.getId_paciente())).thenReturn(paciente);
		String pacienteJson = objectMapper.writeValueAsString(paciente);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/pacientes/buscar/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(pacienteJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id_paciente").value(1L))
				.andExpect(jsonPath("$.nombre").value("Juan"))
				.andExpect(jsonPath("$.apellido").value("Pérez"));
		
		Mockito.verify(pacienteService, times(1)).buscarPaciente(paciente.getId_paciente());
	}
	
	@Test
	public void deberiaEditarUnPaciente() throws Exception{
		paciente.setId_paciente(1L);
		paciente.setNombre("Carlos");
		paciente.setApellido("González");
		paciente.setDni("12312312");
		paciente.setEmail("carlos.gonzalez@example.com");
		paciente.setFecha_nac(LocalDate.of(1982, 4, 20));
		paciente.setTelefono("1166885544");
		paciente.setDireccion("Calle falsa 124");
		paciente.setTiene_obra_social(false);
		String pacienteEditadoJson = objectMapper.writeValueAsString(paciente);

		Mockito.when(pacienteService.editarPaciente(Mockito.any(Paciente.class))).thenReturn(paciente);
		mockMvc.perform(MockMvcRequestBuilders.put("/api/pacientes/editar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(pacienteEditadoJson)
				).andExpect(jsonPath("$.id_paciente").value(1L))
				.andExpect(jsonPath("$.nombre").value("Carlos"))
				.andExpect(jsonPath("$.apellido").value("González"))
                .andExpect(jsonPath("$.dni").value("12312312"))
                .andExpect(jsonPath("$.email").value("carlos.gonzalez@example.com"))
				.andExpect(jsonPath("$.fecha_nac").value("20/04/1982"))
				.andExpect(jsonPath("$.telefono").value("1166885544"))
				.andExpect(jsonPath("$.direccion").value("Calle falsa 124"))
				.andExpect(jsonPath("$.tiene_obra_social").value(false));
	}
}
