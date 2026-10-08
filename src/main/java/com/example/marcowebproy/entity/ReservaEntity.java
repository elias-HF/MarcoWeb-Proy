package com.example.marcowebproy.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reserva")
public class ReservaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "espacio_id")
    private EspacioAcademicoEntity espacioAcademico;
    private LocalDate fechaReserva;
    private String horaInicio;
    private String horaFin;
    private String estado;
    private String motivoRechazo;

    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    @ManyToOne
    @JoinColumn(name = "recurso_id")
    private RecursoEntity recurso;
    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private EstudianteEntity estudiante;

    public ReservaEntity(int id, EspacioAcademicoEntity espacioAcademico, LocalDate fechaReserva, String horaInicio, String estado, String horaFin, String motivoRechazo, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, RecursoEntity recurso, EstudianteEntity estudiante) {
        this.id = id;
        this.espacioAcademico = espacioAcademico;
        this.fechaReserva = fechaReserva;
        this.horaInicio = horaInicio;
        this.estado = estado;
        this.horaFin = horaFin;
        this.motivoRechazo = motivoRechazo;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.recurso = recurso;
        this.estudiante = estudiante;
    }

    public ReservaEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EspacioAcademicoEntity getEspacioAcademico() {
        return espacioAcademico;
    }

    public void setEspacioAcademico(EspacioAcademicoEntity espacioAcademico) {
        this.espacioAcademico = espacioAcademico;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public RecursoEntity getRecurso() {
        return recurso;
    }

    public void setRecurso(RecursoEntity recurso) {
        this.recurso = recurso;
    }

    public EstudianteEntity getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteEntity estudiante) {
        this.estudiante = estudiante;
    }
}
