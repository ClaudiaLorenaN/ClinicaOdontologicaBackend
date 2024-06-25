package com.example.ClinicaOdontologicaBackend.service;

import com.example.ClinicaOdontologicaBackend.dto.TurnoDTO;
import com.example.ClinicaOdontologicaBackend.entity.Domicilio;
import com.example.ClinicaOdontologicaBackend.entity.Odontologo;
import com.example.ClinicaOdontologicaBackend.entity.Paciente;
import com.example.ClinicaOdontologicaBackend.entity.Turno;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnoServiceTest {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;


    @Test
    @Order(1)
    public void guardarTurno() {
        Paciente paciente= new Paciente("Jorgito","pereyra","11111", LocalDate.of(2024,6,20),new Domicilio("calle falsa",123,"La Rioja","Argentina"),"jorge.pereyra@digitalhouse.com");
        Odontologo odontologo= new Odontologo("MP10","Gina","Arias");
        pacienteService.guardarPaciente(paciente);
        odontologoService.guardarOdontologo(odontologo);

        Turno turno = new Turno(paciente,odontologo,LocalDate.of(2024,6,20));
        TurnoDTO turnoGuardado = turnoService.registrarTurno(turno);
        assertEquals(1L, turnoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarTurnoPorId() {
        Long id = 1L;
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarPorId(id);
        assertNotNull(turnoBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarTurno() {
        Long id = 1L;
        TurnoDTO turno = new TurnoDTO();
        turno.setId(id);
        turno.setPacienteId(3L);
        turno.setOdontologoId(3L);
        turno.setFecha(LocalDate.of(2024,6,20));

        turnoService.actualizarTurno(turno);
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarPorId(id);
        assertEquals(LocalDate.of(2024,6,20), turnoBuscado.get().getFecha());
    }

    @Test
    @Order(4)
    public void listarTodos() {
        List<TurnoDTO> listaTurnos = turnoService.listarTodos();
        assertEquals(1, listaTurnos.size());
    }

    @Test
    @Order(5)
    public void eliminarTurno() {
        turnoService.eliminarTurno(1L);
        Optional<TurnoDTO> turnoEliminado = turnoService.buscarPorId(2L);
        assertFalse(turnoEliminado.isPresent());
    }
}

