USE rutas;

-- 1) Vehiculos: los 2 existentes pasan a cupo 10 y se agregan 3 (total 5, todos cupo 10)
UPDATE vehiculo SET capacidad = 10 WHERE placa IN ('ABC123', 'XYZ789');
INSERT INTO vehiculo (placa, capacidad, estado) VALUES
 ('QRS456', 10, 'ACTIVO'),
 ('LMN789', 10, 'ACTIVO'),
 ('TUV321', 10, 'ACTIVO');

-- 2) Conductores (se agregan 3 para llegar a 5)
INSERT INTO conductor (documento, nombre, apellido, telefono, licencia, estado) VALUES
 ('2003', 'Andres', 'Pineda', '3102003', 'C2-3', 'ACTIVO'),
 ('2004', 'Felipe', 'Mora', '3102004', 'C2-4', 'ACTIVO'),
 ('2005', 'Camila', 'Suarez', '3102005', 'C2-5', 'ACTIVO');

-- 3) Coordinadores (se agregan 2 para llegar a 5)
INSERT INTO coordinador (documento, nombre, apellido, telefono, correo, estado) VALUES
 ('3003', 'Sofia', 'Ramirez', '3103003', 'sofia.ramirez@mail.com', 'ACTIVO'),
 ('3004', 'Diego', 'Herrera', '3103004', 'diego.herrera@mail.com', 'ACTIVO');

-- 4) Rutas (se agregan 3 para llegar a 5, cada una con su conductor, vehiculo y coordinador)
INSERT INTO ruta (nombre, hora_salida, hora_final, estado, id_conductor, id_vehiculo, id_coordinador)
SELECT 'Ruta Oriente', '06:30:00', '07:30:00', 'ACTIVO',
 (SELECT id_conductor FROM conductor WHERE documento = '2003'),
 (SELECT id_vehiculo FROM vehiculo WHERE placa = 'QRS456'),
 (SELECT id_coordinador FROM coordinador WHERE documento = '1020');
INSERT INTO ruta (nombre, hora_salida, hora_final, estado, id_conductor, id_vehiculo, id_coordinador)
SELECT 'Ruta Occidente', '06:45:00', '07:45:00', 'ACTIVO',
 (SELECT id_conductor FROM conductor WHERE documento = '2004'),
 (SELECT id_vehiculo FROM vehiculo WHERE placa = 'LMN789'),
 (SELECT id_coordinador FROM coordinador WHERE documento = '3003');
INSERT INTO ruta (nombre, hora_salida, hora_final, estado, id_conductor, id_vehiculo, id_coordinador)
SELECT 'Ruta Centro', '07:00:00', '08:00:00', 'ACTIVO',
 (SELECT id_conductor FROM conductor WHERE documento = '2005'),
 (SELECT id_vehiculo FROM vehiculo WHERE placa = 'TUV321'),
 (SELECT id_coordinador FROM coordinador WHERE documento = '3004');

