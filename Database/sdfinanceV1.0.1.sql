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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `covs`
--

LOCK TABLES `covs` WRITE;
/*!40000 ALTER TABLE `covs` DISABLE KEYS */;
INSERT INTO `covs` VALUES (1,'Just To Say Hi!','Sun Sep 13 02:35:30  |  2020','silas.seal7@gmail.com','muwerezaisaac@gmail.com'),(2,'Bowling Game Project','Wed Sep 16 04:20:20  |  2020','muwerezaisaac@gmail.com','muwerezaisaac@gmail.com'),(3,'Fresh Start','Tue Sep 22 12:36:09  |  2020','silas.seal7@gmail.com','muwerezaisaac@gmail.com'),(5,'Approved','Thu Sep 24 14:56:27  |  2020','peterson@seumxplus.com','silas.seal7@gmail.com'),(6,'Progress','Thu Sep 24 15:14:29  |  2020','peterson@seumxplus.com','silas.seal7@gmail.com');
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
-- Table structure for table `marapps`
--

DROP TABLE IF EXISTS `marapps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marapps` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(418) NOT NULL,
  `Owner` varchar(418) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marapps`
--

LOCK TABLES `marapps` WRITE;
/*!40000 ALTER TABLE `marapps` DISABLE KEYS */;
/*!40000 ALTER TABLE `marapps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marchant`
--

DROP TABLE IF EXISTS `marchant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marchant` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(318) NOT NULL,
  `Mname` varchar(318) NOT NULL,
  `Company` varchar(318) NOT NULL,
  `Status` varchar(317) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1000003 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marchant`
--

