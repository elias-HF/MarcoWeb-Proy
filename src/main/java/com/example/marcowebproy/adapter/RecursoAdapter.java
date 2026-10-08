package com.example.marcowebproy.adapter;

import com.example.marcowebproy.entity.RecursoEntity;
import com.example.marcowebproy.model.Recurso;

public class RecursoAdapter {

    private final CategoriaAdapter categoriaAdapter = new CategoriaAdapter();

    public Recurso toModel(RecursoEntity entity) {
        if (entity == null) return null;

        Recurso model = new Recurso();
        model.setId(entity.getId());
        model.setIcono(entity.getIcono());
        model.setNombre(entity.getNombre());
        model.setUbicacion(entity.getUbicacion());
        model.setDisponibilidad(entity.getDisponibilidad());
        model.setCondicionFisica(entity.getCondicionFisica());
        model.setActivo(entity.isActivo());

        if (entity.getCategoria() != null) {
            model.setCategoria(categoriaAdapter.toModel(entity.getCategoria()));
        }

        return model;
    }

    public RecursoEntity toEntity(Recurso model) {
        if (model == null) return null;

        RecursoEntity entity = new RecursoEntity();
        entity.setId(model.getId());
        entity.setIcono(model.getIcono());
        entity.setNombre(model.getNombre());
        entity.setUbicacion(model.getUbicacion());
        entity.setDisponibilidad(model.getDisponibilidad());
        entity.setCondicionFisica(model.getCondicionFisica());
        entity.setActivo(model.isActivo());

        if (model.getCategoria() != null) {
            entity.setCategoria(categoriaAdapter.toEntity(model.getCategoria()));
        }

        return entity;
    }
}
