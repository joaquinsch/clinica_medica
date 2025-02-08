package com.example.clinica_medica.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Pattern.Flag;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass // esto es para que puedan heredar otras clases sin que esta sea una tabla
public class Usuario {

	@NotBlank(message = "El nombre no fué ingresado")
	private String nombre;
	@NotBlank(message = "El apellido no fué ingresado")
	private String apellido;
	@NotBlank(message = "El DNI no fué ingresado")
	@Pattern(message = "El DNI debe ser un número de 8 dígitos", regexp = "^\\d{8}$")
	private String dni;
	@NotNull(message = "La fecha de nacimiento no fué ingresada")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate fecha_nac;
	@Email(message = "El email es inválido", regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Flag.CASE_INSENSITIVE)
	private String email;
	@Pattern(message = "El número de teléfono es inválido", regexp = "^\\d{8,15}$")
	private String telefono;
	@Pattern(message = "La dirección es debe tener entre 3 y 30 carácteres", regexp = "^.{3,30}$")
	private String direccion;

}
