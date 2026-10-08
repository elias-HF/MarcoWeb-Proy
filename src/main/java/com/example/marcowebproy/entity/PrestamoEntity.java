package com.example.marcowebproy.entity;

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
    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private EstudianteEntity estudiante;
    private String condicionDevolucion;

    public PrestamoEntity() {
    }

    public PrestamoEntity(int id, RecursoEntity recurso, LocalDate fechaPrestamo, LocalDate fechaDevolucion, LocalDate fechaLimiteDevolucion, String estado, String observacionDevolucion, EstudianteEntity estudiante, String condicionDevolucion) {
        this.id = id;
        this.recurso = recurso;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.fechaLimiteDevolucion = fechaLimiteDevolucion;
        this.estado = estado;
        this.observacionDevolucion = observacionDevolucion;
        this.estudiante = estudiante;
        this.condicionDevolucion = condicionDevolucion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RecursoEntity getRecurso() {
        return recurso;
    }

    public void setRecurso(RecursoEntity recurso) {
        this.recurso = recurso;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaLimiteDevolucion() {
        return fechaLimiteDevolucion;
    }

    public void setFechaLimiteDevolucion(LocalDate fechaLimiteDevolucion) {
        this.fechaLimiteDevolucion = fechaLimiteDevolucion;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacionDevolucion() {
        return observacionDevolucion;
    }

    public void setObservacionDevolucion(String observacionDevolucion) {
        this.observacionDevolucion = observacionDevolucion;
    }

    public String getCondicionDevolucion() {
        return condicionDevolucion;
    }

    public void setCondicionDevolucion(String condicionDevolucion) {
        this.condicionDevolucion = condicionDevolucion;
    }

    public EstudianteEntity getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteEntity estudiante) {
        this.estudiante = estudiante;
    }
}
