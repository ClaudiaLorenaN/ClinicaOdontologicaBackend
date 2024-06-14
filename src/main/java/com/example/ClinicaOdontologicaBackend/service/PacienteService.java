package com.example.ClinicaOdontologicaBackend.service;

import com.example.ClinicaOdontologicaBackend.entity.Domicilio;
import com.example.ClinicaOdontologicaBackend.entity.Paciente;
import com.example.ClinicaOdontologicaBackend.repository.DomicilioRepository;
import com.example.ClinicaOdontologicaBackend.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private DomicilioRepository domicilioRepository;

    public Paciente guardarPaciente(Paciente paciente){
        Optional<Domicilio> domicilio = domicilioRepository.findById(paciente.getDomicilio().getId());
        if (domicilio.isPresent()) {
            paciente.setDomicilio(domicilio.get());
            return pacienteRepository.save(paciente);
        }else{
            return null;
        }

    }
    public Optional<Paciente> buscarPorId(Long id){
        return pacienteRepository.findById(id);
    }
    public Optional<Paciente> buscarPorEmail(String email){
        return pacienteRepository.findByEmail(email);
    }
    public void actualizarPaciente(Paciente paciente){
        Optional<Domicilio> domicilio = domicilioRepository.findById(paciente.getDomicilio().getId());
        if (domicilio.isPresent()) {
            paciente.setDomicilio(domicilio.get());
            pacienteRepository.save(paciente);
        }
    }
    public void eliminarPaciente(Long id){
        pacienteRepository.deleteById(id);
    }
    public List<Paciente> listarTodos(){
        return pacienteRepository.findAll();
    }
}
