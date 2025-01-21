package com.example.clinica_medica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Paciente extends Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_paciente;
}
