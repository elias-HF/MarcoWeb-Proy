package com.example.marcowebproy.controller;

import com.example.marcowebproy.data.DataStore;
import com.example.marcowebproy.model.Prestamo;
import com.example.marcowebproy.model.Recurso;
import com.example.marcowebproy.model.Reserva;
import com.example.marcowebproy.model.EspacioAcademico;
import com.example.marcowebproy.service.EstudianteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {
    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService){
        this.estudianteService = estudianteService;
    }

    @GetMapping("")
    public String estudiante() {
        return "compoEstudiante/estudiante";
    }

    @GetMapping("/recursos")
    public String recursos(@RequestParam(name = "query", required = false) String query, @RequestParam(name = "categoriaId", required = false) Integer categoriaId, Model model) {

        model.addAttribute("recursos",estudianteService.buscarRecurso(query, categoriaId));
        model.addAttribute("espacios",estudianteService.buscarEspacio(query, categoriaId));
        model.addAttribute("categorias", estudianteService.listarCategorias());
        model.addAttribute("query", query);
        model.addAttribute("categoriaSeleccionadaId", categoriaId);

        return "compoEstudiante/recursos";
    }

    @GetMapping("/reservas")
    public String reservas(@RequestParam(name = "estado", required = false) String estado, Model model) {

        model.addAttribute("reservas",estudianteService.lisarReservas(estado));
        model.addAttribute("estadoSeleccionado",estado);
        return "compoEstudiante/reservas";
    }

    @GetMapping("/prestamos")
    public String prestamos(Model model) {
        Map<String, Long> stats = estudianteService.obtenerEstadisticasPrestamos();

        model.addAttribute("prestamos",estudianteService.listarPrestamos());
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
        model.addAttribute("reserva", estudianteService.obtenerReservasPorEspacioId(idEspacio));
        return "compoEstudiante/espacio";
    }

    @PostMapping("/prestamos/guardar")
    public String guardarPrestamo(@RequestParam("recursoId") int recursoId, @RequestParam("fechaDevolucion") String fechaDevolucionStr, @RequestParam(value = "observacion", required = false) String observacion) {
        estudianteService.guardarPrestamo(recursoId, fechaDevolucionStr, observacion);
        return "redirect:/estudiante/prestamos";
    }

    @PostMapping("/prestamos/cancelar/{id}")
    public String cancelarPrestamo(@PathVariable("id") int id) {
        estudianteService.cancelarPrestamo(id);
        return "redirect:/estudiante/prestamos";
    }

    @PostMapping("/reservar-espacio/guardar")
    public String guardarReserva(@ModelAttribute("reserva") Reserva reserva) {
        estudianteService.guardarReserva(reserva);
        return "redirect:/estudiante/reservas";
    }

    @GetMapping("/reservas/cancelar/{id}")
    public String cancelarReserva(@PathVariable("id") int id) {
        estudianteService.cancelarReserva(id);
        return "redirect:/estudiante/reservas";
    }
}