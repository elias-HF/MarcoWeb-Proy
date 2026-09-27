package com.example.marcowebproy.model;

import java.time.LocalDate;

public class Prestamo {
    private int id;
    private Recurso recurso;
    private LocalDate fechaPrestamo;
    private LocalDate fechaLimiteDevolucion;
    private LocalDate fechaDevolucion;
    private String estado;
    private String observacionDevolucion;
    private Estudiante estudiante;
    private String condicionDevolucion;

    public Prestamo() {}

    public Prestamo(int id, Recurso recurso, LocalDate fechaPrestamo, LocalDate fechaLimiteDevolucion, LocalDate fechaDevolucion, String estado, String observacionDevolucion, Estudiante estudiante, String condicionDevolucion) {
        this.id = id;
        this.recurso = recurso;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaLimiteDevolucion = fechaLimiteDevolucion;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
        this.observacionDevolucion = observacionDevolucion;
        this.estudiante = estudiante;
        this.condicionDevolucion = condicionDevolucion;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public Recurso getRecurso() {return recurso;}
    public void setRecurso(Recurso recurso) {this.recurso = recurso;}

    public LocalDate getFechaPrestamo() {return fechaPrestamo;}
    public void setFechaPrestamo(LocalDate fechaPrestamo) {this.fechaPrestamo = fechaPrestamo;}

    public LocalDate getFechaLimiteDevolucion() {return fechaLimiteDevolucion;}
    public void setFechaLimiteDevolucion(LocalDate fechaLimiteDevolucion) {this.fechaLimiteDevolucion = fechaLimiteDevolucion;}

    public LocalDate getFechaDevolucion() {return fechaDevolucion;}
    public void setFechaDevolucion(LocalDate fechaDevolucion) {this.fechaDevolucion = fechaDevolucion;}

    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}

    public String getObservacionDevolucion() {return observacionDevolucion;}
    public void setObservacionDevolucion(String observacionDevolucion) {this.observacionDevolucion = observacionDevolucion;}

    public Estudiante getEstudiante() {return estudiante;}
    public void setEstudiante(Estudiante estudiante) {this.estudiante = estudiante;}

    public String getCondicionDevolucion() {return condicionDevolucion;}
    public void setCondicionDevolucion(String condicionDevolucion) {this.condicionDevolucion = condicionDevolucion;}
}