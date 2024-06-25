package com.example.ClinicaOdontologicaBackend.service;

import com.example.ClinicaOdontologicaBackend.entity.Domicilio;
import com.example.ClinicaOdontologicaBackend.entity.Odontologo;
import com.example.ClinicaOdontologicaBackend.entity.Paciente;
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
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;


    @Test
    @Order(1)
    public void guardarOdontologo(){
        Odontologo odontologo = new Odontologo( "123","Jorgito", "pereyra");
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo);
        assertEquals(3L, odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarOdontologoPorId(){
        Long id =3L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
        assertNotNull(odontologoBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarOdontologo(){
        Long id =3L;
        Odontologo odontologo = new Odontologo(id, "123", "German", "test");
        odontologoService.actualizarOdontologo(odontologo);
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
        assertEquals("German", odontologoBuscado.get().getNombre());
    }

    @Test
    @Order(4)
    public void listarTodos(){
        List<Odontologo> listaOdontologos= odontologoService.listarTodos();
        assertEquals(3, listaOdontologos.size());
    }

    @Test
    @Order(5)
    public void eliminarOdontologo(){
        odontologoService.eliminarOdontologo(3L);
        Optional<Odontologo> odontologoEliminado = odontologoService.buscarPorId(3L);
        assertFalse(odontologoEliminado.isPresent());
    }



    }


