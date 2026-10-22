package com.example.marcowebproy.service;

import com.example.marcowebproy.entity.RecursoEntity;
import com.example.marcowebproy.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {
    private final RecursoRepository recursoRepository;
    private final EspacioAcademicoRepository espacioRepository;
    private final CategoriaRepository categoriaRepository;
    private final ReservaRepository reservaRepository;
    private final PrestamoRepository prestamoRepository;
    private final EstudianteRepository estudianteRepository;

    public EmpleadoService(RecursoRepository recursoRepository, EspacioAcademicoRepository espacioRepository, CategoriaRepository categoriaRepository, ReservaRepository reservaRepository, PrestamoRepository prestamoRepository, EstudianteRepository estudianteRepository) {
        this.recursoRepository = recursoRepository;
        this.espacioRepository = espacioRepository;
        this.categoriaRepository = categoriaRepository;
        this.reservaRepository = reservaRepository;
        this.prestamoRepository = prestamoRepository;
        this.estudianteRepository = estudianteRepository;
    }

    /*@Transactional(readOnly = true)
    public List<RecursoEntity> listarRecursos(){
        return recursoRepository.findAll();
    }

    @Transactional
    public void guardarRecurso(){


    }
    */
}
