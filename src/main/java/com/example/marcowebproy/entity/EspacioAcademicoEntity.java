package com.example.marcowebproy.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "espacioAcademico")
public class EspacioAcademicoEntity {

    private int id;
    private String nombre;
    private String tipo;
    private String ubicacion;
    private int capacidad;
    private boolean estado;
}
