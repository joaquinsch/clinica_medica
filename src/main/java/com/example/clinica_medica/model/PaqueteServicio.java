package com.example.clinica_medica.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class PaqueteServicio extends TipoDeConsulta{

	/**
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo_paquete;
	@OneToMany(mappedBy = "un_paquete_servicio")
	private List<ServicioMedico> lista_servicios_incluidos; 
	private String descripcion;
	private Double precio_paquete;
}
