package com.example.ClinicaOdontologicaBackend.controller;

import com.example.ClinicaOdontologicaBackend.entity.Odontologo;
import com.example.ClinicaOdontologicaBackend.exception.ResourceNotFoundException;
import com.example.ClinicaOdontologicaBackend.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo) {
        try {
            Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo);
            return ResponseEntity.ok(odontologoGuardado);
        } catch (Exception e) {
            // Manejo de la excepción genérica, podrías personalizar según el tipo de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        try {
            Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(odontologo.getId());
            if (odontologoBuscado.isPresent()) {
                odontologoService.actualizarOdontologo(odontologo);
                return ResponseEntity.ok("Odontologo actualizado con éxito");
            } else {
                throw new ResourceNotFoundException("Odontologo no encontrado");
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Long id) {
        try {
            Optional<Odontologo> odontologo = odontologoService.buscarPorId(id);
            return odontologo.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodos() {
        try {
            List<Odontologo> odontologos = odontologoService.listarTodos();
            return ResponseEntity.ok(odontologos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) {
        try {
            Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
            if (odontologoBuscado.isPresent()) {
                odontologoService.eliminarOdontologo(id);
                return ResponseEntity.ok("Odontologo eliminado con éxito");
            } else {
                throw new ResourceNotFoundException("Odontologo no encontrado");
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
