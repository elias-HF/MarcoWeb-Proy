package com.example.marcowebproy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empleado")
public class empleadoController {

    @GetMapping("")
    public String empleado() {return "compoEmpleado/empleado";}

    @GetMapping("/inicio")
    public String inicio() {return "compoEmpleado/inicio";}

    @GetMapping("/dashboard")
    public String dashboard() {
        return "compoEmpleado/dashboard";
    }

    @GetMapping("/categorias")
    public String categorias() {
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
