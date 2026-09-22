package com.example.marcowebproy.model;

import java.time.LocalDate;

public class Prestamo {

    private int id;
    private Recurso recurso;
    private LocalDate fechaPrestamo;
    private LocalDate fechaLimiteDevolucion;
    private LocalDate fechaDevolucion;
    private String estado;
    private String observacionDevolcuion;

    public Prestamo() {

    }

    public Prestamo(int id, Recurso recurso, LocalDate fechaPrestamo, LocalDate fechaLimiteDevolucion, LocalDate fechaDevolucion, String estado, String observacionDevolcuion) {
        this.id = id;
        this.recurso = recurso;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaLimiteDevolucion = fechaLimiteDevolucion;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
        this.observacionDevolcuion = observacionDevolcuion;
    }
}
