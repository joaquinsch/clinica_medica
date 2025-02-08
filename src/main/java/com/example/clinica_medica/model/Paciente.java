package com.example.clinica_medica.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
public class Paciente extends Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_paciente;
	
	@NotNull(message = "Debes colocar si tiene obra social o no")
	private Boolean tiene_obra_social;

	@OneToMany(mappedBy = "un_paciente")
	private List<ConsultaMedica> consultas;
	

}
