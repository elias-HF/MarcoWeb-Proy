package com.example.marcowebproy.controller;

import com.example.marcowebproy.service.RegistroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistroController {

    private final RegistroService registroService;

    public RegistroController(RegistroService registroService) {
        this.registroService = registroService;
    }

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String procesarRegistro(@RequestParam("nombres") String nombres,@RequestParam("apellidos") String apellidos ,@RequestParam("email") String email,@RequestParam("carrera") String carrera,@RequestParam("codigoEstudiante") String codigoEstudiante , @RequestParam("password") String password, @RequestParam("confirmarPassword") String confirmarPassword, Model model ){
        try{
            registroService.registrarEstudiante(nombres,apellidos, email, carrera, codigoEstudiante, password, confirmarPassword);
            return "redirect:/login?registrado=true";
        }catch(IllegalArgumentException | IllegalStateException e){
            model.addAttribute("error",e.getMessage());
            return "registro";
        }
    }

}
