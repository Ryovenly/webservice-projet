-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: j2ee_td_spring
-- ------------------------------------------------------
-- Server version	5.7.24

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
-- Table structure for table `charactergame`
--

DROP TABLE IF EXISTS `charactergame`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `charactergame` (
  `pseudo` varchar(255) NOT NULL,
  `agility` bigint(20) DEFAULT NULL,
  `created` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `hp` bigint(20) DEFAULT NULL,
  `hp_max` bigint(20) DEFAULT NULL,
  `hunger` bigint(20) DEFAULT NULL,
  `hunger_max` bigint(20) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `intelligence` bigint(20) DEFAULT NULL,
  `last_login` varchar(255) DEFAULT NULL,
  `luck` bigint(20) DEFAULT NULL,
  `mana` bigint(20) DEFAULT NULL,
  `mana_max` bigint(20) DEFAULT NULL,
  `money` bigint(20) DEFAULT NULL,
  `stamina` bigint(20) DEFAULT NULL,
  `stamina_max` bigint(20) DEFAULT NULL,
  `strength` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pseudo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `charactergame`
--

LOCK TABLES `charactergame` WRITE;
/*!40000 ALTER TABLE `charactergame` DISABLE KEYS */;
INSERT INTO `charactergame` VALUES ('Akane2',5,'2022-07-01T21:44:00.558','MALE',125,125,100,100,'',5,'2022-07-01T21:44:00.560',5,100,100,1000,100,100,5),('Akane3',5,'2022-07-01T21:49:33.609','FEMALE',125,125,100,100,'',5,'2022-07-01T21:49:33.611',5,100,100,1000,100,100,5),('Akane4',20,'2022-07-01T21:52:42.486','MALE',125,125,100,100,'',15,'2022-07-01T21:52:42.488',50,100,140,1000,100,100,5),('Akane5',42,'2022-07-01T22:00:42.062','FEMALE',125,147,100,100,'',32,'2022-07-01T22:00:42.064',27,100,205,1000,100,100,90),('Akane6',10,'2022-07-01T22:04:35.148','MALE',125,167,100,100,'',20,'2022-07-01T22:04:35.151',20,100,160,1000,100,100,185),('sfeaftring',0,'string','string',0,0,0,0,'string',0,'string',0,0,0,0,0,0,0),('strifzfzeg',0,'string','string',0,0,0,0,'string',0,'string',0,0,0,0,0,0,0),('test2',0,'string','string',0,450,0,0,'string',0,'string',0,0,0,0,0,0,0);
/*!40000 ALTER TABLE `charactergame` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `character_game` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `timestamp` datetime(6) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'string','string','2022-06-01 17:03:26.634000','string');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `charactergame_pseudo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `FKpl8jawypikhmb48xgabjj86il` (`charactergame_pseudo`),
  CONSTRAINT `FKpl8jawypikhmb48xgabjj86il` FOREIGN KEY (`charactergame_pseudo`) REFERENCES `charactergame` (`pseudo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1',NULL,'string',NULL),('ADMIN','$2a$10$ifY9/OmF6YAFizhuBrBdKe5FCLmy7ytQmAP7HWL1Ux/6I1NpcrU5u','ADMIN',NULL),('Akane','$2a$10$dRSCeXjceq4MDCm7vS53EObbJhSdjiEluAKGphyfU2Wd8V1zsgNjO',NULL,NULL),('Akanetest2','$2a$10$a7ArXGY36aXJ8/K88GWAR.Fv.K8u1Gp4.14HIfXjhd8ZSL6cl.1s.','USER','Akane2'),('Akanetest3','$2a$10$QYLlGGQSVbU6gJ9GTX0dd.1I/CTJVWArIIJ4blmzFFkz61owb.JE.','USER','Akane4'),('Akanetest4','$2a$10$ZpfQQsUXxMGLCzkJk3SwXePs4iPtQl6gC6mYQol2ZBl4OnOJj0Yoq','USER','Akane5'),('Akanetest6','$2a$10$EWcr.hnCC33HNinCYh0iDuZqwoaGJerzhaTQHx869J6abaxFZToTm','USER','Akane6'),('Akatest','$2a$10$zFeuXytAnED3XXyVTqbf0u88dva7yU3SVPvzBprc1f4VAVRahovOS','USER',NULL),('Kimi',NULL,NULL,NULL),('string','$2a$10$XSP5Czqa1.3jYjslUt5t/O8DLf.dDa4EODH9HLPsD9Qn4XSoHaIXm',NULL,NULL),('string3',NULL,'string',NULL),('string5',NULL,'string',NULL),('Test1',NULL,'string',NULL),('test2',NULL,'string','test2'),('testa','$2a$10$eATIllwTTcWx3M4HcZcXPO2ODuJmnZxUQDe1yHcFnYy/b/gA4X7fi','USER',NULL),('Testae','$2a$10$TirK6z2rLjAPfDkJ4qJbl.fvKC1STsBk3Mt9YCHDUNBJbHytWFipe','USER',NULL),('Testae8','$2a$10$Q/Kq0M33nv8kqun4T3gdIOla1bQmY4MJlRmqKip16r7yERzWjo.hC','USER',NULL),('Testae9','$2a$10$hlypjHNHgvPKSvMhKKj2vuA62LRrFh0Zhv0ggdhrri33y9UIA1xtO','USER',NULL),('USER','$2a$10$FKpUcYDju667Vepusgi03OHltmhOaZdrAwihfVxLE1k0FOjOopnL6','USER',NULL);
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

-- Dump completed on 2022-07-03 17:23:41
