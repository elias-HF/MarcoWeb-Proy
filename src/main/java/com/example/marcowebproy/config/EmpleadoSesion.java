package com.example.marcowebproy.config;

import com.example.marcowebproy.entity.EmpleadoEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class EmpleadoSesion{

    @ModelAttribute("empleadoLogueado")
    public EmpleadoEntity obtenerEmpleadoDeSesion(HttpSession session) {
        return (EmpleadoEntity) session.getAttribute("empleadoLogueado");
    }
}