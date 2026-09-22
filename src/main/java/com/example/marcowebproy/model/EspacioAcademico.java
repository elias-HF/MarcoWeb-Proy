package com.example.marcowebproy.model;

public class EspacioAcademico {

    private int id;
    private String nombre;
    private String ubicacion;
    private int capacidad;
    private boolean disponible;

    public EspacioAcademico() {
    }

    public EspacioAcademico(int id, String nombre, String ubicacion, int capacidad, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.disponible = disponible;
    }
}
