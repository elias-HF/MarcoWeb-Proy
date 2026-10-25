package com.example.marcowebproy.controller;

import com.example.marcowebproy.repository.PrestamoRepository;
import com.example.marcowebproy.repository.RecursoRepository;
import com.example.marcowebproy.repository.ReservaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class DashboardController {
    private final ReservaRepository reservaRepository;
    private final PrestamoRepository prestamoRepository;
    private final RecursoRepository recursoRepository;

    public DashboardController(ReservaRepository reservaRepository, PrestamoRepository prestamoRepository, RecursoRepository recursoRepository) {
        this.reservaRepository = reservaRepository;
        this.prestamoRepository = prestamoRepository;
        this.recursoRepository = recursoRepository;
    }

    @GetMapping("/empleado")
    public String redireccionarDashboard() {return "redirect:/empleado/dashboard";}

    @GetMapping("/empleado/dashboard")
    public String mostrarDashboard(Model model) {

        long reservasActuales = reservaRepository.findAll().stream()
                .filter(r -> !"CANCELADA".equalsIgnoreCase(r.getEstado())).count();

        long prestamosActivos = prestamoRepository.findAll().stream()
                .filter(p -> "ACTIVO".equalsIgnoreCase(p.getEstado())).count();

        long prestamosVencidos = prestamoRepository.findAll().stream()
                .filter(p -> ("ACTIVO".equalsIgnoreCase(p.getEstado()) || "Atrasado".equalsIgnoreCase(p.getEstado()))
                        && p.getFechaLimiteDevolucion() != null
                        && p.getFechaLimiteDevolucion().isBefore(LocalDate.now())).count();

        long devoluciones = prestamoRepository.findAll().stream()
                .filter(p -> "DEVUELTO".equalsIgnoreCase(p.getEstado())).count();

        long recursosDisponibles = recursoRepository.findAll().stream()
                .filter(r -> r.isActivo() && "Disponible".equalsIgnoreCase(r.getDisponibilidad())).count();

        String[] meses = {"Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre"};
        int[] reservasSerie = {12, 18, 15, 22, 19, 24};
        int[] prestamosSerie = {8, 13, 11, 16, 15, 18};
        int[] confirmadasSerie = {7, 11, 10, 15, 13, 20};
        int[] devolucionesSerie = {5, 8, 9, 11, 12, 15};
        int[] recursosSerie = {9, 14, 12, 18, 16, 21};

        model.addAttribute("reservasActuales", reservasActuales);
        model.addAttribute("prestamosActivos", prestamosActivos);
        model.addAttribute("prestamosVencidos", prestamosVencidos);
        model.addAttribute("devoluciones", devoluciones);
        model.addAttribute("recursosDisponibles", recursosDisponibles);

        model.addAttribute("meses", meses);
        model.addAttribute("reservasSerie", reservasSerie);
        model.addAttribute("prestamosSerie", prestamosSerie);
        model.addAttribute("confirmadasSerie", confirmadasSerie);
        model.addAttribute("devolucionesSerie", devolucionesSerie);
        model.addAttribute("recursosSerie", recursosSerie);

        return "compoEmpleado/dashboard";
    }
}