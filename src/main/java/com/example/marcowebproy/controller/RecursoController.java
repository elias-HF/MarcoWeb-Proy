package com.example.marcowebproy.controller;

import com.example.marcowebproy.service.CategoriaService;
import com.example.marcowebproy.service.RecursoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecursoController {
    private final RecursoService recursoService;
    private final CategoriaService categoriaService;

    public RecursoController(RecursoService recursoService, CategoriaService categoriaService) {
        this.recursoService = recursoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/empleado/recursos")
    public String mostrar(Model model) {
        model.addAttribute("recursos", recursoService.listar());
        model.addAttribute("categorias", categoriaService.listar());
        return "compoEmpleado/recursos";
    }

    @PostMapping("/empleado/recursos/guardar")
    public String guardar(@RequestParam String nombre, @RequestParam int categoriaId, @RequestParam String ubicacion,
                          @RequestParam String condicionFisica, @RequestParam String icono) {
        recursoService.guardar(0, nombre, categoriaId, ubicacion, condicionFisica, icono);
        return "redirect:/empleado/recursos";
    }

    @PostMapping("/empleado/recursos/modificar")
    public String modificar(@RequestParam int id, @RequestParam String nombre, @RequestParam int categoriaId,
                            @RequestParam String ubicacion, @RequestParam String condicionFisica, @RequestParam String icono) {
        recursoService.guardar(id, nombre, categoriaId, ubicacion, condicionFisica, icono);
        return "redirect:/empleado/recursos";
    }

    @PostMapping("/empleado/recursos/desactivar")
    public String desactivar(@RequestParam int id) {
        recursoService.desactivar(id);
        return "redirect:/empleado/recursos";
    }
}