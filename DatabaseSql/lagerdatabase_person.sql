-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: lagerdatabase
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
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `person_id` int NOT NULL AUTO_INCREMENT,
  `licence_passport_nr` varchar(255) DEFAULT NULL,
  `account_status` varchar(255) NOT NULL DEFAULT 'pending',
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `picture_url` varchar(5000) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `approved_by` int DEFAULT NULL,
  PRIMARY KEY (`person_id`),
  UNIQUE KEY `licence_passport_nr` (`licence_passport_nr`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (26,'123','Approved','Frederik','Brøsen','/images/img_cf7095c6.png','haslev',NULL),(28,'99999','Approved','Peter','Brøsen','/images/img_2fd1435e.png','BO',NULL),(29,'123123123434435345','Declined','Frederik','Brøsen','/images/img_6282beda.png','BO',NULL),(30,'2222222212312312','Approved','Frederik','Brøsen','/images/img_aa5fd1c2.png','BO',NULL),(31,'214123123123123124','Declined','Frederik','Brøsen','/images/img_ccc1f311.png','BO',NULL),(32,'444444434324','Approved','Frederik','Brøsen','/images/img_ccf4635c.png','BO',NULL),(33,'00000000','Approved','Frederik','Brøsen','/images/img_a8f73bfa.png','BO',NULL),(34,'00000000000','Approved','Frederik','Brøsen','/images/img_1b1a23ad.png','BO',26),(35,'000909090','Approved','Frederik','Brøsen','/images/img_f5af7a60.png','BO',26),(36,'123938123812','Approved','Frederik','Brøsen','/images/img_48ffc74e.png',NULL,26),(37,'123123123123','Approved','Frederik','Brøsen','/images/img_00e0d961.png','haslev',26),(38,'90900909090','Approved','Frederik','Brøsen','/images/img_5514e530.png','BO',26),(39,'123213123123','Declined','Frederik','Brøsen','/images/img_7910138f.png','BO',26),(41,'900000900990909090','Declined','Frederik','Brøsen','/images/img_0ddb8c76.png','BO',26),(42,'123435345346456','pending','Frederik','Brøsen','/images/img_003205c9.png','BO',NULL),(43,'213','pending','123123','213','/images/img_6870bd42.png','BO',NULL),(44,'2131231231','pending','213','213','/images/img_e8e0f8bc.png','BO',NULL),(45,'123123123','pending','jjj','jhhjh','/images/img_1bf1406a.png','BO',NULL),(46,'123135345645','pending','hej','hej','/images/img_bdff5594.png','BO',NULL);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-07 14:00:50
