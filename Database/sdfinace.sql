-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: sdfinance
-- ------------------------------------------------------
-- Server version	5.5.62

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `fname` varchar(91) NOT NULL,
  `lname` varchar(91) NOT NULL,
  `email` varchar(91) NOT NULL,
  `own` varchar(137) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `covs`
--

DROP TABLE IF EXISTS `covs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `covs` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(317) NOT NULL,
  `date_time` varchar(317) NOT NULL,
  `own` varchar(318) NOT NULL,
  `recepiet` varchar(318) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `covs`
--

LOCK TABLES `covs` WRITE;
/*!40000 ALTER TABLE `covs` DISABLE KEYS */;
INSERT INTO `covs` VALUES (1,'Just To Say Hi!','Sun Sep 13 02:35:30  |  2020','silas.seal7@gmail.com','muwerezaisaac@gmail.com'),(2,'Bowling Game Project','Wed Sep 16 04:20:20  |  2020','muwerezaisaac@gmail.com','muwerezaisaac@gmail.com');
/*!40000 ALTER TABLE `covs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctcus`
--

DROP TABLE IF EXISTS `ctcus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ctcus` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(317) NOT NULL,
  `content` varchar(618) NOT NULL,
  `date_time` varchar(317) NOT NULL,
  `own` varchar(318) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctcus`
--

