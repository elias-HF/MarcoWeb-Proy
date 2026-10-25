package com.example.marcowebproy.controller;

import com.example.marcowebproy.repository.EstudianteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empleado")
public class EstudiantesEmpController {
    private final EstudianteRepository estudianteRepository;

    public EstudiantesEmpController(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @GetMapping("/estudiantes")
    public String mostrarEstudiantes(Model model) {
        model.addAttribute("estudiantes", estudianteRepository.findAll());
        return "compoEmpleado/estudiantes";
    }

    @PostMapping("/estudiantes/modificar")
    public String modificarEstudiante(@RequestParam int id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String correo, @RequestParam int puntajeMerito, @RequestParam int demeritos) {
        estudianteRepository.findById(id).ifPresent(estudiante -> {
            estudiante.setNombre(nombre);
            estudiante.setApellido(apellido);
            estudiante.setCorreo(correo);
            estudiante.setPuntajeMerito(puntajeMerito);

            estudiante.setDemeritos(estudiante.getDemeritos() + demeritos);
            if (demeritos > 0) {
                estudiante.setEstado(false);
            }
            estudianteRepository.save(estudiante);
        });

        return "redirect:/empleado/estudiantes";
    }
}