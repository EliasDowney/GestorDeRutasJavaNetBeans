CREATE DATABASE IF NOT EXISTS rutas CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE rutas;

CREATE TABLE IF NOT EXISTS administrador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    documento VARCHAR(20) NOT NULL,
    clave VARCHAR(50) NOT NULL,
    nombre VARCHAR(60) NOT NULL,
    UNIQUE KEY uq_admin_documento (documento)
);

-- Unico administrador de la aplicacion
INSERT IGNORE INTO administrador (documento, clave, nombre) VALUES ('1000', 'admin', 'Administrador');

CREATE TABLE IF NOT EXISTS coordinador (
    id_coordinador INT AUTO_INCREMENT PRIMARY KEY,
    documento VARCHAR(20) NOT NULL,
    nombre VARCHAR(60) NOT NULL,
    apellido VARCHAR(60) NOT NULL,
    telefono VARCHAR(20),
    correo VARCHAR(80),
    estado VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS conductor (
    id_conductor INT AUTO_INCREMENT PRIMARY KEY,
    documento VARCHAR(20) NOT NULL,
    nombre VARCHAR(60) NOT NULL,
    apellido VARCHAR(60) NOT NULL,
    telefono VARCHAR(20),
    licencia VARCHAR(20),
    estado VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS estudiante (
    id_estudiante INT AUTO_INCREMENT PRIMARY KEY,
    documento VARCHAR(20) NOT NULL,
    nombre VARCHAR(60) NOT NULL,
    apellido VARCHAR(60) NOT NULL,
    telefono VARCHAR(20),
    grado VARCHAR(15),
    direccion VARCHAR(120),
    nombre_acudiente VARCHAR(80),
    correo_acudiente VARCHAR(80),
    estado VARCHAR(15) NOT NULL,
    id_ruta INT
);

CREATE TABLE IF NOT EXISTS vehiculo (
    id_vehiculo INT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(10) NOT NULL,
    capacidad INT NOT NULL,
    estado VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS ruta (
    id_ruta INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    hora_salida TIME,
    hora_final TIME,
    estado VARCHAR(15) NOT NULL,
    id_conductor INT,
    id_vehiculo INT,
    id_coordinador INT,
    FOREIGN KEY (id_conductor) REFERENCES conductor(id_conductor),
    FOREIGN KEY (id_vehiculo) REFERENCES vehiculo(id_vehiculo),
    FOREIGN KEY (id_coordinador) REFERENCES coordinador(id_coordinador)
);

CREATE TABLE IF NOT EXISTS novedad (
    id_novedad INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(200),
    fecha DATE,
    estado VARCHAR(15) NOT NULL,
    categoria VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS novedad_estudiante (
    id_novedad INT PRIMARY KEY,
    id_estudiante INT NOT NULL,
    id_ruta INT,
    FOREIGN KEY (id_novedad) REFERENCES novedad(id_novedad),
    FOREIGN KEY (id_estudiante) REFERENCES estudiante(id_estudiante),
    FOREIGN KEY (id_ruta) REFERENCES ruta(id_ruta)
);

CREATE TABLE IF NOT EXISTS novedad_vehiculo (
    id_novedad INT PRIMARY KEY,
    id_vehiculo INT NOT NULL,
    id_ruta INT,
    id_conductor INT,
    FOREIGN KEY (id_novedad) REFERENCES novedad(id_novedad),
    FOREIGN KEY (id_vehiculo) REFERENCES vehiculo(id_vehiculo),
    FOREIGN KEY (id_ruta) REFERENCES ruta(id_ruta),
    FOREIGN KEY (id_conductor) REFERENCES conductor(id_conductor)
);

-- Cada estudiante queda asignado a una ruta (sin tabla intermedia)
ALTER TABLE estudiante
    ADD CONSTRAINT fk_estudiante_ruta FOREIGN KEY (id_ruta) REFERENCES ruta(id_ruta) ON DELETE SET NULL;
