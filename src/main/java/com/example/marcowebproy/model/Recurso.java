package com.example.marcowebproy.model;

public class Recurso {

    private int id;
    private String nombre;
    private Categoria categoria;
    private String estado;
    private String condicionFisica;

    public Recurso() {
    }

    public Recurso(int id, String nombre, Categoria categoria, String estado, String condicionFisica) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.estado = estado;
        this.condicionFisica = condicionFisica;
    }
}
