package com.example.marcowebproy.adapter;

import com.example.marcowebproy.entity.PrestamoEntity;
import com.example.marcowebproy.model.Prestamo;

public class PrestamoAdapter {

    private final RecursoAdapter recursoAdapter = new RecursoAdapter();
    private final EstudianteAdapter estudianteAdapter = new EstudianteAdapter();

    public Prestamo toModel(PrestamoEntity entity) {
        if (entity == null) return null;

        Prestamo model = new Prestamo();
        model.setId(entity.getId());
        model.setFechaPrestamo(entity.getFechaPrestamo());
        model.setFechaLimiteDevolucion(entity.getFechaLimiteDevolucion());
        model.setFechaDevolucion(entity.getFechaDevolucion());
        model.setEstado(entity.getEstado());
        model.setObservacionDevolucion(entity.getObservacionDevolucion());
        model.setCondicionDevolucion(entity.getCondicionDevolucion());

        if (entity.getRecurso() != null) {
            model.setRecurso(recursoAdapter.toModel(entity.getRecurso()));
        }
        if (entity.getEstudiante() != null) {
            model.setEstudiante(estudianteAdapter.toModel(entity.getEstudiante()));
        }

        return model;
    }

    public PrestamoEntity toEntity(Prestamo model) {
        if (model == null) return null;

        PrestamoEntity entity = new PrestamoEntity();
        entity.setId(model.getId());
        entity.setFechaPrestamo(model.getFechaPrestamo());
        entity.setFechaLimiteDevolucion(model.getFechaLimiteDevolucion());
        entity.setFechaDevolucion(model.getFechaDevolucion());
        entity.setEstado(model.getEstado());
        entity.setObservacionDevolucion(model.getObservacionDevolucion());
        entity.setCondicionDevolucion(model.getCondicionDevolucion());

        if (model.getRecurso() != null) {
            entity.setRecurso(recursoAdapter.toEntity(model.getRecurso()));
        }
        if (model.getEstudiante() != null) {
            entity.setEstudiante(estudianteAdapter.toEntity(model.getEstudiante()));
        }

        return entity;
    }
}
