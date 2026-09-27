package com.example.marcowebproy.controller;

import com.example.marcowebproy.model.Categoria;
import com.example.marcowebproy.model.Recurso;
import com.example.marcowebproy.data.DataStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/espacios")
    public String espacios() {
        return "compoEmpleado/espacios";
    }

    @GetMapping("/estudiantes")
    public String estudiantes() {
        return "compoEmpleado/estudiantes";
    }

    @GetMapping("/prestamos")
    public String prestamos() {
        return "compoEmpleado/prestamos";
    }

    @GetMapping("/recursos")
    public String recursos(Model model) {
        model.addAttribute("recursos", dataStore.recursos);
        return "compoEmpleado/recursos";
    }

    @GetMapping("/reservas")
    public String reservas() {
        return "compoEmpleado/reservas";
    }
}