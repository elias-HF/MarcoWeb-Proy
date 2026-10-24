package com.example.marcowebproy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // clase temporal hasta la implementacion de spring security
public class ConfiguracionRutasWeb implements WebMvcConfigurer {

    private final VerificarSesionEmpleado empleadoAuthInterceptor;

    @Autowired
    public ConfiguracionRutasWeb(VerificarSesionEmpleado empleadoAuthInterceptor) {
        this.empleadoAuthInterceptor = empleadoAuthInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(empleadoAuthInterceptor)
                .addPathPatterns("/empleado/**")
                .excludePathPatterns("/login", "/css/**", "/js/**", "/images/**");
    }
}