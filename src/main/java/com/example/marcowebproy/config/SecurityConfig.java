package com.example.marcowebproy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    //tiempo de expiracion 1dia cambiar a 1hora
    private static final long EXPIRATION_TIME = 86400000;
}
