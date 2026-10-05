package com.example.marcowebproy.repository;

import com.example.marcowebproy.entity.RecursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecursoRepository extends JpaRepository<RecursoEntity, Integer> {
}
