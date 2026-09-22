package com.example.marcowebproy.model;

public class Estudiante {

    private int id;
    private String codigoEstudiante;
    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;
    private boolean estado;

    public Estudiante(){

    }

    public Estudiante(int id, String codigoEstudiante, String nombre, String apellido, String correo, String contraseña, boolean estado) {
        this.id = id;
        this.codigoEstudiante = codigoEstudiante;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
        this.estado = estado;
    }


}
