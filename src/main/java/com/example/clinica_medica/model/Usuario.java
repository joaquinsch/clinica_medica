package com.example.clinica_medica.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

	private String nombre;
	private String apellido;
	private String dni;
	private LocalDate fecha_nac;
	private String email;
	private String telefono;
	private String direccion;
}
