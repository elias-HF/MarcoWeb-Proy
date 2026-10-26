package com.example.marcowebproy.service;

import com.example.marcowebproy.adapter.PrestamoAdapter;
import com.example.marcowebproy.entity.EstudianteEntity;
import com.example.marcowebproy.model.Prestamo;
import com.example.marcowebproy.repository.EstudianteRepository;
import com.example.marcowebproy.repository.PrestamoRepository;
import com.example.marcowebproy.repository.RecursoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final RecursoRepository recursoRepository;
    private final EstudianteRepository estudianteRepository;
    private final PrestamoAdapter prestamoAdapter = new PrestamoAdapter();

    public PrestamoService(PrestamoRepository prestamoRepository, RecursoRepository recursoRepository, EstudianteRepository estudianteRepository) {
        this.prestamoRepository = prestamoRepository;
        this.recursoRepository = recursoRepository;
        this.estudianteRepository = estudianteRepository;
    }

    @Transactional(readOnly = true)
    public List<Prestamo> listarTodos() {
        return prestamoRepository.findAll().stream()
                .map(prestamoAdapter::toModel)
                .collect(Collectors.toList());
    }

    @Transactional
    public void marcarComoRecogido(int idPrestamo, String fechaLimiteDevolucion) {
        prestamoRepository.findById(idPrestamo).ifPresent(prestamo -> {
            if ("Pendiente".equalsIgnoreCase(prestamo.getEstado())) {
                prestamo.setFechaPrestamo(LocalDate.now());
                prestamo.setFechaLimiteDevolucion(LocalDate.parse(fechaLimiteDevolucion));
                prestamo.setEstado("Activo");

                if (prestamo.getRecurso() != null) {
                    prestamo.getRecurso().setDisponibilidad("Prestado");
                    recursoRepository.save(prestamo.getRecurso());
                }

                prestamoRepository.save(prestamo);
            }
        });
    }

    @Transactional
    public void cancelarPrestamo(int idPrestamo) {
        prestamoRepository.findById(idPrestamo).ifPresent(prestamo -> {
            if ("Pendiente".equalsIgnoreCase(prestamo.getEstado())) {
                prestamo.setEstado("Cancelado");
                prestamoRepository.save(prestamo);
            }
        });
    }

    @Transactional
    public void registrarDevolucion(int idPrestamo, String condicionDevolucion, String observacionDevolucion) {
        prestamoRepository.findById(idPrestamo).ifPresent(prestamo -> {
            if ("Activo".equalsIgnoreCase(prestamo.getEstado()) || "Atrasado".equalsIgnoreCase(prestamo.getEstado())) {
                prestamo.setFechaDevolucion(LocalDate.now());
                prestamo.setCondicionDevolucion(condicionDevolucion);
                prestamo.setObservacionDevolucion(observacionDevolucion);
                prestamo.setEstado("Devuelto");

                if (prestamo.getRecurso() != null) {
                    prestamo.getRecurso().setDisponibilidad("Disponible");
                    if ("Dañado".equalsIgnoreCase(condicionDevolucion)) {
                        prestamo.getRecurso().setCondicionFisica("Dañada");
                    } else {
                        prestamo.getRecurso().setCondicionFisica("Buena");
                    }
                    recursoRepository.save(prestamo.getRecurso());
                }

                if ("Dañado".equalsIgnoreCase(condicionDevolucion) && prestamo.getEstudiante() != null) {
                    EstudianteEntity estudiante = prestamo.getEstudiante();

                    int nuevosDemeritos = estudiante.getDemeritos() + 1;
                    estudiante.setDemeritos(nuevosDemeritos);

                    if (nuevosDemeritos >= 3) {
                        estudiante.setEstado(false);
                    }

                    estudianteRepository.save(estudiante);
                }

                prestamoRepository.save(prestamo);
            }
        });
    }
}
