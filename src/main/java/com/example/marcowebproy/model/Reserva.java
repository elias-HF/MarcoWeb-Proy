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
}
