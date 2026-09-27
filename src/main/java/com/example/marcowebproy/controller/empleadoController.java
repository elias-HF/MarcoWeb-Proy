package com.example.marcowebproy.controller;

import com.example.marcowebproy.data.DataStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empleado")
public class empleadoController {
    private final DataStore dataStore;

    public empleadoController(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @GetMapping("")
    public String inicio() {return "compoEmpleado/inicio";}

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalCategorias", dataStore.categorias.size());
        model.addAttribute("totalRecursos", dataStore.recursos.size());

        return "compoEmpleado/dashboard";
    }

    @GetMapping("/categorias")
    public String categorias(Model model) {
        model.addAttribute("categorias", dataStore.categorias);
        return "compoEmpleado/categorias";
    }

    @GetMapping("/disponibilidad")
    public String disponibilidad() {
        return "compoEmpleado/disponibilidad";
    }

    @GetMapping("/estudiantes")
    public String estudiantes(Model model) {
        model.addAttribute("estudiantes", dataStore.estudiantes);
        return "compoEmpleado/estudiantes";
    }

    @GetMapping("/reservas")
    public String reservas() {
        return "compoEmpleado/reservas";
    }
}