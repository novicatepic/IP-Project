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
-- Table structure for table `odgovor`
--

DROP TABLE IF EXISTS `odgovor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `odgovor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `odgovor` varchar(1000) DEFAULT NULL,
  `pitanje_id` int DEFAULT NULL,
  `korisnik_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtr3wu7hx2xtpwt8f2go8rkytk` (`pitanje_id`),
  KEY `FKb763dwvpbfa9lv7kaa7es9k5h` (`korisnik_id`),
  CONSTRAINT `FKb763dwvpbfa9lv7kaa7es9k5h` FOREIGN KEY (`korisnik_id`) REFERENCES `korisnik` (`id`),
  CONSTRAINT `FKtr3wu7hx2xtpwt8f2go8rkytk` FOREIGN KEY (`pitanje_id`) REFERENCES `pitanje` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `odgovor`
--

LOCK TABLES `odgovor` WRITE;
/*!40000 ALTER TABLE `odgovor` DISABLE KEYS */;
INSERT INTO `odgovor` VALUES (2,'odg1',1,1),(3,'postman',1,3),(4,'pitanje 3',1,3),(5,'abc',1,3),(6,'33',7,3),(7,'ooo',1,1),(8,'I realized in the end...',11,1),(9,'answered to myself',2,1),(10,'afasfas',2,1),(11,'asfaf',2,1),(12,'got it',12,53);
/*!40000 ALTER TABLE `odgovor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-26 14:58:23
