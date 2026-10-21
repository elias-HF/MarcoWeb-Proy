package com.example.marcowebproy.repository;

import com.example.marcowebproy.entity.PrestamoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<PrestamoEntity, Integer> {
    boolean existsByEstudianteIdAndEstadoIn(int estudianteId, List<String> estados);

    long countByEstudianteIdAndEstadoIn(int estudianteId, List<String> estados);
}
