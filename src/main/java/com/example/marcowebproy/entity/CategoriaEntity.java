package com.example.marcowebproy.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria")
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "recurso_id")
    private RecursoEntity recurso;
    private boolean estado;
}
