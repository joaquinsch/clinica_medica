package com.example.clinica_medica.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "consultas_medicas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ConsultaMedica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_consulta_medica;

	@NotNull(message = "La fecha de la consulta no fue ingresada")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate fecha_consulta;
	@NotNull(message = "La hora de la consulta no fue ingresada")
	@JsonFormat(pattern = "HH:mm")
	private LocalTime hora_consulta;
	@ManyToOne
	@JoinColumn(name = "id_paciente")
	@NotNull(message = "No se ha ingresado un paciente")
	private Paciente un_paciente;
	@ManyToOne
	@JoinColumn(name = "id_medico")
	@NotNull(message = "No se ha ingresado un médico")
	private Medico un_medico;

	/*
	 * ACA NO ENCONTRE OTRA FORMA DE PODER ELEGIR SI LA CONSULTAMEDICA ES CON
	 * PAQUETE O CON SERVICIOMEDICO.
	 * 
	 * EN LA TABLA VA A QUEDAR UNO DE LOS DOS EN NULL YA QUE LA CONSULTA MEDICA SE
	 * ELIGE POR PAQUETE O POR SERVICIO MEDICO.
	 * 
	 */

	@OneToOne(cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "codigo_paquete")

	private PaqueteServicio un_paquete_servicio;
	@ManyToOne
	@JoinColumn(name = "codigo_servicio")

	private ServicioMedico un_servicio_medico;

	private Double monto_total;
	private Boolean pagado_o_no;
}
