package com.example.marcowebproy.controller;

import com.example.marcowebproy.model.Reserva;
import com.example.marcowebproy.model.EspacioAcademico;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estudiante")
public class estudianteController {

    @GetMapping("")
    public String estudiante() {return "compoEstudiante/estudiante";}

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

    @GetMapping("/reservar-espacio/{id}")
    public String mostrarFormularioReserva(@PathVariable("id") int idEspacio, Model model) {
        Reserva reserva = new Reserva();
    
        EspacioAcademico espacio = new EspacioAcademico();
        espacio.setId(idEspacio);
        reserva.setEspacioAcademico(espacio);
        
        model.addAttribute("reserva", reserva);
        return "compoEstudiante/reservar-espacio"; 
    }

    @PostMapping("/reservar-espacio/guardar")
    public String guardarReserva(@ModelAttribute("reserva") Reserva reserva) {
        reserva.setEstado("PENDIENTE");


        return "redirect:/estudiante/reservas";
    }
    
}