package com.example.ClinicaOdontologicaBackend.service;

import com.example.ClinicaOdontologicaBackend.entity.Usuario;
import com.example.ClinicaOdontologicaBackend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    //aca necesitamos un metodo que nos devuelva la autenticación
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //cómo resolvemos?
        Optional<Usuario> usuarioBuscado = usuarioRepository.findByEmail(username);
        if (usuarioBuscado.isPresent()){
            return usuarioBuscado.get();
        }else{
            throw new UsernameNotFoundException("Usuario inexistente: " +  username);
        }
    }
}
