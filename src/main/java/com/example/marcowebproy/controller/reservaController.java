package com.example.marcowebproy.controller;

import com.example.marcowebproy.data.DataStore;
import com.example.marcowebproy.model.Reserva;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class reservaController {
    private final DataStore dataStore;
    public reservaController(DataStore dataStore) {this.dataStore = dataStore;}

    @GetMapping("/empleado/reservas")
    public String listarReservas(@RequestParam(required = false) String busqueda, @RequestParam(required = false) String estado, @RequestParam(required = false) String fecha, Model model) {
        List<Reserva> reservasFiltradas = new ArrayList<>(dataStore.reservas);

        if (busqueda != null && !busqueda.trim().isEmpty()) {
            String texto = busqueda.trim().toLowerCase();
            reservasFiltradas = reservasFiltradas.stream().filter(r -> {String estudiante = "";String recurso = "";String espacio = "";
                if (r.getEstudiante() != null) {estudiante = (r.getEstudiante().getNombre() + " " + r.getEstudiante().getApellido() + " " + r.getEstudiante().getCodigoEstudiante()).toLowerCase();}
                if (r.getRecurso() != null) {recurso = r.getRecurso().getNombre().toLowerCase();}
                if (r.getEspacioAcademico() != null) {espacio = r.getEspacioAcademico().getNombre().toLowerCase();}

                return estudiante.contains(texto) || recurso.contains(texto) || espacio.contains(texto);}).collect(Collectors.toList());
        }

        if (estado != null && !estado.trim().isEmpty() && !estado.equals("Todos")) {
            reservasFiltradas = reservasFiltradas.stream().filter(r -> estado.equals(r.getEstado())).collect(Collectors.toList());
        }

        if (fecha != null && !fecha.trim().isEmpty()) {
            LocalDate fechaFiltro = LocalDate.parse(fecha);
            reservasFiltradas = reservasFiltradas.stream().filter(r -> fechaFiltro.equals(r.getFechaReserva())).collect(Collectors.toList());
        }

        long pendientes = dataStore.reservas.stream().filter(r -> "Pendiente".equals(r.getEstado())).count();
        long confirmadas = dataStore.reservas.stream().filter(r -> "Confirmada".equals(r.getEstado())).count();

        long rechazadas = dataStore.reservas.stream().filter(r -> "Rechazada".equals(r.getEstado())).count();

        model.addAttribute("reservas", reservasFiltradas);
        model.addAttribute("totalReservas", dataStore.reservas.size());

        model.addAttribute("pendientes", pendientes);
        model.addAttribute("confirmadas", confirmadas);
        model.addAttribute("rechazadas", rechazadas);

        model.addAttribute("busqueda", busqueda != null ? busqueda : "");
        model.addAttribute("estado", estado != null ? estado : "Todos");
        model.addAttribute("fecha", fecha != null ? fecha : "");

        return "compoEmpleado/reservas";
    }

    @PostMapping("/empleado/reservas/modificar")
    public String modificarReserva(@RequestParam int id, @RequestParam String estado) {
        Reserva reserva = buscarReserva(id);
        if (reserva != null) {
            if (estado.equals("Pendiente") || estado.equals("Confirmada") || estado.equals("Rechazada")) {
                reserva.setEstado(estado);
                if (!estado.equals("Rechazada")) {reserva.setMotivoRechazo(null);}
            }
        }

        return "redirect:/empleado/reservas";
    }

    @PostMapping("/empleado/reservas/rechazar")
    public String rechazarReserva(@RequestParam int id, @RequestParam String motivoRechazo) {
        Reserva reserva = buscarReserva(id);
        if (reserva != null) {
            if (motivoRechazo != null && !motivoRechazo.trim().isEmpty()) {
                reserva.setEstado("Rechazada");
                reserva.setMotivoRechazo(motivoRechazo.trim());
            }
        }

        return "redirect:/empleado/reservas";
    }

    @PostMapping("/empleado/reservas/confirmar")
    public String confirmarReserva(@RequestParam int id) {
        Reserva reserva = buscarReserva(id);
        if (reserva != null) {
            boolean ocupado = dataStore.reservas.stream().anyMatch(r -> r.getId() != reserva.getId() && "Confirmada".equals(r.getEstado()) && mismoRecursoOespacio(r, reserva) && seCruzanHorarios(r, reserva));
            if (!ocupado) {
                reserva.setEstado("Confirmada");
                reserva.setMotivoRechazo(null);
            }
        }

        return "redirect:/empleado/reservas";
    }

    @PostMapping("/empleado/reservas/cancelar")
    public String cancelarReserva(@RequestParam int id) {
        Reserva reserva = buscarReserva(id);
        if (reserva != null) {
            reserva.setEstado("Cancelada");
            reserva.setMotivoRechazo(null);
        }

        return "redirect:/empleado/reservas";
    }

    private Reserva buscarReserva(int id) {
        return dataStore.reservas.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
    }

    private boolean mismoRecursoOespacio(Reserva a, Reserva b) {
        if (a.getEspacioAcademico() != null && b.getEspacioAcademico() != null) {
            return a.getEspacioAcademico().getId() == b.getEspacioAcademico().getId();
        }

        if (a.getRecurso() != null && b.getRecurso() != null) {
            return a.getRecurso().getId() == b.getRecurso().getId();
        }

        return false;
    }

    private boolean seCruzanHorarios(Reserva a, Reserva b) {
        if (a.getFechaReserva() == null || b.getFechaReserva() == null) {return false;}
        if (!a.getFechaReserva().equals(b.getFechaReserva())) {return false;}

        LocalTime inicioA = LocalTime.parse(a.getHoraInicio());
        LocalTime finA = LocalTime.parse(a.getHoraFin());
        LocalTime inicioB = LocalTime.parse(b.getHoraInicio());
        LocalTime finB = LocalTime.parse(b.getHoraFin());

        return inicioA.isBefore(finB) && inicioB.isBefore(finA);
    }
}