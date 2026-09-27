package com.example.marcowebproy.controller;

import com.example.marcowebproy.data.DataStore;
import com.example.marcowebproy.model.EspacioAcademico;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class espacioController {
    private final DataStore dataStore;
    public espacioController(DataStore dataStore) {this.dataStore = dataStore;}

    @GetMapping("/empleado/espacios")
    public String mostrarEspacios(Model model) {
        model.addAttribute("espacios", dataStore.espacios);

        return "compoEmpleado/espacios";
    }

    @PostMapping("/empleado/espacios/guardar")
    public String guardarEspacio(@RequestParam String nombre, @RequestParam String tipo, @RequestParam String ubicacion, @RequestParam int capacidad) {
        int nuevoID = dataStore.espacios.stream().mapToInt(EspacioAcademico::getId).max().orElse(0) + 1;
        EspacioAcademico espacio = new EspacioAcademico(nuevoID, nombre, tipo, ubicacion, capacidad, true);
        dataStore.espacios.add(espacio);

        return "redirect:/empleado/espacios";
    }

    @PostMapping("/empleado/espacios/modificar")
    public String modificarEspacio(@RequestParam int id, @RequestParam String nombre, @RequestParam String tipo, @RequestParam String ubicacion, @RequestParam int capacidad) {
        EspacioAcademico espacio = dataStore.espacios.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
        if (espacio != null) {espacio.setNombre(nombre);espacio.setTipo(tipo);espacio.setUbicacion(ubicacion);espacio.setCapacidad(capacidad);}

        return "redirect:/empleado/espacios";
    }

    @PostMapping("/empleado/espacios/desactivar")
    public String desactivarEspacio(@RequestParam int id) {
        EspacioAcademico espacio = dataStore.espacios.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
        if (espacio != null) {espacio.setEstado(false);}

        return "redirect:/empleado/espacios";
    }
}