package com.example.marcowebproy.service;

import com.example.marcowebproy.adapter.EstudianteAdapter;
import com.example.marcowebproy.entity.EstudianteEntity;
import com.example.marcowebproy.model.Estudiante;
import com.example.marcowebproy.repository.EstudianteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RegistroService {
    private final EstudianteRepository estudianteRepository;
    private final EstudianteAdapter estudianteAdapter = new EstudianteAdapter();

    public RegistroService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Transactional
    public void registrarEstudiante(String nombres,String apellidos, String email, String carrera, String codigoEstudiante, String password, String confirmarPassword) {

        if (!password.equals(confirmarPassword)) {
            throw new IllegalArgumentException("Las contraseñas no coinciden.");
        }

        boolean correoExiste = estudianteRepository.findAll().stream()
                .anyMatch(e -> e.getCorreo() != null && e.getCorreo().equalsIgnoreCase(email));

        if (correoExiste) {
            throw new IllegalStateException("El correo institucional ya se encuentra registrado.");
        }

        Estudiante modeloEstudiante = new Estudiante();
        modeloEstudiante.setCodigoEstudiante(codigoEstudiante);
        modeloEstudiante.setNombre(nombres);
        modeloEstudiante.setApellido(apellidos);
        modeloEstudiante.setCorreo(email);
        modeloEstudiante.setPassword(password);
        modeloEstudiante.setCarrera(carrera);
        modeloEstudiante.setEstado(true);
        modeloEstudiante.setPuntajeMerito(100);
        modeloEstudiante.setDemeritos(0);

        EstudianteEntity entidad = estudianteAdapter.toEntity(modeloEstudiante);
        estudianteRepository.save(entidad);
    }
}
