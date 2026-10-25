
INSERT INTO categoria (nombre, descripcion, estado) VALUES ('Tecnología', 'Laptops, cámaras y proyectores multimedia', true),
 ('Salas de Estudio', 'Cubículos y salas grupales equipadas', true),
('Material Bibliográfico', 'Libros de ingeniería, bases de datos y redes', true);

INSERT INTO empleado (id, codigo_empleado, nombre, apellido, correo, password, rol)
VALUES (1, 'EMP001', 'Carlos', 'Mendoza', 'carlos.mendoza@utp.edu.pe', 'admin123', 'ADMINISTRADOR'),
(2, 'EMP002', 'Lucía', 'Fernández', 'lucia.fernandez@utp.edu.pe', 'soporte123', 'ASISTENTE');

INSERT INTO estudiante (codigo_estudiante, nombre, apellido, correo, password, carrera, estado, demeritos, puntaje_merito)
VALUES ('U20251234', 'Juan', 'Pérez', 'u20251234@utp.edu.pe', '123456', 'Ingeniería de Sistemas', true, 0, 200),
('U20255678', 'María', 'Gómez', 'u20255678@utp.edu.pe', '123456', 'Ingeniería de Software', true, 0, 100),
('U23273152', 'Elias', 'Huaringa', 'u23273152@utp.edu.pe', '123456', 'Ingeniería de Sistemas e Informatica', true, 0, 40);

INSERT INTO espacio_academico (nombre, tipo, ubicacion, capacidad, estado)
VALUES ('Laboratorio de Redes 101', 'Laboratorio', 'Pabellón A - Piso 1', 25, true),
('Sala de Estudio Grupal 03', 'Sala', 'Biblioteca Central - Piso 2', 6, true),
('Auditorio Principal', 'Auditorio', 'Pabellón B - Planta Baja', 120, true);

INSERT INTO recurso ( icono, nombre, categoria_id, ubicacion, disponibilidad, condicion_fisica, activo)
VALUES ( 'bi-laptop', 'Laptop Core i7 - Dell', 1, 'Biblioteca - Módulo 2', 'Disponible', 'Excelente', true);

INSERT INTO recurso ( icono, nombre, categoria_id, ubicacion, disponibilidad, condicion_fisica, activo)
VALUES ('bi-camera-video', 'Cámara Sony 4K Profesional', 1, 'Multimedia - Piso 3', 'Disponible', 'Bueno', true),
( 'bi-projector', 'Proyector Epson PowerLite', 1, 'Almacén Central', 'En Mantenimiento', 'Regular', true);

INSERT INTO reserva (espacio_id, fecha_reserva, hora_inicio, hora_fin, estado, motivo_rechazo, fecha_hora_inicio, fecha_hora_fin, recurso_id, estudiante_id)
VALUES (1, '2026-10-15', '08:00', '10:00', 'Aprobada', NULL, '2026-10-15 08:00:00', '2026-10-15 10:00:00', 1, 1),
(2, '2026-10-16', '14:00', '16:00', 'Pendiente', NULL, '2026-10-16 14:00:00', '2026-10-16 16:00:00', NULL, 2);

INSERT INTO prestamo (recurso_id, fecha_prestamo, fecha_limite_devolucion, fecha_devolucion, estado, observacion_devolucion, estudiante_id, condicion_devolucion)
VALUES (2, '2026-10-10', '2026-10-12', '2026-10-11', 'Devuelto', 'Entregado en perfectas condiciones y con accesorios completos.', 1, 'Excelente');

ALTER TABLE categoria ALTER COLUMN id RESTART WITH 100;
ALTER TABLE categoria ALTER COLUMN id RESTART WITH 100;
ALTER TABLE recurso ALTER COLUMN id RESTART WITH 100;
ALTER TABLE espacio_academico ALTER COLUMN id RESTART WITH 100;