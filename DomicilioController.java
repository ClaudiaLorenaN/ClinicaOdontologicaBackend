package com.example.ClinicaOdontologicaBackend.controller;

import com.example.ClinicaOdontologicaBackend.entity.Domicilio;
import com.example.ClinicaOdontologicaBackend.exception.ResourceNotFoundException;
import com.example.ClinicaOdontologicaBackend.service.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {
    @Autowired
    DomicilioService domicilioService;

    @PostMapping
    public ResponseEntity<Domicilio> guardarDomicilio(@RequestBody Domicilio domicilio) {
        try {
            Domicilio domicilioGuardado = domicilioService.guardarDomicilio(domicilio);
            return ResponseEntity.ok(domicilioGuardado);
        } catch (Exception e) {
            // Manejo de la excepción genérica, podrías personalizar según el tipo de error
            return ResponseEntity.status(Objects.requireNonNull(HttpStatus.INTERNAL_SERVER_ERROR)).build();
        }

    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Domicilio> buscarPorId(@PathVariable Long id) {
        try {
            Optional<Domicilio> domicilio = domicilioService.buscarPorId(id);
            return domicilio.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            // Manejo de la excepción genérica, podrías personalizar según el tipo de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarDomicilio(@RequestBody Domicilio domicilio) {
        try {
            Optional<Domicilio> domicilioBuscado = domicilioService.buscarPorId(domicilio.getId());
            if (domicilioBuscado.isPresent()) {
                domicilioService.actualizarDomicilio(domicilio);
                return ResponseEntity.ok("Domicilio actualizado con éxito");
            } else {
                throw new ResourceNotFoundException("Domicilio no encontrado");
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Domicilio>> listarTodos() {
        try {
            List<Domicilio> domicilios = domicilioService.listarTodos();
            return ResponseEntity.ok(domicilios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarDomicilio(@PathVariable Long id) {
        try {
            Optional<Domicilio> domicilioBuscado = domicilioService.buscarPorId(id);
            if (domicilioBuscado.isPresent()) {
                domicilioService.eliminarDomicilio(id);
                return ResponseEntity.ok("Domicilio eliminado con éxito");
            } else {
                throw new ResourceNotFoundException("Domicilio no encontrado");
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
