DROP TABLE IF EXISTS prestamo CASCADE;
DROP TABLE IF EXISTS reserva CASCADE;
DROP TABLE IF EXISTS recurso CASCADE;
DROP TABLE IF EXISTS espacio_academico CASCADE;
DROP TABLE IF EXISTS estudiante CASCADE;
DROP TABLE IF EXISTS empleado CASCADE;
DROP TABLE IF EXISTS categoria CASCADE;

CREATE TABLE categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255),
    estado BOOLEAN NOT NULL
);

CREATE TABLE empleado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo_empleado VARCHAR(50) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    correo VARCHAR(150) NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    rol VARCHAR(50) NOT NULL
);

CREATE TABLE estudiante (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo_estudiante VARCHAR(50) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    correo VARCHAR(150) NOT NULL,
    password VARCHAR(255) NOT NULL,
    carrera VARCHAR(150) NOT NULL,
    estado BOOLEAN NOT NULL,
    demeritos INT DEFAULT 0,
    puntaje_merito INT DEFAULT 100
);

CREATE TABLE espacio_academico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    tipo VARCHAR(100) NOT NULL,
    ubicacion VARCHAR(150) NOT NULL,
    capacidad INT NOT NULL,
    estado BOOLEAN NOT NULL
);

CREATE TABLE recurso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    icono VARCHAR(50),
    nombre VARCHAR(150) NOT NULL,
    categoria_id INT,
    ubicacion VARCHAR(150),
    disponibilidad VARCHAR(50),
    condicion_fisica VARCHAR(50),
    activo BOOLEAN NOT NULL,
    CONSTRAINT fk_recurso_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);

CREATE TABLE reserva (
    id INT AUTO_INCREMENT PRIMARY KEY,
    espacio_id INT,
    fecha_reserva DATE,
    hora_inicio VARCHAR(10),
    hora_fin VARCHAR(10),
    estado VARCHAR(50),
    motivo_rechazo VARCHAR(255),
    fecha_hora_inicio TIMESTAMP,
    fecha_hora_fin TIMESTAMP,
    recurso_id INT,
    estudiante_id INT,
    CONSTRAINT fk_reserva_espacio FOREIGN KEY (espacio_id) REFERENCES espacio_academico(id),
    CONSTRAINT fk_reserva_recurso FOREIGN KEY (recurso_id) REFERENCES recurso(id),
    CONSTRAINT fk_reserva_estudiante FOREIGN KEY (estudiante_id) REFERENCES estudiante(id)
);

CREATE TABLE prestamo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    recurso_id INT,
    fecha_prestamo DATE,
    fecha_limite_devolucion DATE,
    fecha_devolucion DATE,
    estado VARCHAR(50),
    observacion_devolucion VARCHAR(255),
    estudiante_id INT,
    condicion_devolucion VARCHAR(50),
    CONSTRAINT fk_prestamo_recurso FOREIGN KEY (recurso_id) REFERENCES recurso(id),
    CONSTRAINT fk_prestamo_estudiante FOREIGN KEY (estudiante_id) REFERENCES estudiante(id)
);