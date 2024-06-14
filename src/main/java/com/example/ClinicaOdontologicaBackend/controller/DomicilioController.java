package com.example.ClinicaOdontologicaBackend.controller;

import com.example.ClinicaOdontologicaBackend.entity.Domicilio;
import com.example.ClinicaOdontologicaBackend.exception.ResourceNotFoundException;
import com.example.ClinicaOdontologicaBackend.service.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {
    @Autowired
    DomicilioService domicilioService;

    @PostMapping
    public ResponseEntity<Domicilio> guardarDomicilio(@RequestBody Domicilio domicilio){
        return ResponseEntity.ok(domicilioService.guardarDomicilio(domicilio));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<Domicilio>> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(domicilioService.buscarPorId(id));
    }

    @PutMapping
    public ResponseEntity<String> actualizarDomicilio(@RequestBody Domicilio domicilio) throws ResourceNotFoundException{
        Optional<Domicilio> domicilioBuscado= domicilioService.buscarPorId(domicilio.getId());
        if(domicilioBuscado.isPresent()){
            domicilioService.actualizarDomicilio(domicilio);
            return ResponseEntity.ok("Domicilio actualizado con exito");
        }else{
            throw new ResourceNotFoundException("Domicilio no encontrado");
        }
    }

    @GetMapping
    public ResponseEntity<List<Domicilio>> listarTodos(){
        return ResponseEntity.ok(domicilioService.listarTodos());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarDomicilio(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Domicilio> domicilioBuscado = domicilioService.buscarPorId(id);
        if (domicilioBuscado.isPresent()){
            domicilioService.eliminarDomicilio(id);
            return ResponseEntity.ok("Domicilio eliminado con exito");
        }else{
            throw new ResourceNotFoundException("Domicilio no encontrado");
        }

    }

}
