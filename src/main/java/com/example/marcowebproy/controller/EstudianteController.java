package com.example.marcowebproy.controller;

import com.example.marcowebproy.entity.EstudianteEntity;
import com.example.marcowebproy.model.Reserva;
import com.example.marcowebproy.repository.EstudianteRepository;
import com.example.marcowebproy.service.EstudianteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Map;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {
    private final EstudianteService estudianteService;
    private final EstudianteRepository estudianteRepository;

    public EstudianteController(EstudianteService estudianteService, EstudianteRepository estudianteRepository){
        this.estudianteService = estudianteService;
        this.estudianteRepository = estudianteRepository;
    }

    @GetMapping("")
    public String estudiante(Model model) {

        EstudianteEntity estudianteActual = obtenerEstudianteAutenticado();

        if (estudianteActual == null) {
            return "redirect:/login";
        }

        model.addAttribute("estudiante", estudianteActual);
        return "compoEstudiante/estudiante";
    }

    @GetMapping("/recursos")
    public String recursos(@RequestParam(name = "query", required = false) String query,
                           @RequestParam(name = "categoriaId", required = false) Integer categoriaId,
                           Model model) {

        model.addAttribute("recursos", estudianteService.buscarRecurso(query, categoriaId));
        model.addAttribute("espacios", estudianteService.buscarEspacio(query, categoriaId));
        model.addAttribute("categorias", estudianteService.listarCategorias());
        model.addAttribute("query", query);
        model.addAttribute("categoriaSeleccionadaId", categoriaId);
        model.addAttribute("horariosPermitidos", estudianteService.obtenerHorariosDisponibles());

        return "compoEstudiante/recursos";
    }

    @GetMapping("/reservas")
    public String reservas(@RequestParam(name = "estado", required = false) String estado, Model model) {
        EstudianteEntity estudianteActual = obtenerEstudianteAutenticado();
        if(estudianteActual == null){
            return "redirect:/login";
        }

        model.addAttribute("reservas",estudianteService.listarReservasPorEstudiante(estudianteActual.getId(), estado));
        model.addAttribute("estadoSeleccionado",estado);
        return "compoEstudiante/reservas";
    }

    @GetMapping("/prestamos")
    public String prestamos( Model model) {
        EstudianteEntity estudianteActual = obtenerEstudianteAutenticado();
        if(estudianteActual == null){
            return "redirect:/login";
        }

        Map<String, Long> stats = estudianteService.obtenerEstadisticasPrestamosPorEstudiante(estudianteActual.getId());

        model.addAttribute("prestamos",estudianteService.listarPrestamosPorEstudiante(estudianteActual.getId()));
        model.addAttribute("cantEnCurso",stats.get("Activos"));
        model.addAttribute("cantPendientes",stats.get("Pendientes"));
        model.addAttribute("cantAtrasados",stats.get("Atrasados"));
        model.addAttribute("cantDevueltos",stats.get("Devueltos"));

        return "compoEstudiante/prestamos";
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "compoEstudiante/contacto";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/reservar-espacio/{id}")
    public String mostrarFormularioReserva(@PathVariable("id") int idEspacio, Model model) {
        Reserva reserva = estudianteService.obtenerReservasPorEspacioId(idEspacio);
        model.addAttribute("reserva", reserva);
        model.addAttribute("horariosPermitidos", estudianteService.obtenerHorariosDisponibles());
        return "compoEstudiante/recursos";
    }

    @PostMapping("/prestamos/guardar")
    public String guardarPrestamo(@RequestParam("recursoId") int recursoId, @RequestParam("fechaLimiteDevolucion") String fechaLimiteDevolucion, @RequestParam(value = "observacion", required = false) String observacion,RedirectAttributes redirectAttributes) {
        EstudianteEntity estudianteActual = obtenerEstudianteAutenticado();
        if(estudianteActual == null){
            return "redirect:/login";
        }

        if (!estudianteActual.isEstado()) {
            redirectAttributes.addFlashAttribute("mensajeError", "Tu cuenta está inhabilitada por deméritos. No puedes solicitar préstamos.");
            return "redirect:/estudiante/prestamos";
        }

        try{
            estudianteService.guardarPrestamo(recursoId, fechaLimiteDevolucion, observacion, estudianteActual.getId());
            redirectAttributes.addFlashAttribute("mensajeExito","Prestamo Solicitado con exito.");
        }catch(IllegalStateException | IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
        }

        return "redirect:/estudiante/prestamos";
    }

    @PostMapping("/prestamos/cancelar/{id}")
    public String cancelarPrestamo(@PathVariable("id") int id) {
        estudianteService.cancelarPrestamo(id);
        return "redirect:/estudiante/prestamos";
    }

    @PostMapping("/reservar-espacio/guardar")
    public String guardarReserva(@ModelAttribute("reserva") Reserva reserva, RedirectAttributes redirectAttributes) {
        EstudianteEntity estudianteActual = obtenerEstudianteAutenticado();

        if(estudianteActual == null){
            return "redirect:/login";
        }

        if (!estudianteActual.isEstado()) {
            redirectAttributes.addFlashAttribute("mensajeError", "Tu cuenta está inhabilitada por deméritos. No puedes realizar reservas.");
            return "redirect:/estudiante/recursos";
        }

        try{
            estudianteService.guardarReserva(reserva, estudianteActual.getId());
            redirectAttributes.addFlashAttribute("mensajeExito","Reserva realizada con exito.");
        }catch (IllegalStateException | IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
            return "redirect:/estudiante/recursos";
        }

        return "redirect:/estudiante/reservas";
    }

    @GetMapping("/reservas/cancelar/{id}")
    public String cancelarReserva(@PathVariable("id") int id) {
        estudianteService.cancelarReserva(id);
        return "redirect:/estudiante/reservas";
    }

    private EstudianteEntity obtenerEstudianteAutenticado(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())){
            return null;
        }

        return estudianteRepository.findByCorreo(auth.getName()).orElse(null);
    }

}