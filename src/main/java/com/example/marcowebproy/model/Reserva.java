package com.example.marcowebproy.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reserva {
    private int id;
    private EspacioAcademico espacioAcademico;
    private LocalDate fechaReserva;
    private String horaInicio;
    private String horaFin;
    private String estado;
    private String motivoRechazo;

    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;

    private Recurso recurso;
    private Estudiante estudiante;

    public Reserva() {}

    public Reserva(int id, EspacioAcademico espacioAcademico, LocalDate fechaReserva, String horaInicio, String horaFin, String estado, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, Recurso recurso, Estudiante estudiante, String motivoRechazo) {
        this.id = id;
        this.espacioAcademico = espacioAcademico;
        this.fechaReserva = fechaReserva;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.recurso = recurso;
        this.estudiante = estudiante;
        this.motivoRechazo = motivoRechazo;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public EspacioAcademico getEspacioAcademico() {return espacioAcademico;}
    public void setEspacioAcademico(EspacioAcademico espacioAcademico) {this.espacioAcademico = espacioAcademico;}

    public LocalDate getFechaReserva() {return fechaReserva;}
    public void setFechaReserva(LocalDate fechaReserva) {this.fechaReserva = fechaReserva;}

    public String getHoraInicio() {return horaInicio;}
    public void setHoraInicio(String horaInicio) {this.horaInicio = horaInicio;}

    public String getHoraFin() {return horaFin;}
    public void setHoraFin(String horaFin) {this.horaFin = horaFin;}

    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}

    public LocalDateTime getFechaHoraInicio() {return fechaHoraInicio;}
    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {this.fechaHoraInicio = fechaHoraInicio;}

    public LocalDateTime getFechaHoraFin() {return fechaHoraFin;}
    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {this.fechaHoraFin = fechaHoraFin;}

    public Recurso getRecurso() {return recurso;}
    public void setRecurso(Recurso recurso) {this.recurso = recurso;}

    public Estudiante getEstudiante() {return estudiante;}
    public void setEstudiante(Estudiante estudiante) {this.estudiante = estudiante;}

    public String getMotivoRechazo() {return motivoRechazo;}
    public void setMotivoRechazo(String motivoRechazo) {this.motivoRechazo = motivoRechazo;}
}