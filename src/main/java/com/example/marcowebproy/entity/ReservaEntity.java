package com.example.marcowebproy.entity;

import com.example.marcowebproy.model.Estudiante;
import com.example.marcowebproy.model.Recurso;
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

    private Recurso recurso;
    private Estudiante estudiante;
}
