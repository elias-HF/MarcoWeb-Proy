package com.example.marcowebproy.adapter;

import com.example.marcowebproy.entity.EstudianteEntity;
import com.example.marcowebproy.model.Estudiante;

public class EstudianteAdapter {

    public Estudiante toModel(EstudianteEntity entity) {
        if (entity == null) return null;

        Estudiante model = new Estudiante();
        model.setId(entity.getId());
        model.setCodigoEstudiante(entity.getCodigoEstudiante());
        model.setNombre(entity.getNombre());
        model.setApellido(entity.getApellido());
        model.setCorreo(entity.getCorreo());
        model.setPassword(entity.getPassword());
        model.setCarrera(entity.getCarrera());
        model.setEstado(entity.isEstado());
        model.setDemeritos(entity.getDemeritos());
        model.setPuntajeMerito(entity.getPuntajeMerito());
        return model;
    }

    public EstudianteEntity toEntity(Estudiante model) {
        if (model == null) return null;

        EstudianteEntity entity = new EstudianteEntity();
        entity.setId(model.getId());
        entity.setCodigoEstudiante(model.getCodigoEstudiante());
        entity.setNombre(model.getNombre());
        entity.setApellido(model.getApellido());
        entity.setCorreo(model.getCorreo());
        entity.setPassword(model.getPassword());
        entity.setCarrera(model.getCarrera());
        entity.setEstado(model.isEstado());
        entity.setDemeritos(model.getDemeritos());
        entity.setPuntajeMerito(model.getPuntajeMerito());
        return entity;
    }
}
