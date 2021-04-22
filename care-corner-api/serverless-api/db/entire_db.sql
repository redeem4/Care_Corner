-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: care_corner
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `audio`
--

DROP TABLE IF EXISTS `audio`;
CREATE TABLE `audio` (
  `audio_id` varchar(15) NOT NULL,
  `audio_path` varchar(100) NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`audio_id`),
  UNIQUE KEY `audio_id_UNIQUE` (`audio_id`),
  UNIQUE KEY `audio_path_UNIQUE` (`audio_path`)
);

--
-- Dumping data for table `audio`
--

LOCK TABLES `audio` WRITE;
/*!40000 ALTER TABLE `audio` DISABLE KEYS */;
INSERT INTO `audio` VALUES ('a1','audio_path_1','2020-10-18 23:05:00'),('a2','audio_path_2','2020-09-29 13:42:00'),('a3','audio_path_3','2021-01-05 22:15:00');
/*!40000 ALTER TABLE `audio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `contact_id` varchar(15) NOT NULL,
  `user_id` varchar(15) NOT NULL,
  `name` varchar(45) NOT NULL,
  `phone` varchar(10) NOT NULL,
  PRIMARY KEY (`contact_id`),
  UNIQUE KEY `contact_id_UNIQUE` (`contact_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `contact_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES ('c0','111','Zues','7575550000'),('c1','222','Yandy','7575550001'),('c10','222','Peter','7575550010'),('c11','999','Oscar','7575550011'),('c12','777','Nancy','7575550012'),('c13','444','Marge','7575550013'),('c2','444','Xavier','7575550002'),('c3','555','Wilson','7575550003'),('c4','888','Vickie','7575550004'),('c5','999','Uriel','7575550005'),('c6','777','Thomas','7575550006'),('c7','111','Sam','7575550007'),('c8','333','Roger','7575550008'),('c9','666','Quintin','7575550009'),('c14','111','Brandon','7575550009');
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incident`
--

DROP TABLE IF EXISTS `incident`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incident` (
  `incident_id` varchar(15) NOT NULL,
  `user_id` varchar(15) NOT NULL,
  `audio_id` varchar(15) DEFAULT NULL,
  `journey_id` varchar(15) DEFAULT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`incident_id`),
  UNIQUE KEY `incident_id_UNIQUE` (`incident_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  KEY `user_id` (`user_id`) /*!80000 INVISIBLE */,
  KEY `audio_id` (`audio_id`) /*!80000 INVISIBLE */,
  KEY `journey_id` (`journey_id`),
  CONSTRAINT `incident_audio_id_fk` FOREIGN KEY (`audio_id`) REFERENCES `audio` (`audio_id`),
  CONSTRAINT `incident_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incident`
--

LOCK TABLES `incident` WRITE;
/*!40000 ALTER TABLE `incident` DISABLE KEYS */;
INSERT INTO `incident` VALUES ('i1','333','a2','j3','2020-10-18 23:05:00'),('i2','666','a3','j1','2020-09-29 13:42:00'),('i3','999','a1','j2','2021-01-05 22:15:00');
/*!40000 ALTER TABLE `incident` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journey`
--

DROP TABLE IF EXISTS `journey`;
CREATE TABLE `journey` (
  `user_id` varchar(15) NOT NULL,
  `journey_id` int NOT NULL AUTO_INCREMENT,
  `journey_path` varchar(100) NOT NULL,
  `time` varchar(255) NOT NULL,
  `start_latitude` decimal(8,5) DEFAULT NULL,
  `start_longitude` decimal(8,5) DEFAULT NULL,
  `end_latitude` decimal(8,5) DEFAULT NULL,
  `end_longitude` decimal(8,5) DEFAULT NULL,
  PRIMARY KEY (`journey_id`)
);

--
-- Dumping data for table `journey`
--

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `resource_id` varchar(15) NOT NULL,
  `name` varchar(45) NOT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `street_num` varchar(6) DEFAULT NULL,
  `street_name` varchar(45) DEFAULT NULL,
  `street_type` varchar(5) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  `zip` varchar(5) DEFAULT NULL,
  `longitude` decimal(8,5) DEFAULT NULL,
  `latitude` decimal(8,5) DEFAULT NULL,
  `is_local` varbinary(10) DEFAULT NULL,
  `is_shelter` binary(1) DEFAULT NULL,
  `can_help_report` binary(1) DEFAULT NULL,
  PRIMARY KEY (`resource_id`),
  UNIQUE KEY `resource_id_UNIQUE` (`resource_id`)
);

--
-- Dumping data for table `resource`
--

LOCK TABLES `resource` WRITE;
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
INSERT INTO `resource` VALUES ('r00','Caseys Womens Shelter','7575551234','caseys@shelter.com','314','Londonshire','Ter','Hampton','VA','23666',37.05124,-76.42210,NULL,NULL,NULL),('r01','Grissoms Giving Center','7575557845','grissom@giving.com','1421','Kristina','way','Chesapeake','VA','23320',36.77465,-76.23757,NULL,NULL,NULL),('r02','Ernest Nest','7575557943','dale@mynest.com','3313','Croft','St','Norfolk','VA','23513',36.88459,-76.22044,NULL,NULL,NULL);
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_for`
--

DROP TABLE IF EXISTS `resource_for`;
CREATE TABLE `resource_for` (
  `user_id` varchar(15) NOT NULL,
  `resource_id` varchar(15) NOT NULL,
  KEY `user_id` (`user_id`) /*!80000 INVISIBLE */,
  KEY `resource_id` (`resource_id`),
  CONSTRAINT `r_for_resource_id_fk` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`resource_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `r_for_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

--
-- Dumping data for table `resource_for`
--

LOCK TABLES `resource_for` WRITE;
/*!40000 ALTER TABLE `resource_for` DISABLE KEYS */;
/*!40000 ALTER TABLE `resource_for` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `school`
--

DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `id` varchar(15) NOT NULL,
  `name` varchar(60) NOT NULL,
  `police_phone_1` varchar(10) DEFAULT NULL,
  `police_phone_2` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
);

--
-- Dumping data for table `school`
--

LOCK TABLES `school` WRITE;
/*!40000 ALTER TABLE `school` DISABLE KEYS */;
INSERT INTO `school` VALUES ('101','Old Dominion University','7575551111',NULL),('202','Norfolk State University','7575552222',NULL),('303','Hampton University','7575553333',NULL);
/*!40000 ALTER TABLE `school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(15) NOT NULL,
  `username` varchar(45) NOT NULL,
  `fname` varchar(45) NOT NULL,
  `email` varchar(60) NOT NULL,
  `password` varchar(20) NOT NULL,
  `school_id` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `school_id` (`school_id`),
  CONSTRAINT `user_school_id_fk` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('111','alpha','amandaa','amandaa@gmail.com','passworda','101'),('222','bravo','barbara','barbarab@yahoo.com','passwordb','202'),('333','charlie','charles','charlesc@ymail.com','passwordc','303'),('444','delta','dawn','dawnd@hotmail.com','passwordd',NULL),('555','echo','erika','erikae@verizon.net','passworde','101'),('666','foxtrot','felicia','feliciaf@icloud.com','passwordf','202'),('777','golf','grant','grantg@odu.edu','passwordg','303'),('888','hotel','harry','harryh@msn.com','passwordh',NULL),('999','india','ingrid','ingridi@live.com','passwordi','101');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-21 16:10:59
