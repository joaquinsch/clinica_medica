package com.example.clinica_medica.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaMedica {

	private LocalDate fecha_consulta;
	private LocalTime hora_consulta;
	private Paciente un_paciente;
	private Medico un_medico;
	//(el servicio o paquete contratado)
	private Double monto_total;
	private Boolean pagado_o_no;
}
