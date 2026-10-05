package com.example.marcowebproy.repository;

import com.example.marcowebproy.entity.EspacioAcademicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspacioAcademicoRepository extends JpaRepository<EspacioAcademicoEntity, Integer> {
}
