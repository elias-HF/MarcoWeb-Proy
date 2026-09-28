package com.example.marcowebproy.controller;

import com.example.marcowebproy.data.DataStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class dashboardController {
    private final DataStore dataStore;
    public dashboardController(DataStore dataStore) {this.dataStore = dataStore;}

    @GetMapping("/empleado")
    public String redireccionarDashboard() {return "redirect:/empleado/dashboard";}

    @GetMapping("/empleado/dashboard")
    public String mostrarDashboard(Model model) {

        long reservasActuales = dataStore.reservas.stream().filter(r -> !"Cancelada".equals(r.getEstado())).count();
        long prestamosActivos = dataStore.prestamos.stream().filter(p -> "Activo".equals(p.getEstado())).count();
        long prestamosVencidos = dataStore.prestamos.stream().filter(p -> "Activo".equals(p.getEstado()) && p.getFechaLimiteDevolucion() != null && p.getFechaLimiteDevolucion().isBefore(java.time.LocalDate.now())).count();
        long devoluciones = dataStore.prestamos.stream().filter(p -> "Devuelto".equals(p.getEstado())).count();
        long recursosDisponibles = dataStore.recursos.stream().filter(r -> r.isActivo() && "Disponible".equals(r.getDisponibilidad())).count();

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