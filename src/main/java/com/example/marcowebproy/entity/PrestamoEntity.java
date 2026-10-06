package com.example.marcowebproy.entity;

import com.example.marcowebproy.model.Estudiante;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "prestamo")
public class PrestamoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "recurso_id")
    private RecursoEntity recurso;
    private LocalDate fechaPrestamo;
    private LocalDate fechaLimiteDevolucion;
    private LocalDate fechaDevolucion;
    private String estado;
    private String observacionDevolucion;
    private Estudiante estudiante;
    private String condicionDevolucion;
}
