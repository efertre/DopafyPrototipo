-- MySQL dump 10.16  Distrib 10.1.48-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: db
-- ------------------------------------------------------
-- Server version	10.1.48-MariaDB-0+deb9u2

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
-- Table structure for table `COMPLETO`
--

DROP TABLE IF EXISTS `COMPLETO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `COMPLETO` (
  `subscriptionId` tinyint(4) DEFAULT NULL,
  `objetivosIlimitados` varchar(4) DEFAULT NULL,
  `noAnuncios` varchar(4) DEFAULT NULL,
  `descuentos` varchar(4) DEFAULT NULL,
  `avataresExclusivos` varchar(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `COMPLETO`
--

LOCK TABLES `COMPLETO` WRITE;
/*!40000 ALTER TABLE `COMPLETO` DISABLE KEYS */;
INSERT INTO `COMPLETO` VALUES (3,'True','True','True','True');
/*!40000 ALTER TABLE `COMPLETO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GRATUITO`
--

DROP TABLE IF EXISTS `GRATUITO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GRATUITO` (
  `subscriptionId` tinyint(4) DEFAULT NULL,
  `maxObjetivo5` varchar(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GRATUITO`
--

LOCK TABLES `GRATUITO` WRITE;
/*!40000 ALTER TABLE `GRATUITO` DISABLE KEYS */;
INSERT INTO `GRATUITO` VALUES (1,'True');
/*!40000 ALTER TABLE `GRATUITO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MEDIANO`
--

DROP TABLE IF EXISTS `MEDIANO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MEDIANO` (
  `subscriptionId` tinyint(4) DEFAULT NULL,
  `maxObjetivo10` varchar(4) DEFAULT NULL,
  `noAnuncios` varchar(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MEDIANO`
--

LOCK TABLES `MEDIANO` WRITE;
/*!40000 ALTER TABLE `MEDIANO` DISABLE KEYS */;
INSERT INTO `MEDIANO` VALUES (2,'True','True');
/*!40000 ALTER TABLE `MEDIANO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OBJETIVO`
--

DROP TABLE IF EXISTS `OBJETIVO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OBJETIVO` (
  `goalId` tinyint(4) DEFAULT NULL,
  `tipo` varchar(39) DEFAULT NULL,
  `scoreId` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OBJETIVO`
--

LOCK TABLES `OBJETIVO` WRITE;
/*!40000 ALTER TABLE `OBJETIVO` DISABLE KEYS */;
INSERT INTO `OBJETIVO` VALUES (1,'Limitar el tiempo en las redes sociales',5),(2,'Practicar estiramientos matutinos',3),(3,'Mantener un diario de gratitud',3),(4,'Beber más agua',2),(5,'Hacer la cama durante 21 días seguidos',7);
/*!40000 ALTER TABLE `OBJETIVO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PLAN_MEMBRESIA`
--

DROP TABLE IF EXISTS `PLAN_MEMBRESIA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PLAN_MEMBRESIA` (
  `subscriptionId` tinyint(4) DEFAULT NULL,
  `tipo` varchar(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PLAN_MEMBRESIA`
--

LOCK TABLES `PLAN_MEMBRESIA` WRITE;
/*!40000 ALTER TABLE `PLAN_MEMBRESIA` DISABLE KEYS */;
INSERT INTO `PLAN_MEMBRESIA` VALUES (1,'GRATUITO'),(2,'MEDIANO'),(3,'COMPLETO');
/*!40000 ALTER TABLE `PLAN_MEMBRESIA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PREMIO`
--

DROP TABLE IF EXISTS `PREMIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PREMIO` (
  `rewardId` tinyint(4) DEFAULT NULL,
  `precio` smallint(6) DEFAULT NULL,
  `cantidad` tinyint(4) DEFAULT NULL,
  `tipo` varchar(29) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PREMIO`
--

LOCK TABLES `PREMIO` WRITE;
/*!40000 ALTER TABLE `PREMIO` DISABLE KEYS */;
INSERT INTO `PREMIO` VALUES (1,112,10,'Tarjetas de regalo'),(2,50,99,'Avatar Premium'),(3,54,21,'10% Descuento Amazon'),(4,206,3,'Earbuds (Cascos Inalámbricos)'),(5,179,17,'Curso de SQL');
/*!40000 ALTER TABLE `PREMIO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PUNTUACIÓN`
--

DROP TABLE IF EXISTS `PUNTUACIÓN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PUNTUACIÓN` (
  `scoreId` tinyint(4) DEFAULT NULL,
  `cantidadPuntos` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PUNTUACIÓN`
--

LOCK TABLES `PUNTUACIÓN` WRITE;
/*!40000 ALTER TABLE `PUNTUACIÓN` DISABLE KEYS */;
INSERT INTO `PUNTUACIÓN` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10);
/*!40000 ALTER TABLE `PUNTUACIÓN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USUARIO`
--

DROP TABLE IF EXISTS `USUARIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USUARIO` (
  `userId` tinyint(4) DEFAULT NULL,
  `nombre` varchar(15) DEFAULT NULL,
  `apellido` varchar(16) DEFAULT NULL,
  `edad` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USUARIO`
--

LOCK TABLES `USUARIO` WRITE;
/*!40000 ALTER TABLE `USUARIO` DISABLE KEYS */;
INSERT INTO `USUARIO` VALUES (1,'Emanuel Esteban','Fernández Trejos',19),(2,'Paula','Sánchez Vélez',20),(3,'Abel','González Guerra',21),(4,'Jesús','Martín de Arcos',22),(5,'Jesús','Recacha Freire',20);
/*!40000 ALTER TABLE `USUARIO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USUARIO_ADQUIERE_PLANMEMBRESIA`
--

DROP TABLE IF EXISTS `USUARIO_ADQUIERE_PLANMEMBRESIA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USUARIO_ADQUIERE_PLANMEMBRESIA` (
  `userId` tinyint(4) DEFAULT NULL,
  `subscriptionId` tinyint(4) DEFAULT NULL,
  `fechaInscripción` varchar(19) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USUARIO_ADQUIERE_PLANMEMBRESIA`
--

LOCK TABLES `USUARIO_ADQUIERE_PLANMEMBRESIA` WRITE;
/*!40000 ALTER TABLE `USUARIO_ADQUIERE_PLANMEMBRESIA` DISABLE KEYS */;
INSERT INTO `USUARIO_ADQUIERE_PLANMEMBRESIA` VALUES (1,3,'2024-05-08 00:00:00'),(2,3,'2024-05-10 00:00:00'),(3,2,'2024-05-12 00:00:00'),(4,1,'2024-05-14 00:00:00'),(5,1,'2024-05-15 00:00:00');
/*!40000 ALTER TABLE `USUARIO_ADQUIERE_PLANMEMBRESIA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USUARIO_ADQUIERE_PREMIO`
--

DROP TABLE IF EXISTS `USUARIO_ADQUIERE_PREMIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USUARIO_ADQUIERE_PREMIO` (
  `userId` tinyint(4) DEFAULT NULL,
  `rewardId` tinyint(4) DEFAULT NULL,
  `fechaAdquisición` varchar(19) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USUARIO_ADQUIERE_PREMIO`
--

LOCK TABLES `USUARIO_ADQUIERE_PREMIO` WRITE;
/*!40000 ALTER TABLE `USUARIO_ADQUIERE_PREMIO` DISABLE KEYS */;
INSERT INTO `USUARIO_ADQUIERE_PREMIO` VALUES (1,1,'2024-05-08 00:00:00'),(1,2,'2024-05-08 00:00:00'),(2,4,'2024-05-12 00:00:00'),(3,5,'2024-05-16 00:00:00'),(4,1,'2024-05-22 00:00:00');
/*!40000 ALTER TABLE `USUARIO_ADQUIERE_PREMIO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USUARIO_COMPLETA_OBJETIVO`
--

DROP TABLE IF EXISTS `USUARIO_COMPLETA_OBJETIVO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USUARIO_COMPLETA_OBJETIVO` (
  `userId` tinyint(4) DEFAULT NULL,
  `goalId` tinyint(4) DEFAULT NULL,
  `progreso` smallint(6) DEFAULT NULL,
  `recordatorio` varchar(51) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USUARIO_COMPLETA_OBJETIVO`
--

LOCK TABLES `USUARIO_COMPLETA_OBJETIVO` WRITE;
/*!40000 ALTER TABLE `USUARIO_COMPLETA_OBJETIVO` DISABLE KEYS */;
INSERT INTO `USUARIO_COMPLETA_OBJETIVO` VALUES (1,1,15,'¡Recuerda, no te sobrepases con las Redes Sociales!'),(2,1,7,'¡Recuerda, no te sobrepases con las Redes Sociales!'),(2,2,90,'¡Cada vez más cerca de la meta!'),(3,3,100,''),(3,4,45,'¡No te olvides del vasito con agua!'),(4,5,50,'¡Ya solo queda la mitad!'),(5,5,10,'¡Ánimo, es un gran comienzo!');
/*!40000 ALTER TABLE `USUARIO_COMPLETA_OBJETIVO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USUARIO_ESTABLECE_OBJETIVO`
--

DROP TABLE IF EXISTS `USUARIO_ESTABLECE_OBJETIVO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USUARIO_ESTABLECE_OBJETIVO` (
  `userId` tinyint(4) DEFAULT NULL,
  `goalId` tinyint(4) DEFAULT NULL,
  `fechaInicio` varchar(19) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USUARIO_ESTABLECE_OBJETIVO`
--

LOCK TABLES `USUARIO_ESTABLECE_OBJETIVO` WRITE;
/*!40000 ALTER TABLE `USUARIO_ESTABLECE_OBJETIVO` DISABLE KEYS */;
INSERT INTO `USUARIO_ESTABLECE_OBJETIVO` VALUES (1,1,'2024-05-08 00:00:00'),(2,1,'2024-05-12 00:00:00'),(2,2,'2024-05-14 00:00:00'),(3,3,'2024-05-17 00:00:00'),(3,4,'2024-05-16 00:00:00'),(4,5,'2024-05-16 00:00:00'),(5,5,'2024-05-16 00:00:00');
/*!40000 ALTER TABLE `USUARIO_ESTABLECE_OBJETIVO` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-30 16:42:45
