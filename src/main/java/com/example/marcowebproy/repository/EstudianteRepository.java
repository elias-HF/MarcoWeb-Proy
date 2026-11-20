package com.example.marcowebproy.repository;

import com.example.marcowebproy.entity.EstudianteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<EstudianteEntity, Integer> {
    Optional<EstudianteEntity> findByCorreo(String correo);
}
