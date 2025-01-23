package com.example.clinica_medica.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Table(name = "servicios_medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServicioMedico {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo_servicio;
	private String nombre;
	private String descripcion;
	private Double precio;
	// es manytomany
	@ManyToMany
	@JoinTable(name = "rel_serviciomedico_paquete", 
		joinColumns = @JoinColumn(name = "codigo_servicio"), 
		inverseJoinColumns = @JoinColumn(name = "codigo_paquete"))
	private List<PaqueteServicio> lista_paquetes;

}
