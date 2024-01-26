CREATE DATABASE  IF NOT EXISTS `ip_project` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ip_project`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: ip_project
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `savjetnik`
--

DROP TABLE IF EXISTS `savjetnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `savjetnik` (
  `id` int NOT NULL AUTO_INCREMENT,
  `korisnicko_ime` varchar(45) NOT NULL,
  `lozinka` varchar(1000) DEFAULT NULL,
  `ime` varchar(45) NOT NULL,
  `prezime` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `korisnicko_ime` (`korisnicko_ime`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `savjetnik`
--

LOCK TABLES `savjetnik` WRITE;
/*!40000 ALTER TABLE `savjetnik` DISABLE KEYS */;
INSERT INTO `savjetnik` VALUES (2,'s2','ad328846aa18b32a335816374511cac1063c704b8c57999e51da9f908290a7a4','s2','s2'),(3,'s3','41242b9fae56fad4e6e77dfe33cb18d1c3fc583f988cf25ef9f2d9be0d440bbb','s3','s3'),(4,'s4','5b840157e7e86aef3b3fd0fc24f3add34d3e7f210370d429475ed1bcd3e7fca2','s4','s4'),(5,'s5','3b96fc064fa874a80a132bda60bebf54efbc780a358fdcae4fbbd7e12b66b630','s5','s5'),(9,'s6','71e7690959239ca065841eba3ebb281072baa78ba0bb31079b9acb4a009a9fe3','s6','s6');
/*!40000 ALTER TABLE `savjetnik` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-26 14:58:24
