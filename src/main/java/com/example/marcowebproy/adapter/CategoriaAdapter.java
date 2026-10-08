package com.example.marcowebproy.adapter;

import com.example.marcowebproy.entity.CategoriaEntity;
import com.example.marcowebproy.model.Categoria;

public class CategoriaAdapter {

    public Categoria toModel(CategoriaEntity entity) {
        if (entity == null) return null;

        Categoria model = new Categoria();
        model.setId(entity.getId());
        model.setNombre(entity.getNombre());
        model.setDescripcion(entity.getDescripcion());
        model.setEstado(entity.isEstado());
        return model;
    }

    public CategoriaEntity toEntity(Categoria model) {
        if (model == null) return null;

        CategoriaEntity entity = new CategoriaEntity();
        entity.setId(model.getId());
        entity.setNombre(model.getNombre());
        entity.setDescripcion(model.getDescripcion());
        entity.setEstado(model.isEstado());
        return entity;
    }

}
