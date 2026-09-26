package com.example.marcowebproy.model;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

    private int id;
    private String nombre;
    private String descripcion;
    private Recurso recursos;
    private boolean estado;


    public Categoria() {
    }

    public Categoria(int id, String nombre, String descripcion, Recurso recursos, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.recursos = recursos;
        this.estado = estado;
    }
    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Recurso getRecurso() { return recursos; }
    public void setRecursos(Recurso recursos) {this.recursos = recursos;}

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }
}
