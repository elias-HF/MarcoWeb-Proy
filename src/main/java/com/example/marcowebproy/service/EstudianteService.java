package com.example.marcowebproy.service;

import com.example.marcowebproy.adapter.*;
import com.example.marcowebproy.entity.*;
import com.example.marcowebproy.model.*;
import com.example.marcowebproy.repository.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EstudianteService {

    private final RecursoRepository recursoRepository;
    private final EspacioAcademicoRepository espacioRepository;
    private final CategoriaRepository categoriaRepository;
    private final ReservaRepository reservaRepository;
    private final PrestamoRepository prestamoRepository;
    private final EstudianteRepository estudianteRepository;

    private final RecursoAdapter recursoAdapter = new RecursoAdapter();
    private final EspacioAcademicoAdapter espacioAdapter = new EspacioAcademicoAdapter();
    private final CategoriaAdapter categoriaAdapter = new CategoriaAdapter();
    private final ReservaAdapter reservaAdapter = new ReservaAdapter();
    private final PrestamoAdapter prestamoAdapter = new PrestamoAdapter();

    public EstudianteService(RecursoRepository recursoRepository, EspacioAcademicoRepository espacioRepository, CategoriaRepository categoriaRepository, ReservaRepository reservaRepository, PrestamoRepository prestamoRepository, EstudianteRepository estudianteRepository) {
        this.recursoRepository = recursoRepository;
        this.espacioRepository = espacioRepository;
        this.categoriaRepository = categoriaRepository;
        this.reservaRepository = reservaRepository;
        this.prestamoRepository = prestamoRepository;
        this.estudianteRepository = estudianteRepository;
    }

    @Transactional(readOnly = true)
    public EstudianteEntity obtenerEstudiantePorId(int id){
        return estudianteRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Recurso> buscarRecurso(String query, Integer categoriaId){
        List<RecursoEntity> entidades = recursoRepository.findAll().stream().filter(RecursoEntity::isActivo).collect(Collectors.toList());

        if(query != null && !query.trim().isEmpty()){
            String q = query.toLowerCase().trim();
            entidades = entidades.stream().filter(r -> (r.getNombre() != null && r.getNombre().toLowerCase().contains(q))
                    || (r.getUbicacion() != null && r.getUbicacion().toLowerCase().contains(q))).collect(Collectors.toList());
        }
        if(categoriaId != null && categoriaId > 0){
            entidades = entidades.stream().filter(r -> r.getCategoria().getId() == categoriaId).collect(Collectors.toList());
        }

        return entidades.stream().map(recursoAdapter::toModel).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EspacioAcademico> buscarEspacio(String query, Integer categoriaId){
        if(categoriaId != null && categoriaId > 0){
            return List.of();
        }

        List<EspacioAcademicoEntity> entidades = espacioRepository.findAll().stream().filter(EspacioAcademicoEntity::isEstado).collect(Collectors.toList());
        if(query != null && !query.isEmpty()){
            String q = query.toLowerCase().trim();
            entidades = entidades.stream().filter(e -> (e.getNombre() != null && e.getNombre().toLowerCase().contains(q))
                    || (e.getUbicacion() != null && e.getUbicacion().toLowerCase().contains(q))).collect(Collectors.toList());
        }
        return entidades.stream().map(espacioAdapter::toModel).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll().stream().map(categoriaAdapter::toModel).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Reserva> lisarReservas(String estado){
        List<ReservaEntity> reservas = reservaRepository.findAll();
        if(estado != null && !estado.trim().isEmpty()){
            reservas = reservas.stream().filter(r -> r.getEstado() != null && r.getEstado().equalsIgnoreCase(estado)).collect(Collectors.toList());
        }
        return reservas.stream().map(reservaAdapter::toModel).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Reserva obtenerReservasPorEspacioId(int idEspacio){
        Reserva reserva = new Reserva();
        EspacioAcademicoEntity espacioEntity = espacioRepository.findById(idEspacio).orElse(null);
        if(espacioEntity != null){
            reserva.setEspacioAcademico(espacioAdapter.toModel(espacioEntity));
        }
        return reserva;
    }

    @Transactional(readOnly = true)
    public List<String> obtenerHorariosDisponibles(){
        return List.of("08:00 - 10:00","10:00 - 12:00","12:00 - 14:00","14:00 - 16:00", "16:00 - 18:00", "18:00 - 20:00");
    }

    @Transactional
    public void guardarReserva(Reserva reserva, int estudianteId){

        if(reserva.getEspacioAcademico() == null || reserva.getEspacioAcademico().getId() == 0){
            throw new IllegalArgumentException("Debe seleccionar un espacio académico válido.");
        }

        EstudianteEntity estudiante = estudianteRepository.findById(estudianteId).orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));

        int puntajeMinimo = 50;
        if(estudiante.getPuntajeMerito() < puntajeMinimo){
            throw new IllegalStateException("Acceso Denegado: Su puntaje de mérito " + estudiante.getPuntajeMerito() + " es inferior al mínimo requerido (" + puntajeMinimo + ") para realizar reservas.");
        }

        long reservasActivas = reservaRepository.countByEstudianteIdAndEstadoIn(estudianteId, List.of("PENDIENTE"));
        if(reservasActivas >= 1){
            throw new IllegalStateException("Solo puede mantener una (1) reserva de espacio académico activa a la vez.");
        }

        boolean tieneDeuda = prestamoRepository.existsByEstudianteIdAndEstadoIn(estudianteId, List.of("ATRASADO"));
        if(tieneDeuda){
            throw new IllegalStateException("No puede realizar nuevas reservas. Mantiene préstamos atrasados en su historial.");
        }

        int espacioId = reserva.getEspacioAcademico().getId();
        LocalDate fechaActual = LocalDate.now();
        LocalDate fecha = reserva.getFechaReserva() != null ? reserva.getFechaReserva() : LocalDate.now();

        if(fecha.isBefore(fechaActual)){
            throw new IllegalArgumentException("No se puede reservar en una fecha pasada.");
        }

        LocalDate fechaMaxima = fechaActual.plusDays(7);
        if(fecha.isAfter(fechaMaxima)){
            throw new IllegalArgumentException("Solo se puede programar reservas con un máximo de 7 días de anticipación.");
        }

        List<ReservaEntity> reservasExistentes = reservaRepository.findByEspacioAcademicoIdAndFechaReservaAndEstadoNotIn(espacioId, fecha, List.of("CANCELADA", "RECHAZADA"));
        boolean ocupado = reservasExistentes.stream().anyMatch(r -> r.getHoraInicio().equals(reserva.getHoraInicio()) || r.getHoraFin().equals(reserva.getHoraFin()));

        if(ocupado){
            throw new IllegalStateException("El espacio ya se encuentra reservado en el horario seleccionado.");
        }

        ReservaEntity entity = new ReservaEntity();
        EspacioAcademicoEntity espacioEntity = espacioRepository.findById(espacioId).orElse(null);
        entity.setEspacioAcademico(espacioEntity);
        entity.setFechaReserva(fecha);
        entity.setHoraInicio(reserva.getHoraInicio());
        entity.setHoraFin(reserva.getHoraFin());
        entity.setEstado("PENDIENTE");
        entity.setEstudiante(estudiante);

        reservaRepository.save(entity);
    }

    @Transactional
    public void cancelarReserva(int idReserva){
        reservaRepository.findById(idReserva).ifPresent(reserva -> {
            reserva.setEstado("CANCELADA");
            reservaRepository.save(reserva);
        });
    }

    @Transactional(readOnly = true)
    public List<Prestamo> listarPrestamos(){
        return prestamoRepository.findAll().stream().map(prestamoAdapter::toModel).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Map<String, Long> obtenerEstadisticasPrestamos(){
        List<PrestamoEntity> listaP = prestamoRepository.findAll();
        Map<String, Long> stats = new HashMap<>();

        stats.put("Activos", listaP.stream().filter(p -> "ACTIVO".equalsIgnoreCase(p.getEstado())).count());
        stats.put("Pendientes", listaP.stream().filter(p -> "PENDIENTE".equalsIgnoreCase(p.getEstado())).count());
        stats.put("Atrasados", listaP.stream().filter(p -> "ATRASADO".equalsIgnoreCase(p.getEstado())).count());
        stats.put("Devueltos", listaP.stream().filter(p -> "DEVUELTO".equalsIgnoreCase(p.getEstado())).count());

        return stats;
    }

    @Transactional
    public void guardarPrestamo(int recursoId, String fechaDevolucionStr, String observacion, int estudianteId){

        EstudianteEntity estudiante = estudianteRepository.findById(estudianteId).orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));

        int puntajeMinimo = 50;
        if(estudiante.getPuntajeMerito() < puntajeMinimo){
            throw new IllegalStateException("Acceso Denegado: Su puntaje de mérito " + estudiante.getPuntajeMerito() + " es inferior al mínimo requerido (" + puntajeMinimo + ") para solicitar préstamos.");
        }

        boolean tieneDeuda = prestamoRepository.existsByEstudianteIdAndEstadoIn(estudianteId, List.of("ATRASADO"));
        if(tieneDeuda){
            throw new IllegalStateException("Acceso Denegado: Tiene préstamos atrasados pendientes de devolución.");
        }

        long prestamosActivos = prestamoRepository.countByEstudianteIdAndEstadoIn(estudianteId, List.of("PENDIENTE", "ACTIVO", "ATRASADO"));
        if(prestamosActivos >= 1){
            throw new IllegalStateException("Límite superado: Solo puede mantener un (1) préstamo de recurso físico activo de forma simultánea.");
        }

        RecursoEntity recursoEntity = recursoRepository.findById(recursoId).orElseThrow(() -> new IllegalArgumentException("El recurso seleccionado no se encuentra disponible."));

        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaLimite = null;

        if(fechaDevolucionStr != null && !fechaDevolucionStr.isEmpty()){
            fechaLimite = LocalDate.parse(fechaDevolucionStr);
        }

        if(fechaLimite == null || fechaLimite.isBefore(fechaActual)){
            throw new IllegalArgumentException("No se puede registrar un préstamo con una fecha anterior a la actual.");
        }
        recursoEntity.setDisponibilidad("No disponible");
        recursoRepository.save(recursoEntity);

        PrestamoEntity nuevo = new PrestamoEntity();
        nuevo.setRecurso(recursoEntity);
        nuevo.setFechaPrestamo(fechaActual);
        nuevo.setFechaLimiteDevolucion(fechaLimite);
        nuevo.setEstado("PENDIENTE");
        nuevo.setObservacionDevolucion(observacion);
        nuevo.setEstudiante(estudiante);
        prestamoRepository.save(nuevo);
    }

    @Transactional
    public void cancelarPrestamo(int idPrestamo){
        prestamoRepository.findById(idPrestamo).ifPresent(prestamo -> {
            if("PENDIENTE".equalsIgnoreCase(prestamo.getEstado())){
                prestamo.setEstado("CANCELADO");
                prestamoRepository.save(prestamo);
            }
        });
    }
}