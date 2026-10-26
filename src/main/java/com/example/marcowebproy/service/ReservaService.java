package com.example.marcowebproy.service;

import com.example.marcowebproy.adapter.ReservaAdapter;
import com.example.marcowebproy.entity.ReservaEntity;
import com.example.marcowebproy.model.Reserva;
import com.example.marcowebproy.repository.ReservaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final ReservaAdapter reservaAdapter = new ReservaAdapter();

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Transactional(readOnly = true)
    public List<Reserva> listarFiltradas(String busqueda, String estado, String fecha) {
        List<ReservaEntity> entidades = reservaRepository.findAll();

        if (busqueda != null && !busqueda.trim().isEmpty()) {
            String texto = busqueda.trim().toLowerCase();
            entidades = entidades.stream().filter(r -> {
                String estudiante = "";
                String recurso = "";
                String espacio = "";
                if (r.getEstudiante() != null) {
                    estudiante = (r.getEstudiante().getNombre() + " " + r.getEstudiante().getApellido() + " " + r.getEstudiante().getCodigoEstudiante()).toLowerCase();
                }
                if (r.getRecurso() != null) {
                    recurso = r.getRecurso().getNombre().toLowerCase();
                }
                if (r.getEspacioAcademico() != null) {
                    espacio = r.getEspacioAcademico().getNombre().toLowerCase();
                }

                return estudiante.contains(texto) || recurso.contains(texto) || espacio.contains(texto);
            }).collect(Collectors.toList());
        }

        if (estado != null && !estado.trim().isEmpty() && !estado.equalsIgnoreCase("Todos")) {
            entidades = entidades.stream()
                    .filter(r -> estado.equalsIgnoreCase(r.getEstado()))
                    .collect(Collectors.toList());
        }

        if (fecha != null && !fecha.trim().isEmpty()) {
            LocalDate fechaFiltro = LocalDate.parse(fecha);
            entidades = entidades.stream()
                    .filter(r -> fechaFiltro.equals(r.getFechaReserva()))
                    .collect(Collectors.toList());
        }

        return entidades.stream().map(reservaAdapter::toModel).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public long contarTotal() {
        return reservaRepository.count();
    }

    @Transactional(readOnly = true)
    public long contarPorEstado(String estado) {
        return reservaRepository.findAll().stream()
                .filter(r -> estado.equalsIgnoreCase(r.getEstado()))
                .count();
    }

    @Transactional
    public void actualizarEstado(int id, String estado) {
        reservaRepository.findById(id).ifPresent(reserva -> {
            if ("Pendiente".equalsIgnoreCase(estado) || "Aprobada".equalsIgnoreCase(estado) || "No Presentado".equalsIgnoreCase(estado)) {
                reserva.setEstado(estado.toUpperCase());
                if (!"No Presentado".equalsIgnoreCase(estado)) {
                    reserva.setMotivoRechazo(null);
                }
                reservaRepository.save(reserva);
            }
        });
    }

    @Transactional
    public void registrarInasistencia(int id, String motivo) {
        reservaRepository.findById(id).ifPresent(reserva -> {
            if (motivo != null && !motivo.trim().isEmpty()) {
                reserva.setEstado("No Presentado");
                reserva.setMotivoRechazo(motivo.trim());
                reservaRepository.save(reserva);
            }
        });
    }

    @Transactional
    public void confirmarUsoReserva(int id) {
        reservaRepository.findById(id).ifPresent(reserva -> {
            List<ReservaEntity> todas = reservaRepository.findAll();
            boolean ocupado = todas.stream().anyMatch(r ->
                    r.getId() != reserva.getId() &&
                            ("Aprobada".equalsIgnoreCase(r.getEstado()) || "Confirmada".equalsIgnoreCase(r.getEstado()) || "Uso Activo".equalsIgnoreCase(r.getEstado())) &&
                            mismoRecursoOespacio(r, reserva) &&
                            seCruzanHorarios(r, reserva)
            );
            if (!ocupado) {
                reserva.setEstado("Aprobada"); // O "USO_ACTIVO" según prefieras manejar el estado de entrega de llaves
                reserva.setMotivoRechazo(null);
                reservaRepository.save(reserva);
            }
        });
    }

    @Transactional
    public void cancelarReserva(int id) {
        reservaRepository.findById(id).ifPresent(reserva -> {
            reserva.setEstado("Cancelada");
            reserva.setMotivoRechazo(null);
            reservaRepository.save(reserva);
        });
    }

    private boolean mismoRecursoOespacio(ReservaEntity a, ReservaEntity b) {
        if (a.getEspacioAcademico() != null && b.getEspacioAcademico() != null) {
            return a.getEspacioAcademico().getId() == b.getEspacioAcademico().getId();
        }
        if (a.getRecurso() != null && b.getRecurso() != null) {
            return a.getRecurso().getId() == b.getRecurso().getId();
        }
        return false;
    }

    private boolean seCruzanHorarios(ReservaEntity a, ReservaEntity b) {
        if (a.getFechaReserva() == null || b.getFechaReserva() == null) {
            return false;
        }
        if (!a.getFechaReserva().equals(b.getFechaReserva())) {
            return false;
        }

        LocalTime inicioA = LocalTime.parse(a.getHoraInicio());
        LocalTime finA = LocalTime.parse(a.getHoraFin());
        LocalTime inicioB = LocalTime.parse(b.getHoraInicio());
        LocalTime finB = LocalTime.parse(b.getHoraFin());

        return inicioA.isBefore(finB) && inicioB.isBefore(inicioA);
    }
}
