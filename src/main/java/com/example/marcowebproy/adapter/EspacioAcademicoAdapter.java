package com.example.marcowebproy.adapter;

import com.example.marcowebproy.entity.EspacioAcademicoEntity;
import com.example.marcowebproy.model.EspacioAcademico;

public class EspacioAcademicoAdapter {

    public EspacioAcademico toModel(EspacioAcademicoEntity entity) {
        if (entity == null) return null;

        EspacioAcademico model = new EspacioAcademico();
        model.setId(entity.getId());
        model.setNombre(entity.getNombre());
        model.setTipo(entity.getTipo());
        model.setUbicacion(entity.getUbicacion());
        model.setCapacidad(entity.getCapacidad());
        model.setEstado(entity.isEstado());
        return model;
    }

    public EspacioAcademicoEntity toEntity(EspacioAcademico model) {
        if (model == null) return null;

        EspacioAcademicoEntity entity = new EspacioAcademicoEntity();
        entity.setId(model.getId());
        entity.setNombre(model.getNombre());
        entity.setTipo(model.getTipo());
        entity.setUbicacion(model.getUbicacion());
        entity.setCapacidad(model.getCapacidad());
        entity.setEstado(model.isEstado());
        return entity;
    }
}
