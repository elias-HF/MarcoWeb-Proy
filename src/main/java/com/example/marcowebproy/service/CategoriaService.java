
package com.example.marcowebproy.service;

import com.example.marcowebproy.adapter.CategoriaAdapter;
import com.example.marcowebproy.entity.CategoriaEntity;
import com.example.marcowebproy.model.Categoria;
import com.example.marcowebproy.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaAdapter categoriaAdapter = new CategoriaAdapter();

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional(readOnly = true)
    public List<Categoria> listar() {
        return categoriaRepository.findAll().stream()
                .map(categoriaAdapter::toModel)
                .toList();
    }

    @Transactional
    public void guardar(String nombre, String descripcion) {
        CategoriaEntity c = new CategoriaEntity();
        c.setNombre(nombre);
        c.setDescripcion(descripcion);
        c.setEstado(true);
        categoriaRepository.save(c);
    }

    @Transactional
    public void modificar(int id, String nombre, String descripcion) {
        categoriaRepository.findById(id).ifPresent(c -> {
            c.setNombre(nombre);
            c.setDescripcion(descripcion);
            categoriaRepository.save(c);
        });
    }

    @Transactional
    public void desactivar(int id) {
        categoriaRepository.findById(id).ifPresent(c -> {
            c.setEstado(false);
            categoriaRepository.save(c);
        });
    }
}