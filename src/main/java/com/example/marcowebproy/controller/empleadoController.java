package com.example.marcowebproy.controller;

import com.example.marcowebproy.data.DataStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empleado")
public class empleadoController {
    private final DataStore dataStore;
    public empleadoController(DataStore dataStore) {this.dataStore = dataStore;}

    @GetMapping("/categorias")
    public String categorias(Model model) {
        model.addAttribute("categorias", dataStore.categorias);

        return "compoEmpleado/categorias";
    }
}