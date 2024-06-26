package com.example.ClinicaOdontologicaBackend.repository;

import com.example.ClinicaOdontologicaBackend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

}

