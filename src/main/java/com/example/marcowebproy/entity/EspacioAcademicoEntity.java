package com.example.marcowebproy.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "espacioAcademico")
public class EspacioAcademicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String tipo;
    private String ubicacion;
    private int capacidad;
    private boolean estado;
}
