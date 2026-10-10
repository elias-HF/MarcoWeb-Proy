package com.example.marcowebproy.repository;

import com.example.marcowebproy.entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Integer> {
    List<ReservaEntity> findByEspacioAcademicoIdAndFechaReservaAndEstadoNotIn(
            int espacioId,
            LocalDate fechaReserva,
            List<String> estadosExcluidos
    );
}
