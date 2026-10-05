package com.example.marcowebproy.entity;

import com.example.marcowebproy.model.Recurso;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria")
public class CategoriaEntity {

    private int id;
    private String nombre;
    private String descripcion;
    private Recurso recurso;
    private boolean estado;
}
