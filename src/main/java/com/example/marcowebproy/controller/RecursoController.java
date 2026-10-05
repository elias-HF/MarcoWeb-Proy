package com.example.marcowebproy.controller;

import com.example.marcowebproy.data.DataStore;
import com.example.marcowebproy.model.Categoria;
import com.example.marcowebproy.model.Recurso;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecursoController {
    private final DataStore dataStore;

    public RecursoController(DataStore dataStore) {this.dataStore = dataStore;}

    @PostMapping("/empleado/recursos/guardar")
    public String guardarRecurso(@RequestParam String nombre, @RequestParam int categoriaId, @RequestParam String ubicacion, @RequestParam String condicionFisica, @RequestParam String icono) {
        int nuevoID = dataStore.recursos.size() + 1;
        Categoria categoria = dataStore.categorias.stream().filter(c -> c.getId() == categoriaId).findFirst().orElse(null);
        if (categoria != null) {
            Recurso recurso = new Recurso(nuevoID, icono, nombre, categoria, ubicacion, "Disponible", condicionFisica, true);
            dataStore.recursos.add(recurso);
        }

        return "redirect:/empleado/recursos";
    }

    @PostMapping("/empleado/recursos/modificar")
    public String modificarRecurso(@RequestParam int id, @RequestParam String nombre, @RequestParam int categoriaId, @RequestParam String ubicacion, @RequestParam String condicionFisica, @RequestParam String icono) {
        Recurso recurso = dataStore.recursos.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
        Categoria categoria = dataStore.categorias.stream().filter(c -> c.getId() == categoriaId).findFirst().orElse(null);
        if (recurso != null && categoria != null) {
            recurso.setNombre(nombre);
            recurso.setCategoria(categoria);
            recurso.setUbicacion(ubicacion);
            recurso.setCondicionFisica(condicionFisica);
            recurso.setIcono(icono);
        }

        return "redirect:/empleado/recursos";
    }

    @PostMapping("/empleado/recursos/desactivar")
    public String desactivarRecurso(@RequestParam int id) {
        Recurso recurso = dataStore.recursos.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
        if (recurso != null) {recurso.setActivo(false);}

        return "redirect:/empleado/recursos";
    }

    @GetMapping("/empleado/recursos")
    public String mostrarRecursos(Model model) {
        model.addAttribute("recursos", dataStore.recursos);
        model.addAttribute("categorias", dataStore.categorias);

        return "compoEmpleado/recursos";
    }
}