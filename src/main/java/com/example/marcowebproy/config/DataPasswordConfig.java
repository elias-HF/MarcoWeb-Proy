package com.example.marcowebproy.config;

import com.example.marcowebproy.entity.EmpleadoEntity;
import com.example.marcowebproy.entity.EstudianteEntity;
import com.example.marcowebproy.repository.EmpleadoRepository;
import com.example.marcowebproy.repository.EstudianteRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class DataPasswordConfig {

    @Bean
    @Transactional
    public CommandLineRunner encriptarPasswordsExistentes(EstudianteRepository estudianteRepository, EmpleadoRepository empleadoRepository, PasswordEncoder passwordEncoder){
        return args ->{
            List<EstudianteEntity> estudiantes = estudianteRepository.findAll();
            for(EstudianteEntity est : estudiantes){
                String clave = est.getPassword();
                if(clave != null && !clave.isBlank() && !esHashBcrypt(clave)){
                    est.setPassword(passwordEncoder.encode(clave));
                    estudianteRepository.save(est);
                }
            }
            List<EmpleadoEntity> empleados = empleadoRepository.findAll();
            for(EmpleadoEntity emp : empleados){
                String clave = emp.getPassword();
                if(clave != null && !clave.isBlank() && !esHashBcrypt(clave)){
                    emp.setPassword(passwordEncoder.encode(clave));
                    empleadoRepository.save(emp);
                }
            }
        };
    }

    private boolean esHashBcrypt(String valor){
        return valor.startsWith("$2a$") || valor.startsWith("$2b$") || valor.startsWith("$2y$");
    }
}
