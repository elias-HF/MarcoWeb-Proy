package com.example.marcowebproy.config;

import com.example.marcowebproy.entity.EmpleadoEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component // clase temporal hasta la implementacion de spring security
public class VerificarSesionEmpleado implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        EmpleadoEntity empleadoLogueado = (EmpleadoEntity) session.getAttribute("empleadoLogueado");

        if (empleadoLogueado == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        return true;
    }
}