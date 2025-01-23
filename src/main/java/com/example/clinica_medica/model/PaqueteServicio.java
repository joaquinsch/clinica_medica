package com.example.clinica_medica.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
//import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Table(name = "paquetes")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaqueteServicio{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo_paquete;
	@ManyToMany(mappedBy = "lista_paquetes")
	private List<ServicioMedico> lista_servicios_incluidos;
	private String descripcion;
	private Double precio_paquete;
}
