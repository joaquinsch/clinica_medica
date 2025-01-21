package com.example.clinica_medica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "servicios_medicos")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServicioMedico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo_servicio;
	private String nombre;
	private String descripcion;
	private String precio;
}
