package com.example.marcowebproy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "estudiante")
public class EstudianteEntity {

    private int id;
    private String codigoEstudiante;
    private String nombre;
    private String apellido;
    private String correo;
    private String password;
    private String carrera;
    private boolean estado;

    private int demeritos;
    private int puntajeMerito;
}
