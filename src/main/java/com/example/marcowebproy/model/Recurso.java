package com.example.marcowebproy.model;

public class Recurso {
    private int id;
    private String icono;
    private String nombre;
    private Categoria categoria;
    private String ubicacion;
    private String disponibilidad;
    private String condicionFisica;
    private boolean activo;

    public Recurso() {}

    public Recurso(int id, String icono,String nombre, Categoria categoria, String ubicacion,String disponibilidad, String condicionFisica, boolean activo) {
        this.id = id;
        this.icono = icono;
        this.nombre = nombre;
        this.categoria = categoria;
        this.ubicacion = ubicacion;
        this.disponibilidad = disponibilidad;
        this.condicionFisica = condicionFisica;
        this.activo = activo;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getIcono() {return icono;}
    public void setIcono(String icono) {this.icono = icono;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public Categoria getCategoria() {return categoria;}
    public void setCategoria(Categoria categoria) {this.categoria = categoria;}

    public String getUbicacion() {return ubicacion;}
    public void setUbicacion(String ubicacion) {this.ubicacion = ubicacion;}

    public String getDisponibilidad() {return disponibilidad;}
    public void setDisponibilidad(String disponibilidad) {this.disponibilidad = disponibilidad;}

    public String getCondicionFisica() {return condicionFisica;}
    public void setCondicionFisica(String condicionFisica) {this.condicionFisica = condicionFisica;}

    public boolean isActivo() {return activo;}
    public void setActivo(boolean activo) {this.activo = activo;}
}