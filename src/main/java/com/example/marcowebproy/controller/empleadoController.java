package com.example.marcowebproy.controller;

import com.example.marcowebproy.model.Categoria;
import com.example.marcowebproy.model.Recurso;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/empleado")
public class empleadoController {

    @GetMapping("")
    public String inicio() {return "compoEmpleado/inicio";}

    @GetMapping("/dashboard")
    public String dashboard() {return "compoEmpleado/dashboard";}

    @GetMapping("/categorias")
    public String categorias(Model model) {
        List<Categoria> categorias = new ArrayList<Categoria>();
        /* Datos temporales creados dentro de @GetMapping para maquetación y renderizado dinámico con Thymeleaf/Bootstrap.
        No se utilizo @PostMapping aún al no contar con el formulario de envío (Modal de registro)*/

        Recurso r1 = new Recurso(101, "Laptop", null, "Disponible", "Buena");
        Recurso r2 = new Recurso(102, "Proyector Epson", null, "Disponible", "Buena");

        categorias.add(new Categoria(1, "Tecnología", "Equipos tecnológicos para uso académico.", r1, true));
        categorias.add(new Categoria(2, "Audiovisual", "Equipos utilizados para presentaciones.", r2, true));
        categorias.add(new Categoria(3, "Material académico", "Libros y materiales de consulta.", null, true));
        categorias.add(new Categoria(4, "Accesorios", "Accesorios y complementos tecnológicos.", null, false));

        model.addAttribute("categorias", categorias);

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
    public String recursos() {
        return "compoEmpleado/recursos";
    }

    @GetMapping("/reservas")
    public String reservas() {
        return "compoEmpleado/reservas";
    }
}
