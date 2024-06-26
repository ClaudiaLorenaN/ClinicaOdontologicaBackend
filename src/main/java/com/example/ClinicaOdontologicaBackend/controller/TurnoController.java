package com.example.ClinicaOdontologicaBackend.controller;

import com.example.ClinicaOdontologicaBackend.dto.TurnoDTO;
import com.example.ClinicaOdontologicaBackend.entity.Odontologo;
import com.example.ClinicaOdontologicaBackend.entity.Paciente;
import com.example.ClinicaOdontologicaBackend.entity.Turno;
import com.example.ClinicaOdontologicaBackend.exception.BadRequestException;
import com.example.ClinicaOdontologicaBackend.exception.ResourceNotFoundException;
import com.example.ClinicaOdontologicaBackend.service.OdontologoService;
import com.example.ClinicaOdontologicaBackend.service.PacienteService;
import com.example.ClinicaOdontologicaBackend.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    TurnoService turnoService;

    @Autowired
    private PacienteService pacienteService;;

    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<TurnoDTO> guardarTurno(@RequestBody Turno turno) throws BadRequestException {
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorId(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorId(turno.getOdontologo().getId());

        if (!pacienteBuscado.isPresent()) {
            throw new BadRequestException("Paciente no encontrado");
        }

        if (!odontologoBuscado.isPresent()) {
            throw new BadRequestException("Odontólogo no encontrado");
        }

        if(turno.getFecha() == null){
            throw new BadRequestException("Fecha no valida");

        }
        turno.setPaciente(pacienteBuscado.get());
        turno.setOdontologo(odontologoBuscado.get());
        return ResponseEntity.ok(turnoService.registrarTurno(turno));

    }
    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turno) throws ResourceNotFoundException{
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarPorId(turno.getId());
        if (turnoBuscado.isPresent()){
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("Turno actualizado con éxito");
        }else{
            throw new ResourceNotFoundException("Turno no encontrado");
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<TurnoDTO>> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<TurnoDTO> turnoEncontrado = turnoService.buscarPorId(id);
        if(turnoEncontrado.isPresent()){
            return ResponseEntity.ok(turnoEncontrado);
        }else{
            throw new ResourceNotFoundException("Turno no encontrado");
        }
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listarTodos(){
        return ResponseEntity.ok(turnoService.listarTodos());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<TurnoDTO> turnoBuscado= turnoService.buscarPorId(id);
        if (turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Turno eliminado con exito");
        }else {
            throw new ResourceNotFoundException("Turno no encontrado");
        }
    }
}
