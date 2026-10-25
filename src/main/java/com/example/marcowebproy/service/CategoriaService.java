
package com.example.marcowebproy.service;

import com.example.marcowebproy.adapter.CategoriaAdapter;
import com.example.marcowebproy.entity.CategoriaEntity;
import com.example.marcowebproy.entity.EspacioAcademicoEntity;
import com.example.marcowebproy.entity.RecursoEntity;
import com.example.marcowebproy.model.Categoria;
import com.example.marcowebproy.repository.CategoriaRepository;
import com.example.marcowebproy.repository.EspacioAcademicoRepository;
import com.example.marcowebproy.repository.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final RecursoRepository recursoRepository;
    private final EspacioAcademicoRepository espacioAcademicoRepository;
    private final CategoriaAdapter categoriaAdapter = new CategoriaAdapter();

    public CategoriaService(CategoriaRepository categoriaRepository, RecursoRepository recursoRepository, EspacioAcademicoRepository espacioAcademicoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.recursoRepository = recursoRepository;
        this.espacioAcademicoRepository = espacioAcademicoRepository;
    }

    @Transactional(readOnly = true)
    public List<Categoria> listar() {
        List<CategoriaEntity> categorias = categoriaRepository.findAll();
        List<RecursoEntity> recursos = recursoRepository.findAll();
        List<EspacioAcademicoEntity> espacios = espacioAcademicoRepository.findAll();

        return categorias.stream().map(c -> {
            Categoria model = categoriaAdapter.toModel(c);
            long totalRecursos = recursos.stream()
                    .filter(r -> r.getCategoria() != null && r.getCategoria().getId() == c.getId())
                    .count();
            long totalEspacios = espacios.stream()
                    .filter(e -> e.getTipo() != null && (e.getTipo().equalsIgnoreCase(c.getNombre())
                            || c.getNombre().toLowerCase().contains(e.getTipo().toLowerCase()))).count();
            model.setCantidadRecursos((int) (totalRecursos + totalEspacios));
            return model;
        }).collect(Collectors.toList());
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