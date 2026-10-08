package com.example.marcowebproy.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "recurso")
public class RecursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String icono;
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;
    private String ubicacion;
    private String disponibilidad;
    private String condicionFisica;
    private boolean activo;

    public RecursoEntity() {
    }

    public RecursoEntity(int id, String icono, String nombre, CategoriaEntity categoria, String disponibilidad, String ubicacion, String condicionFisica, boolean activo) {
        this.id = id;
        this.icono = icono;
        this.nombre = nombre;
        this.categoria = categoria;
        this.disponibilidad = disponibilidad;
        this.ubicacion = ubicacion;
        this.condicionFisica = condicionFisica;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getCondicionFisica() {
        return condicionFisica;
    }

    public void setCondicionFisica(String condicionFisica) {
        this.condicionFisica = condicionFisica;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
