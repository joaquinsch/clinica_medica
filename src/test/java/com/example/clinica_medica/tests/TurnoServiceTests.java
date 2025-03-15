package com.example.clinica_medica.tests;

import com.example.clinica_medica.exception.TurnoNoDisponibleError;
import com.example.clinica_medica.model.Medico;
import com.example.clinica_medica.model.Turno;
import com.example.clinica_medica.repository.TurnoRepository;
import com.example.clinica_medica.services.MedicoService;
import com.example.clinica_medica.services.TurnoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TurnoServiceTests {

    @Mock
    private TurnoRepository turnoRepository;

    @Mock
    private MedicoService medicoService;

    @InjectMocks
    private TurnoService turnoService;

    private Medico med;

    @BeforeEach
    void setUp(){
        med = new Medico();
        med.setId_medico(1L);
        med.setNombre("asd");
        med.setApellido("asd");
        med.setDni("12341234");
        med.setFecha_nac(LocalDate.of(1959,10,10));
        med.setEmail("asd@mail.com");
        med.setTelefono("1234123412");
        med.setDireccion("asd 123");
        med.setTurnos_disponibles(List.of(new Turno()));

    }

    @Test
    public void deberiaGuardarElTurno(){
        Turno turno = new Turno();
        turno.setId_turno(1L);
        turno.setFecha_turno(LocalDate.of(2025, 3,16));
        turno.setHora_turno(LocalTime.of(19, 0));
        turno.setUn_medico(med);

        Mockito.when(turnoRepository.save(turno)).thenReturn(turno);
        Mockito.when(medicoService.buscarMedico(med.getId_medico())).thenReturn(med);
        Turno turnoGuardado = turnoService.guardarTurno(turno);
        Assertions.assertNotNull(turnoGuardado);
        Assertions.assertEquals(LocalTime.of(19,0), turnoGuardado.getHora_turno());
        Mockito.verify(turnoRepository, Mockito.times(1)).save(turno);
    }

    @Test
    public void deberiaDarErrorSiCreaTurnoConFechaPasada(){
        Turno turno = new Turno();
        turno.setId_turno(1L);
        turno.setFecha_turno(LocalDate.of(2025, 3,13));
        turno.setHora_turno(LocalTime.of(19, 0));
        turno.setUn_medico(med);

        Assertions.assertThrows(TurnoNoDisponibleError.class,
                () -> turnoService.guardarTurno(turno));
    }
}
