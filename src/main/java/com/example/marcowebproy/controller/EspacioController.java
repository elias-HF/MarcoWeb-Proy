package com.example.marcowebproy.controller;

import com.example.marcowebproy.entity.EspacioAcademicoEntity;
import com.example.marcowebproy.service.EspacioAcademicoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EspacioController {
    private final EspacioAcademicoService service;

    public EspacioController(EspacioAcademicoService service) {
        this.service = service;
    }

    @GetMapping("/empleado/espacios")
    public String mostrar(Model model) {
        model.addAttribute("espacios", service.listar());
        return "compoEmpleado/espacios";
    }

    // sirve para registrar y para modificar
    @PostMapping({"/empleado/espacios/guardar", "/empleado/espacios/modificar"})
    public String guardar(@Valid EspacioAcademicoEntity espacio, BindingResult resultado, RedirectAttributes ra) {
        if (resultado.hasErrors()) {
            ra.addFlashAttribute("error", resultado.getFieldError().getDefaultMessage());
        } else {
            service.guardar(espacio);
        }
        return "redirect:/empleado/espacios";
    }

    @PostMapping("/empleado/espacios/desactivar")
    public String desactivar(@RequestParam int id) {
        service.desactivar(id);
        return "redirect:/empleado/espacios";
    }
}