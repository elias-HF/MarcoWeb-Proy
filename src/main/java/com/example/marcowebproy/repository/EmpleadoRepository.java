package com.example.marcowebproy.repository;


import com.example.marcowebproy.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Integer> {
    Optional<EmpleadoEntity> findByCorreo(String correo);
}
