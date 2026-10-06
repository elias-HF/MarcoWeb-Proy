package com.example.marcowebproy.entity;

import com.example.marcowebproy.model.Categoria;
import jakarta.persistence.*;


@Entity
@Table(name = "recurso")
public class RecursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String icono;
    private String nombre;
    @OneToMany
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;
    private String ubicacion;
    private String disponibilidad;
    private String condicionFisica;
    private boolean activo;
}
