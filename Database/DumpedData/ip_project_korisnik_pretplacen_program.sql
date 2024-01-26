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
-- Table structure for table `korisnik_pretplacen_program`
--

DROP TABLE IF EXISTS `korisnik_pretplacen_program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik_pretplacen_program` (
  `korisnik_id` int NOT NULL,
  `program_id` int NOT NULL,
  `nacin_placanja` varchar(255) NOT NULL,
  `vrijednost` int NOT NULL,
  PRIMARY KEY (`korisnik_id`,`program_id`),
  KEY `fk_korisnik_has_program_program1_idx` (`program_id`),
  KEY `fk_korisnik_has_program_korisnik1_idx` (`korisnik_id`),
  CONSTRAINT `fk_korisnik_has_program_korisnik1` FOREIGN KEY (`korisnik_id`) REFERENCES `korisnik` (`id`),
  CONSTRAINT `fk_korisnik_has_program_program1` FOREIGN KEY (`program_id`) REFERENCES `program` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik_pretplacen_program`
--

LOCK TABLES `korisnik_pretplacen_program` WRITE;
/*!40000 ALTER TABLE `korisnik_pretplacen_program` DISABLE KEYS */;
INSERT INTO `korisnik_pretplacen_program` VALUES (1,1,'a',123456),(1,18,'Debit card',55),(1,19,'Paypal',4),(1,20,'Debit card',61),(3,1,'b',5000),(3,3,'Paypal',5),(3,7,'Debit card',100),(38,3,'c',4000),(40,20,'Paypal',70),(52,23,'Debit card',50),(53,23,'Debit card',51);
/*!40000 ALTER TABLE `korisnik_pretplacen_program` ENABLE KEYS */;
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
