package com.example.marcowebproy.controller;

import com.example.marcowebproy.data.DataStore;
import com.example.marcowebproy.model.Estudiante;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    private final DataStore dataStore;

    public LoginController(DataStore dataStore){
        this.dataStore=dataStore;
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model){
        Optional<Estudiante> estudiante = dataStore.estudiantes.stream().filter(e -> e.getCorreo().equalsIgnoreCase(email) && e.getPassword().equals(password)).findFirst();
        if(estudiante.isPresent()){
            return "redirect:/estudiante";
        }
        if(email.endsWith("@empleado.edu.pe") || email.contains("empleado")){
            return "redirect:/empleado";
        }
        model.addAttribute("error", "Correo o contraseña incorrectos.");
        return "login";
    }

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro";
    }
}