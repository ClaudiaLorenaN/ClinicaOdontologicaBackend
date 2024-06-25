package com.example.ClinicaOdontologicaBackend.security;

import com.example.ClinicaOdontologicaBackend.dto.TurnoDTO;
import com.example.ClinicaOdontologicaBackend.entity.*;
import com.example.ClinicaOdontologicaBackend.repository.OdontologoRepository;
import com.example.ClinicaOdontologicaBackend.repository.PacienteRepository;
import com.example.ClinicaOdontologicaBackend.repository.TurnoRepository;
import com.example.ClinicaOdontologicaBackend.repository.UsuarioRepository;
import com.example.ClinicaOdontologicaBackend.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatosIniciales implements ApplicationRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private OdontologoRepository odontologoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passSinCifrar = "user";
        String passCifrado = passwordEncoder.encode(passSinCifrar);
        Usuario usuario = new Usuario("jorgito","jpereryradh","user@user.com",passCifrado, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario);

        String passSinCifrar1 = "admin";
        String passCifrado1 = passwordEncoder.encode(passSinCifrar1);
        Usuario usuarioAdmin = new Usuario("Claudia","ClauLore","admin@admin.com",passCifrado1, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuarioAdmin);

        //Creando 2 odontologos
        Odontologo odontologo = new Odontologo("10b","Carlos", "Lopez");
        odontologoRepository.save(odontologo);
        Odontologo odontologo1 = new Odontologo("10c","Lucas", "Sandoval");
        odontologoRepository.save(odontologo1);

        //Creando 2 Pacientes
        Paciente paciente = new Paciente("Luis","Sanchez","1111", LocalDate.of(2024, 05, 21), new Domicilio("calle1", 123, "localidad1", "provincia1"), "luis@luis.com");
        pacienteRepository.save(paciente);
        Paciente paciente1 = new Paciente("Luisa","Hoyos","2222", LocalDate.of(2024, 05, 21), new Domicilio("calle2", 123, "localidad2", "provincia2"), "luisa@luisa.com");
        pacienteRepository.save(paciente1);


    }
}
