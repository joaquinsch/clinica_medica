package com.example.clinica_medica.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Turno {
	private Long id_turno;
	private LocalDate fecha_turno;
	private Medico un_medico;

}
