package com.example.marcowebproy.model;

public class Categoria {

    private int id;
    private String nombre;
    private String descripcion;
    private boolean estado;

    public Categoria() {
    }

    public Categoria(int id, String nombre, String descripcion, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }
}
