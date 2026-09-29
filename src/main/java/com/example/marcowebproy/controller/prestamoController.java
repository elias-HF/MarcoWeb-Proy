package com.example.marcowebproy.controller;

import com.example.marcowebproy.data.DataStore;
import org.springframework.stereotype.Controller;
import com.example.marcowebproy.model.Prestamo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class prestamoController {
    private final DataStore dataStore;
    public prestamoController(DataStore dataStore) {this.dataStore = dataStore;}

    @GetMapping("/empleado/prestamos")
    public String mostrarPrestamo(Model model) {
        model.addAttribute("prestamos", dataStore.prestamos);

        return "compoEmpleado/prestamos";
    }

    @PostMapping("/empleado/prestamos/guardar")
    public String guardarPrestamo(@RequestParam int idPrestamo, @RequestParam String fechaLimiteDevolucion){
        for (Prestamo prestamo : dataStore.prestamos){
            if (prestamo.getId() == idPrestamo && prestamo.getEstado().equals("Pendiente")){
                prestamo.setFechaPrestamo(LocalDate.now());
                prestamo.setFechaLimiteDevolucion(LocalDate.parse(fechaLimiteDevolucion));
            }
            prestamo.setEstado("Activo");
            if (prestamo.getRecurso() != null) {prestamo.getRecurso().setDisponibilidad("Prestado");}
            break;
        }
        return "redirect:/empleado/prestamos";
    }

    @PostMapping("/empleado/prestamos/cancelar")
    public String cancelarPrestamo(@RequestParam int idPrestamo) {
        for (Prestamo prestamo : dataStore.prestamos) {
            if (prestamo.getId() == idPrestamo && prestamo.getEstado().equals("Pendiente")) {
                prestamo.setEstado("Cancelado");
                break;
            }
        }
        return "redirect:/empleado/prestamos";
    }

    @PostMapping("/empleado/prestamos/devolver")
    public String devolverPrestamo(@RequestParam int idPrestamo, @RequestParam String condicionDevolucion, @RequestParam(required = false) String observacionDevolucion){
        for (Prestamo prestamo : dataStore.prestamos) {
            if (prestamo.getId() == idPrestamo && prestamo.getEstado().equals("Activo")) {
                prestamo.setFechaDevolucion(LocalDate.now());
                prestamo.setCondicionDevolucion(condicionDevolucion);
                prestamo.setObservacionDevolucion(observacionDevolucion);
                prestamo.setEstado("Devuelto");

                if (prestamo.getRecurso() != null) {
                    prestamo.getRecurso().setDisponibilidad("Disponible");
                    if (condicionDevolucion.equals("Dañado")) {prestamo.getRecurso().setCondicionFisica("Dañada");}
                    else{prestamo.getRecurso().setCondicionFisica("Buena");}
                }
                break;
            }
        }
        return "redirect:/empleado/prestamos";
    }
}