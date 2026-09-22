package com.example.marcowebproy.model;

public class Empleado {

    private int id;
    private String codigoEmpleado;
    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;
    private String rol;

    public Empleado() {
    }

    public Empleado(int id, String codigoEmpleado, String nombre, String apellido, String rol, String correo, String contraseña) {
        this.id = id;
        this.codigoEmpleado = codigoEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.correo = correo;
        this.contraseña = contraseña;
    }
}
