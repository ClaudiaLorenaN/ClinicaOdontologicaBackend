package com.example.ClinicaOdontologicaBackend.controller;

import com.example.ClinicaOdontologicaBackend.entity.Turno;
import com.example.ClinicaOdontologicaBackend.exception.ResourceNotFoundException;
import com.example.ClinicaOdontologicaBackend.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    TurnoService turnoService;

    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno) {
        try {
            Turno turnoGuardado = turnoService.guardarTurno(turno);
            return ResponseEntity.ok(turnoGuardado);
        } catch (Exception e) {
            // Manejo de la excepción genérica, podrías personalizar según el tipo de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno) {
        try {
            Optional<Turno> turnoBuscado = turnoService.buscarPorId(turno.getId());
            if (turnoBuscado.isPresent()) {
                turnoService.actualizarTurno(turno);
                return ResponseEntity.ok("Turno actualizado con éxito");
            } else {
                throw new ResourceNotFoundException("Turno no encontrado");
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Long id) {
        try {
            Optional<Turno> turno = turnoService.buscarPorId(id);
            return turno.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTodos() {
        try {
            List<Turno> turnos = turnoService.listarTodos();
            return ResponseEntity.ok(turnos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) {
        try {
            Optional<Turno> turnoBuscado = turnoService.buscarPorId(id);
            if (turnoBuscado.isPresent()) {
                turnoService.eliminarTurno(id);
                return ResponseEntity.ok("Turno eliminado con éxito");
            } else {
                throw new ResourceNotFoundException("Turno no encontrado");
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
