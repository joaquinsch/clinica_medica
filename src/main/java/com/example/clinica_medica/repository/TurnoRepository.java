package com.example.clinica_medica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.clinica_medica.model.Turno;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long>{

}
