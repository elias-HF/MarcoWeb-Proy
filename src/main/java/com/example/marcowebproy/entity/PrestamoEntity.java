package com.example.marcowebproy.entity;

import com.example.marcowebproy.model.Estudiante;
import com.example.marcowebproy.model.Recurso;

import java.time.LocalDate;

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
