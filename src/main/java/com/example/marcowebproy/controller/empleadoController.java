package com.example.marcowebproy.controller;

import com.example.marcowebproy.model.Categoria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/empleado")
public class empleadoController {

    List<Categoria> categorias = new ArrayList<Categoria>();

    @GetMapping("")
    public String inicio() {return "compoEmpleado/inicio";}

    @GetMapping("/dashboard")
    public String dashboard() {return "compoEmpleado/dashboard";}

    @GetMapping("/categorias")
    public String categorias(Model model) {
        model.addAttribute("categorias", categorias);
        model.addAttribute("nuevaCategoria", new Categoria());
        return "compoEmpleado/categorias";
    }

    @PostMapping("/categorias")
    public String guardarCategoria(@ModelAttribute("nuevaCategoria") Categoria categoria) {
        categoria.setRecursos(null);
        categoria.setEstado(true);
        categorias.add(categoria);
        return "redirect:/empleado/categorias";
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
    public String recursos() {
        return "compoEmpleado/recursos";
    }

    @GetMapping("/reservas")
    public String reservas() {
        return "compoEmpleado/reservas";
    }
}
