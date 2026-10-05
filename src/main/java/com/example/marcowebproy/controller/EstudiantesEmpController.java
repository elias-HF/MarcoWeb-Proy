package com.example.marcowebproy.controller;

import com.example.marcowebproy.data.DataStore;
import com.example.marcowebproy.model.Estudiante;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empleado")
public class EstudiantesEmpController {
    private final DataStore dataStore;

    public EstudiantesEmpController(DataStore dataStore) {this.dataStore = dataStore;}

    @GetMapping("/estudiantes")
    public String mostrarEstudiantes(Model model) {
        model.addAttribute("estudiantes", dataStore.estudiantes);

        return "compoEmpleado/estudiantes";
    }

    @PostMapping("/estudiantes/modificar")
    public String modificarEstudiante(@RequestParam int id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String correo, @RequestParam int puntajeMerito, @RequestParam int demeritos) {
        for (Estudiante estudiante : dataStore.estudiantes) {
            if (estudiante.getId() == id) {
                estudiante.setNombre(nombre);
                estudiante.setApellido(apellido);
                estudiante.setCorreo(correo);
                estudiante.setPuntajeMerito(puntajeMerito);

                estudiante.setDemeritos(estudiante.getDemeritos() + demeritos);
                if (demeritos > 0) {estudiante.setEstado(false);}
                break;
            }
        }

        return "redirect:/empleado/estudiantes";
    }
}