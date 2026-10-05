package com.example.marcowebproy.repository;

import com.example.marcowebproy.entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<ReservaEntity, Integer> {
}
