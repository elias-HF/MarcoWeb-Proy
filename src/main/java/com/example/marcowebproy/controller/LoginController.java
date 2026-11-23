package com.example.marcowebproy.controller;

import com.example.marcowebproy.filter.JwtAuthenticationFilter;
import com.example.marcowebproy.service.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@Controller
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @GetMapping("/login")
    public String mostrarLogin(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Correo o contraseña incorrectos.");
        }
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletResponse response){
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String rol = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().orElse("");

            String token = jwtService.generateToken(Map.of("rol",rol),userDetails);

            Cookie cookie = new Cookie(JwtAuthenticationFilter.JWT_COOKIE_NAME, token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            response.addCookie(cookie);

            if("ROLE_ESTUDIANTE".equals(rol)){
                return "redirect:/estudiante";
            }

            if("ROLE_EMPLEADO".equals(rol)){
                return "redirect:/empleado";
            }

            return "redirect:/login?error=true";

        }catch(AuthenticationException ex){
            return "redirect:/login?error=true";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie(JwtAuthenticationFilter.JWT_COOKIE_NAME, "");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/login";
    }

}