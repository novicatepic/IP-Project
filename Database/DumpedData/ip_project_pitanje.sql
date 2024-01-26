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
-- Table structure for table `pitanje`
--

DROP TABLE IF EXISTS `pitanje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pitanje` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tekst` varchar(1000) NOT NULL,
  `program_id` int NOT NULL,
  `korisnik_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pitanje_program1_idx` (`program_id`),
  KEY `FKmenqsk70su0mau22ydvheap3c` (`korisnik_id`),
  CONSTRAINT `fk_pitanje_program1` FOREIGN KEY (`program_id`) REFERENCES `program` (`id`),
  CONSTRAINT `FKmenqsk70su0mau22ydvheap3c` FOREIGN KEY (`korisnik_id`) REFERENCES `korisnik` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pitanje`
--

LOCK TABLES `pitanje` WRITE;
/*!40000 ALTER TABLE `pitanje` DISABLE KEYS */;
INSERT INTO `pitanje` VALUES (1,'pitanje?',1,1),(2,'gcd',1,1),(3,'pitanje?',1,1),(4,'iz springa',1,1),(5,'abcdef',1,1),(6,'abc',1,1),(7,'postman',1,3),(8,':)',1,3),(9,'notification',1,1),(10,'novo pitanje',1,1),(11,'What is this?',9,1),(12,'question123',16,53),(13,'abcc',24,53),(14,'what?',24,53);
/*!40000 ALTER TABLE `pitanje` ENABLE KEYS */;
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
