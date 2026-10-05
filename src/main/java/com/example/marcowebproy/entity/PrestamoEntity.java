package com.example.marcowebproy.entity;

import com.example.marcowebproy.model.Estudiante;
import com.example.marcowebproy.model.Recurso;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "prestamo")
public class PrestamoEntity {

    private int id;
    private Recurso recurso;
    private LocalDate fechaPrestamo;
    private LocalDate fechaLimiteDevolucion;
    private LocalDate fechaDevolucion;
    private String estado;
    private String observacionDevolucion;
    private Estudiante estudiante;
    private String condicionDevolucion;
}
