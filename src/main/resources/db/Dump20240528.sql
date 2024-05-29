-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: electronic_db
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `unique_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `last_access` datetime DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`unique_id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `username` (`username`),
  KEY `fk_account_user_1` (`unique_id`),
  CONSTRAINT `fk_account_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'phu','$2a$10$VbGUM8Z2CjKOXbBJ9HLMg.z7vu6IusJknVjJR06DModLFTRwuYv5O','2023-04-08 23:12:58',NULL,_binary '',NULL,1),(14,'nhatabc','$2a$10$fDVM77ls5JU5xysMPh9ubOjmKuXuuKI6zUC/3balaojIHJyGhv7f.',NULL,NULL,_binary '',NULL,25),(15,'long','$2a$09$3v/9yLYrM6t.OinpvQm.A.BlRWHD7pR/P88mT9tRkezxak6NZwt7S',NULL,NULL,_binary '',NULL,26),(16,'hoang','$2a$10$cxZlMtX5U.baKcF5q7Fw2.WJx45nhXv26HFlLYwFCeTp76YC4toyq',NULL,NULL,_binary '',NULL,37),(17,'hieuhoang','$2a$10$h1LHtDrHMZF5PBe6FNqtiu4THeoR2KsFZmQF8k2NFO75nZvOEykfy',NULL,NULL,_binary '',NULL,40),(18,'tainguyen','$2a$10$FwX4VoxMcEDH5VXAkVRHauI3.q08LUEo3UBfZnWFiEKf5F/2qx3ea',NULL,NULL,_binary '',NULL,44),(19,'wolade','$2a$10$UeJFcONX/5j2TQuRLWsfFOKgMU5tHvTctfOyKSdXIyRM0e0wWv5Ai',NULL,NULL,_binary '',NULL,45),(20,'hoanglong','$2a$09$s1KgAZYspU.ycOLKu.HidO4aY0mDZ0ZjU1KZn/h/3eq.rqcFXBuSq',NULL,NULL,_binary '',NULL,46),(22,'tungtd203642','$2a$10$nPtubRAxLqcPHZhguTJRueZdQdJ8OjxS9C.rkSA218nYZ4kQgwa/i',NULL,NULL,_binary '',NULL,48);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `wards` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `district` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `address_line` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `province` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `postal_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `is_default` bit(1) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_address_1` (`user_id`),
  CONSTRAINT `fk_user_address_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Phường Tân Chánh Hiệp','Quận 12','đường 12','Thành phố Hồ Chí Minh','00000000',_binary '',1),(2,'Tân chánh hiệp','Quận 12','đường','Hồ Chí Minh','00000000',_binary '',26),(21,'Phường 02','Quận 11','123A Nguyễn Thiện Thuật','Thành phố Hồ Chí Minh','457726',_binary '',25),(22,'Xã Hưng Mỹ','Huyện Cái Nước','12/12/90 Phạm Ngũ Lão','Tỉnh Cà Mau','814771',_binary '\0',25),(25,'Phường Duyên Hải','Thành phố Lào Cai','123','Tỉnh Lào Cai','616654',_binary '',44),(32,'Phường Phạm Ngũ Lão','Quận 1','12A Đa Kao','Thành phố Hồ Chí Minh','300536',_binary '',45),(33,'Phường Cống Vị','Quận Ba Đình','A3 ','Thành phố Hà Nội','547262',_binary '',41),(34,'Tan Chanh Hiep','Quan 12','57c dong bac','TP Ho Chi Minh',NULL,_binary '',46);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admintable`
--

