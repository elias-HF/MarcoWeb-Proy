package com.example.marcowebproy.service;

import com.example.marcowebproy.adapter.*;
import com.example.marcowebproy.entity.EspacioAcademicoEntity;
import com.example.marcowebproy.entity.PrestamoEntity;
import com.example.marcowebproy.entity.RecursoEntity;
import com.example.marcowebproy.entity.ReservaEntity;
import com.example.marcowebproy.model.*;
import com.example.marcowebproy.repository.*;
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

    public List<Recurso> buscarRecurso(String query, Integer categoriaId){
        List<RecursoEntity> entidades = recursoRepository.findAll().stream().filter(RecursoEntity::isActivo).collect(Collectors.toList());

        if(query != null && !query.trim().isEmpty()){
            String q = query.toLowerCase().trim();
            entidades = entidades.stream().filter(r -> (r.getNombre() != null && r.getNombre().toLowerCase().contains(q))
                    || (r.getUbicacion() != null && r.getUbicacion().toLowerCase().contains(q))).collect(Collectors.toList());
        }
        if(categoriaId != null && categoriaId >0){
            entidades = entidades.stream().filter(r -> r.getCategoria().getId() == categoriaId).collect(Collectors.toList());
        }

        return entidades.stream().map(recursoAdapter::toModel).collect(Collectors.toList());
    }

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

    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll().stream().map(categoriaAdapter::toModel).collect(Collectors.toList());
    }

    public List<Reserva> lisarReservas(String estado){
        List<ReservaEntity> reservas = reservaRepository.findAll();
        if(estado != null && !estado.trim().isEmpty()){
            reservas = reservas.stream().filter(r -> r.getEstado() != null && r.getEstado().equalsIgnoreCase(estado)).collect(Collectors.toList());
        }
        return reservas.stream().map(reservaAdapter::toModel).collect(Collectors.toList());
    }

    public Reserva obtenerReservasPorEspacioId(int idEspacio){
        Reserva reserva = new Reserva();
        EspacioAcademicoEntity espacioEntity = espacioRepository.findById(idEspacio).orElse(null);
        if(espacioEntity != null){
            reserva.setEspacioAcademico(espacioAdapter.toModel(espacioEntity));
        }
        return reserva;
    }

    public List<String> obtenerHorariosDisponibles(){
        return List.of("08:00 - 10:00","10:00 - 12:00","12:00 - 14:00","14:00 - 16:00", "16:00 - 18:00", "18:00 - 20:00");
    }

    public void guardarReserva(Reserva reserva){

        if(reserva.getEspacioAcademico() == null || reserva.getEspacioAcademico().getId() == 0){
            throw new IllegalArgumentException("Debe Seleccionar un espacio academico valido.");
        }

        int espacioId = reserva.getEspacioAcademico().getId();
        LocalDate fecha = reserva.getFechaReserva() != null ? reserva.getFechaReserva() : LocalDate.now();

        if(fecha.isBefore((LocalDate.now()))){
            throw new IllegalArgumentException("No se puede reservar en un fecha pasada.");
        }

        List<ReservaEntity> reservasExistentes = reservaRepository.findByEspacioAcademicoIdAndFechaReservaAndEstadoNotIn(espacioId,fecha,List.of("Cancelada","Rechazada"));
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
        entity.setEstado("Pendiente");

        estudianteRepository.findAll().stream().findFirst().ifPresent(entity::setEstudiante);

        reservaRepository.save(entity);
    }

    public void cancelarReserva(int idReserva){
        reservaRepository.findById(idReserva).ifPresent(reserva ->{
           reserva.setEstado("Cancelada");
           reservaRepository.save(reserva);
        });
    }

    public List<Prestamo> listarPrestamos(){
        return prestamoRepository.findAll().stream().map(prestamoAdapter::toModel).collect(Collectors.toList());
    }

    public Map<String, Long> obtenerEstadisticasPrestamos(){
        List<PrestamoEntity> listaP = prestamoRepository.findAll();
        Map<String, Long> stats = new HashMap<>();

        stats.put("Activos", listaP.stream().filter(p -> "Activo".equalsIgnoreCase(p.getEstado())).count());
        stats.put("pendientes", listaP.stream().filter(p -> "Pendiente".equalsIgnoreCase(p.getEstado())).count());
        stats.put("atrasados", listaP.stream().filter(p -> "Atrasado".equalsIgnoreCase(p.getEstado())).count());
        stats.put("devueltos", listaP.stream().filter(p -> "Devuelto".equalsIgnoreCase(p.getEstado())).count());

        return stats;
    }

    public void guardarPrestamo(int recursoId, String fechaDevolucionStr, String observacion){
        PrestamoEntity nuevo = new PrestamoEntity();

        RecursoEntity recursoEntity = recursoRepository.findById(recursoId).orElse(null);
        nuevo.setRecurso(recursoEntity);
        nuevo.setFechaPrestamo(LocalDate.now());

        if(fechaDevolucionStr != null && !fechaDevolucionStr.isEmpty()){
            nuevo.setFechaLimiteDevolucion(LocalDate.parse(fechaDevolucionStr));
        }

        nuevo.setEstado("Pendiente");
        nuevo.setObservacionDevolucion(observacion);
        estudianteRepository.findAll().stream().findFirst().ifPresent(nuevo::setEstudiante);
        prestamoRepository.save(nuevo);
    }

    public void cancelarPrestamo(int idPrestamo){
        prestamoRepository.findById(idPrestamo).ifPresent(prestamo -> {
           if("Pendiente".equalsIgnoreCase(prestamo.getEstado())){
                prestamo.setEstado("Cancelado");
                prestamoRepository.save(prestamo);
           }
        });
    }



}
