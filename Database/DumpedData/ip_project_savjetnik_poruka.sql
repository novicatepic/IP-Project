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
-- Table structure for table `savjetnik_poruka`
--

DROP TABLE IF EXISTS `savjetnik_poruka`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `savjetnik_poruka` (
  `tekst` varchar(1000) NOT NULL,
  `procitana` bit(1) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `korisnik_id` int NOT NULL,
  `datum` date NOT NULL,
  `naslov` varchar(100) NOT NULL,
  `odgovorena` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_savjetnik_poruka_korisnik1_idx` (`korisnik_id`),
  CONSTRAINT `fk_savjetnik_poruka_korisnik1` FOREIGN KEY (`korisnik_id`) REFERENCES `korisnik` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `savjetnik_poruka`
--

LOCK TABLES `savjetnik_poruka` WRITE;
/*!40000 ALTER TABLE `savjetnik_poruka` DISABLE KEYS */;
INSERT INTO `savjetnik_poruka` VALUES ('t1 neki tekst 123456',_binary '',2,1,'2023-11-11','naslov1',_binary ''),('iz springa',_binary '',3,1,'2023-12-23','spring',_binary ''),('Sample Program',_binary '',4,1,'2023-12-31','This is a sample program description.',_binary ''),('t111',_binary '',5,1,'2023-12-25','t1',_binary ''),('text new',_binary '',6,1,'2023-12-30','title new',_binary ''),('ggg',_binary '',7,1,'2024-01-11','ggg',_binary ''),('abc',_binary '',8,1,'2024-12-12','bca',_binary ''),('which program to buy?',_binary '',9,1,'2024-01-20','question',_binary ''),('text1',_binary '',10,1,'2024-01-21','title1',_binary '\0'),('me',_binary '',11,52,'2024-01-22','help',_binary '\0'),('help me',_binary '',12,53,'2024-01-26','hi consultant',_binary '');
/*!40000 ALTER TABLE `savjetnik_poruka` ENABLE KEYS */;
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
