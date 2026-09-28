package com.example.marcowebproy.controller;

import com.example.marcowebproy.data.DataStore;
import com.example.marcowebproy.model.Prestamo;
import com.example.marcowebproy.model.Recurso;
import com.example.marcowebproy.model.Reserva;
import com.example.marcowebproy.model.EspacioAcademico;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/estudiante")
public class estudianteController {
    private final DataStore dataStore;

    public estudianteController(DataStore dataStore){this.dataStore= dataStore;}

    @GetMapping("")
    public String estudiante() {return "compoEstudiante/estudiante";}

    @GetMapping("/recursos")
    public String recursos(@RequestParam(name = "query", required = false) String query, @RequestParam(name = "categoriaId", required = false) Integer categoriaId, Model model) {
        List<Recurso> recursos = dataStore.recursos.stream().filter(Recurso::isActivo).collect(Collectors.toList());
        List<EspacioAcademico> espacios = dataStore.espacios.stream().filter(EspacioAcademico::isEstado).collect(Collectors.toList());

        if(query != null && !query.trim().isEmpty()){
            String q = query.toLowerCase().trim();
            recursos = recursos.stream().filter(r -> r.getNombre().toLowerCase().contains(q) || r.getUbicacion().toLowerCase().contains(q)).collect(Collectors.toList());

            espacios = espacios.stream().filter(e -> e.getNombre().toLowerCase().contains(q) || e.getUbicacion().toLowerCase().contains(q)).collect(Collectors.toList());
        }

        if(categoriaId != null && categoriaId > 0){
            recursos = recursos.stream().filter(r -> r.getCategoria() !=null && r.getCategoria().getId() == categoriaId).collect(Collectors.toList());
            espacios.clear();
        }

        model.addAttribute("recursos",recursos);
        model.addAttribute("espacios",espacios);
        model.addAttribute("categorias", dataStore.categorias);
        model.addAttribute("query", query);
        model.addAttribute("categoriaSeleccionadaId", categoriaId);

        return "compoEstudiante/recursos";
    }

    @GetMapping("/reservas")
    public String reservas(@RequestParam(name = "estado", required = false) String estado, Model model) {
        List<Reserva> listaR = dataStore.reservas;

        if(estado != null && !estado.trim().isEmpty()){
            listaR = dataStore.reservas.stream().filter(r ->r.getEstado().equalsIgnoreCase(estado)).toList();
        }
        model.addAttribute("reservas",listaR);
        model.addAttribute("estadoSeleccionado",estado);
        return "compoEstudiante/reservas";
    }

    @GetMapping("/prestamos")
    public String prestamos(Model model) {
        List<Prestamo> listaP = dataStore.prestamos;
        long enCurso = listaP.stream().filter(p -> "Activo".equalsIgnoreCase(p.getEstado())).count();
        long pendientes = listaP.stream().filter(p -> "Pendiente".equalsIgnoreCase(p.getEstado())).count();
        long atrasados = listaP.stream().filter(p -> "Atrasado".equalsIgnoreCase(p.getEstado())).count();
        long devueltos = listaP.stream().filter(p -> "Devuelto".equalsIgnoreCase(p.getEstado())).count();

        model.addAttribute("prestamos",listaP);
        model.addAttribute("cantEnCurso",enCurso);
        model.addAttribute("cantPendientes",pendientes);
        model.addAttribute("cantAtrasados",atrasados);
        model.addAttribute("cantDevueltos",devueltos);

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
        Reserva reserva = new Reserva();
        EspacioAcademico espacio = dataStore.espacios.stream().filter(e -> e.getId() == idEspacio).findFirst().orElse(null);
        reserva.setEspacioAcademico(espacio);
        model.addAttribute("reserva", reserva);

        return "compoEstudiante/reservar-espacio";
    }

    @PostMapping("/prestamos/guardar")
    public String guardarPrestamo(@RequestParam("recursoId") int recursoId, @RequestParam("fechaDevolucion") String fechaDevolucionStr, @RequestParam(value = "observacion", required = false) String observacion) {

        Prestamo nuevo = new Prestamo();
        nuevo.setId(dataStore.prestamos.size() + 1);

        Recurso recurso = dataStore.recursos.stream().filter(r -> r.getId() == recursoId).findFirst().orElse(null);
        nuevo.setRecurso(recurso);
        nuevo.setFechaPrestamo(LocalDate.now());
        if (fechaDevolucionStr != null && !fechaDevolucionStr.isEmpty()) {
            nuevo.setFechaLimiteDevolucion(LocalDate.parse(fechaDevolucionStr));
        }
        nuevo.setEstado("Pendiente");
        nuevo.setObservacionDevolucion(observacion);
        nuevo.setEstudiante(dataStore.estudiantes.get(0));
        dataStore.prestamos.add(nuevo);

        return "redirect:/estudiante/prestamos";
    }

    @PostMapping("/prestamos/cancelar/{id}")
    public String cancelarPrestamo(@PathVariable("id") int id) {
        Prestamo prestamo = dataStore.prestamos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);

        if (prestamo != null && "Pendiente".equalsIgnoreCase(prestamo.getEstado())) {
            prestamo.setEstado("Cancelado");
        }

        return "redirect:/estudiante/prestamos";
    }

    @PostMapping("/reservar-espacio/guardar")
    public String guardarReserva(@ModelAttribute("reserva") Reserva reserva) {
        reserva.setEstado("Pendiente");

        reserva.setId(dataStore.reservas.size() + 1);
        dataStore.reservas.add(reserva);

        return "redirect:/estudiante/reservas";
    }
}