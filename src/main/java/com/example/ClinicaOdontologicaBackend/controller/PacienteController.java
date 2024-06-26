package com.example.ClinicaOdontologicaBackend.controller;

import com.example.ClinicaOdontologicaBackend.entity.Domicilio;
import com.example.ClinicaOdontologicaBackend.entity.Odontologo;
import com.example.ClinicaOdontologicaBackend.entity.Paciente;
import com.example.ClinicaOdontologicaBackend.exception.BadRequestException;
import com.example.ClinicaOdontologicaBackend.exception.ResourceNotFoundException;
import com.example.ClinicaOdontologicaBackend.repository.PacienteRepository;
import com.example.ClinicaOdontologicaBackend.service.PacienteService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //para trabajar sin tecnologia de vista
// @Controller<-- es controller pq vamos a usar una tecnologia de vista

@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private PacienteService pacienteService;

    @PostMapping //--> nos permite persistir los datos que vienen desde la vista
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente)throws BadRequestException{
        if(StringUtils.isEmpty(paciente.getCedula())){
            throw new BadRequestException("La cédula esta vacía");
        }
        if(StringUtils.isEmpty(paciente.getNombre())){
            throw new BadRequestException("El nombre esta vacío");
        }
        if(StringUtils.isEmpty(paciente.getApellido())){
            throw new BadRequestException("El apellido esta vacío");
        }
        if (paciente.getFechaIngreso() == null) {
            throw new BadRequestException("La fecha de ingreso es nula");
        }
        if(StringUtils.isEmpty(paciente.getEmail())){
            throw new BadRequestException("El email esta vacío");
        }

        Domicilio domicilio = paciente.getDomicilio();
        if (StringUtils.isEmpty(domicilio.getCalle())) {
            throw new BadRequestException("La calle del domicilio está vacía");
        }

        if (paciente.getDomicilio().getNumero() == null || domicilio.getNumero() <= 0) {
            throw new BadRequestException("El número del domicilio no es válido");
        }

        if (StringUtils.isEmpty(domicilio.getLocalidad())) {
            throw new BadRequestException("La localidad del domicilio está vacía");
        }

        if (StringUtils.isEmpty(domicilio.getProvincia())) {
            throw new BadRequestException("La provincia del domicilio está vacía");
        }
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) throws ResourceNotFoundException, BadRequestException{
        if (StringUtils.isEmpty(paciente.getNombre())) {
            throw new BadRequestException("El nombre está vacío");
        }

        if (StringUtils.isEmpty(paciente.getApellido())) {
            throw new BadRequestException("El apellido está vacío");
        }

        if (StringUtils.isEmpty(paciente.getCedula())) {
            throw new BadRequestException("La cédula está vacía");
        }

        if (paciente.getFechaIngreso() == null) {
            throw new BadRequestException("La fecha de ingreso es nula");
        }

        if (StringUtils.isEmpty(paciente.getDomicilio().getCalle())) {
            throw new BadRequestException("La calle del domicilio está vacía");
        }

        if (paciente.getDomicilio().getNumero() == null || paciente.getDomicilio().getNumero() <= 0) {
            throw new BadRequestException("El número del domicilio no es válido");
        }

        if (StringUtils.isEmpty(paciente.getDomicilio().getLocalidad())) {
            throw new BadRequestException("La localidad del domicilio está vacía");
        }

        if (StringUtils.isEmpty(paciente.getDomicilio().getProvincia())) {
            throw new BadRequestException("La provincia del domicilio está vacía");
        }

        if (StringUtils.isEmpty(paciente.getEmail())) {
            throw new BadRequestException("El email está vacío");
        }

        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(paciente.getId());
        if(pacienteBuscado.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Paciente actualizado con éxito");
        }else{
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPorPaciente(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteEncontrado = pacienteService.buscarPorId(id);
        if(pacienteEncontrado.isPresent()){
            return ResponseEntity.ok(pacienteEncontrado);
        }else{
            throw new ResourceNotFoundException("Paciente no encontrado");
        }

    }

    @GetMapping("/buscarEmail/{email}")
    public ResponseEntity<Optional<Paciente>> buscarPorEmail(@PathVariable String email) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorEmail(email);
        if(pacienteBuscado.isPresent()){
            pacienteService.buscarPorEmail(email);
            return ResponseEntity.ok(pacienteBuscado);
        }else{
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String > eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(id);
        if(pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Paciente eliminado con éxito");
        }else{
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
    }

}
