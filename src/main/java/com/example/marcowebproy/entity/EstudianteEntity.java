package com.example.marcowebproy.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "estudiante")
public class EstudianteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String codigoEstudiante;
    private String nombre;
    private String apellido;
    private String correo;
    private String password;
    private String carrera;
    private boolean estado;

    private int demeritos;
    private int puntajeMerito;

    public EstudianteEntity() {
    }

    public EstudianteEntity(int id, String codigoEstudiante, String nombre, String apellido, String correo, boolean estado, String password, String carrera, int demeritos, int puntajeMerito) {
        this.id = id;
        this.codigoEstudiante = codigoEstudiante;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.estado = estado;
        this.password = password;
        this.carrera = carrera;
        this.demeritos = demeritos;
        this.puntajeMerito = puntajeMerito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(String codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getDemeritos() {
        return demeritos;
    }

    public void setDemeritos(int demeritos) {
        this.demeritos = demeritos;
    }

    public int getPuntajeMerito() {
        return puntajeMerito;
    }

    public void setPuntajeMerito(int puntajeMerito) {
        this.puntajeMerito = puntajeMerito;
    }
}
