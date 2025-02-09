package com.example.clinica_medica.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "servicios_medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServicioMedico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo_servicio;
	private String nombre;
	private String descripcion;
	private Double precio;

	@ManyToMany(mappedBy = "lista_servicios_incluidos")
	@JsonIgnore
	private List<PaqueteServicio> lista_paquetes;

	@OneToMany(mappedBy = "un_servicio_medico")
	@JsonIgnore
	private List<ConsultaMedica> lista_consultas;

}
