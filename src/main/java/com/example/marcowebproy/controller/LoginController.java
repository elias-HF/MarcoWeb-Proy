package com.example.marcowebproy.controller;


import com.example.marcowebproy.entity.EmpleadoEntity;
import com.example.marcowebproy.entity.EstudianteEntity;
import com.example.marcowebproy.repository.EmpleadoRepository;
import com.example.marcowebproy.repository.EstudianteRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    private final EstudianteRepository estudianteRepository;
    private final EmpleadoRepository empleadoRepository;

    public LoginController(EstudianteRepository estudianteRepository, EmpleadoRepository empleadoRepository) {
        this.estudianteRepository = estudianteRepository;
        this.empleadoRepository = empleadoRepository;
    }

    @GetMapping("/login")
    public String mostrarLogin(@RequestParam(value = "error", required = false) String error, Model model) {
      
        if (error != null) {
            model.addAttribute("error", "Correo o contraseña incorrectos.");
        }
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session){
        Optional<EstudianteEntity> estudiante = estudianteRepository.findAll().stream().filter(
                e -> e.getCorreo() != null && e.getCorreo().equalsIgnoreCase(email) && e.getPassword().equals(password)).findFirst();

        Optional<EmpleadoEntity> empleado = empleadoRepository.findAll().stream().filter(
                e -> e.getCorreo() != null && e.getCorreo().equalsIgnoreCase(email) && e.getPassword().equals(password)).findFirst();


        if(estudiante.isPresent()){
            session.setAttribute("estudianteLogueado",estudiante.get());
            return "redirect:/estudiante";
        }

        if(empleado.isPresent()){
            session.setAttribute("empleadoLogueado",empleado.get());
            return "redirect:/empleado";
        }

        return "redirect:/login?error=true";
    }

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro";
    }
}