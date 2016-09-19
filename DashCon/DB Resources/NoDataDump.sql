CREATE DATABASE  IF NOT EXISTS `sri` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sri`;
-- MySQL dump 10.13  Distrib 5.6.19, for Win32 (x86)
--
-- Host: localhost    Database: sri
-- ------------------------------------------------------
-- Server version	5.6.21-log

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
-- Table structure for table `company_data`
--

DROP TABLE IF EXISTS `company_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company_data` (
  `us_dot_number` varchar(10) NOT NULL,
  `dba_name` varchar(100) DEFAULT NULL,
  `drivers` varchar(10) DEFAULT NULL,
  `duns_number` varchar(45) DEFAULT NULL,
  `entity_type` varchar(20) DEFAULT NULL,
  `legal_name` varchar(100) DEFAULT NULL,
  `mailing_address` varchar(100) DEFAULT NULL,
  `mcmxff_number` varchar(20) DEFAULT NULL,
  `mcs150` varchar(10) DEFAULT NULL,
  `mcs150_mileage` varchar(20) DEFAULT NULL,
  `operating_status` varchar(45) DEFAULT NULL,
  `out_of_service_date` varchar(10) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `physical_address` varchar(100) DEFAULT NULL,
  `power_units` varchar(5) DEFAULT NULL,
  `state_carrier_id` varchar(10) DEFAULT NULL,
  `company_datacol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`us_dot_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_data`
--

