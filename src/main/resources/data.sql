
INSERT INTO categoria (id, nombre, descripcion, estado) VALUES (1, 'Tecnologia', 'Equipos de computo y multimedia', true);
INSERT INTO categoria (id, nombre, descripcion, estado) VALUES (2, 'Salas de Estudio', 'Espacios grupales e individuales', true);

INSERT INTO estudiante (id, codigo_estudiante, nombre, apellido, correo, password, carrera, estado, demeritos, puntaje_merito)
VALUES (1, 'U20251234', 'Juan', 'Perez', 'juanPerez@utp.edu.pe', '123456', 'Ingeniería de Sistemas', true, 0, 20);

INSERT INTO recurso (id, icono, nombre, ubicacion, disponibilidad, condicion_fisica, activo, categoria_id)
VALUES (1, 'bi-laptop', 'Laptop Core i7', 'Biblioteca - Módulo 2', 'Disponible', 'Excelente', true, 1);

INSERT INTO recurso (id, icono, nombre, ubicacion, disponibilidad, condicion_fisica, activo, categoria_id)
VALUES (2, 'bi-camera-video', 'Cámara Sony 4K', 'Multimedia - Piso 3', 'Disponible', 'Bueno', true, 1);

INSERT INTO espacio_academico (id, nombre, tipo, ubicacion, capacidad, estado)
VALUES (1, 'Laboratorio de Redes 101', 'Laboratorio', 'Pabellan A - Piso 1', 25, true);

INSERT INTO espacio_academico (id, nombre, tipo, ubicacion, capacidad, estado)
VALUES (2, 'Sala de Estudio Grupal 03', 'Sala', 'Biblioteca - Piso 2', 6, true);