LOCK TABLES `marchant` WRITE;
/*!40000 ALTER TABLE `marchant` DISABLE KEYS */;
INSERT INTO `marchant` VALUES (1000000,'silas.seal7@gmail.com','Reports','Seumx Plus','active'),(1000001,'silas.seal7@gmail.com','Mysql','Oracle','demo'),(1000002,'silas.seal7@gmail.com','Talent','Andela','demo');
/*!40000 ALTER TABLE `marchant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `ID` varchar(317) NOT NULL,
  `message` longtext NOT NULL,
  `date_time` varchar(317) NOT NULL,
  `own` varchar(318) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES ('1','Come now','Sun Sep 13 03:20:49  |  2020','silas.seal7@gmail.com'),('1','Okay Bro','Sun Sep 13 03:22:17  |  2020','silas.seal7@gmail.com'),('1','Am on my way bro. be there in a minute\r<p/>Isaac','Sun Sep 13 03:24:00  |  2020','muwerezaisaac@gmail.com'),('1','Am on my way bro. be there in a minute\r<p/>Isaac','Sun Sep 13 03:25:43  |  2020','muwerezaisaac@gmail.com'),('1','Alright man. Waiting!','Sun Sep 13 04:46:29  |  2020','silas.seal7@gmail.com'),('1','Yop man, the app worked correctly on primary cases. Let you know on secondary & turtairy tests - silas.seal7@gmail.com','Wed Sep 16 04:10:10  |  2020','silas.seal7@gmail.com'),('1','Yop man, the app worked correctly on primary cases. Let you know on secondary & turtairy tests -<hr/> silas.seal7@gmail.com','Wed Sep 16 04:11:39  |  2020','silas.seal7@gmail.com'),('1','Yop man, the app worked correctly on primary cases. Let you know on secondary & turtairy tests -<hr/> silas.seal7@gmail.com','Wed Sep 16 04:12:02  |  2020','silas.seal7@gmail.com'),('1','Ok man, I can see it\'s now working fine. No more corresponding threads.  -<hr/> muwerezaisaac@gmail.com','Wed Sep 16 04:15:43  |  2020','muwerezaisaac@gmail.com'),('2','Hello bro. \r<p/>\r<p/>The bowling project in JavaFX has been successfully completed. All properties added. 100% accurate results.\r<p/>\r<p/>Silas','Wed Sep 16 04:20:20  |  2020','muwerezaisaac@gmail.com'),('2','Am I talking to my self!? -<hr/> muwerezaisaac@gmail.com','Wed Sep 16 04:26:51  |  2020','muwerezaisaac@gmail.com'),('1','Yeah bro. I can\'t find any more bugs.\r<p/>\r<p/>Please continue to test and give feedback. Thanks for your time\r<p/> -<hr/> silas.seal7@gmail.com','Tue Sep 22 12:25:38  |  2020','silas.seal7@gmail.com'),('1','Yeah bro. I can\'t find any more bugs.\r<p/>\r<p/>Please continue to test and give feedback. Thanks for your time\r<p/> -<hr/> silas.seal7@gmail.com','Tue Sep 22 12:25:47  |  2020','silas.seal7@gmail.com'),('1','Yeah bro. I can\'t find any more bugs.\r<p/>\r<p/>Please continue to test and give feedback. Thanks for your time\r<p/> -<hr/> silas.seal7@gmail.com','Tue Sep 22 12:30:37  |  2020','silas.seal7@gmail.com'),('1','Yeah bro. I can\'t find any more bugs.\r<p/>\r<p/>Please continue to test and give feedback. Thanks for your time\r<p/> -<hr/> silas.seal7@gmail.com','Tue Sep 22 12:30:39  |  2020','silas.seal7@gmail.com'),('1','Yeah bro. I can\'t find any more bugs.\r<p/>\r<p/>Please continue to test and give feedback. Thanks for your time\r<p/> -<hr/> silas.seal7@gmail.com','Tue Sep 22 12:30:46  |  2020','silas.seal7@gmail.com'),('1','Why is it doing so -<hr/> silas.seal7@gmail.com','Tue Sep 22 12:32:01  |  2020','silas.seal7@gmail.com'),('1','I think it is now ok! Isn\'t it? -<hr/> silas.seal7@gmail.com','Tue Sep 22 12:32:37  |  2020','silas.seal7@gmail.com'),('3','Hi Isaac\r<p/>\r<p/>I just wanted to make a fresh test start on this messaging platform. Your further help is highly needed.\r<p/>\r<p/>Continue to test and give feedback\r<p/>\r<p/>Silas','Tue Sep 22 12:36:09  |  2020','silas.seal7@gmail.com'),('3','Ok, Let\'s try it here again -<hr/> silas.seal7@gmail.com','Tue Sep 22 12:36:41  |  2020','silas.seal7@gmail.com'),('5','Reaching Out To: silas.seal7@gmail.com','Thu Sep 24 14:56:27  |  2020','silas.seal7@gmail.com'),('5','Hello Silas, \r<p/>\r<p/>This is just to inform you that I have been approved and account is ready to transact.\r<p/>\r<p/>I send you my request.','Thu Sep 24 14:56:27  |  2020','peterson@seumxplus.com'),('5','Ohhh man, Good to see you here.\r<p/>\r<p/>Let me check your request! -<hr/> silas.seal7@gmail.com','Thu Sep 24 14:58:44  |  2020','silas.seal7@gmail.com'),('5','Ohhh man, Good to see you here.\r<p/>\r<p/>Let me check your request! -<hr/> silas.seal7@gmail.com','Thu Sep 24 14:59:18  |  2020','silas.seal7@gmail.com'),('5','Ohhh man, Good to see you here.\r<p/>\r<p/>Let me check your request! -<hr/> silas.seal7@gmail.com','Thu Sep 24 14:59:40  |  2020','silas.seal7@gmail.com'),('5','Ok But I aint complete -<hr/> peterson@seumxplus.com','Thu Sep 24 15:00:34  |  2020','peterson@seumxplus.com'),('5','On this side men -<hr/> silas.seal7@gmail.com','Thu Sep 24 15:02:20  |  2020','silas.seal7@gmail.com'),('5','I think its getting better now -<hr/> peterson@seumxplus.com','Thu Sep 24 15:05:42  |  2020','peterson@seumxplus.com'),('5','OK bro, Am sure The System is now responding as programmed.\r<p/>\r<p/>Keep testing more functions. Will be great to fix any bug -<hr/> silas.seal7@gmail.com','Thu Sep 24 15:07:30  |  2020','silas.seal7@gmail.com'),('5','OK bro, Am sure The System is now responding as programmed.\r<p/>\r<p/>Keep testing more functions. Will be great to fix any bug -<hr/> silas.seal7@gmail.com','Thu Sep 24 15:07:40  |  2020','silas.seal7@gmail.com'),('5','I gat it bro. Its on Paragraphs. They cause troble -<hr/> silas.seal7@gmail.com','Thu Sep 24 15:09:21  |  2020','silas.seal7@gmail.com'),('5','FIX IT NOW!!!! -<hr/> peterson@seumxplus.com','Thu Sep 24 15:09:44  |  2020','peterson@seumxplus.com'),('5','Am On It! -<hr/> silas.seal7@gmail.com','Thu Sep 24 15:10:24  |  2020','silas.seal7@gmail.com'),('6','Reaching Out To: silas.seal7@gmail.com','Thu Sep 24 15:14:29  |  2020','silas.seal7@gmail.com'),('6','Hello Man, How are going!\r<p/>\r<p/>Can we deliver Today?','Thu Sep 24 15:14:29  |  2020','peterson@seumxplus.com'),('6','Hi Peter,\r<p/>\r<p/>Thanks for your Time\r<p/>\r<p/>Test this out. -<hr/> silas.seal7@gmail.com','Thu Sep 24 15:16:10  |  2020','silas.seal7@gmail.com'),('6','This is Just Owesome. 100% working \r<p/>\r<p/>I am sure boss will be pleased. Hahahaaah\r<p/>\r<p/>Get it hosted and share link with Me. Well done man -<hr/> peterson@seumxplus.com','Thu Sep 24 15:17:59  |  2020','peterson@seumxplus.com'),('6','thanks bro. The link will be on your desk before midday tomorrow. -<hr/> silas.seal7@gmail.com','Thu Sep 24 15:19:39  |  2020','silas.seal7@gmail.com'),('6','Ok, Thanks -<hr/> peterson@seumxplus.com','Thu Sep 24 15:20:47  |  2020','peterson@seumxplus.com');
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
-- Table structure for table `payroll`
--

DROP TABLE IF EXISTS `payroll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payroll` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Pname` varchar(318) NOT NULL,
  `Pown` varchar(318) NOT NULL,
  `Company` varchar(418) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payroll`
--

LOCK TABLES `payroll` WRITE;
/*!40000 ALTER TABLE `payroll` DISABLE KEYS */;
INSERT INTO `payroll` VALUES (2,'Distribution Sales Representatives','silas.seal7@gmail.com','Airtel Uganda'),(4,'Web Developers','silas.seal7@gmail.com','Oracle');
/*!40000 ALTER TABLE `payroll` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payroller`
--

DROP TABLE IF EXISTS `payroller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payroller` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(418) NOT NULL,
  `Title` varchar(318) NOT NULL,
  `Amount` float NOT NULL,
  `PID` varchar(31) NOT NULL,
  `Email` varchar(317) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payroller`
--

LOCK TABLES `payroller` WRITE;
/*!40000 ALTER TABLE `payroller` DISABLE KEYS */;
INSERT INTO `payroller` VALUES (1,'Mukisa Peter','Software Developer',150,'2','peterson@seumxplus.com'),(3,'Muwereza Isaac','UI/UX Designer',90,'2','muwerezaisaac@gmail.com'),(4,'Mukisa Peter','Data Analyst',250.57,'4','peterson@seumxplus.com'),(5,'Muwereza Isaac','Network Engineer',170.81,'4','muwerezaisaac@gmail.com');
/*!40000 ALTER TABLE `payroller` ENABLE KEYS */;
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
  `main` float NOT NULL,
  `boss` int(11) NOT NULL,
  `lancer` int(11) NOT NULL,
  `user_type` varchar(80) NOT NULL,
  `status` varchar(137) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10000004 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (10000000,'Muwanguzi','Silas','silas.seal7@gmail.com','+256704049977','male','country','dob','Namwendwa,kamuli,Uganda,Africa','silmatic001',829.84,10,73,'client','approved'),(10000001,'Muwereza','Isaac','muwerezaisaac@gmail.com','0702043057','male','Uganda','2000-01-14','Namwendwa, Kamuli, Uganda','izoex',806.54,0,0,'client','pending'),(10000002,'Mukisa','Peter','peterson@seumxplus.com','+256787019873','male','Uganda','2000-07-17','Namwendwa, Kamuli, Uganda','pet256',608.56,0,0,'client','approved'),(10000003,'Mwesigwa','Victory','victory@gmail.com','+256787019871','female','Uganda','2020-09-09','Namwendwa, Kamuli, Uganda','vic',0,0,0,'client','waiting');
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
  `amount` float NOT NULL,
  `status` varchar(71) NOT NULL,
  `own` varchar(137) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requests`
--

LOCK TABLES `requests` WRITE;
/*!40000 ALTER TABLE `requests` DISABLE KEYS */;
INSERT INTO `requests` VALUES (1,'muwerezaisaac@gmail.com','Fri Sep 04 09:25:44 MST 2020',71,'ignore','silas.seal7@gmail.com'),(2,'silas.seal7@gmail.com','Tue Sep 08 23:05:45 MST 2020',31,'ignore','muwerezaisaac@gmail.com'),(3,'muwerezaisaac@gmail.com','Sat Sep 12 00:56:45 MST 2020',45,'ignore','silas.seal7@gmail.com'),(4,'muwerezaisaac@gmail.com','Sat Sep 12 01:13:17 MST 2020',27,'ignore','silas.seal7@gmail.com'),(5,'muwerezaisaac@gmail.com','Sat Sep 12 01:18:08 MST 2020',27,'ignore','silas.seal7@gmail.com'),(6,'silas.seal7@gmail.com','Sat Sep 12 01:25:48 MST 2020',43,'ignore','muwerezaisaac@gmail.com'),(7,'silas.seal7@gmail.com','Sat Sep 12 01:26:33 MST 2020',54,'ignore','muwerezaisaac@gmail.com'),(8,'silas.seal7@gmail.com','Sat Sep 12 01:28:38 MST 2020',60,'ignore','muwerezaisaac@gmail.com'),(9,'muwerezaisaac@gmail.com','Wed Sep 16 04:05:42  |  2020',81,'ignore','silas.seal7@gmail.com'),(10,'silas.seal7@gmail.com','Thu Sep 24 14:10:41  |  2020',13,'ignore','peterson@seumxplus.com'),(11,'silas.seal7@gmail.com','Thu Sep 24 23:17:28  |  2020',48,'ignore','peterson@seumxplus.com'),(12,'peterson@seumxplus.com','Mon Sep 28 02:31:22  |  2020',12.31,'waiting','silas.seal7@gmail.com'),(13,'peterson@seumxplus.com','Mon Sep 28 03:38:03  |  2020',54.43,'ignore','silas.seal7@gmail.com');
/*!40000 ALTER TABLE `requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(318) NOT NULL,
  `Status` varchar(318) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
