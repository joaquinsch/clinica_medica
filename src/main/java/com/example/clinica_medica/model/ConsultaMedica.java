package com.example.clinica_medica.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
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
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "tipo_consulta", discriminatorType = DiscriminatorType.STRING)
public class ConsultaMedica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_consulta_medica;

	private LocalDate fecha_consulta;
	private LocalTime hora_consulta;
	@ManyToOne
	private Paciente un_paciente;
	@ManyToOne
	private Medico un_medico;
	private TipoDeConsulta tipo_de_consulta;
	private Long id_servicio_o_paquete;
	private Double monto_total;
	private Boolean pagado_o_no;
}