LOCK TABLES `company_data` WRITE;
/*!40000 ALTER TABLE `company_data` DISABLE KEYS */;
INSERT INTO `company_data` VALUES ('2465358','TWO MEN AND A TRUCK OF BOYNTON DELRAY','8','--',' Carrier ','W & W MOVING SOLUTIONS LLC',' 130 LANSING IS DR INDIAN HARBOUR BEACH, FL  32937 ',' MC-852029 ','10/06/2014','20,000 (2014)',' AUTHORIZED FOR HHG ',' None ','(561) 404-8807',' 1515 N CONGRESS AVE DELRAY BEACH, FL  33445 ','6','',NULL),('7437','','','--',' Carrier ','TANAHILL TRKG',' 1518 W 5 FRIONA, TX  79035-2208 ',' ','08/13/2001','0 ()',' ACTIVE ',' None ','',' BOX 902 FRIONA, TX  79035 ','0','',NULL),('7646','','40','11-013-406 ',' Carrier ','SCHAGRIN GAS CO',' PO BOX 427 MIDDLETOWN, DE  19709-0427 ',' ','01/27/2014','456,811 (2013)',' ACTIVE ',' None ','(302) 378-2000',' 1000 N BROAD ST MIDDLETOWN, DE  19709-1069 ','70','',NULL);
/*!40000 ALTER TABLE `company_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crash_data`
--

DROP TABLE IF EXISTS `crash_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crash_data` (
  `us_dot_number` varchar(10) NOT NULL,
  `country` varchar(3) NOT NULL,
  `fatal` varchar(5) DEFAULT NULL,
  `injury` varchar(5) DEFAULT NULL,
  `total` varchar(5) DEFAULT NULL,
  `tow` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`us_dot_number`,`country`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crash_data`
--

LOCK TABLES `crash_data` WRITE;
/*!40000 ALTER TABLE `crash_data` DISABLE KEYS */;
INSERT INTO `crash_data` VALUES ('2465358','CAN','0','0','0','0'),('2465358','USA','0','0','0','0'),('7437','CAN','0','0','0','0'),('7437','USA','0','0','0','0'),('7646','CAN','0','0','0','0'),('7646','USA','0','0','2','2');
/*!40000 ALTER TABLE `crash_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device` (
  `DEVICE_NAME` varchar(45) DEFAULT NULL,
  `DEVICE_TYPE_ID` int(11) DEFAULT NULL,
  `VENDOR_ID` int(11) DEFAULT NULL,
  `DEVICE_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` VALUES ('TestDevice1',1234,1234,1234),('TestDevice2',2234,2234,2234),('TestDevice3',3234,3234,3234),('SCALE(STATIC)',1111,1111,1111),('TRANSPONDER',1111,1111,1111),('TRANSPONDER',1111,3333,1111),('VM SIGN',2222,4444,2222),('VM SIGN',3333,2222,3333),('VM SIGN',3333,2222,3333);
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device_type`
--

DROP TABLE IF EXISTS `device_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device_type` (
  `TYPE_ID` int(11) DEFAULT NULL,
  `TYPE` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device_type`
--

LOCK TABLES `device_type` WRITE;
/*!40000 ALTER TABLE `device_type` DISABLE KEYS */;
INSERT INTO `device_type` VALUES (1234,'WIM'),(2234,'WHIM'),(3234,'WHIME'),(1111,'B-TEK'),(2222,'IG'),(3333,'INTERCOMP'),(4444,'HAENNI'),(5555,'INTERCOMP');
/*!40000 ALTER TABLE `device_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group` (
  `id` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
INSERT INTO `group` VALUES ('admin','Site Admin'),('external','3rd Party Vendors'),('super','Super User'),('user','User');
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inspection`
--

DROP TABLE IF EXISTS `inspection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inspection` (
  `RPTNUM` varchar(12) NOT NULL,
  `INSPSTARTDATE` date DEFAULT '2015-01-01',
  `INSPSTARTTIME` time DEFAULT '00:00:00',
  `INSPENDDATE` date DEFAULT NULL,
  `INSPENDTIME` time DEFAULT NULL,
  `DURATION` int(11) DEFAULT NULL,
  `LEVEL` varchar(1) DEFAULT NULL,
  `TIMEZONE` varchar(2) DEFAULT NULL,
  `INSPLOCATIONCODE` varchar(6) DEFAULT NULL,
  `INSPLOCATIONDESC` varchar(30) DEFAULT NULL,
  `HIGHWAY` varchar(25) DEFAULT NULL,
  `MILEPOST` varchar(6) DEFAULT NULL,
  `FACILITYTYPE` varchar(1) DEFAULT NULL,
  `INSPSTATE` varchar(2) DEFAULT NULL,
  `INSPCOUNTY` varchar(25) DEFAULT NULL,
  `FIPSCOUNTYCODE` varchar(3) DEFAULT NULL,
  `STATECOUNTYCODE` varchar(3) DEFAULT NULL,
  `INSPECTORCODE` varchar(6) DEFAULT NULL,
  `INSPECTORNAME` varchar(40) DEFAULT NULL,
  `COINSPECTORCODE` varchar(6) DEFAULT NULL,
  `COINSPECTORNAME` varchar(40) DEFAULT NULL,
  `ALCSUBCHK` varchar(1) DEFAULT 'N',
  `DRUGSEARCH` varchar(1) DEFAULT 'N',
  `DRUGARREST` varchar(2) DEFAULT NULL,
  `SIZEWEIGHTENF` varchar(1) DEFAULT 'N',
  `TRAFFICENF` varchar(1) DEFAULT 'N',
  `LOCALJURISDICTION` varchar(1) DEFAULT 'N',
  `HMINSPTYPE` varchar(1) DEFAULT NULL,
  `USDOTNUM` varchar(8) DEFAULT NULL,
  `STATECENSUSNUM` varchar(12) DEFAULT NULL,
  `ICCNUM` varchar(6) DEFAULT NULL,
  `MEXICANCARRIERID` varchar(15) DEFAULT NULL,
  `INTERSTATE` varchar(1) DEFAULT 'N',
  `CARRNAME` varchar(120) DEFAULT NULL,
  `CARRSTREET` varchar(50) DEFAULT NULL,
  `CARRCOLONIA` varchar(100) DEFAULT NULL,
  `CARRCITY` varchar(25) DEFAULT NULL,
  `CARRSTATE` varchar(2) DEFAULT NULL,
  `CARRZIPCODE` varchar(10) DEFAULT NULL,
  `CARRPHONE` varchar(13) DEFAULT NULL,
  `CARRFAX` varchar(13) DEFAULT NULL,
  `SHIPNAME` varchar(120) DEFAULT NULL,
  `SHIPNUM` varchar(15) DEFAULT NULL,
  `DRIVERLNAME` varchar(20) DEFAULT NULL,
  `DRIVERFNAME` varchar(20) DEFAULT NULL,
  `DRIVERMI` varchar(1) DEFAULT NULL,
  `DRIVERLICNUM` varchar(25) DEFAULT NULL,
  `DRIVERLICSTATE` varchar(2) DEFAULT NULL,
  `DRIVERDOB` date DEFAULT NULL,
  `CODRIVERLNAME` varchar(20) DEFAULT NULL,
  `CODRIVERFNAME` varchar(20) DEFAULT NULL,
  `CODRIVERMI` varchar(1) DEFAULT NULL,
  `CODRIVERLICNUM` varchar(25) DEFAULT NULL,
  `CODRIVERLICSTATE` varchar(2) DEFAULT NULL,
  `CODRIVERDOB` date DEFAULT NULL,
  `CARGO` varchar(25) DEFAULT NULL,
  `ORIGIN_CITY` varchar(40) DEFAULT NULL,
  `DESTINATION_CITY` varchar(40) DEFAULT NULL,
  `HMPLACARD` varchar(1) DEFAULT 'N',
  `CARGOTANKSPECNUM` varchar(3) DEFAULT NULL,
  `GCWR` int(11) DEFAULT NULL,
  `AXLES` varchar(2) DEFAULT NULL,
  `TOTALHM` varchar(1) DEFAULT '0',
  `TOTALVEHICLE` varchar(1) DEFAULT '0',
  `TOTALVIO` varchar(3) DEFAULT '0',
  `TOTALOOSVIO` varchar(3) DEFAULT '0',
  `TOTALVEHICLEOOSVIO` varchar(3) DEFAULT '0',
  `TOTALDRIVEROOSVIO` varchar(3) DEFAULT '0',
  `ACCIDENT` varchar(1) DEFAULT 'N',
  `OOSUNTIL` varchar(40) DEFAULT NULL,
  `VEHICLEOOS` varchar(1) DEFAULT 'N',
  `DRIVEROOS` varchar(1) DEFAULT 'N',
  `UNIT1PLATENUM` varchar(12) DEFAULT NULL,
  `ASPENMATCH` varchar(1) DEFAULT NULL,
  `ASPENVERSIONNUM` varchar(10) DEFAULT NULL,
  `RPTSTATUS` varchar(1) DEFAULT 'I',
  `TRANSFERRED` varchar(1) DEFAULT 'F',
  `TRANDATE` date DEFAULT NULL,
  `TRANTIME` time DEFAULT NULL,
  `TAG` varchar(1) DEFAULT NULL,
  `DRIVERSIGNATURE` blob,
  `ASPEN1` varchar(10) DEFAULT NULL,
  `ASPEN2` varchar(10) DEFAULT NULL,
  `STUDY1` varchar(40) DEFAULT NULL,
  `STUDY2` varchar(40) DEFAULT NULL,
  `STUDY3` varchar(40) DEFAULT NULL,
  `STUDY4` varchar(40) DEFAULT NULL,
  `STUDY5` varchar(40) DEFAULT NULL,
  `CARRCOUNTRY` varchar(2) DEFAULT NULL,
  `BIPDVERIFIED` varchar(1) DEFAULT NULL,
  `PASAINSPECTION` varchar(1) DEFAULT 'N',
  `INSPCOUNTRY` varchar(2) DEFAULT NULL,
  `STUDY6` varchar(40) DEFAULT NULL,
  `STUDY7` varchar(40) DEFAULT NULL,
  `STUDY8` varchar(40) DEFAULT NULL,
  `STUDY9` varchar(40) DEFAULT NULL,
  `STUDY10` varchar(40) DEFAULT NULL,
  `PBBTCHECK` varchar(1) DEFAULT 'N',
  `PBBTAXLES` varchar(2) DEFAULT NULL,
  `PBBTBRAKEFORCE` double DEFAULT NULL,
  `PBBTMINBRAKEFORCE` double DEFAULT NULL,
  `PBBTVEHICLETYPEID` smallint(6) DEFAULT NULL,
  `BEGFUNDEDINSPECTION` varchar(1) DEFAULT 'N',
  `BEGFUNDEDTYPE` varchar(1) DEFAULT NULL,
  `IEPPRETRIPSPACE` varchar(1) DEFAULT NULL,
  `IEPPRETRIPINSPECTION` varchar(1) DEFAULT NULL,
  `ESCREEN` varchar(1) DEFAULT 'N',
  `CARRDBANAME` varchar(120) DEFAULT NULL,
  `NOTES` blob,
  `MANUAL_OOS_OPER_AUTH_CHECK` varchar(1) DEFAULT 'N',
  `MANUAL_OOS_CHECK_METHOD` varchar(1) DEFAULT NULL,
  `MANUAL_CARRIER_IS_OOS` varchar(1) DEFAULT NULL,
  `MANUAL_CARRIER_NO_OP_AUTH` varchar(1) DEFAULT NULL,
  `ORIGIN_STATE` varchar(2) DEFAULT NULL,
  `DESTINATION_STATE` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`RPTNUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inspection`
--

LOCK TABLES `inspection` WRITE;
/*!40000 ALTER TABLE `inspection` DISABLE KEYS */;
INSERT INTO `inspection` VALUES ('IL1234000001','2014-11-20','09:41:17','2014-11-20','09:46:38',5,'1','CT','TEST','TEST','TEST','TEST','R','IL','SAINT CLAIR','163',NULL,'1138','LUKE SKYWALKER',NULL,NULL,'N','N',NULL,'N','N','N','X','00000004',NULL,NULL,NULL,'Y','CONSOLIDATED GRANITE CO INC','P O BOX 765',NULL,'ELBERTON','GA','30635-0765','(706)283-5463',NULL,NULL,NULL,'DOE','JOHN',NULL,'1234','IL','1971-05-12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'N',NULL,0,'3','0','1','1','0','0','0','N',NULL,'N','N','TEMP','I','3.0','C','F','2015-03-27','08:52:57',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'US','F','N','US',NULL,NULL,NULL,NULL,NULL,'N',NULL,0,0,0,'N',NULL,NULL,NULL,'N',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `inspection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inspection_data`
--

DROP TABLE IF EXISTS `inspection_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inspection_data` (
  `us_dot_number` varchar(10) NOT NULL,
  `country` varchar(3) NOT NULL,
  `type` int(11) NOT NULL,
  `driver` varchar(10) DEFAULT NULL,
  `hazmat` varchar(10) DEFAULT NULL,
  `iep` varchar(10) DEFAULT NULL,
  `vehicle` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`us_dot_number`,`country`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inspection_data`
--

LOCK TABLES `inspection_data` WRITE;
/*!40000 ALTER TABLE `inspection_data` DISABLE KEYS */;
INSERT INTO `inspection_data` VALUES ('2465358','CAN',1,'0',NULL,NULL,'0'),('2465358','CAN',3,'0',NULL,NULL,'0'),('2465358','CAN',4,' 0% ',NULL,NULL,' 0% '),('2465358','USA',1,'13','0','0','10'),('2465358','USA',2,'5.51%','4.50%','N/A','20.72%'),('2465358','USA',3,'1','0','0','0'),('2465358','USA',4,' 7.7% ',' % ',' 0% ',' 0% '),('7437','CAN',1,'0',NULL,NULL,'0'),('7437','CAN',3,'0',NULL,NULL,'0'),('7437','CAN',4,' 0% ',NULL,NULL,' 0% '),('7437','USA',1,'0','0','0','0'),('7437','USA',2,'5.51%','4.50%','N/A','20.72%'),('7437','USA',3,'0','0','0','0'),('7437','USA',4,' 0% ',' 0% ',' 0% ',' 0% '),('7646','CAN',1,'0',NULL,NULL,'0'),('7646','CAN',3,'0',NULL,NULL,'0'),('7646','CAN',4,' 0% ',NULL,NULL,' 0% '),('7646','USA',1,'38','32','0','37'),('7646','USA',2,'5.51%','4.50%','N/A','20.72%'),('7646','USA',3,'0','0','0','0'),('7646','USA',4,' 0% ',' 0% ',' 0% ',' 0% ');
/*!40000 ALTER TABLE `inspection_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lpr`
--

DROP TABLE IF EXISTS `lpr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lpr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `site_id` int(11) NOT NULL,
  `file_name` varchar(45) NOT NULL,
  `sequence_number` int(11) DEFAULT NULL,
  `license_plate_number` varchar(45) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lpr`
--

LOCK TABLES `lpr` WRITE;
/*!40000 ALTER TABLE `lpr` DISABLE KEYS */;
INSERT INTO `lpr` VALUES (53,'2015-09-24 16:07:12',2,'lp0.jpg',0,'193419',NULL),(54,'2015-09-24 16:07:22',2,'lp1.jpg',1,'190420',NULL),(55,'2015-09-24 16:07:26',2,'lp2.jpg',2,'186630',NULL),(56,'2015-09-24 16:07:35',2,'lp3.jpg',3,'191594',NULL),(57,'2015-09-24 16:07:39',2,'lp4.jpg',4,'188572',NULL),(58,'2015-09-24 16:07:43',2,'lp5.jpg',5,'188436',NULL),(59,'2015-09-24 16:07:52',2,'lp6.jpg',6,'190615',NULL),(60,'2015-09-24 16:07:56',2,'lp7.jpg',7,'191396',NULL),(61,'2015-09-24 16:08:00',2,'lp8.jpg',8,'189857',NULL),(62,'2015-09-24 16:08:05',2,'lp9.jpg',9,'191421',NULL);
/*!40000 ALTER TABLE `lpr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `safety_rating`
--

DROP TABLE IF EXISTS `safety_rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `safety_rating` (
  `us_dot_number` varchar(10) NOT NULL,
  `rating` varchar(45) DEFAULT NULL,
  `rating_date` varchar(10) DEFAULT NULL,
  `review_date` varchar(10) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`us_dot_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `safety_rating`
--

LOCK TABLES `safety_rating` WRITE;
/*!40000 ALTER TABLE `safety_rating` DISABLE KEYS */;
INSERT INTO `safety_rating` VALUES ('2465358','None ','None ','None ','None '),('7437','None ','None ','None ','None '),('7646','None ','None ','05/14/2004','Non-Ratable ');
/*!40000 ALTER TABLE `safety_rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site`
--

DROP TABLE IF EXISTS `site`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `site` (
  `ID` int(11) NOT NULL,
  `STATE_ID` varchar(2) DEFAULT NULL,
  `NAME` varchar(45) NOT NULL,
  `DESCRIPTION` varchar(45) NOT NULL,
  `LATITUDE` varchar(45) NOT NULL,
  `LONGITUDE` varchar(45) NOT NULL,
  `SITE_TYPE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `STATE_idx` (`STATE_ID`),
  KEY `SITE_TYPE_idx` (`SITE_TYPE_ID`),
  CONSTRAINT `SITE_SITETYPE_SITETYPE` FOREIGN KEY (`SITE_TYPE_ID`) REFERENCES `site_type` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `SITE_STATE_STATE` FOREIGN KEY (`STATE_ID`) REFERENCES `state` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site`
--

LOCK TABLES `site` WRITE;
/*!40000 ALTER TABLE `site` DISABLE KEYS */;
INSERT INTO `site` VALUES (1111,'MO','Site2','testSite','01','1234',1111),(1234,'Il','Site1','testSite','01','1234',1234),(2222,'IA','Site3','testSite','01','1234',2222),(3333,'LA','Site4','testSite','01','1234',3333);
/*!40000 ALTER TABLE `site` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_type`
--

DROP TABLE IF EXISTS `site_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `site_type` (
  `ID` int(11) NOT NULL,
  `DESCRIPTION` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_type`
--

LOCK TABLES `site_type` WRITE;
/*!40000 ALTER TABLE `site_type` DISABLE KEYS */;
INSERT INTO `site_type` VALUES (1111,'Site2'),(1234,'Site1'),(2222,'Site3'),(3333,'Site4');
/*!40000 ALTER TABLE `site_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `state` (
  `ID` varchar(2) NOT NULL,
  `NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state`
--

LOCK TABLES `state` WRITE;
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
INSERT INTO `state` VALUES ('AK','Alaska'),('AL','Alabama'),('AR','Arkansas'),('AZ','Arizona'),('CA','California'),('CO','Colorado'),('CT','Connecticut'),('DC','District Of Columbia'),('DE','Delaware'),('FL','Florida'),('GA','Georgia'),('HI','Hawaii'),('IA','Iowa'),('ID','Idaho'),('IL','Illinois'),('IN','Indiana'),('KS','Kansas'),('KY','Kentucky'),('LA','Louisiana'),('MA','Massachusetts'),('MD','Maryland'),('ME','Maine'),('MI','Michigan'),('MN','Minnesota'),('MO','Missouri'),('MS','Mississippi'),('MT','Montana'),('NC','North Carolina'),('ND','North Dakota'),('NE','Nebraska'),('NH','New Hampshire'),('NJ','New Jersey'),('NM','New Mexico'),('NV','Nevada'),('NY','New York'),('OH','Ohio'),('OK','Oklahoma'),('OR','Oregon'),('PA','Pennsylvania'),('RI','Rhode Island'),('SC','South Carolina'),('SD','South Dakota'),('TN','Tennessee'),('TX','Texas'),('UT','Utah'),('VA','Virginia'),('VT','Vermont'),('WA','Washington'),('WI','Wisconsin'),('WV','West Virginia'),('WY','Wyoming');
/*!40000 ALTER TABLE `state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `truck_feed`
--

DROP TABLE IF EXISTS `truck_feed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `truck_feed` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_created` datetime DEFAULT CURRENT_TIMESTAMP,
  `timestamp` datetime NOT NULL,
  `approach_entered` datetime DEFAULT NULL,
  `wim_entered` datetime DEFAULT NULL,
  `wim_left` datetime DEFAULT NULL,
  `site_id` int(11) NOT NULL,
  `sequence_number` int(11) DEFAULT NULL,
  `license_plate` varchar(45) NOT NULL,
  `image_url` varchar(100) DEFAULT NULL,
  `drivers_license` varchar(45) DEFAULT NULL,
  `commercial_drivers_license` varchar(45) DEFAULT NULL,
  `vin` varchar(45) NOT NULL,
  `usdot_number` varchar(45) NOT NULL,
  `latitude` decimal(10,8) NOT NULL,
  `longitude` decimal(11,8) NOT NULL,
  `mobile_app_version` varchar(10) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `truck_feed`
--

LOCK TABLES `truck_feed` WRITE;
/*!40000 ALTER TABLE `truck_feed` DISABLE KEYS */;
INSERT INTO `truck_feed` VALUES (20,'2015-09-24 11:07:16','2015-09-24 11:07:20','2015-09-24 11:07:16','2015-09-24 11:07:18','2015-09-24 11:07:21',2,1,'190420',NULL,NULL,'FL9567','US4011','2465358',1.00000000,1.00000000,'1.1.8','F'),(21,'2015-09-24 11:07:29','2015-09-24 11:07:33','2015-09-24 11:07:29','2015-09-24 11:07:31','2015-09-24 11:07:34',2,3,'191594',NULL,NULL,'FL53','US3016','7437',1.00000000,1.00000000,'1.1.6','F'),(22,'2015-09-24 11:07:46','2015-09-24 11:07:51','2015-09-24 11:07:46','2015-09-24 11:07:49','2015-09-24 11:07:51',2,6,'190615',NULL,NULL,'FL8653','US7695','7646',1.00000000,1.00000000,'1.1.8','P');
/*!40000 ALTER TABLE `truck_feed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `truck_weight`
--

DROP TABLE IF EXISTS `truck_weight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `truck_weight` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `timestamp` int(11) NOT NULL,
  `weight` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `timestamp_UNIQUE` (`timestamp`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `truck_weight`
--

LOCK TABLES `truck_weight` WRITE;
/*!40000 ALTER TABLE `truck_weight` DISABLE KEYS */;
INSERT INTO `truck_weight` VALUES (1,1418920589,20540),(2,1418920597,22156),(3,1418920653,19187);
/*!40000 ALTER TABLE `truck_weight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usdot_number`
--

DROP TABLE IF EXISTS `usdot_number`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usdot_number` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usdot_number` varchar(45) NOT NULL,
  `sequence_number` varchar(45) NOT NULL,
  `manual_entered` tinyint(1) DEFAULT '0',
  `user_id` varchar(45) DEFAULT NULL,
  `site_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usdot_number`
--

LOCK TABLES `usdot_number` WRITE;
/*!40000 ALTER TABLE `usdot_number` DISABLE KEYS */;
INSERT INTO `usdot_number` VALUES (31,'7646','5',0,NULL,2),(32,'2465358','1',0,NULL,2),(33,'7437','3',0,NULL,2),(34,'7646','6',0,NULL,2);
/*!40000 ALTER TABLE `usdot_number` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `USER_ID` varchar(25) NOT NULL,
  `FIRST_NAME` varchar(45) NOT NULL,
  `LAST_NAME` varchar(45) NOT NULL,
  `STATE_ID` varchar(2) DEFAULT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `USER_PASSWORD` varchar(255) NOT NULL,
  `CREATE_DATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `SITE_ID` varchar(45) NOT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `EMAIL_UNIQUE` (`EMAIL`),
  UNIQUE KEY `USER_ID_UNIQUE` (`USER_ID`),
  KEY `USER_STATE_SHORTNAME_idx` (`STATE_ID`),
  CONSTRAINT `USER_STATE_SHORTNAME` FOREIGN KEY (`STATE_ID`) REFERENCES `state` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('aasdsad','rothattack2','Some LastName','IL','rothattack@gmail.net','a3a998437edd1f176919c38a34f6d202d03ade4d216f05638e99651623d05a2a','2015-04-17 15:06:20','1'),('aoisud','asdou','saoiud','IL','k@k.com','b7ed510eeecbe7fae9442a42133a72dc1bc64d718c52e9b0cb355927c9260a83','2015-04-17 15:06:20','1'),('asdsadsa','asd','asd','IL','asd@aasd','901cbba93dcb0392ea555deb8a5ec66b61da678ff6be10efa0cd0033a2561b7','2015-04-17 15:06:20','1'),('asdsadsadsd','asdsa','dsasd','IL','a!@asd','d4d9f06eeffd45e80fbd584a74e21ba0b4d19b8a1d71b06ed254c672d4293fcb','2015-04-17 15:06:20','1'),('chuckerin','Chuckerin','Black','IL','chuckerin@gmail.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','2015-04-17 15:06:20','2'),('chuckerin2','Chuckerin','Black','MD','chuck@leidos.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','2015-04-17 15:06:20','-1'),('dsaf','asd','asd','IL','asd@a','da0965c2fb8efc50db927e365d623867d740e107837485ac08c4157fc9babb4e','2015-04-17 15:06:20','1'),('dsasd1','asdsa','asdsa','IL','a@a','3ebed201e428e87967f402d6586a98bf64af5b9a0cb651e34838a937acbd2288','2015-04-17 15:06:20','1'),('finn','Finn','Human','IL','a@a.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','2015-04-17 15:06:20','1'),('human','Hu','Man','AL','hu@man.com','2fd1f0e988b628e256f2504122c5e9b8921d0b69e4a67de31015f90fcb658c7','2015-04-17 15:06:20','1'),('humanz','hu','man','AL','a@1a.com','e63c7ecf63dccdc1c277405fca0627de9af4b3270b47aa242e9a2d9bc5fccc4d','2015-04-17 15:06:20','1'),('jedi','Luke','Skywalker','IL','blackch@leidos.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','2015-04-17 15:06:20','1'),('jedi1','Phil','Black','AL','chuckerin.black@gmail.com','28efb68dcba507ecd182bead31e4e2d159b0f9185861d1ebfe60a12dfb310300','2015-04-17 15:06:20','1'),('jedi2','Phil','Black','AL','chuckerin1@gmail.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','2015-04-17 15:06:20','1'),('mtsri','Dan','Benedum','OH','Dan.Benedum@mt.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','2015-04-17 15:06:20','1'),('newUserID','User','Uber','AL','rothattack@gmail.c','b1fedb324c9aeef6c6f2c3b4b50832b404546719995d94b8e91b655939659e1b','2015-04-17 15:06:20','1'),('rothattack','Robert','Roth','IL','rothattack@gmail.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','2015-04-17 15:06:20','1');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_group`
--

DROP TABLE IF EXISTS `user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_group` (
  `user_id` varchar(25) NOT NULL,
  `group_id` varchar(25) NOT NULL,
  PRIMARY KEY (`user_id`,`group_id`),
  KEY `fk_group_id_idx` (`group_id`),
  CONSTRAINT `fk_group_id` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_group`
--

LOCK TABLES `user_group` WRITE;
/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
INSERT INTO `user_group` VALUES ('chuckerin','admin'),('jedi','admin'),('mtsri','external'),('chuckerin2','user'),('jedi2','user');
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehicle` (
  `rptnum` varchar(12) NOT NULL,
  `unitnum` varchar(1) NOT NULL,
  `unittype` varchar(2) DEFAULT NULL,
  `unitmake` varchar(10) DEFAULT NULL,
  `unityear` varchar(4) DEFAULT NULL,
  `unitconum` varchar(15) DEFAULT NULL,
  `unitlicnum` varchar(12) DEFAULT NULL,
  `unitlicstate` varchar(2) DEFAULT NULL,
  `unitvin` varchar(17) DEFAULT NULL,
  `unitgvwr` int(11) DEFAULT NULL,
  `cvsadecal` varchar(1) DEFAULT NULL,
  `decalnum` varchar(8) DEFAULT NULL,
  `oosnum` varchar(10) DEFAULT NULL,
  `existingdecalnum` varchar(8) DEFAULT NULL,
  `existingdecalstatus` varchar(1) DEFAULT NULL,
  `cargosealremovedid` varchar(25) DEFAULT NULL,
  `cargosealreplacedid` varchar(25) DEFAULT NULL,
  `iepusdotnum` varchar(8) DEFAULT NULL,
  `iepname` varchar(120) DEFAULT NULL,
  `iepchassispool` varchar(25) DEFAULT NULL,
  `iepdatasource` varchar(3) DEFAULT NULL,
  `ieplookuptimestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`unitnum`,`rptnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES ('IL1234000001','1','BU','ABCC','2014',NULL,'TEMP','IL','123456',0,'N',NULL,NULL,NULL,'N',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendor`
--

DROP TABLE IF EXISTS `vendor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendor` (
  `VENDOR_NAME` varchar(45) DEFAULT NULL,
  `VENDOR_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendor`
--

LOCK TABLES `vendor` WRITE;
/*!40000 ALTER TABLE `vendor` DISABLE KEYS */;
INSERT INTO `vendor` VALUES ('VendorTest',1234),('VendorTest2',2234),('VendorTest3',3234),('IIS',1111),('M-T',2222),('PREPASS',3333),('IRD',4444);
/*!40000 ALTER TABLE `vendor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weight_report`
--

DROP TABLE IF EXISTS `weight_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weight_report` (
  `site_id` int(11) NOT NULL,
  `scale_type` varchar(1) NOT NULL,
  `timestamp` datetime NOT NULL,
  `sequence_number` int(11) NOT NULL,
  `axle_count` int(11) DEFAULT '0',
  `gross_weight` int(11) NOT NULL,
  `mass_unit` varchar(1) NOT NULL,
  `status` varchar(1) NOT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Test table for Metler-Toledo API';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weight_report`
--

LOCK TABLES `weight_report` WRITE;
/*!40000 ALTER TABLE `weight_report` DISABLE KEYS */;
INSERT INTO `weight_report` VALUES (2,'W','2015-09-24 11:07:11',0,6,82000,'P','F','The truck failed the weight test.','2015-09-24 11:07:11'),(2,'S','2015-09-24 11:07:15',0,6,82000,'P','F','The truck failed the weight test.','2015-09-24 11:07:15'),(2,'W','2015-09-24 11:07:19',1,6,81000,'P','F','The truck failed the weight test.','2015-09-24 11:07:19'),(2,'S','2015-09-24 11:07:24',1,6,81000,'P','F','The truck failed the weight test.','2015-09-24 11:07:24'),(2,'W','2015-09-24 11:07:25',2,6,94000,'P','F','The truck failed the weight test.','2015-09-24 11:07:25'),(2,'S','2015-09-24 11:07:28',2,6,94000,'P','F','The truck failed the weight test.','2015-09-24 11:07:28'),(2,'W','2015-09-24 11:07:32',3,6,88000,'P','F','The truck failed the weight test.','2015-09-24 11:07:32'),(2,'S','2015-09-24 11:07:37',3,6,88000,'P','F','The truck failed the weight test.','2015-09-24 11:07:37'),(2,'W','2015-09-24 11:07:38',4,6,66000,'P','P','The truck passed the weight test.','2015-09-24 11:07:38'),(2,'W','2015-09-24 11:07:42',5,6,79000,'P','P','Passed the Scale Check.','2015-09-24 11:07:42'),(2,'W','2015-09-24 11:07:50',6,6,71000,'P','P','The truck passed the weight test.','2015-09-24 11:07:50'),(2,'W','2015-09-24 11:07:55',7,6,76000,'P','P','The truck passed the weight test.','2015-09-24 11:07:55'),(2,'W','2015-09-24 11:07:59',8,6,88000,'P','F','Failed the Scale Check.','2015-09-24 11:07:59'),(2,'S','2015-09-24 11:08:02',8,6,88000,'P','F','Failed the Scale Check.','2015-09-24 11:08:02'),(2,'W','2015-09-24 11:08:03',9,6,69000,'P','P','The truck passed the weight test.','2015-09-24 11:08:03');
/*!40000 ALTER TABLE `weight_report` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-30  9:39:34
