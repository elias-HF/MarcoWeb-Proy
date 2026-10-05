package com.example.marcowebproy.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "empleado")
public class EmpleadoEntity {

    private int id;
    private String codigoEmpleado;
    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;
    private String rol;

}
