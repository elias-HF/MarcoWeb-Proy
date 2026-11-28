package com.example.marcowebproy.service;

import com.example.marcowebproy.adapter.*;
import com.example.marcowebproy.entity.*;
import com.example.marcowebproy.model.*;
import com.example.marcowebproy.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
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

    public EstudianteService(RecursoRepository recursoRepository,
                             EspacioAcademicoRepository espacioRepository,
                             CategoriaRepository categoriaRepository,
                             ReservaRepository reservaRepository,
                             PrestamoRepository prestamoRepository,
                             EstudianteRepository estudianteRepository) {
        this.recursoRepository = recursoRepository;
        this.espacioRepository = espacioRepository;
        this.categoriaRepository = categoriaRepository;
        this.reservaRepository = reservaRepository;
        this.prestamoRepository = prestamoRepository;
        this.estudianteRepository = estudianteRepository;
    }

    @Transactional(readOnly = true)
    public EstudianteEntity obtenerEstudiantePorId(int id) {
        return estudianteRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Recurso> buscarRecurso(String query, Integer categoriaId) {
        List<RecursoEntity> entidades = recursoRepository.findAll().stream()
                .filter(RecursoEntity::isActivo)
                .collect(Collectors.toList());

        if (query != null && !query.trim().isEmpty()) {
            String q = query.toLowerCase().trim();
            entidades = entidades.stream().filter(r -> (r.getNombre() != null && r.getNombre().toLowerCase().contains(q))
                            || (r.getUbicacion() != null && r.getUbicacion().toLowerCase().contains(q)))
                    .collect(Collectors.toList());
        }
        if (categoriaId != null && categoriaId > 0) {
            entidades = entidades.stream()
                    .filter(r -> r.getCategoria().getId() == categoriaId)
                    .collect(Collectors.toList());
        }

        return entidades.stream().map(recursoAdapter::toModel).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EspacioAcademico> buscarEspacio(String query, Integer categoriaId) {
        if (categoriaId != null && categoriaId > 0) {
            return List.of();
        }

        List<EspacioAcademicoEntity> entidades = espacioRepository.findAll();
        if (query != null && !query.isEmpty()) {
            String q = query.toLowerCase().trim();
            entidades = entidades.stream().filter(e -> (e.getNombre() != null && e.getNombre().toLowerCase().contains(q))
                            || (e.getUbicacion() != null && e.getUbicacion().toLowerCase().contains(q)))
                    .collect(Collectors.toList());
        }
        return entidades.stream().map(espacioAdapter::toModel).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll().stream()
                .map(categoriaAdapter::toModel)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Reserva> listarReservasPorEstudiante(int estudianteId, String estado) {
        List<ReservaEntity> reservas = reservaRepository.findByEstudianteId(estudianteId);

        if (estado != null && !estado.trim().isEmpty()) {
            reservas = reservas.stream()
                    .filter(r -> r.getEstado() != null && r.getEstado().equalsIgnoreCase(estado))
                    .collect(Collectors.toList());
        }
        return reservas.stream().map(reservaAdapter::toModel).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Reserva obtenerReservasPorEspacioId(int idEspacio) {
        Reserva reserva = new Reserva();
        EspacioAcademicoEntity espacioEntity = espacioRepository.findById(idEspacio).orElse(null);
        if (espacioEntity != null) {
            reserva.setEspacioAcademico(espacioAdapter.toModel(espacioEntity));
        }
        return reserva;
    }

    @Transactional(readOnly = true)
    public List<String> obtenerHorariosDisponibles() {
        return List.of(
                "08:00 - 10:00",
                "10:00 - 12:00",
                "12:00 - 14:00",
                "14:00 - 16:00",
                "16:00 - 18:00",
                "18:00 - 20:00"
        );
    }

    @Transactional(readOnly = true)
    public Map<String, Map<String, List<String>>> obtenerHorariosOcupadosPorEspacio() {
        List<String> estadosInactivos = List.of("CANCELADA", "RECHAZADA", "NO PRESENTADO");

        return reservaRepository.findAll().stream()
                .filter(r -> r.getEspacioAcademico() != null
                        && r.getFechaReserva() != null
                        && r.getHoraInicio() != null
                        && r.getHoraFin() != null
                        && (r.getEstado() == null || !estadosInactivos.contains(r.getEstado().trim().toUpperCase())))
                .collect(Collectors.groupingBy(
                        r -> String.valueOf(r.getEspacioAcademico().getId()),
                        Collectors.groupingBy(
                                r -> r.getFechaReserva().toString(),
                                Collectors.mapping(
                                        r -> r.getHoraInicio().trim() + " - " + r.getHoraFin().trim(),
                                        Collectors.toList()
                                )
                        )
                ));
    }

    @Transactional
    public void guardarReserva(Reserva reserva, int estudianteId) {

        if (reserva.getEspacioAcademico() == null || reserva.getEspacioAcademico().getId() == 0) {
            throw new IllegalArgumentException("Debe seleccionar un espacio académico válido.");
        }

        int espacioId = reserva.getEspacioAcademico().getId();
        ZoneId zonaHoraria = ZoneId.of("America/Lima");
        LocalDate fechaActual = LocalDate.now(zonaHoraria);
        LocalTime horaActual = LocalTime.now(zonaHoraria);
        LocalDate fecha = reserva.getFechaReserva() != null ? reserva.getFechaReserva() : fechaActual;

        if (fecha.isBefore(fechaActual)) {
            throw new IllegalArgumentException("No se puede reservar en una fecha pasada.");
        }

        LocalDate fechaMaxima = fechaActual.plusDays(7);
        if (fecha.isAfter(fechaMaxima)) {
            throw new IllegalArgumentException("Solo se puede programar reservas con un máximo de 7 días de anticipación.");
        }

        if (reserva.getHoraInicio() == null || reserva.getHoraInicio().isBlank()
                || reserva.getHoraFin() == null || reserva.getHoraFin().isBlank()) {
            throw new IllegalArgumentException("Debe seleccionar un bloque horario fijo válido (máximo 2 horas).");
        }

        String inicioStr = reserva.getHoraInicio().trim();
        String finStr = reserva.getHoraFin().trim();
        String bloqueSeleccionado = inicioStr + " - " + finStr;

        if (!obtenerHorariosDisponibles().contains(bloqueSeleccionado)) {
            throw new IllegalArgumentException("El horario seleccionado no corresponde a un bloque fijo válido de 2 horas.");
        }

        EstudianteEntity estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado."));

        int puntajeMinimo = 50;
        if (estudiante.getPuntajeMerito() < puntajeMinimo) {
            throw new IllegalStateException("Acceso Denegado: Su puntaje de mérito " + estudiante.getPuntajeMerito()
                    + " es inferior al mínimo requerido (" + puntajeMinimo + ") para realizar reservas.");
        }

        long reservasActivas = reservaRepository.countByEstudianteIdAndEstadoIn(
                estudianteId, List.of("Pendiente", "PENDIENTE", "Aprobada", "APROBADA"));
        if (reservasActivas >= 1) {
            throw new IllegalStateException("Solo puede mantener una (1) reserva de espacio académico activa o pendiente a la vez.");
        }

        boolean tieneDeuda = prestamoRepository.existsByEstudianteIdAndEstadoIn(
                estudianteId, List.of("Atrasado", "ATRASADO", "Vencido", "VENCIDO"));
        if (tieneDeuda) {
            throw new IllegalStateException("No puede realizar nuevas reservas. Mantiene préstamos vencidos o atrasados en su historial.");
        }

        LocalTime horaInicio;
        LocalTime horaFin;
        try {
            horaInicio = LocalTime.parse(inicioStr);
            horaFin = LocalTime.parse(finStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("El formato del horario seleccionado no es válido.");
        }

        if (fecha.isEqual(fechaActual) && !horaInicio.isAfter(horaActual)) {
            throw new IllegalArgumentException("No puede realizar una reserva para una hora pasada");
        }

        EspacioAcademicoEntity espacioEntity = espacioRepository.findById(espacioId)
                .orElseThrow(() -> new IllegalArgumentException("El espacio académico seleccionado no existe."));

        if (!espacioEntity.isEstado()) {
            throw new IllegalStateException("El espacio académico seleccionado no se encuentra disponible.");
        }

        List<ReservaEntity> reservasExistentes = reservaRepository.findByEspacioAcademicoIdAndFechaReservaAndEstadoNotIn(
                espacioId, fecha, List.of("Cancelada", "CANCELADA", "Rechazada", "RECHAZADA", "No Presentado"));
        boolean ocupado = reservasExistentes.stream()
                .anyMatch(r -> inicioStr.equals(r.getHoraInicio().trim()) && finStr.equals(r.getHoraFin().trim()));

        if (ocupado) {
            throw new IllegalStateException("El espacio ya se encuentra reservado en el horario seleccionado.");
        }

        ReservaEntity entity = new ReservaEntity();
        entity.setEspacioAcademico(espacioEntity);
        entity.setFechaReserva(fecha);
        entity.setHoraInicio(inicioStr);
        entity.setHoraFin(finStr);
        entity.setFechaHoraInicio(LocalDateTime.of(fecha, horaInicio));
        entity.setFechaHoraFin(LocalDateTime.of(fecha, horaFin));
        entity.setEstado("Pendiente");
        entity.setEstudiante(estudiante);

        reservaRepository.save(entity);
    }

    @Transactional
    public void cancelarReserva(int idReserva) {
        reservaRepository.findById(idReserva).ifPresent(reserva -> {
            reserva.setEstado("Cancelada");
            reservaRepository.save(reserva);
        });
    }

    @Transactional(readOnly = true)
    public List<Prestamo> listarPrestamosPorEstudiante(int estudianteId) {
        List<PrestamoEntity> listaP = prestamoRepository.findByEstudianteId(estudianteId);
        return listaP.stream().map(prestamoAdapter::toModel).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Map<String, Long> obtenerEstadisticasPrestamosPorEstudiante(int estudianteId) {
        List<PrestamoEntity> listaP = prestamoRepository.findByEstudianteId(estudianteId);
        Map<String, Long> stats = new HashMap<>();

        stats.put("Activos", listaP.stream().filter(p -> "Activo".equalsIgnoreCase(p.getEstado())).count());
        stats.put("pendientes", listaP.stream().filter(p -> "Pendiente".equalsIgnoreCase(p.getEstado())).count());
        stats.put("atrasados", listaP.stream().filter(p -> "Atrasado".equalsIgnoreCase(p.getEstado())).count());
        stats.put("devueltos", listaP.stream().filter(p -> "Devuelto".equalsIgnoreCase(p.getEstado())).count());

        return stats;
    }

    @Transactional
    public void guardarPrestamo(int recursoId, String fechaDevolucionStr, String observacion, int estudianteId) {

        EstudianteEntity estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));

        if (!estudiante.isEstado()) {
            throw new IllegalStateException("Tu cuenta está inhabilitada por deméritos. No puedes solicitar préstamos.");
        }

        int puntajeMinimo = 50;
        if (estudiante.getPuntajeMerito() < puntajeMinimo) {
            throw new IllegalStateException("Acceso Denegado: Su puntaje de mérito " + estudiante.getPuntajeMerito()
                    + " es inferior al mínimo requerido " + puntajeMinimo + " para solicitar préstamos.");
        }

        boolean tieneDeuda = prestamoRepository.existsByEstudianteIdAndEstadoIn(estudianteId, List.of("Atrasado", "Vencido"));

        if (tieneDeuda) {
            throw new IllegalStateException("Acceso Denegado: tiene prestamos vencidos pendientes de devolucion.");
        }

        long prestamosActivos = prestamoRepository.countByEstudianteIdAndEstadoIn(
                estudianteId, List.of("Pendiente", "Activo", "Atrasado"));

        if (prestamosActivos >= 1) {
            throw new IllegalStateException("Limite superado: Solo puede mantener un (1) préstamo de recurso físico activo de forma simultánea.");
        }

        RecursoEntity recursoEntity = recursoRepository.findById(recursoId)
                .orElseThrow(() -> new IllegalArgumentException("El recurso seleccionado no se encuentra Disponible."));

        String disponibilidad = recursoEntity.getDisponibilidad();
        if (disponibilidad == null
                || "No disponible".equalsIgnoreCase(disponibilidad.trim())
                || "En Mantenimiento".equalsIgnoreCase(disponibilidad.trim())
                || !"Disponible".equalsIgnoreCase(disponibilidad.trim())) {
            throw new IllegalStateException("El recurso seleccionado no está disponible para préstamo (estado actual: "
                    + (disponibilidad != null ? disponibilidad : "desconocido") + ").");
        }

        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaLimite = null;

        if (fechaDevolucionStr != null && !fechaDevolucionStr.isEmpty()) {
            fechaLimite = LocalDate.parse(fechaDevolucionStr);
        }

        if (fechaLimite == null || fechaLimite.isBefore(fechaActual)) {
            throw new IllegalArgumentException("No se puede registrar un prestamo con una fecha anterior a la actual.");
        }
        recursoEntity.setDisponibilidad("No disponible");
        recursoRepository.save(recursoEntity);

        PrestamoEntity nuevo = new PrestamoEntity();
        nuevo.setRecurso(recursoEntity);
        nuevo.setFechaPrestamo(fechaActual);
        nuevo.setFechaLimiteDevolucion(fechaLimite);
        nuevo.setEstado("Pendiente");
        nuevo.setObservacionDevolucion(observacion);
        nuevo.setEstudiante(estudiante);
        prestamoRepository.save(nuevo);
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

}
