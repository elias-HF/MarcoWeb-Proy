package com.example.marcowebproy.service;

import com.example.marcowebproy.entity.EmpleadoEntity;
import com.example.marcowebproy.entity.EstudianteEntity;
import com.example.marcowebproy.model.CustomUserDetails;
import com.example.marcowebproy.repository.EmpleadoRepository;
import com.example.marcowebproy.repository.EstudianteRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
        if (estudianteOpt.isPresent()) {
            EstudianteEntity est = estudianteOpt.get();
            return new CustomUserDetails(
                    est.getCorreo(),
                    est.getPassword(),
                    List.of("ESTUDIANTE"),
                    est.isEstado(),
                    est.getNombre() + " " + est.getApellido()
            );
        }

        Optional<EmpleadoEntity> empleadoOpt = empleadoRepository.findByCorreo(correo);
        if (empleadoOpt.isPresent()) {
            EmpleadoEntity emp = empleadoOpt.get();
            return new CustomUserDetails(
                    emp.getCorreo(),
                    emp.getPassword(),
                    List.of("EMPLEADO"),
                    true,
                    emp.getNombre() + " " + emp.getApellido()
            );
        }

        throw new UsernameNotFoundException("Usuario no encontrado con el correo: " + correo);
    }
}
