package com.example.marcowebproy.service;

import com.example.marcowebproy.entity.EspacioAcademicoEntity;
import com.example.marcowebproy.repository.EspacioAcademicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspacioAcademicoService {
    private final EspacioAcademicoRepository repo;

    public EspacioAcademicoService(EspacioAcademicoRepository repo) {
        this.repo = repo;
    }

    public List<EspacioAcademicoEntity> listar() {
        return repo.findAll();
    }

    public void guardar(EspacioAcademicoEntity espacio) {
        // si ya existe, conserva su estado; si es nuevo, queda activo
        boolean estado = espacio.getId() == 0 || repo.findById(espacio.getId()).orElseThrow().isEstado();
        espacio.setEstado(estado);
        repo.save(espacio);
    }

    public void desactivar(int id) {
        EspacioAcademicoEntity e = repo.findById(id).orElseThrow();
        e.setEstado(false);
        repo.save(e);
    }
}