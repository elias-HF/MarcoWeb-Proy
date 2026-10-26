package com.example.marcowebproy.controller;

import com.example.marcowebproy.model.Reserva;
import com.example.marcowebproy.service.ReservaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/empleado")
public class ReservaController {
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping("/reservas")
    public String listarReservas(@RequestParam(required = false) String busqueda, @RequestParam(required = false) String estado, @RequestParam(required = false) String fecha, Model model) {


        model.addAttribute("reservas", reservaService.listarFiltradas(busqueda, estado, fecha));
        model.addAttribute("totalReservas", reservaService.contarTotal());
        model.addAttribute("pendientes", reservaService.contarPorEstado("Pendiente"));
        model.addAttribute("confirmadas", reservaService.contarPorEstado("Aprobada"));
        model.addAttribute("rechazadas", reservaService.contarPorEstado("No Presentado"));

        model.addAttribute("busqueda", busqueda != null ? busqueda : "");
        model.addAttribute("estado", estado != null ? estado : "Todos");
        model.addAttribute("fecha", fecha != null ? fecha : "");

        return "compoEmpleado/reservas";
    }

    @PostMapping("/reservas/modificar")
    public String modificarReserva(@RequestParam int id, @RequestParam String estado) {
        reservaService.actualizarEstado(id, estado);
        return "redirect:/empleado/reservas";
    }

    @PostMapping("/reservas/rechazar")
    public String rechazarReserva(@RequestParam int id, @RequestParam String motivoRechazo) {
        reservaService.registrarInasistencia(id, motivoRechazo);
        return "redirect:/empleado/reservas";
    }

    @PostMapping("/reservas/confirmar")
    public String confirmarReserva(@RequestParam int id) {
        reservaService.confirmarUsoReserva(id);
        return "redirect:/empleado/reservas";
    }

    @PostMapping("/reservas/cancelar")
    public String cancelarReserva(@RequestParam int id) {
        reservaService.cancelarReserva(id);
        return "redirect:/empleado/reservas";
    }

}