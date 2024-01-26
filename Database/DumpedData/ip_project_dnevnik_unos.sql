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
-- Table structure for table `dnevnik_unos`
--

DROP TABLE IF EXISTS `dnevnik_unos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dnevnik_unos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `vjezba` varchar(45) NOT NULL,
  `trajanje` varchar(45) NOT NULL,
  `intenzitet` varchar(45) NOT NULL,
  `dnevnik_korisnik_id` int NOT NULL,
  `kilaza` int NOT NULL,
  `datum` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4jolcuy5s5uhgjwmp0qdjcl21` (`dnevnik_korisnik_id`),
  CONSTRAINT `FK4jolcuy5s5uhgjwmp0qdjcl21` FOREIGN KEY (`dnevnik_korisnik_id`) REFERENCES `korisnik` (`id`),
  CONSTRAINT `fk_dnevnik_korisnik_id` FOREIGN KEY (`dnevnik_korisnik_id`) REFERENCES `dnevnik` (`korisnik_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dnevnik_unos`
--

LOCK TABLES `dnevnik_unos` WRITE;
/*!40000 ALTER TABLE `dnevnik_unos` DISABLE KEYS */;
INSERT INTO `dnevnik_unos` VALUES (3,'vjezba','54 minuta','srednji',1,75,'2023-12-12'),(4,'vjezba2','2 sata','srednji',1,73,'2023-12-15'),(5,'vjezba3','30 minuta','srednji',1,50,'2023-12-20'),(9,'exercise6','120','hard',1,40,'2023-12-26'),(12,'najnovija','70','hard',1,40,'2024-01-05'),(16,'q','q','q',1,45,'2023-12-23'),(20,'asfasf','fassaf','Medium',1,45,'2024-02-11'),(23,'exercise 1','60','Medium',52,80,'2024-01-18'),(24,'exercise6','120','High',52,70,'2024-01-26'),(25,'asfsa','50','Medium',1,44,'2024-01-22'),(26,'entry1','60 minuta','Medium',53,80,'2024-01-23');
/*!40000 ALTER TABLE `dnevnik_unos` ENABLE KEYS */;
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
