package com.example.marcowebproy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class estudianteController {

    @GetMapping("/estudiante")
    public String estudiante() {
        return "compoEstudiante/estudiante";
    }

    @GetMapping("/recursos")
    public String recursos() {
        return "compoEstudiante/recursos";
    }

    @GetMapping("/reservas")
    public String reservas() {
        return "compoEstudiante/reservas";
    }

    @GetMapping("/prestamos")
    public String prestamos() {
        return "compoEstudiante/prestamos";
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "compoEstudiante/contacto";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}

