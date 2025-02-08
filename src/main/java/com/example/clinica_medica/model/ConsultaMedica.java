package com.example.clinica_medica.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "consultas_medicas")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
//esto crea un campo "discriminador" para diferenciar entre ConsultaMedica con paquete o serviciomedico
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // une en 1 tabla las clases q hereden
//@DiscriminatorColumn(name = "tipo_consulta", discriminatorType = DiscriminatorType.STRING)
public class ConsultaMedica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_consulta_medica;

	private LocalDate fecha_consulta;
	/*
	 * La Hora se pasa en json como: "09:10" por ejemplo
	 */
	private LocalTime hora_consulta;
	@ManyToOne
	@JoinColumn(name = "id_paciente")
	@JsonManagedReference
	private Paciente un_paciente;
	@ManyToOne
	@JoinColumn(name = "id_medico")
	private Medico un_medico;

	 /* ACA NO ENCONTRE OTRA FORMA DE PODER ELEGIR SI LA CONSULTAMEDICA ES
	  * CON PAQUETE O CON SERVICIOMEDICO.
	  * 
	  * EN LA TABLA VA A QUEDAR UNO DE LOS DOS EN NULL YA QUE LA CONSULTA MEDICA
	  * SE ELIGE POR PAQUETE O POR SERVICIO MEDICO.
	  * 
	  */
	
	// ESTO NO TIENE LADO INVERSO (TENER CUIDADO)
	@OneToOne
	@JoinColumn(name = "codigo_paquete")
	@JsonManagedReference
	private PaqueteServicio un_paquete_servicio;
	@ManyToOne
	@JoinColumn(name = "codigo_servicio")
	@JsonManagedReference
	private ServicioMedico un_servicio_medico;

	private Double monto_total;
	private Boolean pagado_o_no;
}
