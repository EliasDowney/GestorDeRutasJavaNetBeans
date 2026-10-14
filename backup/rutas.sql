-- MariaDB dump 10.19  Distrib 10.4.32-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: rutas
-- ------------------------------------------------------
-- Server version	10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `rutas`
--

/*!40000 DROP DATABASE IF EXISTS `rutas`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `rutas` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `rutas`;

--
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrador` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `documento` varchar(20) NOT NULL,
  `clave` varchar(50) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_admin_documento` (`documento`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES (1,'1000','admin','Administrador');
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conductor`
--

DROP TABLE IF EXISTS `conductor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conductor` (
  `id_conductor` int(11) NOT NULL AUTO_INCREMENT,
  `documento` varchar(20) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `apellido` varchar(60) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `licencia` varchar(20) DEFAULT NULL,
  `estado` varchar(15) NOT NULL,
  PRIMARY KEY (`id_conductor`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conductor`
--

LOCK TABLES `conductor` WRITE;
/*!40000 ALTER TABLE `conductor` DISABLE KEYS */;
INSERT INTO `conductor` VALUES (1,'2001','Pedro','Lopez','3001112223','C2-1','ACTIVO'),(2,'2002','Maria','Sanchez','3004445556','C2-2','ACTIVO'),(4,'2003','Andres','Pineda','3102003','C2-3','ACTIVO'),(5,'2004','Felipe','Mora','3102004','C2-4','ACTIVO'),(6,'2005','Camila','Suarez','3102005','C2-5','ACTIVO');
/*!40000 ALTER TABLE `conductor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coordinador`
--

DROP TABLE IF EXISTS `coordinador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coordinador` (
  `id_coordinador` int(11) NOT NULL AUTO_INCREMENT,
  `documento` varchar(20) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `apellido` varchar(60) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `correo` varchar(80) DEFAULT NULL,
  `estado` varchar(15) NOT NULL,
  PRIMARY KEY (`id_coordinador`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coordinador`
--

LOCK TABLES `coordinador` WRITE;
/*!40000 ALTER TABLE `coordinador` DISABLE KEYS */;
INSERT INTO `coordinador` VALUES (1,'3001','Carla','Martinez','3101112223','carla@mail.com','ACTIVO'),(2,'3002','Jorge','Ruiz','3104445556','jorge@mail.com','ACTIVO'),(4,'1020','marco','mendez','2222220','mendez@gmail.com','ACTIVO'),(5,'3003','Sofia','Ramirez','3103003','sofia.ramirez@mail.com','ACTIVO'),(6,'3004','Diego','Herrera','3103004','diego.herrera@mail.com','ACTIVO');
/*!40000 ALTER TABLE `coordinador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudiante`
--

DROP TABLE IF EXISTS `estudiante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estudiante` (
  `id_estudiante` int(11) NOT NULL AUTO_INCREMENT,
  `documento` varchar(20) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `apellido` varchar(60) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `grado` varchar(15) DEFAULT NULL,
  `direccion` varchar(120) DEFAULT NULL,
  `nombre_acudiente` varchar(80) DEFAULT NULL,
  `correo_acudiente` varchar(80) DEFAULT NULL,
  `estado` varchar(15) NOT NULL,
  `id_ruta` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_estudiante`),
  KEY `fk_estudiante_ruta` (`id_ruta`),
  CONSTRAINT `fk_estudiante_ruta` FOREIGN KEY (`id_ruta`) REFERENCES `ruta` (`id_ruta`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=947 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudiante`
--

LOCK TABLES `estudiante` WRITE;
/*!40000 ALTER TABLE `estudiante` DISABLE KEYS */;
INSERT INTO `estudiante` VALUES (902,'1001','Ana','Perez','3201111','10','CRA 100 # 77-83','Lucia Perez','lucia@mail.com','ACTIVO',905),(903,'1002','Luis','Gomez','3202222','9','CLL 127 # 5-20','Raul Gomez','raul@mail.com','ACTIVO',906),(904,'1003','Sofia','Diaz','3203333','11','KR 30 # 10-25','Elena Diaz','elena@mail.com','ACTIVO',907),(905,'1004','Carlos','Rojas','3204444','8','CLL 13 # 9-40','Ana Rojas','ana@mail.com','ACTIVO',902),(906,'1005','Marta','Lopez','3205555','10','CLL 88 # 12-30','Pepe Lopez','pepe@mail.com','ACTIVO',903),(907,'1006','Diego','Hernandez','3206666','9','CRA 45 # 60-10','Sara Hernandez','sara@mail.com','ACTIVO',905),(908,'1007','Laura','Torres','3207777','11','CLL 50 # 3-15','Maria Torres','maria@mail.com','ACTIVO',906),(909,'1008','Andres','Castro','3208888','8','KR 15 # 22-40','Luis Castro','luis@mail.com','ACTIVO',907),(911,'79490286','javier','cicuamia','3114348367','11','cra 32 # 35-15','yolanda cantor','yoliscantor@gmail.com','ACTIVO',903),(912,'1009','Juan Camilo','Rios','3101110009','10','CLL 145 # 12-30','Rosa Rios','rosa.rios@mail.com','ACTIVO',905),(913,'1010','Valentina','Cruz','3101110010','9','CRA 7 # 45-10','Mario Cruz','mario.cruz@mail.com','ACTIVO',906),(914,'1011','Santiago','Vega','3101110011','11','CRA 60 # 22-05','Lucia Vega','lucia.vega@mail.com','ACTIVO',907),(915,'1012','Isabella','Mora','3101110012','8','CLL 32 # 40-11','Pedro Mora','pedro.mora@mail.com','ACTIVO',902),(916,'1013','Mateo','Herrera','3101110013','10','CRA 120 # 15-60','Ana Herrera','ana.herrera@mail.com','ACTIVO',903),(917,'1014','Camila','Navas','3101110014','7','CLL 5 # 3-20','Jose Navas','jose.navas@mail.com','ACTIVO',905),(918,'1015','Nicolas','Rey','3101110015','9','CRA 88 # 70-40','Diana Rey','diana.rey@mail.com','ACTIVO',906),(919,'1016','Mariana','Pena','3101110016','11','CLL 50 # 8-15','Carlos Pena','carlos.pena@mail.com','ACTIVO',907),(920,'1017','Samuel','Rueda','3101110017','6','CRA 13 # 55-30','Elena Rueda','elena.rueda@mail.com','ACTIVO',902),(921,'1018','Sara','Ospina','3101110018','10','CLL 77 # 12-45','Oscar Ospina','oscar.ospina@mail.com','ACTIVO',903),(922,'1019','Daniel','Quintero','3101110019','9','CRA 100 # 30-20','Paola Quintero','paola.quintero@mail.com','ACTIVO',905),(923,'1020','Salome','Acosta','3101110020','8','CLL 25 # 6-70','Andres Acosta','andres.acosta@mail.com','ACTIVO',906),(924,'1021','Tomas','Beltran','3101110021','11','CRA 150 # 90-10','Rosa Beltran','rosa.beltran@mail.com','ACTIVO',907),(925,'1022','Gabriela','Nino','3101110022','7','CLL 42 # 14-35','Luis Nino','luis.nino@mail.com','ACTIVO',902),(926,'1023','Emmanuel','Cardenas','3101110023','10','CRA 68 # 33-25','Marta Cardenas','marta.cardenas@mail.com','ACTIVO',903),(927,'1024','Luciana','Silva','3101110024','6','CLL 90 # 5-40','Jorge Silva','jorge.silva@mail.com','ACTIVO',905),(928,'1025','Martin','Arias','3101110025','9','CRA 11 # 48-15','Nubia Arias','nubia.arias@mail.com','ACTIVO',906),(929,'1026','Antonella','Franco','3101110026','11','CLL 135 # 22-60','Ramiro Franco','ramiro.franco@mail.com','ACTIVO',907),(930,'1027','Jeronimo','Salazar','3101110027','8','CRA 55 # 18-30','Angela Salazar','angela.salazar@mail.com','ACTIVO',902),(931,'1028','Emily','Cardona','3101110028','10','CLL 20 # 9-55','Wilson Cardona','wilson.cardona@mail.com','ACTIVO',903),(932,'1029','Sebastian','Londono','3101110029','9','CRA 72 # 27-10','Claudia Londono','claudia.londono@mail.com','ACTIVO',905),(933,'1030','Sofia','Rojas','3101110030','7','CLL 105 # 16-40','Ivan Rojas','ivan.rojas@mail.com','ACTIVO',906),(934,'1031','Alejandro','Gutierrez','3101110031','11','CRA 38 # 44-20','Monica Gutierrez','monica.gutierrez@mail.com','ACTIVO',907),(935,'1032','Renata','Camacho','3101110032','6','CLL 160 # 60-30','Diego Camacho','diego.camacho@mail.com','ACTIVO',902),(936,'1033','Felipe','Osorio','3101110033','10','CRA 8 # 31-15','Sandra Osorio','sandra.osorio@mail.com','ACTIVO',903),(937,'1034','Danna','Villamil','3101110034','9','CLL 95 # 7-50','Rodrigo Villamil','rodrigo.villamil@mail.com','ACTIVO',905),(938,'1035','Miguel','Bernal','3101110035','8','CRA 48 # 52-25','Pilar Bernal','pilar.bernal@mail.com','ACTIVO',906),(939,'1036','Paula','Duarte','3101110036','11','CLL 130 # 24-10','Hernan Duarte','hernan.duarte@mail.com','ACTIVO',907),(940,'1037','Andres','Mejia','3101110037','7','CRA 17 # 66-30','Lorena Mejia','lorena.mejia@mail.com','ACTIVO',902),(941,'1038','Valeria','Prieto','3101110038','10','CLL 82 # 11-45','Fabio Prieto','fabio.prieto@mail.com','ACTIVO',903),(942,'1039','Juan Jose','Rangel','3101110039','6','CRA 58 # 39-20','Nidia Rangel','nidia.rangel@mail.com','ACTIVO',905),(943,'1040','Maria Paz','Sepulveda','3101110040','9','CLL 110 # 13-55','Camilo Sepulveda','camilo.sepulveda@mail.com','ACTIVO',906),(944,'1041','Tomas','Barrera','3101110041','11','CRA 63 # 21-40','Yolanda Barrera','yolanda.barrera@mail.com','ACTIVO',907),(945,'1042','Isabela','Cifuentes','3101110042','8','CLL 28 # 4-25','German Cifuentes','german.cifuentes@mail.com','ACTIVO',902),(946,'1043','Nicolas','Amaya','3101110043','10','CRA 140 # 70-15','Blanca Amaya','blanca.amaya@mail.com','ACTIVO',903);
/*!40000 ALTER TABLE `estudiante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `novedad`
--

DROP TABLE IF EXISTS `novedad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `novedad` (
  `id_novedad` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(200) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `estado` varchar(15) NOT NULL,
  `categoria` varchar(10) NOT NULL,
  PRIMARY KEY (`id_novedad`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `novedad`
--

LOCK TABLES `novedad` WRITE;
/*!40000 ALTER TABLE `novedad` DISABLE KEYS */;
INSERT INTO `novedad` VALUES (3,'El estudiante presento malestar durante el recorrido de la manana','2026-10-14','PENDIENTE','ESTUDIANTE'),(4,'El vehiculo presento una falla mecanica (llanta pinchada) en ruta','2026-10-11','EN PROCESO','VEHICULO');
/*!40000 ALTER TABLE `novedad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `novedad_estudiante`
--

DROP TABLE IF EXISTS `novedad_estudiante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `novedad_estudiante` (
  `id_novedad` int(11) NOT NULL,
  `id_estudiante` int(11) NOT NULL,
  `id_ruta` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_novedad`),
  KEY `id_estudiante` (`id_estudiante`),
  KEY `id_ruta` (`id_ruta`),
  CONSTRAINT `novedad_estudiante_ibfk_1` FOREIGN KEY (`id_novedad`) REFERENCES `novedad` (`id_novedad`),
  CONSTRAINT `novedad_estudiante_ibfk_2` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiante` (`id_estudiante`),
  CONSTRAINT `novedad_estudiante_ibfk_3` FOREIGN KEY (`id_ruta`) REFERENCES `ruta` (`id_ruta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `novedad_estudiante`
--

LOCK TABLES `novedad_estudiante` WRITE;
/*!40000 ALTER TABLE `novedad_estudiante` DISABLE KEYS */;
INSERT INTO `novedad_estudiante` VALUES (3,902,905);
/*!40000 ALTER TABLE `novedad_estudiante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `novedad_vehiculo`
--

DROP TABLE IF EXISTS `novedad_vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `novedad_vehiculo` (
  `id_novedad` int(11) NOT NULL,
  `id_vehiculo` int(11) NOT NULL,
  `id_ruta` int(11) DEFAULT NULL,
  `id_conductor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_novedad`),
  KEY `id_vehiculo` (`id_vehiculo`),
  KEY `id_ruta` (`id_ruta`),
  KEY `id_conductor` (`id_conductor`),
  CONSTRAINT `novedad_vehiculo_ibfk_1` FOREIGN KEY (`id_novedad`) REFERENCES `novedad` (`id_novedad`),
  CONSTRAINT `novedad_vehiculo_ibfk_2` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculo` (`id_vehiculo`),
  CONSTRAINT `novedad_vehiculo_ibfk_3` FOREIGN KEY (`id_ruta`) REFERENCES `ruta` (`id_ruta`),
  CONSTRAINT `novedad_vehiculo_ibfk_4` FOREIGN KEY (`id_conductor`) REFERENCES `conductor` (`id_conductor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `novedad_vehiculo`
--

LOCK TABLES `novedad_vehiculo` WRITE;
/*!40000 ALTER TABLE `novedad_vehiculo` DISABLE KEYS */;
INSERT INTO `novedad_vehiculo` VALUES (4,1,902,1);
/*!40000 ALTER TABLE `novedad_vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ruta`
--

DROP TABLE IF EXISTS `ruta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ruta` (
  `id_ruta` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(60) NOT NULL,
  `hora_salida` time DEFAULT NULL,
  `hora_final` time DEFAULT NULL,
  `estado` varchar(15) NOT NULL,
  `id_conductor` int(11) DEFAULT NULL,
  `id_vehiculo` int(11) DEFAULT NULL,
  `id_coordinador` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_ruta`),
  KEY `id_conductor` (`id_conductor`),
  KEY `id_vehiculo` (`id_vehiculo`),
  KEY `id_coordinador` (`id_coordinador`),
  CONSTRAINT `ruta_ibfk_1` FOREIGN KEY (`id_conductor`) REFERENCES `conductor` (`id_conductor`),
  CONSTRAINT `ruta_ibfk_2` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculo` (`id_vehiculo`),
  CONSTRAINT `ruta_ibfk_3` FOREIGN KEY (`id_coordinador`) REFERENCES `coordinador` (`id_coordinador`)
) ENGINE=InnoDB AUTO_INCREMENT=908 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ruta`
--

LOCK TABLES `ruta` WRITE;
/*!40000 ALTER TABLE `ruta` DISABLE KEYS */;
INSERT INTO `ruta` VALUES (902,'Ruta Norte','06:00:00','07:00:00','ACTIVO',1,1,1),(903,'Ruta Sur','06:30:00','07:30:00','ACTIVO',2,2,2),(905,'Ruta Oriente','06:30:00','07:30:00','ACTIVO',4,4,4),(906,'Ruta Occidente','06:45:00','07:45:00','ACTIVO',5,5,5),(907,'Ruta Centro','07:00:00','08:00:00','ACTIVO',6,6,6);
/*!40000 ALTER TABLE `ruta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculo`
--

DROP TABLE IF EXISTS `vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehiculo` (
  `id_vehiculo` int(11) NOT NULL AUTO_INCREMENT,
  `placa` varchar(10) NOT NULL,
  `capacidad` int(11) NOT NULL,
  `estado` varchar(15) NOT NULL,
  PRIMARY KEY (`id_vehiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculo`
--

LOCK TABLES `vehiculo` WRITE;
/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
INSERT INTO `vehiculo` VALUES (1,'ABC123',10,'ACTIVO'),(2,'XYZ789',10,'ACTIVO'),(4,'QRS456',10,'ACTIVO'),(5,'LMN789',10,'ACTIVO'),(6,'TUV321',10,'ACTIVO');
/*!40000 ALTER TABLE `vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'rutas'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-10-14  7:52:50
