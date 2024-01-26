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
-- Table structure for table `program`
--

DROP TABLE IF EXISTS `program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `program` (
  `id` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(200) NOT NULL,
  `opis` varchar(2000) NOT NULL,
  `cijena` int NOT NULL,
  `tezina` int NOT NULL,
  `trajanje` int NOT NULL,
  `kontakt` varchar(45) NOT NULL,
  `datum` date NOT NULL,
  `ucestvovan` bit(1) NOT NULL,
  `kreator_id` int NOT NULL,
  `kategorija_id` int NOT NULL,
  `naziv_lokacije` varchar(200) NOT NULL DEFAULT 'Link',
  `poruka_lokacije` varchar(200) NOT NULL DEFAULT 'https://www.youtube.com/watch?v=HSW12AruxLI',
  `godine_iskustva` int NOT NULL,
  `datum_kreiranja` date NOT NULL DEFAULT '2023-12-31',
  `instruktor_ime` varchar(45) NOT NULL,
  `instruktor_prezime` varchar(45) NOT NULL,
  `terminiran` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `naziv` (`naziv`),
  KEY `fk_program_korisnik1_idx` (`kreator_id`),
  KEY `FK2qttj9ef16i8uuabnwfe3jfr` (`kategorija_id`),
  CONSTRAINT `FK2qttj9ef16i8uuabnwfe3jfr` FOREIGN KEY (`kategorija_id`) REFERENCES `kategorija` (`id`),
  CONSTRAINT `fk_program_korisnik1` FOREIGN KEY (`kreator_id`) REFERENCES `korisnik` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `program`
--

LOCK TABLES `program` WRITE;
/*!40000 ALTER TABLE `program` DISABLE KEYS */;
INSERT INTO `program` VALUES (1,'n1','o1',100,0,50,'k11','2024-11-11',_binary '\0',1,1,'Link','https://www.youtube.com/watch?v=HSW12AruxLI',1,'2023-12-10','a','a',1),(3,'n3','o3',100,0,50,'k33','2023-11-11',_binary '',1,1,'Link','https://www.youtube.com/watch?v=HSW12AruxLI',1,'2023-12-31','a','a',0),(7,'n4','o4',120,1,65,'k4','2023-11-11',_binary '',1,1,'Link','https://www.youtube.com/watch?v=HSW12AruxLI',1,'2023-12-31','a','a',1),(8,'n5','o5',120,0,50,'k5','2023-11-12',_binary '\0',1,1,'Link','https://www.youtube.com/watch?v=HSW12AruxLI',2,'2023-12-31','a','a',1),(9,'n6','o6',120,2,100,'k6','2023-12-29',_binary '\0',1,1,'Link','https://www.youtube.com/watch?v=HSW12AruxLI',2,'2023-12-31','a','a',1),(11,'novica2','novica2',100,2,70,'c1','2023-12-29',_binary '\0',41,1,'Link','https://www.youtube.com/watch?v=HSW12AruxLI',2,'2023-12-31','a','a',0),(14,'ABCCC','ABCCC',100,2,70,'123456','2024-01-06',_binary '\0',41,1,'Link','https://www.youtube.com/watch?v=HSW12AruxLI',2,'2023-12-31','a','a',0),(15,'asgsag','agsagsa',220,2,70,'123456','2024-01-06',_binary '\0',1,5,'Live','Kod Krivog',1,'2023-12-31','a','a',0),(16,'postmannn','opisss',60,1,60,'Kontakt','2024-11-11',_binary '\0',1,4,'Live','See you!',1,'2023-12-31','a','a',0),(17,'fitnes program 7','fitnes program 7',120,2,70,'123456','2024-01-06',_binary '\0',1,5,'Live','Mssg',1,'2023-12-31','a','a',0),(18,'a','afsa',50,2,70,'novica contact','2024-01-05',_binary '',1,5,'Live','Park',5,'2023-12-31','Instruktor ime','Prezime',1),(19,'fff','fff',3,2,50,'fff','2024-01-21',_binary '',1,2,'Live','hop[',2,'2024-01-11','Instruktor ime','Prezime',1),(20,'fpi123','fpi123',60,2,60,'fpi123','2024-01-28',_binary '',1,2,'Live','It\'s live',3,'2024-01-20','abc','bca',0),(21,'fitness program111','description 111',100,2,50,'novica contact','2024-01-31',_binary '\0',1,1,'Live','abc',5,'2024-01-21','abc','bca',1),(22,'asfasf','fasf',50,2,50,'fpi123','2024-01-22',_binary '\0',1,1,'Live','asfasf',4,'2024-01-21','fas','fsa',1),(23,'mojfitnesprogram','mojfitnesprogram',50,2,60,'mojfitnesprogram','2024-01-31',_binary '',52,1,'Live','hello',5,'2024-01-22','mojfitnesprogram','mojfitnesprogram',0),(24,'novica123','novica123',80,2,70,'novica123','2024-02-11',_binary '\0',53,1,'Live','3 times per week push ups',5,'2024-01-26','novica123','novica123',0);
/*!40000 ALTER TABLE `program` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-26 14:58:22
