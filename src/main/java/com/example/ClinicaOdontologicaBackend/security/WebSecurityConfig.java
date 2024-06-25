package com.example.ClinicaOdontologicaBackend.security;

import com.example.ClinicaOdontologicaBackend.entity.UsuarioRole;
import com.example.ClinicaOdontologicaBackend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity //aca ya no funciona postman ni nada que no se autentique.
public class WebSecurityConfig {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //proveedor de autenticaciones DAO
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        //aca debe venir el usuario y desencriptador de claves
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(usuarioService);
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        return provider;
    }

    //aca deberiamos hablar de las autorizaciones
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/login").permitAll() // Permitir acceso a la página de inicio de sesión
                        .requestMatchers("/favicon.ico", "/get_turnos.html","/index.html", "/TurnoAlta.html", "/js/get_pacientes_select.js", "/js/delete_turno.js", "/js/get_odontologos_select.js", "/js/get_turnos.js", "/js/update_turno.js", "/js/post_turno.js", "/turnos/**", "pacientes","pacientes/buscar/**", "/odontologos", "/odontologos/buscar/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .logout(withDefaults());
        return http.build();

    }

}