-- 5) Estudiantes nuevos (35) repartidos en distintas zonas/direcciones
INSERT INTO estudiante (documento, nombre, apellido, telefono, grado, direccion, nombre_acudiente, correo_acudiente, estado) VALUES
 ('1009', 'Juan Camilo', 'Rios', '3101110009', '10', 'CLL 145 # 12-30', 'Rosa Rios', 'rosa.rios@mail.com', 'ACTIVO'),
 ('1010', 'Valentina', 'Cruz', '3101110010', '9', 'CRA 7 # 45-10', 'Mario Cruz', 'mario.cruz@mail.com', 'ACTIVO'),
 ('1011', 'Santiago', 'Vega', '3101110011', '11', 'CRA 60 # 22-05', 'Lucia Vega', 'lucia.vega@mail.com', 'ACTIVO'),
 ('1012', 'Isabella', 'Mora', '3101110012', '8', 'CLL 32 # 40-11', 'Pedro Mora', 'pedro.mora@mail.com', 'ACTIVO'),
 ('1013', 'Mateo', 'Herrera', '3101110013', '10', 'CRA 120 # 15-60', 'Ana Herrera', 'ana.herrera@mail.com', 'ACTIVO'),
 ('1014', 'Camila', 'Navas', '3101110014', '7', 'CLL 5 # 3-20', 'Jose Navas', 'jose.navas@mail.com', 'ACTIVO'),
 ('1015', 'Nicolas', 'Rey', '3101110015', '9', 'CRA 88 # 70-40', 'Diana Rey', 'diana.rey@mail.com', 'ACTIVO'),
 ('1016', 'Mariana', 'Pena', '3101110016', '11', 'CLL 50 # 8-15', 'Carlos Pena', 'carlos.pena@mail.com', 'ACTIVO'),
 ('1017', 'Samuel', 'Rueda', '3101110017', '6', 'CRA 13 # 55-30', 'Elena Rueda', 'elena.rueda@mail.com', 'ACTIVO'),
 ('1018', 'Sara', 'Ospina', '3101110018', '10', 'CLL 77 # 12-45', 'Oscar Ospina', 'oscar.ospina@mail.com', 'ACTIVO'),
 ('1019', 'Daniel', 'Quintero', '3101110019', '9', 'CRA 100 # 30-20', 'Paola Quintero', 'paola.quintero@mail.com', 'ACTIVO'),
 ('1020', 'Salome', 'Acosta', '3101110020', '8', 'CLL 25 # 6-70', 'Andres Acosta', 'andres.acosta@mail.com', 'ACTIVO'),
 ('1021', 'Tomas', 'Beltran', '3101110021', '11', 'CRA 150 # 90-10', 'Rosa Beltran', 'rosa.beltran@mail.com', 'ACTIVO'),
 ('1022', 'Gabriela', 'Nino', '3101110022', '7', 'CLL 42 # 14-35', 'Luis Nino', 'luis.nino@mail.com', 'ACTIVO'),
 ('1023', 'Emmanuel', 'Cardenas', '3101110023', '10', 'CRA 68 # 33-25', 'Marta Cardenas', 'marta.cardenas@mail.com', 'ACTIVO'),
 ('1024', 'Luciana', 'Silva', '3101110024', '6', 'CLL 90 # 5-40', 'Jorge Silva', 'jorge.silva@mail.com', 'ACTIVO'),
 ('1025', 'Martin', 'Arias', '3101110025', '9', 'CRA 11 # 48-15', 'Nubia Arias', 'nubia.arias@mail.com', 'ACTIVO'),
 ('1026', 'Antonella', 'Franco', '3101110026', '11', 'CLL 135 # 22-60', 'Ramiro Franco', 'ramiro.franco@mail.com', 'ACTIVO'),
 ('1027', 'Jeronimo', 'Salazar', '3101110027', '8', 'CRA 55 # 18-30', 'Angela Salazar', 'angela.salazar@mail.com', 'ACTIVO'),
 ('1028', 'Emily', 'Cardona', '3101110028', '10', 'CLL 20 # 9-55', 'Wilson Cardona', 'wilson.cardona@mail.com', 'ACTIVO'),
 ('1029', 'Sebastian', 'Londono', '3101110029', '9', 'CRA 72 # 27-10', 'Claudia Londono', 'claudia.londono@mail.com', 'ACTIVO'),
 ('1030', 'Sofia', 'Rojas', '3101110030', '7', 'CLL 105 # 16-40', 'Ivan Rojas', 'ivan.rojas@mail.com', 'ACTIVO'),
 ('1031', 'Alejandro', 'Gutierrez', '3101110031', '11', 'CRA 38 # 44-20', 'Monica Gutierrez', 'monica.gutierrez@mail.com', 'ACTIVO'),
 ('1032', 'Renata', 'Camacho', '3101110032', '6', 'CLL 160 # 60-30', 'Diego Camacho', 'diego.camacho@mail.com', 'ACTIVO'),
 ('1033', 'Felipe', 'Osorio', '3101110033', '10', 'CRA 8 # 31-15', 'Sandra Osorio', 'sandra.osorio@mail.com', 'ACTIVO'),
 ('1034', 'Danna', 'Villamil', '3101110034', '9', 'CLL 95 # 7-50', 'Rodrigo Villamil', 'rodrigo.villamil@mail.com', 'ACTIVO'),
 ('1035', 'Miguel', 'Bernal', '3101110035', '8', 'CRA 48 # 52-25', 'Pilar Bernal', 'pilar.bernal@mail.com', 'ACTIVO'),
 ('1036', 'Paula', 'Duarte', '3101110036', '11', 'CLL 130 # 24-10', 'Hernan Duarte', 'hernan.duarte@mail.com', 'ACTIVO'),
 ('1037', 'Andres', 'Mejia', '3101110037', '7', 'CRA 17 # 66-30', 'Lorena Mejia', 'lorena.mejia@mail.com', 'ACTIVO'),
 ('1038', 'Valeria', 'Prieto', '3101110038', '10', 'CLL 82 # 11-45', 'Fabio Prieto', 'fabio.prieto@mail.com', 'ACTIVO'),
 ('1039', 'Juan Jose', 'Rangel', '3101110039', '6', 'CRA 58 # 39-20', 'Nidia Rangel', 'nidia.rangel@mail.com', 'ACTIVO'),
 ('1040', 'Maria Paz', 'Sepulveda', '3101110040', '9', 'CLL 110 # 13-55', 'Camilo Sepulveda', 'camilo.sepulveda@mail.com', 'ACTIVO'),
 ('1041', 'Tomas', 'Barrera', '3101110041', '11', 'CRA 63 # 21-40', 'Yolanda Barrera', 'yolanda.barrera@mail.com', 'ACTIVO'),
 ('1042', 'Isabela', 'Cifuentes', '3101110042', '8', 'CLL 28 # 4-25', 'German Cifuentes', 'german.cifuentes@mail.com', 'ACTIVO'),
 ('1043', 'Nicolas', 'Amaya', '3101110043', '10', 'CRA 140 # 70-15', 'Blanca Amaya', 'blanca.amaya@mail.com', 'ACTIVO');