INSERT INTO `session` VALUES (1,'silas.seal7@gmail.com','active'),(2,'peterson@seumxplus.com','active');
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
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
  `amount` float NOT NULL,
  `balance` double NOT NULL,
  `status` varchar(137) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10000081 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (10000000,'silas.seal7@gmail.com','sending','syrus.sealline@gmail.com','25 August, 2020 2:11PM',21,78,'completed'),(10000001,'silas.seal7@gmail.com','Payment','muwereza.isaac@gmail.com','29 August, 2020 7:47 PM',31,50,'completed'),(10000002,'silas.seal7@gmail.com','Withdrawing','Stanbic Bank','18 August, 2020 5:21 PM',81,31,'completed'),(10000003,'silas.seal7@gmail.com','sending','muwerezaisaac@gmail.com','Thu Sep 03 08:12:43 MST 2020',34,1266,'completed'),(10000004,'muwerezaisaac@gmail.com','receiving','silas.seal7@gmail.com','Thu Sep 03 08:12:43 MST 2020',34,34,'completed'),(10000005,'silas.seal7@gmail.com','sending','null','Thu Sep 03 08:54:04 MST 2020',71,1195,'completed'),(10000006,'null','receiving','silas.seal7@gmail.com','Thu Sep 03 08:54:04 MST 2020',71,0,'completed'),(10000007,'silas.seal7@gmail.com','sending','muwerezaisaac@gmail.com','Thu Sep 03 08:54:55 MST 2020',113,1082,'completed'),(10000008,'muwerezaisaac@gmail.com','receiving','silas.seal7@gmail.com','Thu Sep 03 08:54:55 MST 2020',113,147,'completed'),(10000009,'silas.seal7@gmail.com','sending','muwerezaisaac@gmail.com','Thu Sep 03 09:10:08 MST 2020',43,1039,'completed'),(10000010,'muwerezaisaac@gmail.com','receiving','silas.seal7@gmail.com','Thu Sep 03 09:10:08 MST 2020',43,190,'completed'),(10000011,'silas.seal7@gmail.com','sending','muwerezaisaac@gmail.com','Fri Sep 04 00:39:20 MST 2020',27,1012,'completed'),(10000012,'muwerezaisaac@gmail.com','receiving','silas.seal7@gmail.com','Fri Sep 04 00:39:20 MST 2020',27,217,'completed'),(10000013,'silas.seal7@gmail.com','sending','muwerezaisaac@gmail.com','Fri Sep 04 00:39:39 MST 2020',31,981,'completed'),(10000014,'muwerezaisaac@gmail.com','receiving','silas.seal7@gmail.com','Fri Sep 04 00:39:39 MST 2020',31,248,'completed'),(10000015,'silas.seal7@gmail.com','sending','muwerezaisaac@gmail.com','Fri Sep 04 08:11:56 MST 2020',71,910,'completed'),(10000016,'muwerezaisaac@gmail.com','receiving','silas.seal7@gmail.com','Fri Sep 04 08:11:56 MST 2020',71,319,'completed'),(10000017,'muwerezaisaac@gmail.com','sending','silas.seal7@gmail.com','Tue Sep 08 23:06:54 MST 2020',73,246,'completed'),(10000018,'silas.seal7@gmail.com','receiving','muwerezaisaac@gmail.com','Tue Sep 08 23:06:54 MST 2020',73,983,'completed'),(10000019,'silas.seal7@gmail.com','withdraw','Bank Of Africa','Wed Sep 09 05:24:18 MST 2020',54,929,'pending'),(10000020,'silas.seal7@gmail.com','withdraw','Bank Of Africa','Wed Sep 09 05:28:47 MST 2020',62,867,'pending'),(10000021,'silas.seal7@gmail.com','Send Payment','muwerezaisaac@gmail.com','Sat Sep 12 00:51:42 MST 2020',31,836,'completed'),(10000022,'muwerezaisaac@gmail.com','Receive Payment','silas.seal7@gmail.com','Sat Sep 12 00:51:42 MST 2020',31,277,'completed'),(10000023,'muwerezaisaac@gmail.com','Send Payment','silas.seal7@gmail.com','Sat Sep 12 01:32:30 MST 2020',45,232,'completed'),(10000024,'silas.seal7@gmail.com','Receive Payment','muwerezaisaac@gmail.com','Sat Sep 12 01:32:30 MST 2020',45,881,'completed'),(10000025,'silas.seal7@gmail.com','Send Payment','muwerezaisaac@gmail.com','Sat Sep 12 01:46:49 MST 2020',43,838,'completed'),(10000026,'muwerezaisaac@gmail.com','Receive Payment','silas.seal7@gmail.com','Sat Sep 12 01:46:49 MST 2020',43,275,'completed'),(10000027,'silas.seal7@gmail.com','withdraw','Bank Of Africa','Tue Sep 15 08:10:22  |  2020',54,784,'pending'),(10000028,'silas.seal7@gmail.com','sending','muwerezaisaac@gmail.com','Tue Sep 22 11:31:52  |  2020',135,865,'completed'),(10000029,'muwerezaisaac@gmail.com','receiving','silas.seal7@gmail.com','Tue Sep 22 11:31:52  |  2020',135,410,'completed'),(10000030,'silas.seal7@gmail.com','withdraw','Bank Of Africa','Tue Sep 22 11:32:41  |  2020',15,850,'pending'),(10000031,'silas.seal7@gmail.com','sending','peterson@seumxplus.com','Thu Sep 24 14:07:14  |  2020',57,793,'completed'),(10000032,'peterson@seumxplus.com','receiving','silas.seal7@gmail.com','Thu Sep 24 14:07:14  |  2020',57,57,'completed'),(10000033,'silas.seal7@gmail.com','Send Payment','peterson@seumxplus.com','Thu Sep 24 15:34:02  |  2020',13,780,'completed'),(10000034,'peterson@seumxplus.com','Receive Payment','silas.seal7@gmail.com','Thu Sep 24 15:34:02  |  2020',13,70,'completed'),(10000035,'silas.seal7@gmail.com','withdraw','Bank Of Africa','Thu Sep 24 15:35:37  |  2020',21,759,'pending'),(10000036,'silas.seal7@gmail.com','withdraw','Bank Of Africa','Sat Sep 26 14:30:59  |  2020',56,703,'pending'),(10000037,'silas.seal7@gmail.com','withdraw','Bank Of Africa','Sat Sep 26 14:31:06  |  2020',56,647,'pending'),(10000038,'silas.seal7@gmail.com','withdraw','Bank Of Africa','Sat Sep 26 14:31:12  |  2020',56,591,'pending'),(10000039,'silas.seal7@gmail.com','withdraw','Bank Of Africa','Sat Sep 26 14:31:13  |  2020',56,535,'pending'),(10000040,'silas.seal7@gmail.com','withdraw','Bank Of Africa','Sat Sep 26 14:54:13  |  2020',31,504,'pending'),(10000041,'silas.seal7@gmail.com','withdraw','Bank Of Africa','Sat Sep 26 14:54:19  |  2020',31,473,'pending'),(10000042,'peterson@seumxplus.com','Payment','silas.seal7@gmail.com','Mon Sep 28 00:02:47  |  2020',150,220,'completed'),(10000043,'silas.seal7@gmail.com','Payment','Distribution Sales Representatives - Airtel Uganda','Mon Sep 28 00:02:47  |  2020',240,233,'completed'),(10000044,'muwerezaisaac@gmail.com','Payment','silas.seal7@gmail.com','Mon Sep 28 00:02:47  |  2020',90,500,'completed'),(10000045,'silas.seal7@gmail.com','Payment','Distribution Sales Representatives - Airtel Uganda','Mon Sep 28 00:02:47  |  2020',240,233,'completed'),(10000046,'peterson@seumxplus.com','Receive Payment','silas.seal7@gmail.com','Mon Sep 28 00:11:41  |  2020',150,370,'completed'),(10000047,'muwerezaisaac@gmail.com','Receive Payment','silas.seal7@gmail.com','Mon Sep 28 00:11:41  |  2020',90,590,'completed'),(10000048,'silas.seal7@gmail.com','Send Payment','Distribution Sales Representatives - Airtel Uganda','Mon Sep 28 00:11:41  |  2020',240,760,'completed'),(10000049,'silas.seal7@gmail.com','sending','peterson@seumxplus.com','Mon Sep 28 01:16:05  |  2020',18,742,'completed'),(10000050,'peterson@seumxplus.com','receiving','silas.seal7@gmail.com','Mon Sep 28 01:16:05  |  2020',18,388,'completed'),(10000051,'silas.seal7@gmail.com','sending','peterson@seumxplus.com','Mon Sep 28 01:38:44  |  2020',12.31,729.69,'completed'),(10000052,'peterson@seumxplus.com','receiving','silas.seal7@gmail.com','Mon Sep 28 01:38:44  |  2020',12.31,400.31,'completed'),(10000053,'silas.seal7@gmail.com','sending','peterson@seumxplus.com','Mon Sep 28 01:40:11  |  2020',27.81,701.8800000000001,'completed'),(10000054,'peterson@seumxplus.com','receiving','silas.seal7@gmail.com','Mon Sep 28 01:40:11  |  2020',27.81,428.12,'completed'),(10000055,'silas.seal7@gmail.com','sending','muwerezaisaac@gmail.com','Mon Sep 28 01:51:17  |  2020',13.21,688.79,'completed'),(10000056,'muwerezaisaac@gmail.com','receiving','silas.seal7@gmail.com','Mon Sep 28 01:51:17  |  2020',13.21,603.21,'completed'),(10000057,'silas.seal7@gmail.com','sending','muwerezaisaac@gmail.com','Mon Sep 28 02:00:08  |  2020',67.31,621.69,'completed'),(10000058,'muwerezaisaac@gmail.com','receiving','silas.seal7@gmail.com','Mon Sep 28 02:00:08  |  2020',67.31,670.31,'completed'),(10000059,'silas.seal7@gmail.com','sending','peterson@seumxplus.com','Mon Sep 28 02:01:16  |  2020',21.19,600.5,'completed'),(10000060,'peterson@seumxplus.com','receiving','silas.seal7@gmail.com','Mon Sep 28 02:01:16  |  2020',21.19,449.19,'completed'),(10000061,'silas.seal7@gmail.com','sending','muwerezaisaac@gmail.com','Mon Sep 28 02:01:59  |  2020',14.41,586.09,'completed'),(10000062,'muwerezaisaac@gmail.com','receiving','silas.seal7@gmail.com','Mon Sep 28 02:01:59  |  2020',14.41,684.7199999999999,'completed'),(10000063,'silas.seal7@gmail.com','sending','muwerezaisaac@gmail.com','Mon Sep 28 02:30:45  |  2020',31.82,554.27,'completed'),(10000064,'muwerezaisaac@gmail.com','receiving','silas.seal7@gmail.com','Mon Sep 28 02:30:45  |  2020',31.82,716.5400000000001,'completed'),(10000065,'silas.seal7@gmail.com','withdraw','Bank Of Africa','Mon Sep 28 02:31:53  |  2020',61.78,492.49,'pending'),(10000066,'silas.seal7@gmail.com','withdraw','Bank Of Africa','Mon Sep 28 02:35:47  |  2020',61.78,430.71000000000004,'pending'),(10000067,'silas.seal7@gmail.com','withdraw','Bank Of Africa','Mon Sep 28 02:36:25  |  2020',54.21,376.5,'pending'),(10000068,'silas.seal7@gmail.com','withdraw','Bank Of Africa','Mon Sep 28 02:40:36  |  2020',31.23,345.27,'pending'),(10000069,'silas.seal7@gmail.com','Send Payment','peterson@seumxplus.com','Mon Sep 28 02:48:03  |  2020',48,297.27,'completed'),(10000070,'peterson@seumxplus.com','Receive Payment','silas.seal7@gmail.com','Mon Sep 28 02:48:03  |  2020',48,497.19,'completed'),(10000071,'silas.seal7@gmail.com','sending','peterson@seumxplus.com','Mon Sep 28 03:30:43  |  2020',31.21,266.06,'completed'),(10000072,'peterson@seumxplus.com','receiving','silas.seal7@gmail.com','Mon Sep 28 03:30:43  |  2020',31.21,528.4,'completed'),(10000073,'silas.seal7@gmail.com','withdraw','Bank Of Africa','Mon Sep 28 03:32:56  |  2020',41.34,224.72,'pending'),(10000074,'peterson@seumxplus.com','Receive Payment','silas.seal7@gmail.com','Mon Sep 28 03:39:03  |  2020',150,678.4,'completed'),(10000075,'muwerezaisaac@gmail.com','Receive Payment','silas.seal7@gmail.com','Mon Sep 28 03:39:03  |  2020',90,806.54,'completed'),(10000076,'silas.seal7@gmail.com','Send Payment','Distribution Sales Representatives - Airtel Uganda','Mon Sep 28 03:39:03  |  2020',240,760,'completed'),(10000077,'peterson@seumxplus.com','Send Payment','silas.seal7@gmail.com','Mon Sep 28 03:58:56  |  2020',54.43,623.97003,'completed'),(10000078,'silas.seal7@gmail.com','Receive Payment','peterson@seumxplus.com','Mon Sep 28 03:58:56  |  2020',54.43,814.43,'completed'),(10000079,'peterson@seumxplus.com','Send Payment : sqlbench','Seumx Plus','Tue Sep 29 03:18:03  |  2020',15.41,608.56,'completed'),(10000080,'silas.seal7@gmail.com','Receive Payment : sqlbench','Seumx Plus','Tue Sep 29 03:18:03  |  2020',15.41,829.83997,'completed');
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

-- Dump completed on 2020-09-29  4:29:10