DROP TABLE IF EXISTS `admintable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admintable` (
  `admin_id` varchar(512) NOT NULL,
  `first_name` varchar(512) DEFAULT NULL,
  `last_name` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admintable`
--

LOCK TABLES `admintable` WRITE;
/*!40000 ALTER TABLE `admintable` DISABLE KEYS */;
INSERT INTO `admintable` VALUES ('AID01','Gabriel','Holmes'),('AID02','Wyatt','Rojas'),('AID03','Eva','Coleman'),('AID04','Dominic','Clark'),('AID05','Lucy','Alexander'),('AID06','Everleigh','Washington'),('AID07','Leilani','Butler');
/*!40000 ALTER TABLE `admintable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authority` (
  `unique_id` int NOT NULL AUTO_INCREMENT,
  `role_id` int DEFAULT NULL,
  `account_id` int DEFAULT NULL,
  PRIMARY KEY (`unique_id`),
  KEY `fk_authority_account_1` (`account_id`),
  KEY `fk_authority_role_1` (`role_id`),
  CONSTRAINT `fk_authority_account_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`unique_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_authority_role_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`unique_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` VALUES (2,1,1),(12,1,14),(14,2,15),(15,2,16),(16,1,17),(17,1,18),(19,2,19),(20,2,20),(21,2,22);
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand` (
  `id` int NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'Wellmed',NULL,NULL,NULL),(2,'Scian',NULL,NULL,NULL),(5,'General Life Biotechnology',NULL,NULL,NULL),(77,'MediSmart',NULL,NULL,NULL),(78,'Philips',NULL,NULL,NULL),(79,'FarmaCell',NULL,NULL,NULL),(82,'SnowBear',NULL,NULL,NULL);
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `price_sum` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  KEY `fk_user_cart_1` (`user_id`),
  CONSTRAINT `fk_user_cart_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,1,'2023-04-09 15:51:25',281180000),(2,26,'2023-04-09 19:38:45',0),(6,28,'2023-04-10 14:41:46',44280000),(7,29,'2023-04-11 13:53:28',45980000),(10,32,'2023-04-12 16:18:27',82990000),(12,25,'2023-04-12 16:52:53',23890000),(13,35,'2023-04-14 16:02:55',0),(14,36,'2023-04-15 13:41:26',15000000),(15,37,'2023-04-15 13:43:30',0),(16,38,'2023-04-15 14:25:49',0),(17,39,'2023-04-15 14:53:46',8790000),(18,40,'2023-04-15 16:10:27',25000000),(19,41,'2023-04-16 16:35:50',34000000),(20,42,'2023-04-18 10:03:10',0),(21,43,'2023-04-19 19:19:29',0),(22,44,'2023-04-21 16:42:57',0),(23,45,'2023-04-23 18:59:42',32380000),(24,46,'2023-04-27 09:32:43',27990000),(25,47,'2023-06-26 22:54:02',0),(26,48,'2023-06-28 22:05:10',5955000);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_detail`
--

DROP TABLE IF EXISTS `cart_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cart_id` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `product_variant_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cart_detail_product_variant_1` (`product_variant_id`),
  KEY `fk_cart_detail_cart_1` (`cart_id`),
  CONSTRAINT `fk_cart_detail_cart_1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_cart_detail_product_variant_1` FOREIGN KEY (`product_variant_id`) REFERENCES `product_variant` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=393 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_detail`
--

LOCK TABLES `cart_detail` WRITE;
/*!40000 ALTER TABLE `cart_detail` DISABLE KEYS */;
INSERT INTO `cart_detail` VALUES (45,6,1,'2023-04-10 14:41:48',59),(46,6,1,'2023-04-10 14:41:48',57),(47,7,2,'2023-04-11 15:44:01',65),(68,10,1,'2023-04-12 16:18:37',65),(139,10,4,'2023-04-15 16:01:04',58),(141,18,1,'2023-04-15 16:18:34',73),(156,17,1,'2023-04-16 07:22:38',67),(243,14,1,'2023-04-22 20:36:14',58),(352,23,1,'2023-04-24 14:18:46',63),(356,23,1,'2023-04-24 18:46:40',57),(359,1,3,'2023-04-24 21:25:11',76),(360,19,1,'2023-04-24 21:25:13',68),(366,1,5,'2023-04-24 22:03:35',58),(368,1,5,'2023-04-24 22:03:52',62),(369,1,5,'2023-04-24 22:03:59',66),(372,1,1,'2023-04-24 22:04:13',57),(373,1,1,'2023-04-24 22:04:22',78),(375,12,0,'2023-04-24 22:26:02',55),(383,12,1,'2023-04-25 19:12:56',57),(384,24,1,'2023-05-22 08:45:26',61),(392,26,3,'2023-06-29 08:57:57',99);
/*!40000 ALTER TABLE `cart_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `parent_id` int DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category_category_1` (`parent_id`),
  CONSTRAINT `fk_category_category_1` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,' Y tế gia đình',NULL,NULL,'2023-06-26 23:10:33'),(2,'Y tế chuyên dụng',NULL,NULL,'2023-06-26 23:12:35'),(3,'Thiết bị gia đình',NULL,NULL,'2023-06-27 23:35:11'),(4,'MÁY ĐO HUYẾT ÁP',1,NULL,'2023-06-26 22:45:44'),(6,'MÁY ĐO ĐƯỜNG HUYẾT',1,NULL,'2023-06-26 22:46:33'),(7,'MÁY TẠO OXY Y TẾ',2,NULL,'2023-06-26 23:15:16'),(8,'MÁY TRỢ THỞ',2,NULL,'2023-06-26 23:14:52'),(36,'Máy - Bình lọc nước',3,NULL,'2023-06-27 23:43:19'),(39,'Bàn chải điện',3,NULL,'2023-06-27 23:42:54'),(41,'Máy tăm nước',3,NULL,'2023-06-27 23:42:36'),(42,'Đồ dùng mẹ và bé ',NULL,NULL,'2023-06-27 23:35:26'),(45,'Đai đỡ bụng bầu',42,NULL,'2023-06-27 23:43:47');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classtable`
--

DROP TABLE IF EXISTS `classtable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classtable` (
  `class_id` varchar(512) NOT NULL,
  `size` int DEFAULT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classtable`
--

LOCK TABLES `classtable` WRITE;
/*!40000 ALTER TABLE `classtable` DISABLE KEYS */;
INSERT INTO `classtable` VALUES ('CID01',20),('CID02',15),('CID03',15),('CID04',16),('CID05',20),('CID06',24),('CID07',15),('CID08',20),('CID09',30),('CID10',25);
/*!40000 ALTER TABLE `classtable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `color`
--

DROP TABLE IF EXISTS `color`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `color` (
  `id` int NOT NULL AUTO_INCREMENT,
  `color_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `color_code` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `color`
--

LOCK TABLES `color` WRITE;
/*!40000 ALTER TABLE `color` DISABLE KEYS */;
INSERT INTO `color` VALUES (1,'Màu đen','#000'),(2,'Trắng','#fff'),(3,'Xanh','#00ff00'),(4,'Đỏ','#ff0000'),(5,'Vàng','#'),(6,'Cam','#'),(7,'Xanh lá','#'),(8,'Tím','#'),(9,'Hồng','#'),(10,'Bạc','#');
/*!40000 ALTER TABLE `color` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`next_val`)
) ENGINE=InnoDB AUTO_INCREMENT=1538 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1537);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mainteachertable`
--

DROP TABLE IF EXISTS `mainteachertable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mainteachertable` (
  `teacher_id` varchar(512) NOT NULL,
  `salary` int DEFAULT NULL,
  `contract_expire` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`teacher_id`),
  CONSTRAINT `mainteachertable_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teachertable` (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mainteachertable`
--

LOCK TABLES `mainteachertable` WRITE;
/*!40000 ALTER TABLE `mainteachertable` DISABLE KEYS */;
INSERT INTO `mainteachertable` VALUES ('MT01',2000,'2028-11-23'),('MT02',2500,'2030-09-21'),('MT03',2300,'2026-12-25'),('MT04',4000,'2033-05-01'),('MT05',3000,'2028-02-16'),('MT06',3100,'2028-03-24'),('MT07',1800,'2030-11-15'),('MT08',2500,'2029-10-21');
/*!40000 ALTER TABLE `mainteachertable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `id` int NOT NULL AUTO_INCREMENT,
  `heading` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `subtitle` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,'Thông báo đơn hàng','Khách hàng Truong Hoang Long đã đặt hàng!','order','Số lượng sản phẩm: 1','2023-06-28 00:27:33'),(2,'Thông báo đơn hàng','Khách hàng Truong Hoang Long đã đặt hàng!','order','Số lượng sản phẩm: 1','2023-06-28 14:17:55'),(3,'Thông báo đơn hàng','Khách hàng Truong Hoang Long đã đặt hàng!','order','Số lượng sản phẩm: 1','2023-06-28 14:17:56'),(4,'Thông báo đơn hàng','Khách hàng Truong Hoang Long đã đặt hàng!','order','Số lượng sản phẩm: 1','2023-06-28 14:19:43'),(5,'Thông báo đơn hàng','Khách hàng Trần Đức Tùng đã đặt hàng!','order','Số lượng sản phẩm: 1','2023-06-28 22:07:25'),(6,'Thông báo đơn hàng','Khách hàng Trần Đức Tùng đã đặt hàng!','order','Số lượng sản phẩm: 1','2023-06-28 22:08:46'),(7,'Thông báo đơn hàng','Khách hàng Trần Đức Tùng đã đặt hàng!','order','Số lượng sản phẩm: 1','2023-06-29 00:23:41'),(8,'Thông báo đơn hàng','Khách hàng Trần Đức Tùng đã đặt hàng!','order','Số lượng sản phẩm: 1','2023-06-29 00:25:49');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opt_register`
--

DROP TABLE IF EXISTS `opt_register`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `opt_register` (
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `full_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `otp_code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `time_expire` datetime DEFAULT NULL,
  `is_verified` bit(1) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opt_register`
--

LOCK TABLES `opt_register` WRITE;
/*!40000 ALTER TABLE `opt_register` DISABLE KEYS */;
INSERT INTO `opt_register` VALUES ('123','$2a$10$wxS4H.pGQ1jwwDKlEHZFFez.7OaqY.QurXBQhrd02ZBxGklHPPaTe','nguyenquoctai872@gmail.com','Nguyễn Quốc Tài ','123','3213','2023-04-13 10:58:58',_binary '\0'),('Test','$2a$10$kB5IHCCbFASSlvgmh1Q4ZeH52HF2b98YO7hAtnF3uafX4F6J0/20.','testguestagain@gmail.com','Test','','7319','2023-04-10 14:46:18',_binary '\0'),('admin','$2a$10$bXWG6Pk1fEyAfie82CM3T.8aQipzNSCgn4nXAzfHQ20qu9FOhMnue','admin@gmail.com','Truong Hoang Long','0969777741','7652','2023-04-22 13:53:48',_binary '\0'),('anh123','$2a$10$ODOak.4ryNWQqhu42CkSK.46Wpr.SRDodhBEULsO7Al8k.AXlcg6a','lefoca5370@snowlash.com','Nguyễn Văn A','0981623789','6360','2023-04-15 03:58:03',_binary '\0'),('ebonik','$2a$10$OdqZR5DVpNtaYxQ5kqoozebJ37SchZlpCe7VPvlmvBnXiInteA9vW','ebonikshope@gmail.com','ebonik','0394675679','1931','2023-04-15 07:13:10',_binary '\0'),('hieu2510','$2a$10$hz/md.d9B8kcxCvc0uWFEu9KKX2/i59JQc6xnWNuxUgp5iKSciPgu','hieuhvps19146@fpt.edu.vn','Hiếu','776274144','4863','2023-04-09 13:07:19',_binary '\0'),('hieuadmin','$2a$10$3O8XABuCK17cTTca7.3GCu/1t8ZFDBDIPt.owz/eNmTtRLe43dKC6','hieuhvps19146@fpt.edu.vn','Hoàng Văn Hiếu','0776274144','9924','2023-04-08 14:44:19',_binary '\0'),('string','$2a$10$VJlMmh/nROvhKX.pytRf8evggBdHNK3XV5vHvjDFfWkzLGTiK7NAq','synhatphu2@gmail.com','string','0344963174','2000','2023-04-14 22:47:05',_binary '\0'),('user1','$2a$10$.nONdRp5kmD8wc/YgF6aQeL3EkIF2ahL7te4/ANO8rb2vwvJH1eAO','hieuhoang25102001td@gmail.com','Hiếu','776274144','1265','2023-04-11 01:10:42',_binary '\0'),('vongadmin','$2a$10$oR47K4q.wUNug1s6aP98QOGjfFznLZeMJn9cvdED10naSlx4AfOw.','vong.huynh@aegona.com','vanvong','0987132367','5047','2023-04-08 11:55:37',_binary '\0'),('vonghuynh','$2a$10$bnLAzOY9KEd3eM7x9OjS.exfBQBIu7ZvtALpuoVWwohGNGWDoC1aa','huynhvanvong2002@gmail.com','huynh van vong','0987132367','9637','2023-04-15 03:56:10',_binary '\0');
/*!40000 ALTER TABLE `opt_register` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int DEFAULT NULL,
  `product_variant_id` int DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `price_sum` double DEFAULT NULL,
  `promotion_value` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_detail_orders_1` (`order_id`),
  KEY `fk_order_detail_product_variant_1` (`product_variant_id`),
  CONSTRAINT `fk_order_detail_orders_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_order_detail_product_variant_1` FOREIGN KEY (`product_variant_id`) REFERENCES `product_variant` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (42,23,66,'2023-03-13 00:17:29',129900000,6495000,1),(43,24,67,'2023-03-12 17:18:36',8790000,1758000,1),(44,25,68,'2023-04-13 13:54:38',34000000,3400000,1),(45,26,66,'2023-04-13 13:56:50',12990000,6495000,1),(46,27,66,'2023-04-13 23:23:22',12990000,6495000,1),(47,27,56,'2023-04-13 23:23:22',23890000,11945000,1),(48,27,57,'2023-04-13 23:23:22',23890000,11945000,1),(49,28,65,'2023-04-14 14:38:22',229900000,0,1),(50,29,65,'2023-04-14 14:39:42',22990000,0,1),(51,30,57,'2023-04-14 14:43:06',23890000,0,1),(52,31,67,'2023-04-14 14:47:52',8790000,0,1),(53,32,59,'2023-04-14 14:49:13',20390000,0,1),(55,34,67,'2023-04-14 14:52:05',8790000,0,1),(56,35,59,'2023-04-14 14:53:18',20390000,0,1),(57,36,66,'2023-04-14 14:54:52',12990000,0,1),(58,37,58,'2023-04-14 14:59:44',15000000,0,1),(59,38,70,'2023-04-14 15:15:18',29900000,0,1),(60,39,67,'2023-04-14 15:18:26',8790000,0,1),(61,40,57,'2023-04-14 15:18:51',23890000,0,1),(62,41,68,'2023-04-14 15:19:10',34000000,0,1),(63,42,66,'2023-04-14 15:25:19',12990000,0,1),(64,43,65,'2023-04-14 16:44:01',22990000,2299000,1),(65,44,57,'2023-04-15 04:15:21',23890000,4778000,1),(66,44,56,'2023-04-15 04:15:21',23890000,4778000,1),(67,45,68,'2023-04-15 04:20:34',68000000,5100000,2),(68,45,59,'2023-04-15 04:20:34',40780000,3058500,2),(69,46,66,'2023-04-15 04:33:18',12990000,1948500,1),(70,47,67,'2023-04-15 14:29:25',8790000,1318500,1),(71,47,63,'2023-04-15 14:29:25',16980000,1273500,2),(72,48,63,'2023-04-15 14:29:26',16980000,1273500,2),(73,48,67,'2023-04-15 14:29:26',8790000,1318500,1),(74,49,65,'2023-04-15 14:37:46',22990000,3448500,1),(75,49,67,'2023-04-15 14:37:46',8790000,1318500,1),(76,50,62,'2023-04-15 14:41:50',8490000,1273500,1),(77,50,71,'2023-04-15 14:41:50',12090000,1813500,1),(78,51,59,'2023-04-15 14:46:39',20390000,3058500,1),(79,52,62,'2023-04-15 14:47:14',8490000,1273500,1),(80,53,65,'2023-04-15 14:56:03',22990000,3448500,1),(81,53,71,'2023-04-15 14:56:03',12090000,1813500,1),(82,54,68,'2023-04-15 14:56:18',34000000,5100000,1),(83,55,63,'2023-04-15 14:58:39',8490000,1273500,1),(84,55,67,'2023-04-15 14:58:39',8790000,1318500,1),(85,55,71,'2023-04-15 14:58:39',12090000,1813500,1),(86,56,68,'2023-04-15 15:07:34',68000000,5100000,2),(87,57,66,'2023-04-15 15:09:45',25980000,1948500,2),(88,58,67,'2023-04-15 15:13:06',8790000,1318500,1),(89,59,61,'2023-04-15 15:18:28',27990000,4198500,1),(90,60,59,'2023-04-15 15:20:38',20390000,3058500,1),(91,61,59,'2023-04-15 15:21:19',20390000,3058500,1),(92,62,67,'2023-04-15 15:22:33',8790000,1318500,1),(93,63,70,'2023-04-15 16:11:20',89700000,5980000,3),(94,64,72,'2023-04-15 16:19:06',19900000,3980000,1),(95,65,71,'2023-04-16 04:11:42',12090000,1813500,1),(96,65,59,'2023-04-16 04:11:42',20390000,3058500,1),(97,66,67,'2023-04-16 11:22:57',8790000,1318500,1),(98,66,68,'2023-04-16 11:22:58',34000000,5100000,1),(99,67,59,'2023-04-16 11:26:04',20390000,3058500,1),(100,68,66,'2023-04-16 11:35:16',12990000,1948500,1),(101,69,72,'2023-04-16 11:44:34',19900000,3980000,1),(102,70,57,'2023-04-16 11:51:51',23890000,4778000,1),(103,71,66,'2023-04-16 11:59:05',12990000,1948500,1),(104,72,67,'2023-04-16 12:50:22',8790000,1318500,1),(105,72,59,'2023-04-16 12:50:22',20390000,3058500,1),(106,73,72,'2023-04-16 12:52:25',19900000,3980000,1),(107,73,59,'2023-04-16 12:52:25',20390000,3058500,1),(108,73,71,'2023-04-16 12:52:25',12090000,1813500,1),(109,74,71,'2023-04-17 08:36:18',12090000,1209000,1),(110,75,58,'2023-04-19 14:48:10',15000000,3000000,1),(111,76,67,'2023-04-19 14:49:09',26370000,0,3),(112,77,67,'2023-04-20 15:56:01',17580000,1758000,2),(113,78,58,'2023-04-20 16:23:27',15000000,3000000,1),(114,79,68,'2023-04-21 10:24:36',34000000,6800000,1),(115,80,65,'2023-04-21 10:56:38',22990000,4598000,1),(116,80,72,'2023-04-21 10:56:38',19900000,3980000,1),(117,80,55,'2023-04-21 10:56:38',29900000,5980000,1),(118,81,59,'2023-04-21 18:46:40',20390000,4078000,1),(119,82,68,'2023-04-21 18:47:35',34000000,6800000,1),(120,83,57,'2023-04-21 18:47:54',23890000,4778000,1),(121,84,62,'2023-04-21 18:49:29',8490000,1698000,1),(122,85,71,'2023-04-21 19:32:06',12090000,2418000,1),(123,86,68,'2023-04-21 19:35:36',34000000,6800000,1),(124,87,71,'2023-04-21 20:25:39',12090000,2418000,1),(125,88,72,'2023-04-22 18:40:03',19900000,3980000,1),(126,89,62,'2023-04-22 18:45:33',8490000,1698000,1),(127,90,59,'2023-04-22 22:14:31',40780000,4078000,2),(128,90,67,'2023-04-22 22:14:31',8790000,1758000,1),(129,90,72,'2023-04-22 22:14:31',19900000,3980000,1),(130,90,68,'2023-04-22 22:14:31',102000000,6800000,3),(131,91,71,'2023-04-22 22:34:33',12090000,2418000,1),(132,91,65,'2023-04-22 22:34:33',45980000,4598000,2),(133,92,58,'2023-04-22 23:57:10',15000000,3000000,1),(134,93,76,'2023-04-22 23:57:46',16800000,0,1),(135,94,76,'2023-04-23 00:07:08',16800000,0,1),(136,94,68,'2023-04-23 00:07:08',34000000,6800000,1),(137,95,67,'2023-04-23 00:07:39',8790000,0,1),(138,96,66,'2023-04-23 00:08:28',12990000,2598000,1),(139,97,76,'2023-04-23 00:24:00',16800000,0,1),(140,98,57,'2023-04-23 00:24:31',23890000,4778000,1),(141,99,57,'2023-04-23 00:24:56',23890000,4778000,1),(142,100,58,'2023-04-23 00:31:52',15000000,3000000,1),(143,101,62,'2023-04-23 00:32:17',8490000,1698000,1),(144,102,76,'2023-04-23 10:28:22',16800000,0,1),(145,103,57,'2023-04-23 10:29:30',23890000,4778000,1),(146,104,57,'2023-04-23 11:28:27',23890000,4778000,1),(147,105,57,'2023-04-23 12:29:04',47780000,4778000,2),(148,106,60,'2023-04-23 22:50:48',22990000,0,1),(149,106,66,'2023-04-23 22:50:48',64950000,2598000,5),(150,106,61,'2023-04-23 22:50:48',139950000,0,5),(151,107,62,'2023-04-23 22:52:20',8490000,1698000,1),(152,108,72,'2023-04-23 23:05:52',19900000,0,1),(153,108,71,'2023-04-23 23:05:52',24180000,0,2),(154,109,76,'2023-04-23 23:08:01',16800000,0,1),(155,110,58,'2023-04-23 23:08:03',15000000,3000000,1),(156,111,68,'2023-04-23 23:47:41',34000000,6800000,1),(157,112,68,'2023-04-24 21:21:23',68000000,6800000,2),(158,112,57,'2023-04-24 21:21:23',119450000,4778000,5),(159,113,63,'2023-04-24 21:35:49',16980000,1698000,2),(160,113,67,'2023-04-24 21:35:49',17580000,0,2),(161,113,71,'2023-04-24 21:35:49',24180000,0,2),(162,114,58,'2023-04-24 21:36:33',15000000,3000000,1),(163,114,58,'2023-04-24 21:36:33',15000000,3000000,1),(164,115,57,'2023-04-24 22:37:05',119450000,4778000,5),(165,115,63,'2023-04-24 22:37:05',8490000,1698000,1),(166,115,64,'2023-04-24 22:37:05',8490000,1698000,1),(167,116,57,'2023-04-24 22:42:14',23890000,4778000,1),(168,116,82,'2023-04-24 22:42:14',14980000,0,2),(169,116,56,'2023-04-24 22:42:14',47780000,4778000,2),(170,116,83,'2023-04-24 22:42:14',11990000,0,1),(171,117,58,'2023-04-25 13:29:23',15000000,0,1),(172,117,66,'2023-04-25 13:29:23',12990000,0,1),(173,118,58,'2023-04-25 13:30:22',75000000,0,5),(174,118,67,'2023-04-25 13:30:22',26370000,0,3),(175,119,68,'2023-04-25 13:31:19',170000000,0,5),(176,120,94,'2023-06-28 00:27:33',8250000,0,5),(177,121,94,'2023-06-28 14:17:52',8250000,0,5),(178,122,94,'2023-06-28 14:17:56',8250000,0,5),(179,123,97,'2023-06-28 14:19:43',4450000,0,5),(180,124,108,'2023-06-28 22:07:25',98000000,0,4),(181,125,108,'2023-06-28 22:08:46',98000000,0,4),(182,126,104,'2023-06-29 00:23:41',5450000,0,5),(183,127,98,'2023-06-29 00:25:49',1250000,0,5);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_status`
--

DROP TABLE IF EXISTS `order_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(55) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `title` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_status`
--

LOCK TABLES `order_status` WRITE;
/*!40000 ALTER TABLE `order_status` DISABLE KEYS */;
INSERT INTO `order_status` VALUES (1,'Chờ xác nhận','Chờ người bán xác nhận đơn hàng'),(2,'Đang giao','Đang giao hàng'),(3,'Hoàn thành','Đơn hàng đã được giao thành công'),(4,'Đã hủy','Đã hủy bởi bạn');
/*!40000 ALTER TABLE `order_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `is_pay` bit(1) DEFAULT NULL,
  `payment_id` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `is_cancelled` bit(1) DEFAULT NULL,
  `promotion_id` int DEFAULT NULL,
  `district` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `address_line` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `province` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `postal_id` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_orders_promotion_user_1` (`promotion_id`),
  KEY `fk_orders_order_status_1` (`status`),
  KEY `fk_orders_payment_method_1` (`payment_id`),
  CONSTRAINT `fk_orders_order_status_1` FOREIGN KEY (`status`) REFERENCES `order_status` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_orders_payment_method_1` FOREIGN KEY (`payment_id`) REFERENCES `payment_method` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_orders_promotion_user_1` FOREIGN KEY (`promotion_id`) REFERENCES `promotion_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (23,25,'2023-03-12 00:17:29',_binary '',2,2,NULL,NULL,'string','string','string','string'),(24,1,'2023-03-15 17:18:36',_binary '\0',3,4,NULL,2,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(25,1,'2023-03-17 13:54:38',_binary '',3,3,NULL,NULL,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(26,1,'2023-03-16 13:56:50',_binary '',3,3,NULL,NULL,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(27,1,'2023-03-20 23:23:22',_binary '\0',3,4,NULL,2,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(28,1,'2023-03-07 14:38:22',_binary '\0',3,4,NULL,NULL,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(29,1,'2023-03-14 14:39:42',_binary '\0',3,4,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'161294'),(30,1,'2023-03-14 14:43:06',_binary '\0',3,4,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'598141'),(31,1,'2023-04-14 14:47:52',_binary '\0',3,4,NULL,NULL,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(32,1,'2023-04-14 14:49:13',_binary '',3,3,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'411829'),(33,1,'2023-03-14 14:51:13',_binary '\0',3,4,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'559589'),(34,1,'2023-04-14 14:52:05',_binary '',3,3,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'939053'),(35,1,'2023-04-14 14:53:18',_binary '',3,3,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'602233'),(36,1,'2023-04-14 14:54:52',_binary '',3,3,NULL,NULL,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(37,1,'2023-04-14 14:59:44',_binary '',3,3,NULL,NULL,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(38,1,'2023-04-14 15:15:18',_binary '\0',3,4,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'773065'),(39,1,'2023-04-14 15:18:26',_binary '\0',3,4,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'230897'),(40,1,'2023-04-14 15:18:51',_binary '',3,3,NULL,NULL,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(41,1,'2023-04-14 15:19:10',_binary '',3,3,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'963267'),(42,1,'2023-04-14 15:25:19',_binary '\0',3,4,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'691738'),(43,1,'2023-04-14 16:44:01',_binary '\0',3,4,NULL,2,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(44,1,'2023-04-15 04:15:21',_binary '',3,3,NULL,2,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(45,1,'2023-04-15 04:20:34',_binary '\0',3,4,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'638084'),(46,1,'2023-04-15 04:33:17',_binary '',3,3,NULL,2,NULL,'đường, Tân chánh hiệp',NULL,'142073'),(47,38,'2023-04-15 14:29:25',_binary '',3,3,NULL,NULL,'Thị xã Phước Long','227 TO 3 Khu 5, Phường Long Phước','Tỉnh Bình Phước','276865'),(48,38,'2023-04-15 14:29:26',_binary '',3,3,NULL,NULL,'Thị xã Phước Long','227 TO 3 Khu 5, Phường Long Phước','Tỉnh Bình Phước','276865'),(49,38,'2023-04-15 14:37:46',_binary '',3,3,NULL,NULL,'Thị xã Phước Long','227 TO 3 Khu 5, Phường Long Phước','Tỉnh Bình Phước','697096'),(50,38,'2023-04-15 14:41:50',_binary '',3,3,NULL,NULL,'Thị xã Phước Long','227 TO 3 Khu 5, Phường Long Phước','Tỉnh Bình Phước','579615'),(51,1,'2023-04-15 14:46:39',_binary '\0',3,4,NULL,2,NULL,'đường, Tân chánh hiệp',NULL,'647827'),(52,1,'2023-04-15 14:47:14',_binary '',3,3,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'628354'),(53,39,'2023-04-15 14:56:03',_binary '\0',3,4,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'670685'),(54,39,'2023-04-15 14:56:18',_binary '',3,3,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'851135'),(55,39,'2023-04-15 14:58:39',_binary '',3,3,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'169734'),(56,26,'2023-04-15 15:07:34',_binary '',3,3,NULL,NULL,'Quận 12','57c dong bac , Phường Tân Chánh Hiệp','Thành phố Hồ Chí Minh','366774'),(57,26,'2023-04-15 15:09:45',_binary '',2,3,NULL,NULL,'Quận Ba Đình','123, Phường Trúc Bạch','Thành phố Hà Nội','346199'),(58,26,'2023-04-15 15:13:06',_binary '',3,3,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'835846'),(59,39,'2023-04-15 15:18:28',_binary '\0',3,2,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'371447'),(60,26,'2023-04-15 15:20:38',_binary '\0',3,2,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'861409'),(61,35,'2023-04-15 15:21:19',_binary '\0',3,2,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'359719'),(62,1,'2023-04-15 15:22:33',_binary '',3,3,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'160635'),(63,40,'2023-04-15 16:11:20',_binary '',3,3,NULL,NULL,'Quận Hoàn Kiếm','Xóm 9, Phường Phúc Tân','Thành phố Hà Nội','296549'),(64,26,'2023-04-15 16:19:06',_binary '\0',3,4,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'471134'),(65,39,'2023-04-16 04:11:42',_binary '\0',3,2,NULL,NULL,'Quận Hoàn Kiếm','đường, Tân chánh hiệp','Thành phố Hà Nội','215750'),(66,39,'2023-04-16 11:22:57',_binary '\0',3,2,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'925368'),(67,39,'2023-04-16 11:26:04',_binary '',3,3,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'684999'),(68,39,'2023-04-16 11:35:16',_binary '',3,3,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'137689'),(69,39,'2023-04-16 11:44:34',_binary '\0',3,2,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'379463'),(70,39,'2023-04-16 11:51:51',_binary '\0',3,2,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'683910'),(71,39,'2023-04-16 11:59:05',_binary '',3,3,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'820561'),(72,1,'2023-04-16 12:50:22',_binary '',3,3,NULL,NULL,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(73,39,'2023-04-16 12:52:25',_binary '',3,3,NULL,NULL,NULL,'đường, Tân chánh hiệp',NULL,'905083'),(74,36,'2023-04-17 08:36:18',_binary '\0',3,2,NULL,NULL,'41-TRIAL-Thị xã Phú Thọ 167','34-TRIAL-đường, Tân chánh hiệp 100','269-TRIAL-Tỉnh Phú Thọ 124','78-TRIAL-356757 258'),(75,1,'2023-04-19 14:48:10',_binary '\0',3,4,NULL,NULL,'262-TRIAL-Quận 12 164','5-TRIAL-đường, Tân chánh hiệp 245','181-TRIAL-Hồ Chí Minh 27','61-TRIAL-00000000 191'),(76,1,'2023-04-19 14:49:09',_binary '\0',3,4,NULL,NULL,'295-TRIAL-Quận 12 242','27-TRIAL-đường, Tân chánh hiệp 36','291-TRIAL-Hồ Chí Minh 204','2-TRIAL-00000000 153'),(77,26,'2023-04-20 15:56:01',_binary '',3,3,NULL,NULL,'292-TRIAL- 82','21-TRIAL-đường, Tân chánh hiệp 116','218-TRIAL- 95','47-TRIAL-815937 126'),(78,1,'2023-04-20 16:23:27',_binary '\0',3,4,NULL,NULL,'71-TRIAL-Quận 12 138','69-TRIAL-đường, Tân chánh hiệp 112','167-TRIAL-Hồ Chí Minh 199','235-TRIAL-00000000 294'),(79,1,'2023-04-21 10:24:36',_binary '\0',3,4,NULL,NULL,'203-TRIAL-Quận 12 111','122-TRIAL-đường, Tân chánh hiệp 33','273-TRIAL-Hồ Chí Minh 164','141-TRIAL-00000000 211'),(80,1,'2023-04-21 10:56:38',_binary '\0',3,4,NULL,NULL,'53-TRIAL-Quận Ba Đình 268','47-TRIAL-Tân chánh Hiệp, Phường Phúc Xá 44','262-TRIAL-Thành phố Hà Nội 57','237-TRIAL-390443 259'),(81,44,'2023-04-21 18:46:40',_binary '\0',2,4,NULL,NULL,'23-TRIAL-Huyện Hàm Thuận Nam 141','229-TRIAL-KDL Ta-Kou, Thị trấn Thuận Nam 178','16-TRIAL-Tỉnh Bình Thuận 35','290-TRIAL-101104 42'),(82,44,'2023-04-21 18:47:35',_binary '\0',2,4,NULL,NULL,'288-TRIAL-Thành phố Hà Giang 106','40-TRIAL-28, Phường Quang Trung 242','64-TRIAL-Tỉnh Hà Giang 148','146-TRIAL-379321 105'),(83,44,'2023-04-21 18:47:54',_binary '',2,2,NULL,NULL,'290-TRIAL-Huyện Đồng Văn 129','70-TRIAL-32, Xã Lũng Cú 50','6-TRIAL-Tỉnh Hà Giang 201','93-TRIAL-424385 248'),(84,44,'2023-04-21 18:49:29',_binary '',2,3,NULL,NULL,'129-TRIAL-Quận Ba Đình 23','84-TRIAL-32, Phường Phúc Xá 154','156-TRIAL-Thành phố Hà Nội 140','166-TRIAL-727033 176'),(85,26,'2023-04-21 19:32:06',_binary '\0',3,1,NULL,NULL,'131-TRIAL- 208','144-TRIAL-undefined, undefined 39','26-TRIAL- 223','137-TRIAL-976003 238'),(86,26,'2023-04-21 19:35:36',_binary '\0',3,2,NULL,NULL,'218-TRIAL- 282','129-TRIAL-undefined, undefined 41','33-TRIAL- 215','139-TRIAL-632627 258'),(87,26,'2023-04-21 20:25:39',_binary '\0',3,4,NULL,NULL,'204-TRIAL- 30','177-TRIAL-undefined, undefined 206','173-TRIAL- 186','221-TRIAL-656881 245'),(88,44,'2023-04-22 18:40:03',_binary '\0',2,4,NULL,NULL,'224-TRIAL- 172','270-TRIAL-undefined, undefined 129','77-TRIAL- 273','297-TRIAL-530833 12'),(89,44,'2023-04-22 18:45:33',_binary '\0',2,4,NULL,NULL,'286-TRIAL- 90','161-TRIAL-undefined, undefined 36','155-TRIAL- 167','255-TRIAL-126815 274'),(90,25,'2023-04-22 22:14:31',_binary '',1,3,NULL,NULL,'131-TRIAL-Huyện Cái Nước 52','50-TRIAL-12/12/90 Phạm Ngũ Lão, Xã Hưng Mỹ 250','141-TRIAL-Tỉnh Cà Mau 124','166-TRIAL-814771 130'),(91,44,'2023-04-22 22:34:33',_binary '\0',2,4,NULL,NULL,'207-TRIAL-Thành phố Hà Giang 191','7-TRIAL-KM 12345, Phường Quang Trung 237','157-TRIAL-Tỉnh Hà Giang 287','153-TRIAL-922046 183'),(92,44,'2023-04-22 23:57:10',_binary '\0',2,4,NULL,NULL,'245-TRIAL-Thành phố Lào Cai 209','109-TRIAL-123, Phường Duyên Hải 158','221-TRIAL-Tỉnh Lào Cai 288','122-TRIAL-616654 46'),(93,44,'2023-04-22 23:57:46',_binary '\0',2,4,NULL,NULL,'206-TRIAL- 130','213-TRIAL-undefined, undefined 68','0-TRIAL- 191','162-TRIAL-364343 155'),(94,44,'2023-04-23 00:07:08',_binary '\0',2,4,NULL,NULL,'10-TRIAL-Thành phố Lào Cai 59','24-TRIAL-123, Phường Duyên Hải 137','248-TRIAL-Tỉnh Lào Cai 183','295-TRIAL-616654 141'),(95,44,'2023-04-23 00:07:39',_binary '\0',2,4,NULL,NULL,'2-TRIAL- 50','91-TRIAL-,  236','74-TRIAL- 220','96-TRIAL-918549 21'),(96,44,'2023-04-23 00:08:28',_binary '\0',2,4,NULL,NULL,'48-TRIAL- 99','168-TRIAL-,  184','181-TRIAL- 234','53-TRIAL-196282 199'),(97,44,'2023-04-23 00:24:00',_binary '\0',2,4,NULL,NULL,'18-TRIAL-Thành phố Lào Cai 38','0-TRIAL-123, Phường Duyên Hải 188','127-TRIAL-Tỉnh Lào Cai 167','128-TRIAL-616654 193'),(98,44,'2023-04-23 00:24:31',_binary '\0',3,3,NULL,NULL,'48-TRIAL-Thành phố Lào Cai 283','107-TRIAL-123, Phường Duyên Hải 21','210-TRIAL-Tỉnh Lào Cai 17','13-TRIAL-616654 214'),(99,44,'2023-04-23 00:24:56',_binary '',3,3,NULL,NULL,'209-TRIAL-Quận Ba Đình 116','35-TRIAL-123, Phường Phúc Xá 51','200-TRIAL-Thành phố Hà Nội 149','19-TRIAL-992869 56'),(100,44,'2023-04-23 00:31:52',_binary '\0',2,3,NULL,NULL,'298-TRIAL-Thành phố Lào Cai 3','224-TRIAL-123, Phường Duyên Hải 208','144-TRIAL-Tỉnh Lào Cai 209','289-TRIAL-616654 2'),(101,44,'2023-04-23 00:32:17',_binary '\0',3,3,NULL,NULL,'195-TRIAL-Thành phố Lào Cai 85','93-TRIAL-123, Phường Duyên Hải 243','223-TRIAL-Tỉnh Lào Cai 87','214-TRIAL-616654 203'),(102,44,'2023-04-23 10:28:22',_binary '\0',2,3,NULL,NULL,'248-TRIAL-Thành phố Lào Cai 0','258-TRIAL-123, Phường Duyên Hải 18','180-TRIAL-Tỉnh Lào Cai 296','98-TRIAL-616654 281'),(103,44,'2023-04-23 10:29:30',_binary '\0',3,3,NULL,NULL,'89-TRIAL-Thành phố Lào Cai 98','109-TRIAL-123, Phường Duyên Hải 157','72-TRIAL-Tỉnh Lào Cai 222','238-TRIAL-616654 292'),(104,44,'2023-04-23 11:28:27',_binary '',2,3,NULL,NULL,'38-TRIAL-Thành phố Lào Cai 179','190-TRIAL-123, Phường Duyên Hải 257','158-TRIAL-Tỉnh Lào Cai 191','15-TRIAL-616654 88'),(105,44,'2023-04-23 12:29:04',_binary '',2,3,NULL,NULL,'256-TRIAL-Thành phố Lào Cai 111','2-TRIAL-123, Phường Duyên Hải 234','272-TRIAL-Tỉnh Lào Cai 255','228-TRIAL-616654 146'),(106,45,'2023-04-23 22:50:48',_binary '',2,3,NULL,NULL,'262-TRIAL-Huyện Quản Bạ 86','275-TRIAL-12A Hoàng Văn Thụ, Xã Nghĩa Thuận 233','169-TRIAL-Tỉnh Hà Giang 42','144-TRIAL-839895 216'),(107,44,'2023-04-23 22:52:20',_binary '',2,3,NULL,NULL,'281-TRIAL-Thành phố Lào Cai 198','122-TRIAL-123, Phường Duyên Hải 51','121-TRIAL-Tỉnh Lào Cai 299','257-TRIAL-616654 276'),(108,45,'2023-04-23 23:05:52',_binary '\0',3,3,NULL,NULL,'292-TRIAL-Quận 1 89','275-TRIAL-12A Đa Kao, Phường Phạm Ngũ Lão 212','200-TRIAL-Thành phố Hồ Chí Minh 110','3-TRIAL-300536 169'),(109,45,'2023-04-23 23:08:01',_binary '',2,3,NULL,NULL,'161-TRIAL-Quận 1 288','201-TRIAL-12A Đa Kao, Phường Phạm Ngũ Lão 189','255-TRIAL-Thành phố Hồ Chí Minh 223','202-TRIAL-300536 85'),(110,44,'2023-04-23 23:08:03',_binary '',2,3,NULL,NULL,'182-TRIAL-Thành phố Lào Cai 85','88-TRIAL-123, Phường Duyên Hải 226','117-TRIAL-Tỉnh Lào Cai 57','232-TRIAL-616654 32'),(111,44,'2023-04-23 23:47:41',_binary '',2,2,NULL,NULL,'269-TRIAL-Thành phố Lào Cai 54','221-TRIAL-123, Phường Duyên Hải 89','176-TRIAL-Tỉnh Lào Cai 129','268-TRIAL-616654 192'),(112,41,'2023-04-24 21:21:23',_binary '',1,2,NULL,NULL,'125-TRIAL-Quận Ba Đình 55','134-TRIAL-a3 , Phường Trúc Bạch 49','241-TRIAL-Thành phố Hà Nội 212','145-TRIAL-118380 60'),(113,26,'2023-04-24 21:35:49',_binary '\0',3,2,NULL,NULL,'118-TRIAL-Quận 12 153','239-TRIAL-đường, Tân chánh hiệp 123','79-TRIAL-Hồ Chí Minh 196','187-TRIAL-00000000 229'),(114,26,'2023-04-24 21:36:33',_binary '',3,3,NULL,NULL,'49-TRIAL-Quận 12 37','66-TRIAL-đường, Tân chánh hiệp 49','193-TRIAL-Hồ Chí Minh 95','297-TRIAL-00000000 16'),(115,25,'2023-04-24 22:37:05',_binary '',2,3,NULL,31,'86-TRIAL-Quận 11 205','188-TRIAL-123A Nguyễn Thiện Thuật, Phường 02 82','155-TRIAL-Thành phố Hồ Chí Minh 234','114-TRIAL-457726 1'),(116,25,'2023-04-24 22:42:14',_binary '',2,3,NULL,NULL,'116-TRIAL-Quận 11 271','86-TRIAL-123A Nguyễn Thiện Thuật, Phường 02 263','113-TRIAL-Thành phố Hồ Chí Minh 55','285-TRIAL-457726 253'),(117,26,'2023-04-25 13:29:23',_binary '',1,3,NULL,NULL,'12-TRIAL-Quận 12 8','32-TRIAL-đường, Tân chánh hiệp 245','113-TRIAL-Hồ Chí Minh 156','121-TRIAL-00000000 58'),(118,26,'2023-04-26 13:30:22',_binary '',1,3,NULL,NULL,'246-TRIAL-Quận 12 82','181-TRIAL-đường, Tân chánh hiệp 244','96-TRIAL-Hồ Chí Minh 122','229-TRIAL-00000000 61'),(119,26,'2023-04-27 13:31:19',_binary '',3,3,NULL,NULL,'135-TRIAL-Quận 12 50','73-TRIAL-đường, Tân chánh hiệp 266','44-TRIAL-Hồ Chí Minh 59','192-TRIAL-00000000 39'),(120,26,'2023-06-28 00:27:33',_binary '',3,3,NULL,NULL,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(121,26,'2023-06-28 14:17:52',_binary '',3,3,NULL,NULL,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(122,26,'2023-06-28 14:17:56',_binary '',3,3,NULL,NULL,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(123,26,'2023-06-28 14:19:43',_binary '',3,3,NULL,NULL,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(124,48,'2023-06-28 22:07:25',_binary '',3,3,NULL,NULL,'Quận Hoàn Kiếm','nhà 13, Phường Phúc Tân','Thành phố Hà Nội','613172'),(125,48,'2023-06-28 22:08:46',_binary '',3,3,NULL,NULL,'Quận Ba Đình','13, Phường Phúc Xá','Thành phố Hà Nội','644748'),(126,48,'2023-06-29 00:23:41',_binary '',3,3,NULL,NULL,'Quận Ba Đình','14, Phường Phúc Xá','Thành phố Hà Nội','774564'),(127,48,'2023-06-29 00:25:49',_binary '',2,3,NULL,NULL,'Quận Ba Đình','15, Phường Trúc Bạch','Thành phố Hà Nội','922144');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parenttable`
--

DROP TABLE IF EXISTS `parenttable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parenttable` (
  `first_name` varchar(512) DEFAULT NULL,
  `last_name` varchar(512) DEFAULT NULL,
  `relation` varchar(512) DEFAULT NULL,
  `phone_num` varchar(512) NOT NULL,
  `child_id` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`phone_num`),
  KEY `child_id` (`child_id`),
  CONSTRAINT `parenttable_ibfk_1` FOREIGN KEY (`child_id`) REFERENCES `studenttable` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parenttable`
--

LOCK TABLES `parenttable` WRITE;
/*!40000 ALTER TABLE `parenttable` DISABLE KEYS */;
INSERT INTO `parenttable` VALUES ('Mateo','Vu','father','212-989-5686','SID16'),('Eva','Rivera','mother','213-329-6500','SID02'),('Dylan','Choi','father','229-567-6266','SID08'),('Sadie','Gupta','mother','305-815-0820','SID06'),('Logan','Rivera','father','310-433-7085','SID02'),('Dominic','Barnes','mother','336-416-4049','SID07'),('Nora','Brown','mother','336-918-3831','SID01'),('Carson','Lu','father','347-609-7861','SID14'),('Ellen','Vu','mother','480-255-0578','SID05'),('Michael','Lam','father','502-475-6992','SID15'),('Mateo','Jordan','father','585-955-7270','SID04'),('Adeline','Huang','mother','724-652-0729','SID09'),('Jackson','Huang','father','732-360-3907','SID09'),('Leonardo','Sanders','father','757-722-2917','SID03'),('Abigail','Jordan','mother','757-771-7544','SID04'),('Leah','Rogers','mother','908-820-3300','SID12'),('Caroline','Jones','mother','908-966-1780','SID13'),('Ezekiel','Ali','father','912-201-5631','SID11'),('Riley','Walker','mother','917-798-9894','SID10'),('Alan','Barnes','father','917-945-5169','SID07'),('William','Vu','father','928-201-0245','SID05');
/*!40000 ALTER TABLE `parenttable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_method`
--

DROP TABLE IF EXISTS `payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_method` (
  `id` int NOT NULL AUTO_INCREMENT,
  `method` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_method`
--

LOCK TABLES `payment_method` WRITE;
/*!40000 ALTER TABLE `payment_method` DISABLE KEYS */;
INSERT INTO `payment_method` VALUES (1,'VISA_CARD'),(2,'MOMO'),(3,'CASH');
/*!40000 ALTER TABLE `payment_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `practiceteachertable`
--

DROP TABLE IF EXISTS `practiceteachertable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `practiceteachertable` (
  `teacher_id` varchar(512) NOT NULL,
  `starting_date` varchar(512) DEFAULT NULL,
  `ending_date` varchar(512) DEFAULT NULL,
  `salary` int DEFAULT NULL,
  PRIMARY KEY (`teacher_id`),
  CONSTRAINT `practiceteachertable_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teachertable` (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `practiceteachertable`
--

LOCK TABLES `practiceteachertable` WRITE;
/*!40000 ALTER TABLE `practiceteachertable` DISABLE KEYS */;
INSERT INTO `practiceteachertable` VALUES ('PT01','2021-12-05','2023-12-05',800),('PT02','2021-06-23','2024-06-23',1400),('PT03','2022-07-09','2025-07-09',1500),('PT04','2022-12-11','2024-12-11',800),('PT05','2022-11-25','2025-11-05',1000);
/*!40000 ALTER TABLE `practiceteachertable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `description` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `is_delete` bit(1) DEFAULT NULL,
  `brand_id` int DEFAULT NULL,
  `promotion_id` int DEFAULT NULL,
  `type` int DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `productcol` int DEFAULT NULL,
  `sale` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_category_1` (`category_id`),
  KEY `fk_product_brand_1` (`brand_id`),
  KEY `fk_promotion_product_product_1` (`promotion_id`),
  CONSTRAINT `fk_product_brand_1` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_product_category_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_promotion_product_product_1` FOREIGN KEY (`promotion_id`) REFERENCES `promotion_product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (93,'Máy đo Acid Uric (kiểm tra Gout) Benecheck Plus','iPhone 14 Pro Max là mẫu flagship nổi bật nhất của Apple trong lần trở lại năm 2022 với nhiều cải tiến về công nghệ cũng như vẻ ngoài cao cấp, sang chảnh hợp với gu thẩm mỹ đại chúng. Những chiếc điện thoại đến từ nhà Táo Khuyết nhận được rất nhiều sự kỳ vọng của thị trường ngay từ khi chưa ra mắt. Vậy liệu những chiếc flagship đến từ công ty công nghệ hàng đầu thế giới này có làm bạn thất vọng? Cùng khám phá những điều thú vị về iPhone 14 Pro Max ở bài viết dưới đây nhé.','2023-04-08 08:15:15','2023-06-26 23:01:18',2,_binary '\0',2,NULL,NULL,'product-93.png',NULL,NULL),(94,'Máy tăm nước cầm tay ROAMAN Mini 8','Mặc dù đánh răng hai lần một ngày là phương pháp tốt để bảo vệ sức khỏe răng miệng, nhưng có những vùng khó tiếp cận trong miệng mà bạn có thể không làm sạch được bằng cách đánh răng hay sử dụng chỉ nha khoa. Làm sạch kẽ răng, loại bỏ các mảnh thức ăn và mảng bám giữa các kẽ răng là một phần thiết yếu đối với việc chăm sóc sức khỏe răng miệng. Sử dụng bàn chải truyền thống hay chỉ nha khoa không phải là cách duy nhất để làm sạch kẽ răng, hãy tìm đến Máy tăm nước Roaman Mini 8 - một lựa chọn thay thế hoàn hảo để bảo vệ răng miệng của bạn.','2023-04-08 08:18:06','2023-06-29 00:22:27',41,_binary '',1,2,NULL,'product-94.png',NULL,NULL),(95,'Samsung Galaxy S23 Ultra','Samsung Galaxy S23 Ultra là điện thoại cao cấp của hãng điện thoại Samsung được ra mắt vào đầu năm 2023. Điện thoại Samsung S23 series mới này sở hữu camera độ phân giải 200MP ấn tượng cùng một khung viền vuông vức sang trọng. Cấu hình máy cũng là một điểm nổi bật với con chip Snapdragon 8 Gen 2 mạnh mẽ, bộ nhớ RAM 8GB mang lại hiệu suất xử lý vượt trội.','2023-04-08 09:40:55','2023-04-08 09:40:55',6,_binary '',1,NULL,1,'product-95.png',NULL,NULL),(96,'Samsung Galaxy A34 5G','Galaxy A34 5G sở hữu thiết kế đẹp và hiện đại với mặt lưng nhẵn làm từ nhựa cao cấp, màn hình lớn và cụm camera được bố trí theo một hàng dọc bắt mắt. Do sở hữu thiết kế từ nhựa nên máy sẽ có trọng lượng nhẹ hơn giúp mang lại cảm giác cầm nắm nhẹ nhàng.','2023-04-09 07:46:54','2023-04-09 07:52:19',6,_binary '',1,NULL,1,'product-96.png',NULL,NULL),(97,'Máy tạo oxy 5 lít Reiwa K5BW - Có xông mũi','Chiếc MacBook này được trang bị con chip Apple M1 được sản xuất độc quyền bởi Nhà Táo trên tiến trình 5 nm, 8 lõi bao gồm 4 lõi tiết kiệm điện và 4 lõi hiệu suất cao, mang đến một hiệu năng kinh ngạc, xử lý mọi tác vụ văn phòng một cách mượt mà như Word, Excel, Powerpoint,... thực hiện tốt các nhiệm vụ chỉnh sửa hình ảnh, kết xuất 2D trên các phần mềm Photoshop, AI,... máy còn hỗ trợ tiết kiệm được điện năng cao.','2023-04-09 07:51:20','2023-06-26 23:17:00',8,_binary '',2,NULL,NULL,'product-97.png',NULL,NULL),(98,'Máy đo Acid Uric (kiểm tra Gout) Benecheck Plus','Bệnh gout là căn bệnh ngày càng phổ biến, tỷ lệ người mắc bệnh gia tăng đáng kể. Chính vì thế mọi người cần phải chủ động tầm soát căn bệnh này, bằng cách thường xuyên kiểm tra sức khỏe định kỳ hoặc sử dụng máy đo acid Uric để kiểm tra lượng acid uric ngay tại nhà. Hiện nay trên thị trường, có bán máy đo acid uric Benecheck Plus rất tốt, mọi người có thể tham khảo.','2023-04-09 07:54:06','2023-06-28 14:31:39',6,_binary '',1,NULL,NULL,'product-98.png',NULL,NULL),(99,'Máy tăm nước cầm tay ROAMAN W10','Vệ sinh răng miệng bằng máy tăm nước là phương pháp phổ biến, nhất là với những người đang niềng răng. Nếu bạn đang tìm kiếm sản phẩm chăm sóc sức khỏe răng miệng tối ưu thì Máy tăm nước cầm tay Roaman W10 sẽ là lựa chọn số 1 dành cho bạn.','2023-04-09 07:57:59','2023-06-29 00:21:12',41,_binary '',1,1,NULL,'product-99.png',NULL,NULL),(100,'Máy tạo oxy xách tay LoveGo LG 103','Máy tạo oxy xách tay LoveGo LG 103 là thiết bị cầm tay hiện đại, cung cấp oxy thay thế, được sử dụng hỗ trợ điều trị các vấn đề liên quan đến hô hấp ở bệnh nhân: viêm phổi, viêm phế quản, hen suyễn, lao phổi, bệnh phổi tắc nghẽn mạn tính (COPD),… ','2023-04-09 08:00:07','2023-06-29 00:18:22',4,_binary '',2,NULL,NULL,'product-100.png',NULL,10),(104,'Iphone 11','iPhone 11 sở hữu hiệu năng khá mạnh mẽ, ổn định trong thời gian dài nhờ được trang bị chipset A13 Bionic. Màn hình LCD 6.1 inch sắc nét cùng chất lượng hiển thị Full HD của máy cho trải nghiệm hình ảnh mượt mà và có độ tương phản cao. Hệ thống camera hiện đại được tích hợp những tính năng công nghệ mới kết hợp với viên Pin dung lượng 3110mAh, giúp nâng cao trải nghiệm của người dùng.','2023-04-09 20:09:06','2023-04-09 20:09:06',4,_binary '',2,NULL,NULL,'product-104.png',NULL,NULL),(105,'Máy đo huyết áp điện tử bắp tay Boso Medicus X','Thiết kế sang trọng, lịch lãm - siêu mỏng 11.3mm, chỉ 1.24kg\nHiệu năng hàng đầu - Chip Apple m2, 8 nhân GPU, hỗ trợ tốt các phần mềm như Word, Axel, Adoble Premier\nĐa nhiệm mượt mà - Ram 16GB, SSD 256GB cho phép vừa làm việc, vừa nghe nhạc\nMàn hình sắc nét - Độ phân giải 2560 x 1664 cùng độ sáng 500 nits\nÂm thanh sống động - 4 loa tramg bị công nghệ dolby atmos và âm thanh đa chiều','2023-04-09 13:21:47','2023-06-26 23:06:37',1,_binary '',2,NULL,NULL,'product-105.png',NULL,NULL),(109,'OPPO Find N2 Flip','OPPO Find N2 Flip, chiếc điện thoại gập đầu tiên của OPPO được giới thiệu chính thức vào tháng 03/2023. Với cấu hình mạnh mẽ bao gồm con chip Dimensity 9000+ và bộ camera nổi trội, đây được xem là một trong những mẫu điện thoại đáng chú ý ở thời điểm hiện tại khi sở hữu bộ cấu hình tốt trong tầm giá.','2023-04-15 03:15:22','2023-04-15 03:15:22',6,_binary '',77,NULL,1,'product-109.png',NULL,NULL),(110,'Iphone 15','Đẹp quá','2023-04-15 16:14:57','2023-04-15 16:14:57',4,_binary '',2,NULL,NULL,'product-110.png',NULL,NULL),(111,'Apple Macbook Pro 13 M2 2022','Sau sự thành công của Macbook Pro M1, Apple tiếp tục cho ra mắt phiên bản nâng cấp với con chip mạnh hơn mang tên Macbook Pro M2 vào năm 2022. Macbook Pro M2 2022 sở hữu một hiệu năng vượt trội với con chip M2, card đồ họa 10 nhân GPU hứa hẹn mang lại cho người dùng những trải nghiệm vượt trội.','2023-04-18 17:08:53','2023-04-18 17:08:53',8,_binary '',2,NULL,NULL,'product-111.png',NULL,NULL),(117,'Laptop Lenovo ThinkBook 14s','Lenovo ThinkBook 14s G2 ITL i5 (20VA000NVN) là chiếc laptop học tập - văn phòng phù hợp với học sinh, sinh viên hay người làm văn phòng cần một chiếc máy tính mỏng nhẹ nhưng vẫn có cấu hình ổn định.','2023-04-22 21:05:28','2023-04-22 21:05:28',7,_binary '',82,NULL,NULL,'product-117.png',NULL,NULL),(118,'Laptop Lenovo Ideapad 3','Kích thước màn hình 15.6 inch với độ phân giải Full HD giúp các chi tiết hiển thị trên màn hình được rõ ràng, sắc nét. Tấm nền TN cho tốc độ phản hồi nhanh chóng hơn, đồng thời hạn chế tối đa hiện tượng nhức mỏi mắt nhờ công nghệ chống chói Anti Glare. ','2023-04-22 21:18:12','2023-04-22 21:18:12',8,_binary '',82,NULL,NULL,'product-118.png',NULL,NULL),(119,'Laptop Lenovo ThinkPad X1','Laptop Lenovo ThinkPad được trang bị bộ vi xử lý Intel Core i7 1260P sở hữu kiến trúc Hybrid khi kết hợp các lõi hiệu năng P-core và các lõi tiết kiệm điện E-core, đi cùng card tích hợp Intel Iris Xe hỗ trợ mình giải quyết mọi nhu cầu trong công việc trên các phần mềm doanh nghiệp hay hoàn thành những bản thiết kế đồ họa trên Photoshop, Illustrator,... thậm chí còn có thể chiến game mượt mà.','2023-04-22 21:21:11','2023-04-22 21:21:11',8,_binary '',82,NULL,NULL,'product-119.png',NULL,NULL),(120,'Laptop HP Gaming Victus ','Siêu phẩm laptop Gaming đến từ nhà HP với kích thước 15.6 inch, độ phân giải Full HD đi kèm tần số quét lên đến 144Hz hạn chế giật, xé hình, cho tốc độ mượt mà thao tác mướt mắt.\nMáy trang bị CPU Intel Core i5-12450H cùng card đồ họa NVIDIA GeForce GTX 1650 cân mọi tựa game phổ biến hiện nay hay dễ dàng thiết kế, sáng tạo trên Photoshop, Canva, Figma,...\nRAM 8GB đa nhiệm cho mọi thao tác mượt mà, mở nhiều tab cùng lúc mà không lo lag, giật. Ổ cứng 512GB PCIE cho không gian lưu trữ rộng rãi, tải game hay lưu trữ dữ liệu học tập, làm việc thoải mái.\nTrang bị đầy đủ cổng kết nối như: Ethernet, HDMI 2.1, USB-A, Type-C,... hỗ trợ kết nối, truyền tải dữ liệu nhanh chóng.\nLaptop đi kèm đèn bàn phím hỗ trợ game thủ thao tác nhanh gọn trong môi trường thiếu sáng.\n','2023-04-23 12:54:53','2023-04-23 12:54:53',7,_binary '',78,NULL,NULL,'product-120.png',NULL,NULL),(121,'Laptop Lenovo Gaming Legion 5 15ARH7 82RE002VVN','Laptop Lenovo Gaming Legion 5 15ARH7 82RE002VVN - Tối ưu trải nghiệm với hiệu suất cực cao\nDù là mẫu laptop gaming nhưng với vẻ bề ngoài trang nhã, laptop Lenovo Gaming Legion 5 15ARH7 82RE002VVN còn rất phù hợp để sử dụng trong môi trường công sở. Với hiệu năng mạnh mẽ của mình, sản phẩm laptop Lenovo Gaming hứa hẹn sẽ làm hài lòng cả những yêu cầu khắt khe nhất dù là công việc hay giải trí.','2023-04-23 15:44:06','2023-04-23 15:49:03',7,_binary '',82,NULL,NULL,'product-121.png',NULL,NULL),(122,'OPPO Reno7 Z 5G','OPPO đã trình làng mẫu Reno7 Z 5G với thiết kế OPPO Glow độc quyền, camera mang hiệu ứng như máy DSLR chuyên nghiệp cùng viền sáng kép, máy có một cấu hình mạnh mẽ và đạt chứng nhận xếp hạng A về độ mượt.','2023-04-24 16:51:37','2023-04-24 16:51:37',6,_binary '',77,NULL,NULL,'product-122.png',NULL,NULL),(123,'Laptop Lenovo Ideapad Gaming 3','Laptop Lenovo IdeaPad Gaming 3 15IHU6 82K101B5VN - Chuyên dụng cho cả gaming lẫn đồ họa\nDù là nhu cầu giải trí với những tựa game đình đám cấu hình nặng, hay làm việc trên bộ phần mềm đồ họa chuyên sâu, laptop Lenovo IdeaPad Gaming 3 15IHU6 82K101B5VN đáp ứng trọn vẹn cả hai nhu cầu trên với trải nghiệm sử dụng tuyệt vời mà người dùng laptop tìm kiếm.','2023-04-24 20:47:33','2023-04-24 20:47:33',8,_binary '',82,NULL,NULL,'product-123.png',NULL,NULL),(124,'Điện thoại OPPO A77s ','OPPO vừa cho ra mắt mẫu điện thoại tầm trung mới với tên gọi OPPO A77s, máy sở hữu màn hình lớn, thiết kế đẹp mắt, hiệu năng ổn định cùng khả năng mở rộng RAM lên đến 8 GB vô cùng nổi bật trong phân khúc.','2023-04-24 21:29:09','2023-04-24 21:29:09',6,_binary '',77,NULL,NULL,'product-124.png',NULL,NULL),(125,'May rung tim','Hang free okokokok','2023-06-22 21:26:12','2023-06-22 21:26:12',2,_binary '',1,3,NULL,'product-125.png',NULL,NULL),(126,'May rung tim','Ngon bo re ','2023-06-26 23:30:05','2023-06-26 23:30:05',4,_binary '',2,1,NULL,'product-126.png',NULL,NULL),(127,'Đai đỡ bụng bầu Elife Deluxe E-MB002','Sản phẩm đai đỡ bụng bầu Deluxe E-MB002 thương hiệu nổi tiếng Elife, tập trung sản xuất các thiết bị y tế. Thiết bị giúp hỗ trợ xương sống, xương chậu, giảm đau hiệu quả ở những mẹ bầu. Thiết bị được chỉ định sử dụng khi đau thắt lưng cảm thấy khó chịu khi mang thai.','2023-06-27 23:45:41','2023-06-27 23:45:41',45,_binary '\0',79,1,NULL,'product-127.png',NULL,NULL),(128,'Đai đỡ bụng cho bà bầu Art 5400','Đai đỡ bụng cho bà bầu Art 5400 là sản phẩm hữu ích cho chị em phụ nữ trong thời kỳ mang thai.','2023-06-28 00:13:12','2023-06-28 00:13:12',45,_binary '\0',79,2,NULL,'product-128.png',NULL,NULL),(129,'Áo ngực cho con bú Art 5700','Với những ưu điểm vượt trội, áo ngực cho con bú Art 5700 là sản phẩm “hot” trên thị trường và nhận được sự quan tâm của nhiều bà mẹ.','2023-06-28 00:15:32','2023-06-28 00:15:32',45,_binary '\0',79,NULL,NULL,'product-129.png',NULL,NULL),(130,'Máy đo huyết áp điện tử bắp tay Wellmed AXD-804','Đo huyết áp tại nhà với máy đo huyết áp điện tử bắp tay Wellmed AXD-804 chắc chắn sẽ giúp bạn theo dõi chỉ số huyết áp chuẩn xác mỗi ngày. Thiết bị cung cấp các phép đo huyết áp và nhịp tim có độ chính xác chuẩn y tế với biểu hiện vạch màu theo chuẩn WHO.','2023-06-28 00:17:08','2023-06-28 00:17:08',4,_binary '\0',2,1,NULL,'product-130.png',NULL,NULL),(131,'Máy đo huyết áp cơ Boso Classico - Mặt đồng hồ 60mm','Máy đo huyết áp cơ Boso Classico là máy đo huyết áp cơ của Boso, một thương hiệu máy đo huyết áp nổi tiếng hàng đầu của Đức, được 96% bác sĩ Đức tin dùng. Sản phẩm đo huyết áp chính xác, có kiểu dáng thiết kế bắt mắt, sang trọng, bền đẹp vì thế được nhiều người tin tưởng lựa chọn sử dụng.','2023-06-28 00:18:28','2023-06-28 00:18:28',4,_binary '\0',77,2,NULL,'product-131.png',NULL,NULL),(132,'Máy đo huyết áp điện tử bắp tay Boso Medicus Family 4','Máy đo huyết áp điện tử Boso Medicus Family 4 là loại máy đo huyết áp điện tử có kiểu dáng hiện đại, hoạt động dựa theo nguyên tắc dao động kế. Sự dao động kế được thiết lập bỏi nhịp tim, truyền tín hiệu theo vòng bít và đánh giá bằng bộ vi xử lý, không cần thiết bị nghe, do đó đo huyết áp nhanh chóng, chính xác, độ tin cậy trên 99%. Đặc biệt, điểm vượt trội của Boso Medicus Family 4 là có 60 bộ nhớ cho 4 người. Thiết bị đo huyết áp này đang là sản phẩm được quan tâm hàng đầu hiện nay.','2023-06-28 00:19:55','2023-06-28 00:19:55',4,_binary '\0',77,NULL,NULL,'product-132.png',NULL,NULL),(133,'Máy đo Acid Uric (kiểm tra Gout) Benecheck Plus','Bệnh gout là căn bệnh ngày càng phổ biến, tỷ lệ người mắc bệnh gia tăng đáng kể. Chính vì thế mọi người cần phải chủ động tầm soát căn bệnh này, bằng cách thường xuyên kiểm tra sức khỏe định kỳ hoặc sử dụng máy đo acid Uric để kiểm tra lượng acid uric ngay tại nhà. Hiện nay trên thị trường, có bán máy đo acid uric Benecheck Plus rất tốt, mọi người có thể tham khảo.\n\nMáy đo Acid Uric Benecheck Plus là sản phẩm của thương hiệu General Life Biotechnology nổi tiếng. Máy được nghiên cứu, ứng dụng và sản xuất dựa trên công nghệ cảm biến sinh học điện hóa hiện đại, duy nhất trên thế giới giúp đo nồng độ acid uric nhanh chóng, chính xác chỉ sau vài giây.','2023-06-28 00:22:20','2023-06-28 00:22:20',6,_binary '\0',78,3,NULL,'product-133.png',NULL,NULL),(134,'Kim lấy máu máy đo đường huyết','Kim lấy máu là vật tư tiêu hao trong bộ sản phẩm máy đo đường huyết. Trên thị trường có nhiều dòng kim khác nhau nhưng chủ yếu được chia làm 2 loại để phù hợp với bút lấy máu (gồm có kim 2 cạnh và kim 4 cạnh).\nĐối với dòng Sapphire thì bút được thiết kế là dòng kim 4 cạnh với giá thành rẻ, 1 túi có 25 kim.','2023-06-28 00:25:08','2023-06-28 00:25:08',6,_binary '',78,3,NULL,'product-134.png',NULL,NULL),(135,'Máy đo đường huyết Medismart Sapphire Plus','Máy đo đường huyết Medismart Sapphire Plus là thiết bị chăm sóc sức cao cấp đến từ Thụy Sĩ, được sản xuất theo công nghệ tiên tiến bậc nhất, mang đến người dùng kết quả đo đường huyết tin cậy đến 99%. Sản phẩm phù hợp nhiều đối tượng người dùng, giúp bạn chăm sóc và theo dõi sức khỏe cho cả gia đình','2023-06-28 00:30:01','2023-06-28 00:30:01',6,_binary '\0',2,1,NULL,'product-135.png',NULL,NULL),(136,'Que thử đường huyết máy đo Sapphire Plus (25 que)','Que thử đường huyết dành cho máy đo đường huyết Sapphire Plus.','2023-06-28 00:31:53','2023-06-28 00:31:53',6,_binary '\0',2,2,NULL,'product-136.png',NULL,NULL),(137,'Máy tăm nước cầm tay ROAMAN Mini 8','Mặc dù đánh răng hai lần một ngày là phương pháp tốt để bảo vệ sức khỏe răng miệng, nhưng có những vùng khó tiếp cận trong miệng mà bạn có thể không làm sạch được bằng cách đánh răng hay sử dụng chỉ nha khoa. Làm sạch kẽ răng, loại bỏ các mảnh thức ăn và mảng bám giữa các kẽ răng là một phần thiết yếu đối với việc chăm sóc sức khỏe răng miệng. Sử dụng bàn chải truyền thống hay chỉ nha khoa không phải là cách duy nhất để làm sạch kẽ răng, hãy tìm đến Máy tăm nước Roaman Mini 8 - một lựa chọn thay thế hoàn hảo để bảo vệ răng miệng của bạn.','2023-06-28 13:27:00','2023-06-28 13:27:00',41,_binary '\0',2,1,NULL,'product-137.png',NULL,NULL),(138,'Máy tăm nước cầm tay ROAMAN W10','Vệ sinh răng miệng bằng máy tăm nước là phương pháp phổ biến, nhất là với những người đang niềng răng. Nếu bạn đang tìm kiếm sản phẩm chăm sóc sức khỏe răng miệng tối ưu thì Máy tăm nước cầm tay Roaman W10 sẽ là lựa chọn số 1 dành cho bạn.','2023-06-28 14:07:40','2023-06-28 14:07:40',41,_binary '\0',77,3,NULL,'product-138.png',NULL,NULL),(139,'Máy tăm nước cầm tay ROAMAN Mini 1','Ngày nay, việc chăm sóc răng miệng ngày càng được nhiều người quan tâm. Một hàm răng chắc khỏe không chỉ giúp bạn tự tin mà còn là minh chứng cho một cơ thể khỏe mạnh. Các phương pháp chăm sóc răng miệng vì thế cũng ngày càng trở nên đa dạng, trong đó đang được chú ý nhất hiện nay chính là máy tăm nước.','2023-06-28 14:34:29','2023-06-28 14:34:29',41,_binary '\0',82,2,NULL,'product-139.png',NULL,NULL),(140,'Bàn chải điện ROAMAN RM - T20','Bàn chải điện ROAMAN RM-T20 4in1 là sản phẩm mang thương hiệu nổi tiếng Roaman, đạt các chứng nhận FDA / ETL / CE / RoHS. Máy có một thân máy chính và 4 đầu bàn chải thay thế linh động trong đó 3 đầu bàn chải giúp vệ sinh răng miệng hiệu quả và một đầu bàn chải sillicone mềm mại mát xa làm sạch da mặt.','2023-06-28 14:36:02','2023-06-28 14:36:02',39,_binary '\0',1,2,NULL,'product-140.png',NULL,NULL),(141,'Bàn chải đánh răng điện ROAMAN RM - T3','Bàn chải điện là thiết bị hỗ trợ chăm sóc sức khỏe răng miệng hiện đại, tiện lợi, dễ sử dụng và mang lại hiệu quả cao. Việc chọn bàn chải đánh răng điện theo lứa tuổi, tình trạng sức khỏe và thói quen chăm sóc răng miệng sẽ giúp bạn bảo vệ sức khỏe răng miệng tốt nhất.','2023-06-28 14:38:06','2023-06-28 14:38:06',39,_binary '\0',1,3,NULL,'product-141.png',NULL,NULL),(142,'Bàn chải máy Mini ROAMAN ST - 031 (dành cho trẻ em)','Bàn chải điện mini ST - 031 là sản phẩm chăm sóc răng miệng hiệu quả dành riêng cho trẻ em. Sản phẩm thiết kế hiện đại tay cầm chống nước giúp trẻ dễ cầm nắm, làm sạch sâu kẽ răng, trẻ niềng răng cũng có thể hoàn toàn sử dụng sản phẩm này. ','2023-06-28 14:40:16','2023-06-28 14:40:16',39,_binary '\0',1,3,NULL,'product-142.png',NULL,NULL),(143,'Máy lọc nước RO TPR-D400C & TPR-H75CT','Máy lọc nước RO TPR-D400C & TPR-H75CT là 2 máy lọc nước ứng dụng cộng nghệ lọc nước RO hiện đại, giúp loại bỏ vi sinh, tạp chất bẩn, nước có thể uống trực tiếp sau khi sử dụng. Sản phẩm này hiện nay được nhiều người quan tâm và sử dụng.\n\nTPR-D400C: Hệ thống RO gồm 3 giai đoạn trực tiếp\nTPR-H75CT: Hệ thống RO 3 tầng với Tank & LCD','2023-06-28 14:45:28','2023-06-28 14:45:28',36,_binary '\0',1,1,NULL,'product-143.png',NULL,NULL),(144,'Bộ lọc nước đầu vòi TPR-DW005A','Máy lọc nước đầu vòi TPR-DW005A là máy lọc nước tinh khiết tự động tại vòi. Được ứng dụng sản xuất công nghệ tiên tiến hiện đại, sản phẩm giúp loại bỏ các tạp chất độc hại trong nước, bụi bẩn, hóa chất…giúp nước sạch, tinh khiết, đảm bảo sức khỏe cho người sử dụng.','2023-06-28 14:47:15','2023-06-28 14:47:15',36,_binary '\0',77,2,NULL,'product-144.png',NULL,NULL),(145,'Máy tạo oxy 5 lít Reiwa K5BW - Có xông mũi','Máy tạo oxy 5 lít Reiwa K5BW (Có tích hợp xông mũi họng) là thiết bị cung cấp nguồn oxy tinh khiết với dung tích 5 lít, xuất xứ từ thương hiệu Reiwa hàng đầu. Máy có khả năng phát hiện lỗi như nồng độ oxy thấp và hiển thị các cảnh báo an toàn, giúp người dùng phát hiện kịp thời sự cố và sửa chữa.','2023-06-28 14:49:36','2023-06-28 14:49:36',7,_binary '\0',77,1,NULL,'product-145.png',NULL,NULL),(146,'Máy tạo oxy 10 lít Wellmed','Máy thở oxy dung tích 10 lít Wellmed đáp ứng nhu cầu điều trị tại nhà. Thiết bị cho ra nồng độ trên 93% đáp ứng được tất cả các yêu cầu về lưu lượng oxy cho cả người lớn, nhi khoa và trẻ em sử dụng tại nhà, phòng điều dưỡng, bệnh viện,... Máy có tuổi thọ cao và đồng thời người dùng không cần mở thân máy để thay bộ lọc, đây chính là một điểm mạnh mà sản phẩm Wellmed bán chạy nhất hiện nay.','2023-06-28 14:51:03','2023-06-28 14:51:03',7,_binary '\0',77,1,NULL,'product-146.png',NULL,NULL),(147,'Máy tạo oxy xách tay LoveGo LG 103','Máy tạo oxy xách tay LoveGo LG 103 là thiết bị cầm tay hiện đại, cung cấp oxy thay thế, được sử dụng hỗ trợ điều trị các vấn đề liên quan đến hô hấp ở bệnh nhân: viêm phổi, viêm phế quản, hen suyễn, lao phổi, bệnh phổi tắc nghẽn mạn tính (COPD),… ','2023-06-28 14:54:37','2023-06-28 14:54:37',7,_binary '\0',77,1,NULL,'product-147.png',NULL,NULL),(148,'Máy trợ thở RESmart GII BiPAP Y25T','Máy trợ thở RESmart GII BiPAP Y25T là máy hỗ trợ thở hô hấp đơn giản cho những bệnh nhân có vấn đề về đường thở, phù hợp cho các bệnh nhân với bệnh lý ngưng thở khi ngủ do tắc nghẽn, giúp bệnh nhân cải thiện quá trình hô hấp, giảm công hô hấp. ','2023-06-29 00:27:29','2023-06-29 00:27:29',8,_binary '\0',77,2,NULL,'product-148.png',NULL,NULL),(149,'Máy trợ thở Resvent Auto BiPAP iBreeze 25STA','Các hệ thống máy trợ thở BiPAP của iBreeze là các thiết bị áp lực đường khí dương hai chiều cao cấp của Resvent, được dùng để điều trị chứng ngưng thở khi ngủ (OSA) ở bệnh nhân, nhằm thực hiện tạo hơi thở tự nhiên cho người dùng, thiết bị rất tiện dụng sử dụng tại nhà hoặc các phòng khám,...\nHệ thống này cũng được dùng để đo độ bão hòa oxy qua da (SpO2) trong máu bằng cách sử dụng phát hiện ánh sáng từ một đầu dò đặc biệt cho người lớn và nhi khoa.','2023-06-29 00:28:49','2023-06-29 00:28:49',8,_binary '\0',77,1,NULL,'product-149.png',NULL,NULL),(150,'Máy trợ thở AirSense 10 AutoSet CPAP','Máy điều trị ngưng thở Airsense 10 Autoset là dòng máy hỗ trợ thở CPAP mới nhất của hãng ResMed. Airsense 10 AutoSet có tất cả các ưu điểm của dòng máy S9, và còn được nâng cao hơn. Máy giúp điều trị chứng ngáy ngủ và ngưng thở khi ngủ.','2023-06-29 00:38:11','2023-06-29 00:38:11',8,_binary '',82,15,NULL,'product-150.png',NULL,NULL),(151,'May rung tim','null aaaaaaaaaaa','2024-01-28 14:14:07','2024-01-28 14:14:07',6,_binary '\0',2,NULL,NULL,'product-151.png',NULL,NULL),(152,'May oxi','Gia re nhat thi truong','2024-05-28 21:25:54','2024-05-28 21:25:54',7,_binary '\0',1,1,NULL,'product-152.png',NULL,NULL),(153,'May rung tim','zzzzzzzzzzzzzzzzzzzzzzz','2024-05-28 22:09:38','2024-05-28 22:09:38',4,_binary '\0',2,NULL,NULL,'product-153.png',NULL,NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_attribute`
--

DROP TABLE IF EXISTS `product_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_attribute` (
  `id` int NOT NULL AUTO_INCREMENT,
  `attribute_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `attribute_value` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_attribute_product` (`product_id`),
  CONSTRAINT `fk_product_attribute_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=345 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_attribute`
--

LOCK TABLES `product_attribute` WRITE;
/*!40000 ALTER TABLE `product_attribute` DISABLE KEYS */;
INSERT INTO `product_attribute` VALUES (115,'Màn hình','Retina',95),(116,'Kích thước màn hình','6.1 inches',95),(117,'Camera sau','Camera kép 12MP: - Camera góc rộng: ƒ/1.8 aperture - Camera siêu rộng: ƒ/2.4 aperture',95),(118,'Pin','4750',94),(119,'Loại card đồ họa','8 nhân GPU, 16 nhân Neural Engine',105),(120,'Dung lượng RAM','16GB',105),(121,'Công nghệ màn hình','Liquid Retina Display',105),(122,'Ram','64GB',110),(123,'Màn Hình','15inhc',110),(125,'Pin ','3000',98),(126,'CPU','i5, 1135G, 72.4GHz',117),(130,'Màn hình','Super AMOLED6.6\"Full HD+',98),(131,'Hệ điều hành','Android 13',98),(132,'Camera sau','Chính 48 MP &amp; Phụ 8 MP, 5 MP',98),(133,'Camera trước','13 MP',98),(134,'Chip','MediaTek Dimensity 1080 8 nhân',98),(135,'RAM','8 GB',98),(136,'Dung lượng lưu trữ','256 GB',98),(137,'SIM','2 Nano SIMHỗ trợ 5G',98),(138,'Pin, Sạc','5000 mAh25 W',98),(139,'Màn hình','Super AMOLED6.6\"Full HD+',96),(140,'Hệ điều hành','Android 13',96),(141,'Camera sau','Chính 48 MP & Phụ 8 MP, 5 MP',96),(142,'Camera trước','13 MP',96),(143,'Chip','MediaTek Dimensity 1080 8 nhân',96),(144,'RAM','8 GB',96),(145,'Dung lượng lưu trữ','256 GB',96),(146,'SIM','2 Nano SIMHỗ trợ 5G',96),(147,'Pin, Sạc','5000 mAh25 W',96),(148,'CPU:','i71260P2.1GHz',118),(149,'RAM:','16 GBLPDDR5 (Onboard)5200 MHz',118),(150,'Ổ cứng:','512 GB SSD NVMe PCIe (Có thể tháo ra, lắp thanh khác tối đa 2 TB)',118),(151,'Màn hình:','14\"WUXGA (1920 x 1200)',118),(152,'Card màn hình:','Card tích hợpIntel Iris Xe',118),(153,'Cổng kết nối:','0',118),(154,'Đặc biệt:','Có màn hình cảm ứngCó đèn bàn phím',118),(155,'Hệ điều hành:','Windows 11 Home SL',118),(156,'Thiết kế:','Mặt trên Sợi Carbon - Mặt dưới nhôm',118),(157,'Kích thước, khối lượng:','Dài 315.6 mm - Rộng 222.5 mm - Dày 15.36 mm - Nặng 1.12 kg',118),(158,'Thời điểm ra mắt:','2022',118),(159,'CPU:','i71260P2.1GHz',119),(160,'RAM:','16 GBLPDDR5 (Onboard)5200 MHz',119),(161,'Ổ cứng:','512 GB SSD NVMe PCIe (Có thể tháo ra, lắp thanh khác tối đa 2 TB)',119),(162,'Màn hình:','14\"WUXGA (1920 x 1200)',119),(163,'Card màn hình:','Card tích hợpIntel Iris Xe',119),(164,'Màn hình:','14\"WUXGA (1920 x 1200)',119),(165,'Đặc biệt:','Có màn hình cảm ứngCó đèn bàn phím',119),(166,'Hệ điều hành:','Windows 11 Home SL',119),(167,'Thiết kế:','Mặt trên Sợi Carbon - Mặt dưới nhôm',119),(168,'41-TRIAL-Kích thước, khối lượng: 167','34-TRIAL-Dài 315.6 mm - Rộng 222.5 mm - Dày 15.36 mm - Nặng 1.12 kg 100',119),(169,'269-TRIAL-Thời điểm ra mắt: 124','78-TRIAL-2022 258',119),(170,'262-TRIAL-CPU: 164','5-TRIAL-Apple M2100GB/s 245',111),(171,'181-TRIAL-RAM: 27','61-TRIAL-16 GB 191',111),(172,'295-TRIAL-Ổ cứng: 242','27-TRIAL-512 GB SSD 36',111),(173,'291-TRIAL-Màn hình: 204','2-TRIAL-13.3\"Retina (2560 x 1600) 153',111),(174,'292-TRIAL-Card màn hình: 82','21-TRIAL-Card tích hợp10 nhân GPU 116',111),(175,'218-TRIAL-Cổng kết nối: 95','47-TRIAL-Jack tai nghe 3.5 mm2 x Thunderbolt 3 126',111),(176,'71-TRIAL-Đặc biệt: 138','69-TRIAL-Có đèn bàn phím 112',111),(177,'167-TRIAL-Hệ điều hành: 199','235-TRIAL-Mac OS 294',111),(178,'203-TRIAL-Thiết kế: 111','122-TRIAL-Vỏ kim loại 33',111),(179,'273-TRIAL-Kích thước, khối lượng: 164','141-TRIAL-Dài 304.1 mm - Rộng 212.4 mm - Dày 15.6 mm - Nặng 1.4 kg 211',111),(180,'53-TRIAL-Thời điểm ra mắt: 268','47-TRIAL-06/2022 44',111),(181,'262-TRIAL-Kích thước, khối lượng: 57','237-TRIAL-Dài 322 mm - Rộng 207 mm - Dày 14.9 mm - Nặng 1.27 kg 259',117),(182,'23-TRIAL-Thời điểm ra mắt: 141','229-TRIAL-2020 178',117),(183,'16-TRIAL-CPU: 35','290-TRIAL-Apple M2100GB/s 42',111),(184,'288-TRIAL-RAM: 106','40-TRIAL-16 GB 242',111),(185,'64-TRIAL-Ổ cứng: 148','146-TRIAL-512 GB SSD 105',111),(186,'290-TRIAL-Màn hình: 129','70-TRIAL-13.3\"Retina (2560 x 1600) 50',111),(187,'6-TRIAL-Card màn hình: 201','93-TRIAL-Card tích hợp10 nhân GPU 248',111),(188,'129-TRIAL-Cổng kết nối: 23','84-TRIAL-Jack tai nghe 3.5 mm2 x Thunderbolt 3 154',111),(189,'156-TRIAL-Đặc biệt: 140','166-TRIAL-Có đèn bàn phím 176',111),(190,'131-TRIAL-Hệ điều hành: 208','144-TRIAL-Mac OS 39',111),(191,'26-TRIAL-Thiết kế: 223','137-TRIAL-Vỏ kim loại 238',111),(192,'218-TRIAL-Kích thước, khối lượng: 282','129-TRIAL-Dài 304.1 mm - Rộng 212.4 mm - Dày 15.6 mm - Nặng 1.4 kg 41',111),(193,'33-TRIAL-Thời điểm ra mắt: 215','139-TRIAL-06/2022 258',111),(194,'204-TRIAL-Màn hình: 30','177-TRIAL-Super AMOLED6.5\"Full HD+ 206',99),(195,'173-TRIAL-Hệ điều hành: 186','221-TRIAL-Android 12 245',99),(196,'224-TRIAL-Camera sau: 172','270-TRIAL-Chính 12 MP & Phụ 12 MP, 8 MP 129',99),(197,'77-TRIAL-Camera trước: 273','297-TRIAL-32 MP 12',99),(198,'286-TRIAL-Chip: 90','161-TRIAL-Snapdragon 865 36',99),(199,'155-TRIAL-RAM: 167','255-TRIAL-8 GB 274',99),(200,'131-TRIAL-Dung lượng lưu trữ: 52','50-TRIAL-256 GB 250',99),(201,'141-TRIAL-SIM: 124','166-TRIAL-2 Nano SIM (SIM 2 chung khe thẻ nhớ)Hỗ trợ 4G 130',99),(202,'207-TRIAL-Pin, Sạc: 191','7-TRIAL-4500 mAh25 W 237',99),(203,'157-TRIAL-Màn hình: 287','153-TRIAL-Super AMOLED6.5\"Full HD+ 183',99),(204,'245-TRIAL-Hệ điều hành: 209','109-TRIAL-Android 12 158',99),(205,'221-TRIAL-Camera sau: 288','122-TRIAL-Chính 12 MP & Phụ 12 MP, 8 MP 46',99),(206,'206-TRIAL-Camera trước: 130','213-TRIAL-32 MP 68',99),(207,'0-TRIAL-Chip: 191','162-TRIAL-Snapdragon 865 155',99),(208,'10-TRIAL-RAM: 59','24-TRIAL-8 GB 137',99),(209,'248-TRIAL-Dung lượng lưu trữ: 183','295-TRIAL-256 GB 141',99),(210,'2-TRIAL-SIM: 50','91-TRIAL-2 Nano SIM (SIM 2 chung khe thẻ nhớ)Hỗ trợ 4G 236',99),(211,'74-TRIAL-Pin, Sạc: 220','96-TRIAL-4500 mAh25 W 21',99),(212,'48-TRIAL-Màn hình: 99','168-TRIAL-OLED6.1\"Super Retina XDR 184',100),(213,'181-TRIAL-Hệ điều hành: 234','53-TRIAL-iOS 16 199',100),(214,'18-TRIAL-Camera sau: 38','0-TRIAL-2 camera 12 MP 188',100),(215,'127-TRIAL-Camera trước: 167','128-TRIAL-12 MP 193',100),(216,'48-TRIAL-Chip: 283','107-TRIAL-Apple A15 Bionic 21',100),(217,'210-TRIAL-RAM: 17','13-TRIAL-6 GB 214',100),(218,'209-TRIAL-Dung lượng lưu trữ: 116','35-TRIAL-256 GB 51',100),(219,'200-TRIAL-SIM: 149','19-TRIAL-1 Nano SIM & 1 eSIMHỗ trợ 5G 56',100),(220,'298-TRIAL-Pin, Sạc: 3','224-TRIAL-3279 mAh20 W 208',100),(221,'144-TRIAL-Màn hình: 209','289-TRIAL-AMOLEDChính 6.8\" & Phụ 3.26\"Full HD+ 2',109),(222,'195-TRIAL-Hệ điều hành: 85','93-TRIAL-Android 13 243',109),(223,'223-TRIAL-Camera sau: 87','214-TRIAL-Chính 50 MP & Phụ 8 MP 203',109),(224,'248-TRIAL-Camera trước: 0','258-TRIAL-32 MP 18',109),(225,'180-TRIAL-Chip: 296','98-TRIAL-MediaTek Dimensity 9000+ 8 nhân 281',109),(226,'89-TRIAL-RAM: 98','109-TRIAL-8 GB 157',109),(227,'72-TRIAL-Dung lượng lưu trữ: 222','238-TRIAL-256 GB 292',109),(228,'38-TRIAL-SIM: 179','190-TRIAL-2 Nano SIMHỗ trợ 5G 257',109),(229,'158-TRIAL-Pin, Sạc: 191','15-TRIAL-4300 mAh44 W 88',109),(230,'256-TRIAL-Màn hình: 111','2-TRIAL-IPS LCD6.1\"Liquid Retina 234',104),(231,'272-TRIAL-Hệ điều hành: 255','228-TRIAL-iOS 15 146',104),(232,'262-TRIAL-Camera sau: 86','275-TRIAL-2 camera 12 MP 233',104),(233,'169-TRIAL-Camera trước: 42','144-TRIAL-12 MP 216',104),(234,'281-TRIAL-Chip: 198','122-TRIAL-Apple A13 Bionic 51',104),(235,'121-TRIAL-RAM: 299','257-TRIAL-4 GB 276',104),(236,'292-TRIAL-Dung lượng lưu trữ: 89','275-TRIAL-64 GB 212',104),(237,'200-TRIAL-SIM: 110','3-TRIAL-1 Nano SIM & 1 eSIMHỗ trợ 4G 169',104),(238,'161-TRIAL-Pin, Sạc: 288','201-TRIAL-3110 mAh18 W 189',104),(240,'255-TRIAL-CPU: 223','202-TRIAL-Apple M1 85',97),(241,'182-TRIAL-RAM: 85','88-TRIAL-8 GB 226',97),(242,'117-TRIAL-Ổ cứng: 57','232-TRIAL-256 GB SSD 32',97),(243,'269-TRIAL-Màn hình: 54','221-TRIAL-13.3\"Retina (2560 x 1600) 89',97),(244,'176-TRIAL-Card màn hình: 129','268-TRIAL-Card tích hợp7 nhân GPU 192',97),(245,'125-TRIAL-Đặc biệt: 55','134-TRIAL-Có đèn bàn phím 49',97),(246,'241-TRIAL-Hệ điều hành: 212','145-TRIAL-Mac OS 60',97),(247,'118-TRIAL-Thiết kế: 153','239-TRIAL-Vỏ kim loại nguyên khối 123',97),(248,'79-TRIAL-Kích thước, khối lượng: 196','187-TRIAL-Dài 304.1 mm - Rộng 212.4 mm - Dày 4.1 mm đến 16.1 mm - Nặng 1.29 kg 229',97),(249,'49-TRIAL-Thời điểm ra mắt: 37','66-TRIAL-2020 49',97),(300,'193-TRIAL-Cổng kết nối: 95','297-TRIAL-Jack tai nghe 3.5 mm2 x Thunderbolt 3 (USB-C) 16',97),(302,'86-TRIAL-Màn hình: 205','188-TRIAL-AMOLEDChính 6.8\" & Phụ 3.26\"Full HD+ 82',95),(303,'155-TRIAL-Hệ điều hành: 234','114-TRIAL-Android 13 1',95),(304,'116-TRIAL-Camera sau: 271','86-TRIAL-Chính 50 MP & Phụ 8 MP 263',95),(305,'113-TRIAL-Chip: 55','285-TRIAL-MediaTek Dimensity 9000+ 8 nhân 253',95),(306,'12-TRIAL-RAM: 8','32-TRIAL-8 GB 245',95),(307,'113-TRIAL-Dung lượng lưu trữ: 156','121-TRIAL-256 GB 58',95),(308,'246-TRIAL-SIM: 82','181-TRIAL-2 Nano SIMHỗ trợ 5G 244',95),(309,'96-TRIAL-Pin, Sạc: 122','229-TRIAL-4300 mAh44 W 61',95),(310,'135-TRIAL-Camera trước: 50','73-TRIAL-32 MP 266',95),(311,'44-TRIAL-Ram 59','192-TRIAL-8 39',121),(312,'153-TRIAL-SSD 224','54-TRIAL-512gb 110',121),(313,'245-TRIAL-Màn Hình 249','286-TRIAL-15.6 inch 213',121),(320,'274-TRIAL-Màn hình: 122','68-TRIAL-AMOLEDChính 6.8\" & Phụ 3.26\"Full HD+ 218',122),(323,'187-TRIAL-Hệ điều hành: 5','258-TRIAL-Android 13 191',122),(324,'2-TRIAL-Camera sau: 25','77-TRIAL-Chính 50 MP & Phụ 8 MP 214',122),(325,'14-TRIAL-Chip: 24','234-TRIAL-MediaTek Dimensity 9000+ 8 nhân 74',122),(326,'72-TRIAL-RAM: 59','133-TRIAL-8 GB 170',122),(327,'287-TRIAL-Dung lượng lưu trữ: 97','18-TRIAL-256 GB 77',122),(328,'73-TRIAL-SIM: 170','263-TRIAL-2 Nano SIMHỗ trợ 5G 268',122),(329,'92-TRIAL-Pin, Sạc: 185','102-TRIAL-4300 mAh44 W 80',122),(330,'113-TRIAL-Camera trước: 127','2-TRIAL-32 MP 199',122),(331,'227-TRIAL-Pin 225','43-TRIAL-3000 124',124),(335,'223-TRIAL-CPU 272','161-TRIAL-Snap 81',124),(337,'103-TRIAL-Tần số quét 132','105-TRIAL-120HZ 293',124),(344,'Đai đỡ bụng bầu Elife Deluxe E-MB002','580000',127);
/*!40000 ALTER TABLE `product_attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_variant`
--

DROP TABLE IF EXISTS `product_variant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_variant` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sku_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `display_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `color_id` int DEFAULT NULL,
  `storage_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product_id` (`product_id`,`color_id`,`storage_id`),
  KEY `fk_product_variant_product_1` (`product_id`),
  KEY `fk_color_product` (`color_id`),
  KEY `fk_storage_product` (`storage_id`),
  CONSTRAINT `fk_color_product` FOREIGN KEY (`color_id`) REFERENCES `color` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_product_variant_product_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_storage_product` FOREIGN KEY (`storage_id`) REFERENCES `storage` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_variant`
--

LOCK TABLES `product_variant` WRITE;
/*!40000 ALTER TABLE `product_variant` DISABLE KEYS */;
INSERT INTO `product_variant` VALUES (55,'IP14PRM',0,29900000,_binary '',93,'productVariant-55','Iphone 14 promax yellow',5,2),(56,'SSU23P',-1,23890000,_binary '',95,'productVariant-56','Samsung Galaxy S23 Ultra 256GB',4,3),(57,'SSU23W',24,23890000,_binary '',95,'productVariant-57','Samsung Galaxy S23 Ultra 256GB',2,3),(58,'SSU20B',20,1980000,NULL,94,'productVariant-58','Máy tăm nước cầm tay ROAMAN Mini 8',5,2),(59,'IP14R',0,20390000,_binary '\0',100,'productVariant-59','iPhone 14 128GB',4,2),(60,'IP14R256',97,22990000,_binary '\0',100,'productVariant-60','iPhone 14 256GB',4,3),(61,'IP14R256',95,27990000,_binary '\0',100,'productVariant-61','iPhone 14 256GB',4,4),(62,'SSA53128',100,8490000,_binary '',96,'productVariant-62','Samsung Galaxy A34 5G 128GB',1,2),(63,'SSA53G128',12,8490000,_binary '',96,'productVariant-63','Samsung Galaxy A34 5G 128GB',3,2),(64,'SSAGR53128',199,8490000,_binary '',96,'productVariant-64','Samsung Galaxy A34 5G 128GB',10,2),(65,'MGND3SA/A',25,22990000,NULL,97,'productVariant-65','Máy tạo oxy 5 lít Reiwa K5BW - Có xông mũi',2,3),(66,'SS21FEGRE',12,12990000,_binary '',98,'productVariant-66','Samsung Galaxy S21 FE 5G',7,2),(67,'SS20FEGRE',16,1690000,NULL,99,'productVariant-67','Máy tăm nước cầm tay ROAMAN W10',7,4),(68,'AM2BAC',6,34000000,NULL,105,'productVariant-68','Máy đo huyết áp điện tử bắp tay Boso Medicus X',10,2),(70,'IP14PRMB',11,29900000,_binary '',93,'productVariant-70','Máy đo Acid Uric (kiểm tra Gout) Benecheck Plus ',2,2),(71,'IP11W128GB',12,12090000,_binary '',104,'productVariant-71','iPhone 11 128GB',2,2),(72,'OOPFF',9,19900000,_binary '',109,'productVariant-72','OPPO Find N2 Flip',1,3),(73,'SSSS',0,25000000,_binary '',110,'productVariant-73','Iphone 15 màu den',1,1),(75,'SKU14PRM',10,45000000,NULL,93,'productVariant-75','Iphone 14 Promax 1TB  Tím',8,5),(76,'20VA000NVN',19,16800000,_binary '',117,'productVariant-76','Laptop Lenovo ThinkBook 14s G2 ITL i5 1135G7/8GB/512GB/Win10',2,3),(77,'82RK005LVN',10,13890000,_binary '',118,'productVariant-77','Laptop Lenovo Ideapad 3 15IAU7 i3 1215U/8GB/256GB/Win11',10,3),(78,'82RE002VVN',10,24490000,_binary '',121,'productVariant-78','Laptop Lenovo Gaming Legion 5 15ARH7 82RE002VVN',2,4),(81,'OPP5GB',100,7490000,_binary '',122,'productVariant-81','OPPO Reno7 Z 5G',1,2),(82,'OPP5GS',44,7490000,_binary '',122,'productVariant-82','OPPO Reno7 Z 5G ',10,2),(83,'OPPO5GPRO',89,11990000,_binary '',122,'productVariant-83','OPPO Reno7 Pro 5G',3,3),(85,'OPPO5GPRO',50,11990000,_binary '',122,'productVariant-85','OPPO Reno7 Pro 5G',1,4),(86,'OPPO13',10,12000000,_binary '',124,'productVariant-86','Điện thoại OPPO A77s Black',1,3),(87,'abc1',10,6000000,_binary '',126,'productVariant-87','may do tim  abc',5,2),(88,'E-MB002',23,580000,_binary '',127,'productVariant-88','Đai đỡ bụng bầu Elife Deluxe E-MB002',1,1),(89,'Art 5400',23,599000,_binary '',127,'productVariant-89','Đai đỡ bụng cho bà bầu Art 5400',2,1),(90,'Art 5400',28,499000,_binary '',128,'productVariant-90','Đai đỡ bụng cho bà bầu Art 5400',2,1),(91,'Art 5700',56,460000,_binary '',129,'productVariant-91','Áo ngực cho con bú Art 5700',NULL,1),(92,'AXD804',58,800000,NULL,130,'productVariant-92','Máy đo huyết áp điện tử bắp tay Wellmed AXD-804',2,4),(93,'Classico',30,1980000,_binary '',131,'productVariant-93','Máy đo huyết áp cơ Boso Classico - Đường kính mặt đồng hồ 60mm',NULL,5),(94,'Medicus',56,1650000,_binary '',132,'productVariant-94','Máy đo huyết áp điện tử Boso Medicus Family 4',2,5),(95,'Benecheck',65,799000,_binary '',133,'productVariant-95','Máy đo Acid Uric (kiểm tra Gout) Benecheck Plus',NULL,5),(96,'DCVF5',130,25000,_binary '',134,'productVariant-96','Kim lấy máu máy đo đường huyết',NULL,5),(97,'Sapphire',25,890000,_binary '',135,'productVariant-97','Máy đo đường huyết Medismart Sapphire Plus',3,4),(98,'Sapphire',150,250000,_binary '',136,'productVariant-98','Que thử đường huyết máy đo Sapphire Plus (25 que)',2,4),(99,'ROAMAN',56,1985000,_binary '',137,'productVariant-99','Máy tăm nước cầm tay ROAMAN Mini 8',5,4),(100,'ROAMAN1',58,1690000,_binary '',138,'productVariant-100','Máy tăm nước cầm tay ROAMAN W10',7,4),(101,'ROAMAN1',54,1490000,_binary '',139,'productVariant-101','Máy tăm nước cầm tay ROAMAN Mini 1',3,4),(102,'ROAMANRM',61,1290000,_binary '',140,'productVariant-102','Bàn chải điện ROAMAN RM - T20',9,4),(103,'RMT3',58,1090000,_binary '',141,'productVariant-103','Bàn chải đánh răng điện ROAMAN RM - T3',2,4),(104,'SRT4',100,1090000,_binary '',142,'productVariant-104','Bàn chải máy Mini ROAMAN ST - 031 (dành cho trẻ em)',9,4),(105,'H75CT',51,8700000,_binary '',143,'productVariant-105','Máy lọc nước RO TPR-D400C & TPR-H75CT',2,4),(106,'DW005A',65,2120000,_binary '',144,'productVariant-106','Bộ lọc nước đầu vòi TPR-DW005A',2,4),(107,'K5BW',54,12300000,_binary '',145,'productVariant-107','Máy tạo oxy 5 lít Reiwa K5BW - Có xông mũi',2,4),(108,'Wellmed1',58,24500000,_binary '',146,'productVariant-108','Máy tạo oxy 10 lít Wellmed',2,4),(109,'LoveGo103',58,40000000,_binary '',147,'productVariant-109','Máy tạo oxy xách tay LoveGo LG 103',1,4),(111,'LoveGo',56,40000000,_binary '',100,'productVariant-111','Máy tạo oxy xách tay LoveGo LG 103',1,4),(112,'BiPAP',36,34000000,_binary '',148,'productVariant-112','Máy trợ thở RESmart GII BiPAP Y25T',1,4),(113,'25STA',35,32000000,_binary '',149,'productVariant-113','Máy trợ thở Resvent Auto BiPAP iBreeze 25STA',1,4),(115,'CPAP10',65,49000000,_binary '',150,'productVariant-115','Máy trợ thở AirSense 10 AutoSet CPAP',1,4);
/*!40000 ALTER TABLE `product_variant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion_product`
--

DROP TABLE IF EXISTS `promotion_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotion_product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `expiration_date` datetime DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `maximum_price` double DEFAULT NULL,
  `activate` bit(1) DEFAULT NULL,
  `is_percent` tinyint(1) DEFAULT '1',
  `discount_amount` double DEFAULT '0',
  `discount` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion_product`
--

LOCK TABLES `promotion_product` WRITE;
/*!40000 ALTER TABLE `promotion_product` DISABLE KEYS */;
INSERT INTO `promotion_product` VALUES (1,'2023-04-17 23:59:17','2023-04-12 12:26:14','-20%','2023-04-17 10:22:37',200000,_binary '',1,20,20),(2,'2023-04-17 23:59:00','2023-04-10 12:26:06','-10%','2023-04-17 00:00:00',300000,_binary '',1,10,10),(3,'2023-04-17 23:59:07','2023-04-10 16:57:58','Giảm 50%','2023-04-17 00:00:58',0,_binary '',1,50,50),(4,'2023-04-17 23:12:14','2023-04-15 09:25:37','-15%','2023-04-17 09:25:40',NULL,_binary '',1,15,NULL),(5,'2023-04-24 23:58:40',NULL,'Giảm 10h ngày 19-4 ','2023-04-19 00:00:54',NULL,_binary '',1,20,NULL),(14,'2023-04-23 00:00:00','2023-04-16 00:00:00','Giảm khuya','2023-04-20 00:00:00',NULL,_binary '',1,20,20),(15,'2023-04-18 23:59:00','2023-04-14 23:59:00','Ngày 18-4 sale -20%','2023-04-18 00:00:00',NULL,_binary '',1,20,20),(16,'2023-04-18 23:59:00','2023-04-17 01:18:36','-30%','2023-04-17 00:30:00',NULL,_binary '',1,30,NULL),(17,'2023-04-18 23:59:00','2023-03-15 23:59:00','Giảm tháng 3','2023-03-15 23:59:00',NULL,_binary '',1,20,20);
/*!40000 ALTER TABLE `promotion_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion_type`
--

DROP TABLE IF EXISTS `promotion_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotion_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name_promotion_type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `condition_minimum` int DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `is_limited` bit(1) DEFAULT NULL,
  `descriptions` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `limited_amount` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion_type`
--

LOCK TABLES `promotion_type` WRITE;
/*!40000 ALTER TABLE `promotion_type` DISABLE KEYS */;
INSERT INTO `promotion_type` VALUES (1,'Giảm theo đơn hàng',NULL,NULL,_binary '\0',NULL,0),(2,'Giảm 10% cho khách mới',NULL,NULL,_binary '\0',NULL,0),(3,'Khách hàng thân thiết',NULL,NULL,_binary '\0',NULL,0),(4,'Voucher tháng 4',25000000,100,_binary '',NULL,0),(5,'Giảm 20% cho đơn hàng trên 1 triệu',1000000,NULL,_binary '\0',NULL,0),(6,'Ưu đãi tháng 3 giảm 100k/đơn hàng',100000,NULL,_binary '',NULL,0),(7,'Sale 15-3',NULL,NULL,_binary '\0',NULL,0),(9,'Hoàn tiền 20% cho mỗi đơn hàng',10000000,0,_binary '\0','Hoàn tiền 20% cho đơn hàng trên 10 triệu',0),(10,'Giảm 999k cho đơn hàng đầu tiên',NULL,999000,_binary '\0','Giảm 999k cho người dùng mới',0),(11,'Ưu đãi mua hè ',NULL,15,_binary '','Voucher 15% cho 10 người dùng mới',10),(12,'Sale to cuối tháng',5000000,NULL,_binary '\0','Giảm 15% cho đơn từ 5 triệu',0),(13,'Voucher khách hàng thân thiết',NULL,NULL,_binary '\0','Giảm 2 triệu trên tổng đơn hàng',0),(14,'Hoàn tiền 10%',NULL,NULL,_binary '\0','Giảm 10% tổng giá trị đơn hàng',0),(15,'Mua càng nhiều giảm càng sâu',5000000,NULL,_binary '\0','Giảm 1 triệu cho đơn từ 5 triệu',0);
/*!40000 ALTER TABLE `promotion_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion_user`
--

DROP TABLE IF EXISTS `promotion_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotion_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name_promotion_user` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `discount_value` double DEFAULT '0',
  `is_used` tinyint(1) DEFAULT '0',
  `create_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `expiration_date` datetime DEFAULT NULL,
  `promotion_code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `promotion_type` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `is_percent` tinyint(1) DEFAULT '1',
  `is_deleted` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_promotion_user_user_1` (`user_id`),
  KEY `fk_promotion_user_promotion_type_1` (`promotion_type`),
  CONSTRAINT `fk_promotion_user_promotion_type_1` FOREIGN KEY (`promotion_type`) REFERENCES `promotion_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_promotion_user_user_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion_user`
--

LOCK TABLES `promotion_user` WRITE;
/*!40000 ALTER TABLE `promotion_user` DISABLE KEYS */;
INSERT INTO `promotion_user` VALUES (1,'Giảm theo đơn',500000,0,'2023-03-04 12:00:00','2023-01-04 12:00:00','2023-03-06 12:00:00','ORDERSALE5',1,25,0,0),(2,'Ưu đãi khách hàng mới',10,0,'2023-03-04 12:00:00','2023-01-30 12:00:00','2023-06-10 12:00:00','NEWCUSTOMER',2,1,1,0),(3,'Ưu đãi khách hàng mới',10,0,'2023-03-04 12:00:00','2023-03-04 12:00:00','2023-06-10 12:00:00','NEWCUSTOMER',2,NULL,1,0),(4,'Voucher tháng 4',20,0,'2023-03-04 12:00:00','2023-04-01 12:00:00','2023-04-30 23:59:59','APRILSALE',4,NULL,1,0),(5,'Sale 15-3',2000000,0,'2023-03-04 12:00:00','2023-03-04 12:00:00','2023-03-30 12:00:00','MARCH15',7,25,1,1),(6,'Sale 15-4',2000000,0,'2023-03-04 12:00:00','2023-04-01 12:00:00','2023-04-30 12:00:00','HAPPYAPRIL15',4,NULL,0,0),(7,'Hoàn tiền 20%',20,0,'2023-03-04 12:00:00','2023-03-04 12:00:00','2023-09-04 12:00:00','REDEEM20',9,NULL,1,0),(8,'Ưu đãi mua hè ',15,0,'2023-04-04 12:00:00','2023-04-04 00:00:00','2023-06-30 12:00:00','SUMMER15',11,32,1,0),(9,'Ưu đãi mua hè ',15,0,'2023-04-04 12:00:00','2023-04-04 00:00:00','2023-06-30 12:00:00','SUMMER15',11,36,1,0),(10,'Ưu đãi mua hè ',15,0,'2023-04-04 12:00:00','2023-04-04 00:00:00','2023-06-30 12:00:00','SUMMER15',11,37,1,0),(11,'Ưu đãi mua hè ',15,0,'2023-04-04 12:00:00','2023-04-04 00:00:00','2023-06-30 12:00:00','SUMMER15',11,38,1,0),(12,'Ưu đãi mua hè ',15,0,'2023-04-04 12:00:00','2023-04-04 00:00:00','2023-06-30 12:00:00','SUMMER15',11,39,1,0),(13,'Ưu đãi mua hè ',15,0,'2023-04-04 12:00:00','2023-04-04 00:00:00','2023-06-30 12:00:00','SUMMER15',11,40,1,0),(14,'Ưu đãi mua hè ',15,0,'2023-04-04 12:00:00','2023-04-04 00:00:00','2023-06-30 12:00:00','SUMMER15',11,44,1,0),(15,'Sale to cuối tháng',15,0,'2023-04-04 12:00:00','2023-04-04 00:00:00','2023-06-30 12:00:00','APRILBIGSALE',12,36,1,0),(16,'Sale to cuối tháng ',15,0,'2023-04-04 12:00:00','2023-04-04 00:00:00','2023-06-30 12:00:00','APRILBIGSALE',12,37,1,0),(17,'Sale to cuối tháng',15,0,'2023-04-04 12:00:00','2023-04-04 00:00:00','2023-06-30 12:00:00','APRILBIGSALE',12,38,1,0),(18,'Sale to cuối tháng',15,0,'2023-04-04 12:00:00','2023-04-04 00:00:00','2023-06-30 12:00:00','APRILBIGSALE',12,39,1,0),(19,'Sale to cuối tháng ',15,0,'2023-04-04 12:00:00','2023-04-04 00:00:00','2023-06-30 12:00:00','APRILBIGSALE',12,40,1,0),(20,'Sale to cuối tháng',15,0,'2023-04-04 12:00:00','2023-04-04 00:00:00','2023-06-30 12:00:00','APRILBIGSALE',12,44,1,0),(21,'Voucher khách hàng thân thiết',2000000,0,'2023-04-04 12:00:00','2023-04-01 00:00:00','2023-07-30 12:00:00','CUSTOMER04',13,35,0,0),(22,'Voucher khách hàng thân thiết',2000000,0,'2023-04-04 12:00:00','2023-04-01 00:00:00','2023-07-30 12:00:00','CUSTOMER04',13,36,0,0),(23,'Voucher khách hàng thân thiết',2000000,0,'2023-04-04 12:00:00','2023-04-01 00:00:00','2023-07-30 12:00:00','CUSTOMER04',13,37,0,0),(24,'Voucher khách hàng thân thiết',2000000,0,'2023-04-04 12:00:00','2023-04-01 00:00:00','2023-07-30 12:00:00','CUSTOMER04',13,38,0,0),(25,'Voucher khách hàng thân thiết',2000000,0,'2023-04-04 12:00:00','2023-04-01 00:00:00','2023-07-30 12:00:00','CUSTOMER04',13,39,0,0),(26,'Voucher khách hàng thân thiết',2000000,0,'2023-04-04 12:00:00','2023-04-01 00:00:00','2023-07-30 12:00:00','CUSTOMER04',13,40,0,0),(27,'Voucher khách hàng thân thiết',2000000,0,'2023-04-04 12:00:00','2023-04-01 00:00:00','2023-07-30 12:00:00','CUSTOMER04',13,44,0,0),(28,'Sale to cuối tháng',15,0,'2023-04-04 12:00:00','2023-04-04 00:00:00','2023-06-30 12:00:00','APRILBIGSALE',12,35,1,0),(29,'Voucher khách hàng thân thiết',2000000,0,'2023-04-04 12:00:00','2023-04-01 00:00:00','2023-07-30 12:00:00','CUSTOMER04',13,25,0,0),(30,'Sale to cuối tháng',15,0,'2023-04-04 12:00:00','2023-04-04 00:00:00','2023-06-30 12:00:00','APRILBIGSALE',12,25,1,0),(31,'Hoàn tiền 10%',10,0,'2023-04-04 12:00:00','2023-04-04 00:00:00','2023-06-30 12:00:00','DISCOUNT10%',14,25,1,0),(32,'Hoàn tiền 10%',10,0,'2023-04-04 12:00:00','2023-04-04 00:00:00','2023-06-30 12:00:00','DISCOUNT10%',14,35,1,0),(33,'Hoàn tiền 10%',10,0,'2023-04-04 12:00:00','2023-04-04 00:00:00','2023-06-30 12:00:00','DISCOUNT10%',14,36,1,0),(34,'Hoàn tiền 10%',10,0,'2023-04-04 12:00:00','2023-04-04 00:00:00','2023-06-30 12:00:00','DISCOUNT10%',14,37,1,0),(35,'Hoàn tiền 10%',10,0,'2023-04-04 12:00:00','2023-04-04 00:00:00','2023-06-30 12:00:00','DISCOUNT10%',14,38,1,0),(36,'Hoàn tiền 10%',10,0,'2023-04-04 12:00:00','2023-04-04 00:00:00','2023-06-30 12:00:00','DISCOUNT10%',14,39,1,0),(37,'Hoàn tiền 10%',10,0,'2023-04-04 12:00:00','2023-04-04 00:00:00','2023-06-30 12:00:00','DISCOUNT10%',14,40,1,0),(38,'Hoàn tiền 10%',10,0,'2023-04-04 12:00:00','2023-04-04 00:00:00','2023-06-30 12:00:00','DISCOUNT10%',14,44,1,0),(39,'Mua càng nhiều giảm càng sâu',1000000,0,'2023-04-04 12:00:00','2023-04-15 00:00:00','2023-06-30 12:00:00','SALEOFF1504',15,25,0,0),(40,'Mua càng nhiều giảm càng sâu',1000000,0,'2023-04-04 12:00:00','2023-04-15 00:00:00','2023-06-30 12:00:00','SALEOFF1504',15,44,0,0),(41,'Mua càng nhiều giảm càng sâu',1000000,0,'2023-04-04 12:00:00','2023-04-15 00:00:00','2023-06-30 12:00:00','SALEOFF1504',15,35,0,0),(42,'Mua càng nhiều giảm càng sâu',1000000,0,'2023-04-04 12:00:00','2023-04-15 00:00:00','2023-06-30 12:00:00','SALEOFF1504',15,40,0,0);
/*!40000 ALTER TABLE `promotion_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rating` (
  `id` int NOT NULL AUTO_INCREMENT,
  `point` int DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `order_detail_id` int DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `ratingcol` int DEFAULT NULL,
  `sale` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_detail_id` (`order_detail_id`),
  KEY `fk_rating_product_1` (`product_id`),
  KEY `fk_rating_order_detail_1` (`order_detail_id`),
  KEY `fk_rating_user_1` (`user_id`),
  CONSTRAINT `fk_rating_order_detail_1` FOREIGN KEY (`order_detail_id`) REFERENCES `order_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_rating_product_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_rating_user_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
INSERT INTO `rating` VALUES (41,5,'2023-04-14 15:20:40',1,57,'',98,NULL,10),(42,5,'2023-04-14 16:06:08',1,61,'',95,NULL,NULL),(43,5,'2023-04-15 14:50:51',38,76,'Ship nhanh',96,NULL,NULL),(44,5,'2023-04-15 16:22:14',26,88,'SẢN PHẨM NGON',99,NULL,NULL),(45,5,'2023-04-19 16:54:26',1,104,'',99,NULL,NULL),(46,5,'2023-04-19 16:54:26',1,105,'',100,NULL,NULL),(47,5,'2023-04-19 16:59:06',1,92,'',99,NULL,NULL),(48,4,'2023-04-21 19:00:08',44,121,'Sản phẩm tốt, đúng mẫu, good ',96,NULL,NULL),(49,4,'2023-04-23 23:01:50',45,148,'Mua lúc sale, giảm được hơn 3 triệu',100,NULL,NULL),(50,4,'2023-04-23 23:01:50',45,149,'Màu đẹp, máy ngon',98,NULL,NULL),(51,4,'2023-04-23 23:01:50',45,150,'Giao hàng nhanh, đóng gói ổn',100,NULL,NULL),(52,5,'2023-04-23 23:11:21',45,154,'Giảm giảm hời, nên mua',117,NULL,NULL),(53,4,'2023-04-23 23:17:14',44,147,'good',95,NULL,NULL),(54,5,'2023-04-23 23:42:41',45,152,'Tốt so với tầm giá',109,NULL,NULL),(55,3,'2023-04-23 23:42:41',45,153,'Đời cũ nhưng giá hơi chát',104,NULL,NULL),(56,5,'2023-04-24 21:26:08',1,79,'Tốt',96,NULL,NULL),(57,5,'2023-04-24 22:02:26',1,69,'',98,NULL,NULL),(58,5,'2023-04-24 22:02:32',1,65,'',95,NULL,NULL),(59,5,'2023-04-24 22:02:32',1,66,'',95,NULL,NULL),(60,3,'2023-04-24 22:02:39',1,62,'',105,NULL,NULL),(61,2,'2023-04-24 22:02:43',1,58,'',94,NULL,NULL),(62,2,'2023-04-24 22:02:50',1,56,'',100,NULL,NULL),(63,2,'2023-04-24 22:02:55',1,55,'',99,NULL,NULL),(64,2,'2023-04-24 22:03:01',1,53,'',100,NULL,NULL),(65,2,'2023-04-24 22:03:06',1,45,'',98,NULL,NULL),(66,3,'2023-04-24 22:03:14',1,44,'',105,NULL,NULL);
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refreshtoken`
--

DROP TABLE IF EXISTS `refreshtoken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refreshtoken` (
  `id` int NOT NULL AUTO_INCREMENT,
  `expiry_date` datetime NOT NULL,
  `token` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `tokenToAccount` (`user_id`),
  CONSTRAINT `refreshtoken_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=1537 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refreshtoken`
--

LOCK TABLES `refreshtoken` WRITE;
/*!40000 ALTER TABLE `refreshtoken` DISABLE KEYS */;
INSERT INTO `refreshtoken` VALUES (1453,'2023-05-22 22:15:50','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0YWluZ3V5ZW4iLCJleHAiOjE2ODQ3Njg1NDl9.4BQURJqp73U7szKjHr6v6kep_urUaUsPlE9tPZbFqwo',44),(1457,'2023-05-23 12:23:46','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0YWluZ3V5ZW4iLCJleHAiOjE2ODQ4MTk0MjZ9.TNRq_NbHU85rudyx_CTj_5gNN6fVlQHB45ROLjoeh-w',44),(1468,'2023-05-23 18:59:41','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ3b2xhZGUiLCJleHAiOjE2ODQ4NDMxODF9.GZlSCLUlMmzHfesb7FstBTFb_mVHs53i0Vz56Hz0hqk',45),(1469,'2023-05-23 19:06:49','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ3b2xhZGUiLCJleHAiOjE2ODQ4NDM2MDl9.knKzUZnQbVA7JX25j4pnkPY0gQhIrmCKHCJV5v3iWzI',45),(1474,'2023-05-24 14:33:08','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqbmt6b25lQGdtYWlsLmNvbSIsImV4cCI6MTY4NDkxMzU4OH0.zeVOHJBTB0si_JuHikCVDdmEVr4alGlhU0KWKGeXAY0',41),(1478,'2023-05-24 21:20:17','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqbmt6b25lQGdtYWlsLmNvbSIsImV4cCI6MTY4NDkzODAxNn0.-REyctRGiwKvp93sEp7knfs7RmCgzxzk2-ZDpFkZtoo',41),(1479,'2023-05-24 21:25:10','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwaHUiLCJleHAiOjE2ODQ5MzgzMTB9.j01HXvEtjadH9ovv5AejhHaEJP-4LP-2l65y3cVV7-Q',1),(1488,'2023-05-24 22:01:45','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwaHUiLCJleHAiOjE2ODQ5NDA1MDV9.FtenwlHJftF4A0QPyS6cxp6JsqIdaGeEHhwsiQeJlvw',1),(1495,'2023-05-24 22:42:06','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuaGF0YWJjIiwiZXhwIjoxNjg0OTQyOTI1fQ.CGmzJLmWQ7sHp4ND6JgQswCX-pQQuq_ukWJ-dPLNQd0',25),(1497,'2023-05-25 19:05:45','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuaGF0YWJjIiwiZXhwIjoxNjg1MDE2MzQ0fQ.894C-rqCoVZ3-tcw9qkklidwAcEVGu4blF_Y-jDrVvU',25),(1510,'2023-06-08 10:20:08','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJob2FuZ2xvbmciLCJleHAiOjE2ODYxOTQ0MDd9.0DkacBmaZu2gZQIsJx5YP8PWgl6MMrvCzZYR9TgnK_o',46),(1515,'2023-06-10 15:50:26','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJob2FuZyIsImV4cCI6MTY4NjM4NzAyNn0.UguhQWUOL7dYsdYaYPwQDd6tf8gLwA2MhXnpdjqjOYk',37),(1518,'2023-06-21 08:43:28','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJob2FuZ2xvbmciLCJleHAiOjE2ODczMTE4MDd9.u0cNc-Krsh03n73A-e_ixFE1nF9IRnbCp9Mkticn8fE',46),(1528,'2023-07-28 14:16:53','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsb25nIiwiZXhwIjoxNjkwNTI4NjEzfQ.mgPKfo_uZ_HBRRTYFOodPgyn_n3fagHZAX_n4r8y5JE',26),(1530,'2023-07-28 22:05:26','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsb25nIiwiZXhwIjoxNjkwNTU2NzI1fQ.npfQlNm_xUOcgDIHfRzxM7ieET46v3UQAngpKB9H61w',26),(1535,'2024-02-27 14:08:20','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0dW5ndGQyMDM2NDIiLCJleHAiOjE3MDkwMTc2OTl9.QCgWgaOS1BmRmDEBYSCeEFVnjtqO0d8xrDjOIPWDa1g',48),(1536,'2024-06-27 21:08:32','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0dW5ndGQyMDM2NDIiLCJleHAiOjE3MTk0OTczMTJ9.2bO7mH9MO7DvI8D-UvvbSd8vi8x_Flbi1xcwhMYVKWY',48);
/*!40000 ALTER TABLE `refreshtoken` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `unique_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`unique_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'USER'),(2,'ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storage`
--

DROP TABLE IF EXISTS `storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `storage` (
  `id` int NOT NULL AUTO_INCREMENT,
  `storage_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storage`
--

LOCK TABLES `storage` WRITE;
/*!40000 ALTER TABLE `storage` DISABLE KEYS */;
INSERT INTO `storage` VALUES (1,'Vãi thun 2 chiều'),(2,'Vãi lụa'),(3,'Dựa dẻo'),(4,'Nhựa khô'),(5,'Không có chú ý ');
/*!40000 ALTER TABLE `storage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studenttable`
--

DROP TABLE IF EXISTS `studenttable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studenttable` (
  `student_id` varchar(512) NOT NULL,
  `first_name` varchar(512) DEFAULT NULL,
  `last_name` varchar(512) DEFAULT NULL,
  `dob` varchar(512) DEFAULT NULL,
  `address` varchar(512) DEFAULT NULL,
  `phone_num` varchar(512) DEFAULT NULL,
  `admin_id` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  KEY `admin_id` (`admin_id`),
  CONSTRAINT `studenttable_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `admintable` (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studenttable`
--

LOCK TABLES `studenttable` WRITE;
/*!40000 ALTER TABLE `studenttable` DISABLE KEYS */;
INSERT INTO `studenttable` VALUES ('SID01','Emily','Davis','1999-08-04','1614 Cameron Road, Machias','716-258-3346','AID03'),('SID02','Theodore','Rivera','1999-05-29','2645 Stark Hollow Road, Mountain View','970-219-4093','AID04'),('SID03','Luna','Sanders','2000-02-03','4509 Windy Ridge Road, Monroe','812-690-6542','AID03'),('SID04','Penelope','Jordan','1999-02-21','102 Losh Lane, Fort Washington','267-736-3259','AID03'),('SID05','Austin','Vu','1999-12-24','1733 Fowler Avenue, Norcross','678-914-0887','AID01'),('SID06','Joshua','Gupta','2000-09-11','4826 Pooh Bear Lane, Piedmont','803-201-1324','AID06'),('SID07','Ruby','Barnes','1999-02-24','1498 Nelm Street, Beltsville','703-314-2903','AID05'),('SID08','Luke','Martin','2000-06-03','2065 Hart Ridge Road, Saginaw','989-284-4566','AID05'),('SID09','Easton','Huang','2000-02-10','2962 Marcus Street, Huntsville','256-426-2129','AID02'),('SID10','Madeline','Walker','1999-10-23','679 Logan Lane, Lakewood','970-560-4636','AID06'),('SID11','Savannah','Ali','2000-12-11','3177 Peck Street, Whitefield','603-721-1671','AID07'),('SID12','Camila','Rogers','1998-01-04','1146 Bel Meadow Drive, Colton','909-250-5461','AID06'),('SID13','Eli','Jones','1999-06-14','322 Water Street, Walnut Creek','213-925-3208','AID06'),('SID14','Everleigh','Lu','2000-12-27','4819 Corbin Branch Road, Kingsport','423-416-3009','AID03'),('SID15','Robert','Yang','1999-10-20','494 Bassel Street, Morgan City','225-385-4987','AID05'),('SID16','Isabella','Vu','2000-01-28','3272 Edgewood Road, Pine Bluff','870-556-7679','AID04');
/*!40000 ALTER TABLE `studenttable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjecttable`
--

DROP TABLE IF EXISTS `subjecttable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subjecttable` (
  `subject_id` varchar(512) NOT NULL,
  `name` varchar(512) DEFAULT NULL,
  `credits` int DEFAULT NULL,
  `admin_id` varchar(512) DEFAULT NULL,
  `teacher_id` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`subject_id`),
  KEY `admin_id` (`admin_id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `subjecttable_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `admintable` (`admin_id`),
  CONSTRAINT `subjecttable_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `teachertable` (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjecttable`
--

LOCK TABLES `subjecttable` WRITE;
/*!40000 ALTER TABLE `subjecttable` DISABLE KEYS */;
INSERT INTO `subjecttable` VALUES ('SUID01','Algebra',4,'AID03','MT02'),('SUID02','Calculus',4,'AID03','MT02'),('SUID03','Programming',3,'AID01','PT04'),('SUID04','Chemistry',3,'AID05','MT03'),('SUID05','Physics',3,'AID05','MT06'),('SUID06','Biology',4,'AID06','MT07'),('SUID07','Networking',2,'AID01','PT05'),('SUID08','Economics',4,'AID02','PT01'),('SUID09','Geology',3,'AID07','PT02'),('SUID10','Linguistics',2,'AID04','MT05'),('SUID11','Philosophy',2,'AID04','MT01'),('SUID12','Sociology',3,'AID02','PT01'),('SUID13','Robotics',4,'AID01','MT08');
/*!40000 ALTER TABLE `subjecttable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taketable`
--

DROP TABLE IF EXISTS `taketable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taketable` (
  `student_id` varchar(255) NOT NULL,
  `class_id` varchar(255) NOT NULL,
  PRIMARY KEY (`student_id`,`class_id`),
  KEY `class_id` (`class_id`),
  CONSTRAINT `taketable_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `studenttable` (`student_id`),
  CONSTRAINT `taketable_ibfk_2` FOREIGN KEY (`class_id`) REFERENCES `classtable` (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taketable`
--

LOCK TABLES `taketable` WRITE;
/*!40000 ALTER TABLE `taketable` DISABLE KEYS */;
INSERT INTO `taketable` VALUES ('SID04','CID01'),('SID09','CID01'),('SID15','CID01'),('SID07','CID02'),('SID08','CID02'),('SID09','CID02'),('SID02','CID03'),('SID01','CID04'),('SID10','CID04'),('SID12','CID04'),('SID14','CID04'),('SID04','CID05'),('SID05','CID05'),('SID09','CID05'),('SID01','CID06'),('SID03','CID06'),('SID11','CID06'),('SID15','CID06'),('SID02','CID07'),('SID10','CID07'),('SID13','CID07'),('SID07','CID08'),('SID08','CID08'),('SID09','CID08'),('SID16','CID08'),('SID06','CID09'),('SID02','CID10'),('SID06','CID10'),('SID07','CID10'),('SID09','CID10'),('SID12','CID10');
/*!40000 ALTER TABLE `taketable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachertable`
--

DROP TABLE IF EXISTS `teachertable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teachertable` (
  `teacher_id` varchar(512) NOT NULL,
  `first_name` varchar(512) DEFAULT NULL,
  `last_name` varchar(512) DEFAULT NULL,
  `address` varchar(512) DEFAULT NULL,
  `phone_num` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachertable`
--

LOCK TABLES `teachertable` WRITE;
/*!40000 ALTER TABLE `teachertable` DISABLE KEYS */;
INSERT INTO `teachertable` VALUES ('MT01','John','Contreras','2267 Cliffside Drive, Binghamton','607-743-6114'),('MT02','Rylee','Yu','2764 Adams Drive, Angleton','979-723-6575'),('MT03','Piper','Lewis','3402 Hill Croft Farm Road, Sacramento','530-902-9808'),('MT04','Stella','Alexander','3921 Lilac Lane,Savannah','912-598-7660'),('MT05','Addison','Do','1918 Angie Drive, Burbank','818-447-5629'),('MT06','Zoey','Jackson','1333 Lowland Drive, Schaumburg','815-382-8148'),('MT07','John','Chow','2850 Kooter Lane, Matthews','704-744-4313'),('MT08','Ava','Ayala','3682 Coolidge Street, Maitland','561-767-4827'),('PT01','Natalia','Salazar','3319 Walt Nuzum Farm Road, Rochester','585-414-2097'),('PT02','Skylar','Carrillo','1842 Grove Street, Westbury','631-764-8213'),('PT03','Christian','Sanders','2265 Ella Street, Oakland','510-610-9347'),('PT04','Penelope','Coleman','1605 Union Street, Seattle','206-697-8563'),('PT05','Piper','Richardson','3090 Black Oak Hollow Road, Walnut Creek','408-833-7606');
/*!40000 ALTER TABLE `teachertable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachingtable`
--

DROP TABLE IF EXISTS `teachingtable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teachingtable` (
  `teacher_id` varchar(255) NOT NULL,
  `class_id` varchar(255) NOT NULL,
  PRIMARY KEY (`teacher_id`,`class_id`),
  KEY `class_id` (`class_id`),
  CONSTRAINT `teachingtable_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teachertable` (`teacher_id`),
  CONSTRAINT `teachingtable_ibfk_2` FOREIGN KEY (`class_id`) REFERENCES `classtable` (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachingtable`
--

LOCK TABLES `teachingtable` WRITE;
/*!40000 ALTER TABLE `teachingtable` DISABLE KEYS */;
INSERT INTO `teachingtable` VALUES ('MT02','CID01'),('MT07','CID01'),('PT04','CID01'),('MT01','CID02'),('MT04','CID02'),('MT03','CID03'),('MT05','CID03'),('MT02','CID04'),('MT04','CID04'),('MT01','CID05'),('MT08','CID05'),('PT04','CID05'),('MT05','CID06'),('PT03','CID06'),('MT06','CID07'),('MT02','CID08'),('PT02','CID08'),('PT01','CID09'),('PT05','CID09'),('MT05','CID10'),('MT06','CID10'),('PT01','CID10');
/*!40000 ALTER TABLE `teachingtable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `full_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `phone` varchar(13) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'synhatphu3@gmail.com','Phú','0985746756','2023-04-08 14:53:31','2023-04-24 21:25:35'),(25,'rifiweb707@dogemn.com','Pham Nhat Minh','0901234569','2023-04-09 04:25:04','2023-04-09 04:25:04'),(26,'truonghoanglong1308@gmail.com','Trần Đức Tùng','0969777741','2023-04-09 04:25:04','2023-06-28 21:08:40'),(28,'testguestagain@gmail.com','Test Guest Again',NULL,'2023-04-10 14:41:46','2023-04-10 14:41:46'),(29,'ngocsamyd@gmail.com','Sâm Nguyễn Thị Ngọc',NULL,'2023-04-11 13:53:28','2023-04-11 13:53:28'),(32,'hieuhoang25102001td@gmail.com','Hieu Hoang','0776274144','2023-04-12 16:18:27','2023-04-15 15:53:51'),(35,'synhatphu2@gmail.com','Sỳ Nhật Phú','0365768578','2023-04-14 16:02:55','2023-04-15 00:28:51'),(36,'huynhvanvong2002@gmail.com','vọng huỳnh','0987132367','2023-04-15 13:41:25','2023-04-15 13:41:34'),(37,'hieuhoang251001td@gmail.com','Hoàng','0776274144','2023-04-15 13:42:59','2023-04-15 13:42:59'),(38,'longthps16784@fpt.edu.vn','Trần Đức Tùng (FPL HCM)','0969777741','2023-04-15 14:25:48','2023-04-15 14:25:58'),(39,'phusnps19247@fpt.edu.vn','Sy Nhat Phu (FPL HCM)','0394758675','2023-04-15 14:53:46','2023-04-15 14:53:53'),(40,'hieuhvps19146@fpt.edu.vn','Hoàng Văn Hiếu','0776274144','2023-04-15 16:10:12','2023-04-15 16:10:12'),(41,'jnkzone@gmail.com','Tran','0901234567','2023-04-16 16:35:50','2023-04-16 16:36:23'),(42,'tiennt1407@gmail.com','Tien Nguyen','0937923174','2023-04-18 10:03:09','2023-04-18 10:03:46'),(43,'anhnhq1608@gmail.com','Anh Nguyễn',NULL,'2023-04-19 19:19:29','2023-04-19 19:19:29'),(44,'nguyenquoctai872@gmail.com','Tài Nguyễn','0987877222','2023-04-21 16:42:38','2023-04-21 20:13:54'),(45,'wolade8558@snowlash.com','wolade8558@snowlash.com','0898123456','2023-04-23 18:59:37','2023-04-23 18:59:37'),(46,'admin@gmail.com','admin',NULL,'2023-04-23 18:59:37','2023-04-23 18:59:37'),(47,'tung183202a@gmail.com','Tùng Trần',NULL,'2023-06-26 22:54:02','2023-06-26 22:54:02'),(48,'tung1832002a@gmail.com','Trần Đức Tùng','0776274144','2023-06-28 22:04:48','2023-06-28 22:04:48');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wishlist`
--

DROP TABLE IF EXISTS `wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wishlist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_user_prod` (`product_id`,`user_id`),
  KEY `fk_wishlist_product_1` (`product_id`),
  KEY `fk_wishlist_user_1` (`user_id`),
  CONSTRAINT `fk_wishlist_product_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_wishlist_user_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
INSERT INTO `wishlist` VALUES (45,97,29,'2023-04-11 15:45:01','2023-04-11 15:45:01'),(47,95,40,'2023-04-15 16:12:00','2023-04-15 16:12:00'),(48,96,40,'2023-04-15 16:12:01','2023-04-15 16:12:01'),(50,94,26,'2023-04-19 06:37:29','2023-04-19 06:37:29'),(51,95,26,'2023-04-19 06:37:30','2023-04-19 06:37:30'),(52,98,26,'2023-04-21 16:40:24','2023-04-21 16:40:24'),(57,95,1,'2023-04-22 12:48:17','2023-04-22 12:48:17'),(59,94,44,'2023-04-22 14:35:54','2023-04-22 14:35:54'),(60,96,44,'2023-04-22 14:35:59','2023-04-22 14:35:59'),(61,98,45,'2023-04-23 22:48:56','2023-04-23 22:48:56'),(62,105,45,'2023-04-23 22:49:41','2023-04-23 22:49:41'),(63,104,45,'2023-04-23 23:05:39','2023-04-23 23:05:39'),(64,109,45,'2023-04-23 23:05:40','2023-04-23 23:05:40'),(68,105,41,'2023-04-24 22:03:07','2023-04-24 22:03:07'),(69,95,25,'2023-04-24 22:24:49','2023-04-24 22:24:49');
/*!40000 ALTER TABLE `wishlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'electronic_db'
--

--
-- Dumping routines for database 'electronic_db'
--
/*!50003 DROP PROCEDURE IF EXISTS `deleteAllTokenByUserId` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteAllTokenByUserId`(IN userId INT)
BEGIN
          DECLARE count_account INT;
          DECLARE number_deleted INT;
          SELECT COUNT(*) INTO count_account FROM refreshtoken WHERE user_id = userId;
          SET number_deleted = count_account-2;
          IF (count_account > 2) THEN
          delete from refreshtoken where user_id = userId ORDER BY id limit number_deleted;
          END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `order_history` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `order_history`(in id_order_status int, in user_id int,`limit` INTEGER,in `offset` INTEGER)
BEGIN
			IF EXISTS (SELECT * FROM order_status WHERE order_status.id = id_order_status) THEN
				SELECT * FROM orders WHERE orders.`status` = id_order_status and orders.user_id = user_id LIMIT `limit` OFFSET `offset`;
			ELSE
				SELECT * FROM orders WHERE orders.user_id = user_id LIMIT `limit` OFFSET `offset`;
			END IF;
	END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_checkCurrentInvetory` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_checkCurrentInvetory`(in variant_id int, reQty int, out checked int)
begin
	set  checked = 404;
	if exists (select * from product_variant v where v.id = variant_id ) 
    then
		set @inventory := -1;
        select quantity into @inventory from product_variant v where v.id = variant_id;
        select case 
				when @inventory = 0 and reQty = 0 then 1
                when @inventory = 0 then 0
                when reQty = 0 and @inventory > 0 then 202
                when @inventory >=  reQty then 1
                when @inventory <  reQty then (@inventory -  reQty)
                end into checked; 
		end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_reduceVariantQtyInOrderByOrderId` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_reduceVariantQtyInOrderByOrderId`(In orderId int, out updated bool)
begin
if exists (select * from orders o join order_detail od on od.order_id = o.id where o.id = orderId)
then
UPDATE  product_variant v
INNER JOIN  order_detail od
        ON v.id = od.product_variant_id and od.order_id = orderId
SET v.quantity = case when v.quantity = 0 then v.quantity 
						when v.quantity > 0 then v.quantity  - od.quantity
                    end;
set updated = true;
else set updated = false;
end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_sumTotalInCart` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sumTotalInCart`(In cartId int,
    out updated bool)
BEGIN
IF exists (SELECT * FROM cart c join cart_detail d on d.cart_id = c.id WHERE c.id = cartId) THEN
BEGIN
     DECLARE total DOUBLE DEFAULT 0.0;
		set total = (select sum(d.quantity * v.price) 
		from cart_detail d 
		join cart c on d.cart_id = c.id
		join product_variant v on v.id = d.product_variant_id
		where c.id = cartId);        
        update cart set price_sum = total where id = cartId; 
        set updated = true;
END;
ELSE
BEGIN
	IF (select id from cart c where c.id = cartId) then
    begin
     update cart set price_sum = 0.0 where id = cartId; 
     set updated = true;
    end ;
    else
    begin
	set updated = false;
    end;
    end if;
END;
END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_updateCartByInventory` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_updateCartByInventory`(IN cartId int, OUT ischanged bool)
BEGIN
	DECLARE finished INTEGER DEFAULT 0;
	DECLARE cartDetailId int DEFAULT 0;
	DECLARE cartQty int DEFAULT 0;
	DECLARE flag int default 404;

	-- declare cursor for cart-detail id that has cart_id match param cartId
	DEClARE userCart 
		CURSOR FOR 
			SELECT cd.id  FROM cart c JOIN cart_detail cd on c.id = cd.cart_id where c.id = cartId ;

	-- declare NOT FOUND handler
	DECLARE CONTINUE HANDLER 
        FOR NOT FOUND SET finished = 1;

	OPEN userCart;

	updatedCart: LOOP
		FETCH userCart INTO cartDetailId;
		IF finished = 1 THEN 
			LEAVE updatedCart;
		END IF;
		-- call update cart
        Call sp_updateCartDetailByInventory(cartId, cartDetailId, flag);
        if(flag <= 0 or flag = 202) then begin set ischanged = true; end ;
        end if;
	END LOOP updatedCart;
	CLOSE userCart;
	select case 
		when ischanged is null then false
        when ischanged = false then false
        when ischanged = true then true
        end into ischanged;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_updateCartByVariantStatus` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_updateCartByVariantStatus`(in cartId int, out isRemoved bool)
begin
	DECLARE finished INTEGER DEFAULT 0;
	DECLARE cartDetailId int DEFAULT 0;
	DECLARE flag int default 404;

	-- declare cursor for cart_detail by cart_id
	DEClARE userCart 
		CURSOR FOR 
			SELECT cd.id  FROM cart c JOIN cart_detail cd on c.id = cd.cart_id where c.id = cartId ;

	-- declare NOT FOUND handler
	DECLARE CONTINUE HANDLER 
        FOR NOT FOUND SET finished = 1;

	OPEN userCart;

	updatedCart: LOOP
		FETCH userCart INTO cartDetailId;
		IF finished = 1 THEN 
			LEAVE updatedCart;
		END IF;
		-- class update cart
        Call sp_updateCartDetailByVariantStatus(cartId, cartDetailId, flag);
        if(flag = true) then begin set  isRemoved = true; end ;
        end if;
	END LOOP updatedCart;
	CLOSE userCart;
	select case 
		when  isRemoved is null then false
        when  isRemoved = false then false
        when  isRemoved = true then true
        end into  isRemoved;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_updateCartDetailByInventory` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_updateCartDetailByInventory`(In cartId int, cartDetailId int,out updated int)
begin
if exists (select * from cart c join cart_detail cd on c.id = cd.cart_id where c.id = cartId)
then begin
	set @checked :=404;
	set @variantId := 0;
    set @updatedSum := false;
    select product_variant_id into @variantId from cart_detail where id = cartDetailId;
	set @reQty := 0;
    select quantity into @reQty from cart_detail where id = cartDetailId;
	call sp_checkCurrentInvetory(@variantId, @reQty, @checked);
		if(@checked != 404 and (@checked = 0 or @checked <= -5)) 
			then begin
				update cart_detail set quantity = 0 where id = cartDetailId;
				-- delete from cart_detail where id = cartDetailId;
                call sp_sumTotalInCart(cartId,@updatedSum);
                set updated = @checked;
			end;
		elseif(@checked != 404 and @checked < 0) 
			then begin
			update cart_detail set quantity = quantity + @checked where id = cartDetailId;
             call sp_sumTotalInCart(cartId,@updatedSum);
            set updated = @checked;
			end;
        elseif(@checked  = 202) 
        then begin
			update cart_detail set quantity = 1 where id = cartDetailId;
             call sp_sumTotalInCart(cartId,@updatedSum);
            set updated = @checked;
        end;
		else begin
				set updated = @checked;
			end;
       end if;  
end;       
else begin set updated = 404; end;
end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_updateCartDetailByVariantStatus` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_updateCartDetailByVariantStatus`(in cartId int,cartDetailId int, out isRemoved bool)
begin
    if exists (select * from cart c join cart_detail cd on c.id = cd.cart_id where c.id = cartId and cd.id = cartDetailId) 
    then begin
		set @v_status = true;
        set @variant_id = 0;
        set @sum = true;
        select v.id into @variant_id from product_variant v join cart_detail d on d.product_variant_id = v.id where d.id = cartDetailId;
		select v.status into @v_status from product_variant v where v.id = @variant_id;
        if(@v_status = false) then
        begin
			delete from cart_detail where id = cartDetailId;
            set isRemoved = true;
            call sp_sumTotalInCart(cartId, @sum);
        end;
        else begin  set isRemoved = false; end;
        end if;
    end;
    end if;
	select case 
		when isRemoved  is null then false
        when isRemoved  = false then false
        when isRemoved  = true then true
        end into isRemoved ;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-28 22:13:04