-- 6) Asignar a todos los estudiantes a una de las 5 rutas (reparto por id, robusto ante ids con saltos)
UPDATE estudiante SET id_ruta = (
  CASE (id_estudiante MOD 5)
    WHEN 0 THEN (SELECT id_ruta FROM ruta WHERE nombre = 'Ruta Norte')
    WHEN 1 THEN (SELECT id_ruta FROM ruta WHERE nombre = 'Ruta Sur')
    WHEN 2 THEN (SELECT id_ruta FROM ruta WHERE nombre = 'Ruta Oriente')
    WHEN 3 THEN (SELECT id_ruta FROM ruta WHERE nombre = 'Ruta Occidente')
    WHEN 4 THEN (SELECT id_ruta FROM ruta WHERE nombre = 'Ruta Centro')
  END);

-- 7) Novedades (sin hora, segun lo indicado)
INSERT INTO novedad (descripcion, fecha, estado, categoria)
VALUES ('El estudiante presento malestar durante el recorrido de la manana', CURDATE(), 'PENDIENTE', 'ESTUDIANTE');
INSERT INTO novedad_estudiante (id_novedad, id_estudiante, id_ruta)
SELECT LAST_INSERT_ID(), e.id_estudiante, e.id_ruta FROM estudiante e WHERE e.documento = '1001';

INSERT INTO novedad (descripcion, fecha, estado, categoria)
VALUES ('El vehiculo presento una falla mecanica (llanta pinchada) en ruta', DATE_SUB(CURDATE(), INTERVAL 3 DAY), 'EN PROCESO', 'VEHICULO');
INSERT INTO novedad_vehiculo (id_novedad, id_vehiculo, id_ruta, id_conductor)
SELECT LAST_INSERT_ID(), v.id_vehiculo,
 (SELECT id_ruta FROM ruta WHERE nombre = 'Ruta Norte'),
 (SELECT id_conductor FROM conductor WHERE documento = '2001')
FROM vehiculo v WHERE v.placa = 'ABC123';
