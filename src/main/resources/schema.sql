
DROP TABLE IF EXISTS reserva;
DROP TABLE IF EXISTS recurso;
DROP TABLE IF EXISTS espacio_academico;
DROP TABLE IF EXISTS estudiante;
DROP TABLE IF EXISTS categoria;


CREATE TABLE categoria (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(100) NOT NULL,
                           descripcion VARCHAR(255),
                           estado BOOLEAN NOT NULL
);

CREATE TABLE estudiante (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            codigo_estudiante VARCHAR(20) NOT NULL UNIQUE,
                            nombre VARCHAR(100) NOT NULL,
                            apellido VARCHAR(100) NOT NULL,
                            correo VARCHAR(150) NOT NULL,
                            password VARCHAR(255) NOT NULL,
                            carrera VARCHAR(100),
                            estado BOOLEAN NOT NULL,
                            demeritos INT DEFAULT 0,
                            puntaje_merito INT DEFAULT 0
);

CREATE TABLE espacio_academico (
                                   id INT AUTO_INCREMENT PRIMARY KEY,
                                   nombre VARCHAR(100) NOT NULL,
                                   tipo VARCHAR(50) NOT NULL,
                                   ubicacion VARCHAR(150),
                                   capacidad INT,
                                   estado BOOLEAN NOT NULL
);

CREATE TABLE recurso (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         icono VARCHAR(50),
                         nombre VARCHAR(100) NOT NULL,
                         ubicacion VARCHAR(150),
                         disponibilidad VARCHAR(50),
                         condicion_fisica VARCHAR(50),
                         activo BOOLEAN NOT NULL,
                         categoria_id INT,
                         CONSTRAINT fk_recurso_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);