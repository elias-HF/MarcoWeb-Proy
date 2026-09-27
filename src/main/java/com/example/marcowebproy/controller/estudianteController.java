package com.example.marcowebproy.controller;

import com.example.marcowebproy.data.DataStore;
import com.example.marcowebproy.model.Reserva;
import com.example.marcowebproy.model.EspacioAcademico;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;

@Controller
@RequestMapping("/estudiante")
public class estudianteController {

    private final DataStore dataStore;

    public estudianteController(DataStore dataStore){
        this.dataStore= dataStore;
    }

    @GetMapping("")
    public String estudiante() {return "compoEstudiante/estudiante";}

    @GetMapping("/recursos")
    public String recursos() {
        return "compoEstudiante/recursos";
    }

    @GetMapping("/reservas")
    public String reservas(@RequestParam(name = "estado", required = false) String estado, Model model) {
        List<Reserva> listaR = dataStore.reservas;

        if(estado != null && !estado.trim().isEmpty()){
            listaR = dataStore.reservas.stream().filter(r ->r.getEstado().equalsIgnoreCase(estado)).toList();
        }
        model.addAttribute("reservas",listaR);
        model.addAttribute("estadoSeleccionado",estado);
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