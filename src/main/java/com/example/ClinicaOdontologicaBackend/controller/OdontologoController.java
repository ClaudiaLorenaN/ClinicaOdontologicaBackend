package com.example.ClinicaOdontologicaBackend.controller;

import com.example.ClinicaOdontologicaBackend.entity.Odontologo;
import com.example.ClinicaOdontologicaBackend.exception.BadRequestException;
import com.example.ClinicaOdontologicaBackend.exception.ResourceNotFoundException;
import com.example.ClinicaOdontologicaBackend.repository.OdontologoRepository;
import com.example.ClinicaOdontologicaBackend.service.OdontologoService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    OdontologoService odontologoService;

    //changes
    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {
        if(StringUtils.isEmpty(odontologo.getMatricula())){
            throw new BadRequestException("La matrícula esta vacía");
        }
        if(StringUtils.isEmpty(odontologo.getNombre())){
            throw new BadRequestException("El nombre esta vacío");
        }
        if(StringUtils.isEmpty(odontologo.getApellido())){
            throw new BadRequestException("El apellido esta vacío");
        }
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }
    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) throws ResourceNotFoundException, BadRequestException{
        if (odontologo.getId() == null || odontologo.getId() <= 0) {
            throw new BadRequestException("El ID no es válido");
        }
        if (StringUtils.isEmpty(odontologo.getMatricula())) {
            throw new BadRequestException("La matrícula está vacía");
        }
        if (StringUtils.isEmpty(odontologo.getNombre())) {
            throw new BadRequestException("El nombre está vacío");
        }
        if (StringUtils.isEmpty(odontologo.getApellido())) {
            throw new BadRequestException("El apellido está vacío");
        }
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorId(odontologo.getId());
        if (odontologoBuscado.isPresent()){
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Odontologo actualizado con éxito");
        }else{
            throw new ResourceNotFoundException("Odontologo no encontrado");
        }
    }

    //changes
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarPorId(id);
        if(odontologoEncontrado.isPresent()){
            return ResponseEntity.ok(odontologoEncontrado);
        }else{
            throw new ResourceNotFoundException("Odontologo no encontrado");
        }
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodos(){
        return ResponseEntity.ok(odontologoService.listarTodos());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorId(id);
        if(odontologoBuscado.isPresent()){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("Odontologo eliminado con éxito");
        }else{
            throw new ResourceNotFoundException("Odontologo no encontrado");
        }
    }
}
