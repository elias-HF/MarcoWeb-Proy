package com.example.marcowebproy.entity;

import com.example.marcowebproy.model.EspacioAcademico;
import com.example.marcowebproy.model.Estudiante;
import com.example.marcowebproy.model.Recurso;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservaEntity {

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
}
