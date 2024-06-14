package com.example.ClinicaOdontologicaBackend.service;

import com.example.ClinicaOdontologicaBackend.entity.Odontologo;
import com.example.ClinicaOdontologicaBackend.entity.Paciente;
import com.example.ClinicaOdontologicaBackend.repository.OdontologoRepository;
import com.example.ClinicaOdontologicaBackend.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    @Autowired
    OdontologoRepository odontologoRepository;

    public Odontologo guardarOdontologo(Odontologo odontologo){return odontologoRepository.save(odontologo);}
    public Optional<Odontologo> buscarPorId(Long id){return odontologoRepository.findById(id);}
    public void actualizarOdontologo(Odontologo odontologo){odontologoRepository.save(odontologo);}
    public void eliminarOdontologo(Long id){odontologoRepository.deleteById(id);}
    public List<Odontologo> listarTodos(){return odontologoRepository.findAll();}


}
