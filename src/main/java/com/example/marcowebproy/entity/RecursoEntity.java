package com.example.marcowebproy.entity;

import com.example.marcowebproy.model.Categoria;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "recurso")
public class RecursoEntity {

    private int id;
    private String icono;
    private String nombre;
    private Categoria categoria;
    private String ubicacion;
    private String disponibilidad;
    private String condicionFisica;
    private boolean activo;
}
