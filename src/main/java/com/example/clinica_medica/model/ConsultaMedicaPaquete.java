package com.example.clinica_medica.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ConsultaMedicaPaquete extends ConsultaMedica {

	private Long id_consulta_medica_paquete;
	private PaqueteServicio paquete_servicio;
}
