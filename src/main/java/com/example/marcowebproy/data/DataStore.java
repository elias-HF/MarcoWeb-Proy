package com.example.marcowebproy.data;

import com.example.marcowebproy.model.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataStore {
    public List<Categoria> categorias = new ArrayList<>();
    public List<Recurso> recursos = new ArrayList<>();
    public List<EspacioAcademico> espacios = new ArrayList<>();
    public List<Reserva> reservas = new ArrayList<>();
    public List<Prestamo> prestamos = new ArrayList<>();

    public DataStore() {
        Categoria tecnologia = new Categoria(1, "Tecnología", "Equipos tecnológicos para uso académico.", null, true);
        Categoria audiovisual = new Categoria(2, "Audiovisual", "Equipos utilizados para presentaciones.", null, true);
        Categoria materialAcademico = new Categoria(3, "Material académico", "Libros y materiales de consulta.", null, true);
        Categoria accesorios = new Categoria(4, "Accesorios", "Accesorios y complementos tecnológicos.", null, true);
        categorias.add(tecnologia);
        categorias.add(audiovisual);
        categorias.add(materialAcademico);
        categorias.add(accesorios);

        Recurso r1 = new Recurso(1, "bi-laptop", "Laptop Dell Core i7", tecnologia, "Biblioteca - Piso 3", "Disponible", "Buena", true);
        Recurso r2 = new Recurso(2, "bi-projector","Proyector Epson", audiovisual, "Laboratorio de Sistemas", "Disponible", "Buena", true);
        Recurso r3 = new Recurso(3, "bi-book","Libro de Ingeniería de Software", materialAcademico, "Biblioteca - Piso 1", "Disponible", "Buena", false);
        recursos.add(r1);
        recursos.add(r2);
        recursos.add(r3);

        EspacioAcademico e1 = new EspacioAcademico(1, "Sala de estudio A-102","Sala de estudio", "Torre A - Piso 1", 6, true);
        EspacioAcademico e2 = new EspacioAcademico(2, "Laboratorio de Redes","Laboratorio", "Torre A - Piso 4", 40, true);
        EspacioAcademico e3 = new EspacioAcademico(3, "Aula 204","Aula", "Torre A - Piso 2", 30, true);
        EspacioAcademico e4 = new EspacioAcademico(4, "Sala de estudio B-205","Sala de estudio", "Torre B - Piso 2", 8, false);
        espacios.add(e1);
        espacios.add(e2);
        espacios.add(e3);
        espacios.add(e4);

        Reserva re1 = new Reserva(1, e1, LocalDate.of(2026, 9, 2), "14:00", "16:00", "PENDIENTE");
        Reserva re2 = new Reserva(2, e2, LocalDate.of(2026, 9, 5), "08:00", "10:00", "PENDIENTE");
        Reserva re3 = new Reserva(3, e3, LocalDate.of(2026, 8, 20), "16:00", "18:00", "FINALIZADA");
        Reserva re4 = new Reserva(4, e4, LocalDate.of(2026, 8, 15), "10:00", "12:00", "CANCELADA");
        reservas.add(re1);
        reservas.add(re2);
        reservas.add(re3);
        reservas.add(re4);

        Prestamo p1 = new Prestamo(1, r2, null, null, null, "PENDIENTE", null);
        Prestamo p2 = new Prestamo(2, r1, LocalDate.of(2026, 8, 28), LocalDate.of(2026, 8, 28), null, "EN_CURSO", null);
        Prestamo p3 = new Prestamo(3, r3, LocalDate.of(2026, 8, 10), LocalDate.of(2026, 8, 11), LocalDate.of(2026, 8, 11), "DEVUELTO", "Entrega a tiempo");
        Prestamo p4 = new Prestamo(4, r2, LocalDate.of(2026, 8, 2), LocalDate.of(2026, 8, 2), LocalDate.of(2026, 8, 2), "DEVUELTO", "Sin observaciones");
        prestamos.add(p1);
        prestamos.add(p2);
        prestamos.add(p3);
        prestamos.add(p4);

    }
}