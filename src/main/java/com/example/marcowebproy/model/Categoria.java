package com.example.marcowebproy.model;

public class Categoria {

    private int id;
    private String nombre;
    private String descripcion;
    private Recurso recurso;
    private boolean estado;

    public Categoria() {}

    public Categoria(int id, String nombre, String descripcion, Recurso recurso, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.recurso = recurso;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Recurso getRecurso() { return recurso; }
    public void setRecursos(Recurso recursos) {this.recurso = recurso;}

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }
}