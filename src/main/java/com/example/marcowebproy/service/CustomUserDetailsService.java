package com.example.marcowebproy.service;

import com.example.marcowebproy.entity.EmpleadoEntity;
import com.example.marcowebproy.entity.EstudianteEntity;
import com.example.marcowebproy.repository.EmpleadoRepository;
import com.example.marcowebproy.repository.EstudianteRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {
    private final EstudianteRepository estudianteRepository;
    private final EmpleadoRepository empleadoRepository;

    public CustomUserDetailsService(EstudianteRepository estudianteRepository,EmpleadoRepository empleadoRepository) {
        this.estudianteRepository = estudianteRepository;
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Optional<EstudianteEntity> estudianteOpt = estudianteRepository.findByCorreo(correo);
        if (estudianteOpt.isPresent()){
            EstudianteEntity estudiante = estudianteOpt.get();
            return new User(estudiante.getCorreo(), estudiante.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_ESTUDIANTE")));
        }

        Optional<EmpleadoEntity> empleadoOpt = empleadoRepository.findByCorreo(correo);
        if(empleadoOpt.isPresent()){
            EmpleadoEntity empleado = empleadoOpt.get();
            return new User(empleado.getCorreo(), empleado.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_EMPLEADO")));
        }

        throw new UsernameNotFoundException("Usuario no encontrado con el correo: " + correo);
    }
}
