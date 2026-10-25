package com.example.marcowebproy.controller;

import com.example.marcowebproy.service.PrestamoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/empleado")
public class PrestamoController {
    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping("/prestamos")
    public String mostrarPrestamo(Model model) {
        model.addAttribute("prestamos", prestamoService.listarTodos());
        return "compoEmpleado/prestamos";
    }

    @PostMapping("/prestamos/guardar")
    public String guardarPrestamo(@RequestParam int idPrestamo, @RequestParam String fechaLimiteDevolucion){
        prestamoService.marcarComoRecogido(idPrestamo, fechaLimiteDevolucion);
        return "redirect:/empleado/prestamos";
    }

    @PostMapping("/prestamos/cancelar")
    public String cancelarPrestamo(@RequestParam int idPrestamo) {
        prestamoService.cancelarPrestamo(idPrestamo);
        return "redirect:/empleado/prestamos";
    }

    @PostMapping("/prestamos/devolver")
    public String devolverPrestamo(@RequestParam int idPrestamo, @RequestParam String condicionDevolucion, @RequestParam(required = false) String observacionDevolucion){
        prestamoService.registrarDevolucion(idPrestamo, condicionDevolucion, observacionDevolucion);
        return "redirect:/empleado/prestamos";
    }
}