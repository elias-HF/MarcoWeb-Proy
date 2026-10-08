package com.example.marcowebproy.adapter;

import com.example.marcowebproy.entity.ReservaEntity;
import com.example.marcowebproy.model.Reserva;

public class ReservaAdapter {

    private final EspacioAcademicoAdapter espacioAdapter = new EspacioAcademicoAdapter();
    private final RecursoAdapter recursoAdapter = new RecursoAdapter();
    private final EstudianteAdapter estudianteAdapter = new EstudianteAdapter();

    public Reserva toModel(ReservaEntity entity) {
        if (entity == null) return null;

        Reserva model = new Reserva();
        model.setId(entity.getId());
        model.setFechaReserva(entity.getFechaReserva());
        model.setHoraInicio(entity.getHoraInicio());
        model.setHoraFin(entity.getHoraFin());
        model.setEstado(entity.getEstado());
        model.setMotivoRechazo(entity.getMotivoRechazo());
        model.setFechaHoraInicio(entity.getFechaHoraInicio());
        model.setFechaHoraFin(entity.getFechaHoraFin());

        if (entity.getEspacioAcademico() != null) {
            model.setEspacioAcademico(espacioAdapter.toModel(entity.getEspacioAcademico()));
        }
        if (entity.getRecurso() != null) {
            model.setRecurso(recursoAdapter.toModel(entity.getRecurso()));
        }
        if (entity.getEstudiante() != null) {
            model.setEstudiante(estudianteAdapter.toModel(entity.getEstudiante()));
        }

        return model;
    }

    public ReservaEntity toEntity(Reserva model) {
        if (model == null) return null;

        ReservaEntity entity = new ReservaEntity();
        entity.setId(model.getId());
        entity.setFechaReserva(model.getFechaReserva());
        entity.setHoraInicio(model.getHoraInicio());
        entity.setHoraFin(model.getHoraFin());
        entity.setEstado(model.getEstado());
        entity.setMotivoRechazo(model.getMotivoRechazo());
        entity.setFechaHoraInicio(model.getFechaHoraInicio());
        entity.setFechaHoraFin(model.getFechaHoraFin());

        if (model.getEspacioAcademico() != null) {
            entity.setEspacioAcademico(espacioAdapter.toEntity(model.getEspacioAcademico()));
        }
        if (model.getRecurso() != null) {
            entity.setRecurso(recursoAdapter.toEntity(model.getRecurso()));
        }
        if (model.getEstudiante() != null) {
            entity.setEstudiante(estudianteAdapter.toEntity(model.getEstudiante()));
        }

        return entity;
    }
}