LOCK TABLES `ctcus` WRITE;
/*!40000 ALTER TABLE `ctcus` DISABLE KEYS */;
INSERT INTO `ctcus` VALUES (1,'PAYMENT PROCEDURE','Hello, am writing to consult about payments. I would love add funding agent to my account. Is that possible here as it is possible @ payoneer?\r\n\r\nThanks in advance','Tue Sep 15 08:08:54  |  2020','muwanguzisilas@gmail.com');
/*!40000 ALTER TABLE `ctcus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gmembers`
--

DROP TABLE IF EXISTS `gmembers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gmembers` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `gname` varchar(317) NOT NULL,
  `gowner` varchar(137) NOT NULL,
  `guser` varchar(137) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gmembers`
--

LOCK TABLES `gmembers` WRITE;
/*!40000 ALTER TABLE `gmembers` DISABLE KEYS */;
/*!40000 ALTER TABLE `gmembers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(317) NOT NULL,
  `owner` varchar(137) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoices`
--

DROP TABLE IF EXISTS `invoices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoices` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(91) NOT NULL,
  `lname` varchar(91) NOT NULL,
  `email` varchar(91) NOT NULL,
  `date_time` varchar(309) NOT NULL,
  `amount` int(11) NOT NULL,
  `status` varchar(71) NOT NULL,
  `own` varchar(137) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoices`
--

LOCK TABLES `invoices` WRITE;
/*!40000 ALTER TABLE `invoices` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `ID` varchar(317) NOT NULL,
  `message` varchar(618) NOT NULL,
  `date_time` varchar(317) NOT NULL,
  `own` varchar(318) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES ('1','Come now','Sun Sep 13 03:20:49  |  2020','silas.seal7@gmail.com'),('1','Okay Bro','Sun Sep 13 03:22:17  |  2020','silas.seal7@gmail.com'),('1','Am on my way bro. be there in a minute\r<p/>Isaac','Sun Sep 13 03:24:00  |  2020','muwerezaisaac@gmail.com'),('1','Am on my way bro. be there in a minute\r<p/>Isaac','Sun Sep 13 03:25:43  |  2020','muwerezaisaac@gmail.com'),('1','Alright man. Waiting!','Sun Sep 13 04:46:29  |  2020','silas.seal7@gmail.com'),('1','Yop man, the app worked correctly on primary cases. Let you know on secondary & turtairy tests - silas.seal7@gmail.com','Wed Sep 16 04:10:10  |  2020','silas.seal7@gmail.com'),('1','Yop man, the app worked correctly on primary cases. Let you know on secondary & turtairy tests -<hr/> silas.seal7@gmail.com','Wed Sep 16 04:11:39  |  2020','silas.seal7@gmail.com'),('1','Yop man, the app worked correctly on primary cases. Let you know on secondary & turtairy tests -<hr/> silas.seal7@gmail.com','Wed Sep 16 04:12:02  |  2020','silas.seal7@gmail.com'),('1','Ok man, I can see it\'s now working fine. No more corresponding threads.  -<hr/> muwerezaisaac@gmail.com','Wed Sep 16 04:15:43  |  2020','muwerezaisaac@gmail.com'),('2','Hello bro. \r<p/>\r<p/>The bowling project in JavaFX has been successfully completed. All properties added. 100% accurate results.\r<p/>\r<p/>Silas','Wed Sep 16 04:20:20  |  2020','muwerezaisaac@gmail.com'),('2','Am I talking to my self!? -<hr/> muwerezaisaac@gmail.com','Wed Sep 16 04:26:51  |  2020','muwerezaisaac@gmail.com');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(517) NOT NULL,
  `content` varchar(618) NOT NULL,
  `own` varchar(317) NOT NULL,
  `date_time` varchar(318) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,'Welcome','Hello Muwanguzi, Welcome to \nour community. This is to inform you that your account hass been approved and activated. You can now start transacting','silas.seal7@gmail.com','15th, SEPT. 2020 | 13:64'),(2,'Good to See You','Hello Muwanguzi, Welcome to \nour community. This is to inform you that your account hass been approved and activated. You can now start transacting','silas.seal7@gmail.com','15th, SEPT. 2020 | 13:64');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(70) NOT NULL,
  `lname` varchar(70) NOT NULL,
  `email` varchar(70) NOT NULL,
  `phone` varchar(70) NOT NULL,
  `gender` varchar(70) NOT NULL,
  `country` varchar(90) NOT NULL,
  `dob` varchar(70) NOT NULL,
  `address` varchar(70) NOT NULL,
  `pass` varchar(318) NOT NULL,
  `main` int(11) NOT NULL,
  `boss` int(11) NOT NULL,
  `lancer` int(11) NOT NULL,
  `user_type` varchar(80) NOT NULL,
  `status` varchar(137) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10000002 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (10000000,'Muwanguzi','Silas','silas.seal7@gmail.com','+256704049977','male','country','dob','Namwendwa,kamuli,Uganda,Africa','silmatic001',784,10,73,'client','approved'),(10000001,'Muwereza','Isaac','muwerezaisaac@gmail.com','0702043057','male','Uganda','2000-01-14','Namwendwa, Kamuli, Uganda','izoex',275,0,0,'client','pending');
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requests`
--

DROP TABLE IF EXISTS `requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requests` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(91) NOT NULL,
  `date_time` varchar(309) NOT NULL,
  `amount` int(11) NOT NULL,
  `status` varchar(71) NOT NULL,
  `own` varchar(137) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requests`
--

LOCK TABLES `requests` WRITE;
/*!40000 ALTER TABLE `requests` DISABLE KEYS */;
INSERT INTO `requests` VALUES (1,'muwerezaisaac@gmail.com','Fri Sep 04 09:25:44 MST 2020',71,'ignore','silas.seal7@gmail.com'),(2,'silas.seal7@gmail.com','Tue Sep 08 23:05:45 MST 2020',31,'ignore','muwerezaisaac@gmail.com'),(3,'muwerezaisaac@gmail.com','Sat Sep 12 00:56:45 MST 2020',45,'ignore','silas.seal7@gmail.com'),(4,'muwerezaisaac@gmail.com','Sat Sep 12 01:13:17 MST 2020',27,'ignore','silas.seal7@gmail.com'),(5,'muwerezaisaac@gmail.com','Sat Sep 12 01:18:08 MST 2020',27,'ignore','silas.seal7@gmail.com'),(6,'silas.seal7@gmail.com','Sat Sep 12 01:25:48 MST 2020',43,'ignore','muwerezaisaac@gmail.com'),(7,'silas.seal7@gmail.com','Sat Sep 12 01:26:33 MST 2020',54,'ignore','muwerezaisaac@gmail.com'),(8,'silas.seal7@gmail.com','Sat Sep 12 01:28:38 MST 2020',60,'ignore','muwerezaisaac@gmail.com'),(9,'muwerezaisaac@gmail.com','Wed Sep 16 04:05:42  |  2020',81,'waiting','silas.seal7@gmail.com');
/*!40000 ALTER TABLE `requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactions` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(137) NOT NULL,
  `trn_type` varchar(91) NOT NULL,
  `contact` varchar(317) NOT NULL,
  `date_time` varchar(91) NOT NULL,
  `amount` int(11) NOT NULL,
  `balance` int(11) NOT NULL,
  `status` varchar(137) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10000028 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (10000000,'silas.seal7@gmail.com','sending','syrus.sealline@gmail.com','25 August, 2020 2:11PM',21,78,'completed'),(10000001,'silas.seal7@gmail.com','Payment','muwereza.isaac@gmail.com','29 August, 2020 7:47 PM',31,50,'completed'),(10000002,'silas.seal7@gmail.com','Withdrawing','Stanbic Bank','18 August, 2020 5:21 PM',81,31,'completed'),(10000003,'silas.seal7@gmail.com','sending','muwerezaisaac@gmail.com','Thu Sep 03 08:12:43 MST 2020',34,1266,'completed'),(10000004,'muwerezaisaac@gmail.com','receiving','silas.seal7@gmail.com','Thu Sep 03 08:12:43 MST 2020',34,34,'completed'),(10000005,'silas.seal7@gmail.com','sending','null','Thu Sep 03 08:54:04 MST 2020',71,1195,'completed'),(10000006,'null','receiving','silas.seal7@gmail.com','Thu Sep 03 08:54:04 MST 2020',71,0,'completed'),(10000007,'silas.seal7@gmail.com','sending','muwerezaisaac@gmail.com','Thu Sep 03 08:54:55 MST 2020',113,1082,'completed'),(10000008,'muwerezaisaac@gmail.com','receiving','silas.seal7@gmail.com','Thu Sep 03 08:54:55 MST 2020',113,147,'completed'),(10000009,'silas.seal7@gmail.com','sending','muwerezaisaac@gmail.com','Thu Sep 03 09:10:08 MST 2020',43,1039,'completed'),(10000010,'muwerezaisaac@gmail.com','receiving','silas.seal7@gmail.com','Thu Sep 03 09:10:08 MST 2020',43,190,'completed'),(10000011,'silas.seal7@gmail.com','sending','muwerezaisaac@gmail.com','Fri Sep 04 00:39:20 MST 2020',27,1012,'completed'),(10000012,'muwerezaisaac@gmail.com','receiving','silas.seal7@gmail.com','Fri Sep 04 00:39:20 MST 2020',27,217,'completed'),(10000013,'silas.seal7@gmail.com','sending','muwerezaisaac@gmail.com','Fri Sep 04 00:39:39 MST 2020',31,981,'completed'),(10000014,'muwerezaisaac@gmail.com','receiving','silas.seal7@gmail.com','Fri Sep 04 00:39:39 MST 2020',31,248,'completed'),(10000015,'silas.seal7@gmail.com','sending','muwerezaisaac@gmail.com','Fri Sep 04 08:11:56 MST 2020',71,910,'completed'),(10000016,'muwerezaisaac@gmail.com','receiving','silas.seal7@gmail.com','Fri Sep 04 08:11:56 MST 2020',71,319,'completed'),(10000017,'muwerezaisaac@gmail.com','sending','silas.seal7@gmail.com','Tue Sep 08 23:06:54 MST 2020',73,246,'completed'),(10000018,'silas.seal7@gmail.com','receiving','muwerezaisaac@gmail.com','Tue Sep 08 23:06:54 MST 2020',73,983,'completed'),(10000019,'silas.seal7@gmail.com','withdraw','Bank Of Africa','Wed Sep 09 05:24:18 MST 2020',54,929,'pending'),(10000020,'silas.seal7@gmail.com','withdraw','Bank Of Africa','Wed Sep 09 05:28:47 MST 2020',62,867,'pending'),(10000021,'silas.seal7@gmail.com','Send Payment','muwerezaisaac@gmail.com','Sat Sep 12 00:51:42 MST 2020',31,836,'completed'),(10000022,'muwerezaisaac@gmail.com','Receive Payment','silas.seal7@gmail.com','Sat Sep 12 00:51:42 MST 2020',31,277,'completed'),(10000023,'muwerezaisaac@gmail.com','Send Payment','silas.seal7@gmail.com','Sat Sep 12 01:32:30 MST 2020',45,232,'completed'),(10000024,'silas.seal7@gmail.com','Receive Payment','muwerezaisaac@gmail.com','Sat Sep 12 01:32:30 MST 2020',45,881,'completed'),(10000025,'silas.seal7@gmail.com','Send Payment','muwerezaisaac@gmail.com','Sat Sep 12 01:46:49 MST 2020',43,838,'completed'),(10000026,'muwerezaisaac@gmail.com','Receive Payment','silas.seal7@gmail.com','Sat Sep 12 01:46:49 MST 2020',43,275,'completed'),(10000027,'silas.seal7@gmail.com','withdraw','Bank Of Africa','Tue Sep 15 08:10:22  |  2020',54,784,'pending');
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `withdraw`
--

DROP TABLE IF EXISTS `withdraw`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `withdraw` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(317) NOT NULL,
  `account_typr` varchar(137) NOT NULL,
  `service` varchar(318) NOT NULL,
  `country` varchar(318) NOT NULL,
  `account` varchar(319) NOT NULL,
  `BIC` varchar(318) NOT NULL,
  `status` varchar(318) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1000003 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `withdraw`
--

LOCK TABLES `withdraw` WRITE;
/*!40000 ALTER TABLE `withdraw` DISABLE KEYS */;
INSERT INTO `withdraw` VALUES (1000000,'silas.seal7@gmail.com','bank','Bank Of Africa','Uganda','90057543219318','AFRIUGKA','approved'),(1000002,'silas.seal7@gmail.com','bank','Standard Bank','Uganda','9078129405843','STDDUGKAU','pending');
/*!40000 ALTER TABLE `withdraw` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work`
--

DROP TABLE IF EXISTS `work`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `work` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `boss` varchar(317) NOT NULL,
  `bmail` varchar(317) NOT NULL,
  `work_title` varchar(701) NOT NULL,
  `work_description` varchar(43172) NOT NULL,
  `status` varchar(91) NOT NULL,
  `fl_name` varchar(317) NOT NULL,
  `fl_mail` varchar(137) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work`
--

LOCK TABLES `work` WRITE;
/*!40000 ALTER TABLE `work` DISABLE KEYS */;
/*!40000 ALTER TABLE `work` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workspace`
--

DROP TABLE IF EXISTS `workspace`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workspace` (
  `work_id` int(11) NOT NULL,
  `message` varchar(51500) NOT NULL,
  `date_time` varchar(137) NOT NULL,
  `sender` varchar(317) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workspace`
--

LOCK TABLES `workspace` WRITE;
/*!40000 ALTER TABLE `workspace` DISABLE KEYS */;
/*!40000 ALTER TABLE `workspace` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-17  8:13:29
