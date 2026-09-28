package com.example.marcowebproy.data;

import com.example.marcowebproy.model.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataStore {
    public List<Categoria> categorias = new ArrayList<>();
    public List<Recurso> recursos = new ArrayList<>();
    public List<Estudiante> estudiantes = new ArrayList<>();
    public List<EspacioAcademico> espacios = new ArrayList<>();
    public List<Prestamo> prestamos = new ArrayList<>();
    public List<Reserva> reservas = new ArrayList<>();

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
        Recurso r3 = new Recurso(3, "bi-book","Libro de Ingeniería de Software", materialAcademico, "Biblioteca - Piso 1", "Disponible", "Buena", true);
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

        Estudiante estudiante1 = new Estudiante(1, "20241001", "Juan", "Pérez", "juan.perez@institucion.edu.pe", "123456", "Ingeniería de Sistemas", true, 0, 17);
        Estudiante estudiante2 = new Estudiante(2, "20241002", "María", "López", "maria.lopez@institucion.edu.pe", "123456", "Ingeniería de Sistemas", true, 0, 19);
        Estudiante estudiante3 = new Estudiante(3, "20241003", "Carlos", "Ramírez", "carlos.ramirez@institucion.edu.pe", "123456", "Ingeniería de Sistemas", true, 0, 14);
        Estudiante estudiante4 = new Estudiante(4, "20241004", "Pedro", "Castillo", "pedro.castillo@institucion.edu.pe", "123456", "Ingeniería de Sistemas", true, 1, 10);
        estudiantes.add(estudiante1);
        estudiantes.add(estudiante2);
        estudiantes.add(estudiante3);
        estudiantes.add(estudiante4);

        Prestamo p1 = new Prestamo();p1.setId(1);p1.setEstudiante(estudiante1);p1.setRecurso(r1);p1.setFechaPrestamo(LocalDate.of(2026, 9, 26));p1.setFechaLimiteDevolucion(LocalDate.of(2026, 9, 29));p1.setEstado("Activo");
        Prestamo p2 = new Prestamo();p2.setId(2);p2.setEstudiante(estudiante2);p2.setRecurso(r2);p2.setFechaPrestamo(LocalDate.of(2026, 9, 26));p2.setFechaLimiteDevolucion(LocalDate.of(2026, 9, 30));p2.setEstado("Activo");
        Prestamo p3 = new Prestamo();p3.setId(3);p3.setEstudiante(estudiante3);p3.setRecurso(r3);p3.setFechaPrestamo(LocalDate.of(2026, 9, 27));p3.setEstado("Pendiente");
        Prestamo p4 = new Prestamo();p4.setId(4);p4.setEstudiante(estudiante4);p4.setRecurso(r3);p4.setFechaPrestamo(LocalDate.of(2026, 9, 20));p4.setFechaLimiteDevolucion(LocalDate.of(2026, 9, 23));p4.setFechaDevolucion(LocalDate.of(2026, 9, 23));p4.setCondicionDevolucion("Bueno");p4.setObservacionDevolucion("Recurso devuelto en buen estado.");p4.setEstado("Devuelto");
        prestamos.add(p1);
        prestamos.add(p2);
        prestamos.add(p3);
        prestamos.add(p4);

        reservas.add(new Reserva(1, espacios.get(0), LocalDate.of(2026, 8, 29), "10:00", "12:00",
                "Pendiente", LocalDateTime.of(2026, 8, 29, 10, 0), LocalDateTime.of(2026, 8, 29, 12, 0),
                null, estudiantes.get(0), null
        ));

        reservas.add(new Reserva(
                2, espacios.get(1), LocalDate.of(2026, 8, 30),
                "14:00", "16:00", "Confirmada",
                LocalDateTime.of(2026, 8, 30, 14, 0), LocalDateTime.of(2026, 8, 30, 16, 0), null,
                estudiantes.get(1), null
        ));

        reservas.add(new Reserva(
                3, null, LocalDate.of(2026, 8, 31),
                "09:00", "11:00", "Cancelada",
                LocalDateTime.of(2026, 8, 31, 9, 0), LocalDateTime.of(2026, 8, 31, 11, 0), recursos.get(0),
                estudiantes.get(2), "El recurso ya se encuentra reservado en el horario solicitado."
        ));
    }
}