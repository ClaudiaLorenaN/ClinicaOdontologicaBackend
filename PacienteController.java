package com.example.ClinicaOdontologicaBackend.controller;

import com.example.ClinicaOdontologicaBackend.entity.Odontologo;
import com.example.ClinicaOdontologicaBackend.entity.Paciente;
import com.example.ClinicaOdontologicaBackend.exception.ResourceNotFoundException;
import com.example.ClinicaOdontologicaBackend.service.DomicilioService;
import com.example.ClinicaOdontologicaBackend.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController //para trabajar sin tecnologia de vista
// @Controller<-- es controller pq vamos a usar una tecnologia de vista

@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @PostMapping //--> nos permite persistir los datos que vienen desde la vista
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente) {
        try {
            Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);
            return ResponseEntity.ok(pacienteGuardado);
        } catch (Exception e) {
            // Manejo de la excepción genérica, podrías personalizar según el tipo de error
            return ResponseEntity.status(Objects.requireNonNull(INTERNAL_SERVER_ERROR)).build();
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) {
        try {
            Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(paciente.getId());
            if (pacienteBuscado.isPresent()) {
                pacienteService.actualizarPaciente(paciente);
                return ResponseEntity.ok("Paciente actualizado con éxito");
            } else {
                throw new ResourceNotFoundException("Paciente no encontrado");
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Paciente> buscarPorPaciente(@PathVariable Long id) {
        try {
            Optional<Paciente> paciente = pacienteService.buscarPorId(id);
            return paciente.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/buscarEmail/{email}")
    public ResponseEntity<Optional<Paciente>> buscarPorEmail(@PathVariable String email) {
        try {
            Optional<Paciente> pacienteBuscado = pacienteService.buscarPorEmail(email);
            if (pacienteBuscado.isPresent()) {
                return ResponseEntity.ok(pacienteBuscado);
            } else {
                throw new ResourceNotFoundException("Paciente no encontrado");
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(Optional.empty());
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos() {
        try {
            List<Paciente> pacientes = pacienteService.listarTodos();
            return ResponseEntity.ok(pacientes);
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) {
        try {
            Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(id);
            if (pacienteBuscado.isPresent()) {
                pacienteService.eliminarPaciente(id);
                return ResponseEntity.ok("Paciente eliminado con éxito");
            } else {
                throw new ResourceNotFoundException("Paciente no encontrado");
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
