package com.example.marcowebproy.repository;

import com.example.marcowebproy.entity.PrestamoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<PrestamoEntity, Integer> {
}
