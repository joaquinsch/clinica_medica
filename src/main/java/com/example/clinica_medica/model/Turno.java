package com.example.clinica_medica.model;

import java.time.LocalDate;
import java.time.LocalTime;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "turnos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Turno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id_turno;
	@NotNull(message = "La fecha del turno no fue ingresada")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate fecha_turno;
	@NotNull(message = "La hora del turno no fue ingresada")
	@JsonFormat(pattern = "HH:mm")
	private LocalTime hora_turno;
	@ManyToOne
	@JoinColumn(name = "id_medico")
	@NotNull(message = "El médico no fue ingresado")
	private Medico un_medico;
	@NotNull(message = "La disponibilidad no fue ingresada")
	private Boolean disponibilidad;

}
