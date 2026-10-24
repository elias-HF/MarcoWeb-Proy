package com.example.marcowebproy.service;

import com.example.marcowebproy.entity.RecursoEntity;
import com.example.marcowebproy.repository.CategoriaRepository;
import com.example.marcowebproy.repository.RecursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecursoService {
    private final RecursoRepository repo;
    private final CategoriaRepository categoriaRepo;

    public RecursoService(RecursoRepository repo, CategoriaRepository categoriaRepo) {
        this.repo = repo;
        this.categoriaRepo = categoriaRepo;
    }

    public List<RecursoEntity> listar() {
        return repo.findAll();
    }

    // id = 0 crea uno nuevo, id > 0 modifica el existente
    public void guardar(int id, String nombre, int categoriaId, String ubicacion, String condicion, String icono) {
        RecursoEntity r = new RecursoEntity();
        r.setDisponibilidad("Disponible");
        r.setActivo(true);
        if (id > 0) r = repo.findById(id).orElseThrow();

        r.setNombre(nombre);
        r.setCategoria(categoriaRepo.findById(categoriaId).orElseThrow());
        r.setUbicacion(ubicacion);
        r.setCondicionFisica(condicion);
        r.setIcono(icono);
        repo.save(r);
    }

    public void desactivar(int id) {
        RecursoEntity r = repo.findById(id).orElseThrow();
        r.setActivo(false);
        repo.save(r);
    }
}