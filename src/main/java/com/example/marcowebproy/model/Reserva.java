package com.example.marcowebproy.model;

import java.time.LocalDate;

public class Reserva {

    private int id;
    private EspacioAcademico espacioAcademico;
    private LocalDate fechaReserva;
    private String horaInicio;
    private String horaFin;
    private String estado;

    public Reserva() {
    }

    public Reserva(int id, EspacioAcademico espacioAcademico, LocalDate fechaReserva, String horaInicio, String horaFin, String estado) {
        this.id = id;
        this.espacioAcademico = espacioAcademico;
        this.fechaReserva = fechaReserva;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
    }
public int getId() { return id; }
public void setId(int id) { this.id = id; }

public EspacioAcademico getEspacioAcademico() { return espacioAcademico; }
public void setEspacioAcademico(EspacioAcademico espacioAcademico) { this.espacioAcademico = espacioAcademico; }

public LocalDate getFechaReserva() { return fechaReserva; }
public void setFechaReserva(LocalDate fechaReserva) { this.fechaReserva = fechaReserva; }

public String getHoraInicio() { return horaInicio; }
public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }

public String getHoraFin() { return horaFin; }
public void setHoraFin(String horaFin) { this.horaFin = horaFin; }

public String getEstado() { return estado; }
public void setEstado(String estado) { this.estado = estado; }
}
