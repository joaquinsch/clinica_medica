package com.example.clinica_medica.model;

import java.time.LocalDate;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass // esto es para que puedan heredar otras clases sin que esta sea un Entity
public class Usuario {

	private String nombre;
	private String apellido;
	private String dni;
	private LocalDate fecha_nac;
	private String email;
	private String telefono;
	private String direccion;
}
