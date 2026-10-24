package com.example.marcowebproy.controller;

import com.example.marcowebproy.model.Categoria;
import com.example.marcowebproy.data.DataStore;
import com.example.marcowebproy.service.CategoriaService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/empleado/categorias")
    public String categorias(Model model) {
        model.addAttribute("categorias", categoriaService.listar());
        return "compoEmpleado/categorias";
    }

    @PostMapping("/empleado/categorias/guardar")
    public String guardarCategoria(@RequestParam String nombre, @RequestParam String descripcion) {
        categoriaService.guardar(nombre, descripcion);
        return "redirect:/empleado/categorias";
    }

    @PostMapping("/empleado/categorias/modificar")
    public String modificarCategoria(@RequestParam int id, @RequestParam String nombre, @RequestParam String descripcion) {
        categoriaService.modificar(id, nombre, descripcion);
        return "redirect:/empleado/categorias";
    }

    @PostMapping("/empleado/categorias/desactivar")
    public String desactivarCategoria(@RequestParam int id) {
        categoriaService.desactivar(id);
        return "redirect:/empleado/categorias";
    }
}