-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: 14.225.210.86    Database: electronic_db
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
-- Table structure for table `account`
--
USE electronic_db;
DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `unique_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `last_access` datetime DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`unique_id`) USING BTREE,
  UNIQUE KEY `user_id` (`user_id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE,
  KEY `fk_account_user_1` (`unique_id`) USING BTREE,
  CONSTRAINT `fk_account_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`unique_id`, `username`, `password`, `create_date`, `update_date`, `active`, `last_access`, `user_id`) VALUES (1,'phu','$2a$10$VbGUM8Z2CjKOXbBJ9HLMg.z7vu6IusJknVjJR06DModLFTRwuYv5O','2023-04-08 23:12:58',NULL,_binary '',NULL,1),(14,'nhatabc','$2a$10$fDVM77ls5JU5xysMPh9ubOjmKuXuuKI6zUC/3balaojIHJyGhv7f.',NULL,NULL,_binary '',NULL,25),(15,'long','$2a$09$3v/9yLYrM6t.OinpvQm.A.BlRWHD7pR/P88mT9tRkezxak6NZwt7S',NULL,NULL,_binary '',NULL,26);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_account` AFTER INSERT ON `account` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'account'; 						SET @pk_d = CONCAT('<unique_id>',NEW.`unique_id`,'</unique_id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_account` AFTER UPDATE ON `account` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'account';						SET @pk_d_old = CONCAT('<unique_id>',OLD.`unique_id`,'</unique_id>');						SET @pk_d = CONCAT('<unique_id>',NEW.`unique_id`,'</unique_id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_account` AFTER DELETE ON `account` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'account';						SET @pk_d = CONCAT('<unique_id>',OLD.`unique_id`,'</unique_id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `wards` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `district` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `address_line` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `postal_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `is_default` bit(1) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_user_address_1` (`user_id`) USING BTREE,
  CONSTRAINT `fk_user_address_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` (`id`, `wards`, `district`, `address_line`, `province`, `postal_id`, `is_default`, `user_id`) VALUES (1,'Tân chánh hiệp','Quận 12','đường','Hồ Chí Minh','00000000',_binary '',1),(2,'Tân chánh hiệp','Quận 12','đường','Hồ Chí Minh','00000000',_binary '',26);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_address` AFTER INSERT ON `address` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'address'; 						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_address` AFTER UPDATE ON `address` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'address';						SET @pk_d_old = CONCAT('<id>',OLD.`id`,'</id>');						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_address` AFTER DELETE ON `address` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'address';						SET @pk_d = CONCAT('<id>',OLD.`id`,'</id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
  PRIMARY KEY (`unique_id`) USING BTREE,
  KEY `fk_authority_account_1` (`account_id`) USING BTREE,
  KEY `fk_authority_role_1` (`role_id`) USING BTREE,
  CONSTRAINT `fk_authority_account_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`unique_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_authority_role_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`unique_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` (`unique_id`, `role_id`, `account_id`) VALUES (2,2,1),(12,2,14),(14,2,15);
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_authority` AFTER INSERT ON `authority` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'authority'; 						SET @pk_d = CONCAT('<unique_id>',NEW.`unique_id`,'</unique_id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_authority` AFTER UPDATE ON `authority` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'authority';						SET @pk_d_old = CONCAT('<unique_id>',OLD.`unique_id`,'</unique_id>');						SET @pk_d = CONCAT('<unique_id>',NEW.`unique_id`,'</unique_id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_authority` AFTER DELETE ON `authority` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'authority';						SET @pk_d = CONCAT('<unique_id>',OLD.`unique_id`,'</unique_id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand` (
  `id` int NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` (`id`, `brand_name`, `image`, `created_date`, `updated_date`) VALUES (1,'Samsung',NULL,NULL,NULL),(2,'Apple',NULL,'2023-04-08 14:50:47',NULL),(5,'Huawei',NULL,NULL,NULL),(77,'OPPO',NULL,NULL,NULL),(78,'DELL',NULL,NULL,NULL),(79,'Sony',NULL,NULL,NULL);
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_brand` AFTER INSERT ON `brand` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'brand'; 						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_brand` AFTER UPDATE ON `brand` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'brand';						SET @pk_d_old = CONCAT('<id>',OLD.`id`,'</id>');						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_brand` AFTER DELETE ON `brand` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'brand';						SET @pk_d = CONCAT('<id>',OLD.`id`,'</id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `user_id` (`user_id`) USING BTREE,
  KEY `fk_user_cart_1` (`user_id`) USING BTREE,
  CONSTRAINT `fk_user_cart_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` (`id`, `user_id`, `create_date`, `price_sum`) VALUES (1,1,'2023-04-09 15:51:25',0),(2,26,'2023-04-09 19:38:45',54390000),(6,28,'2023-04-10 14:41:46',44280000),(7,29,'2023-04-11 13:53:28',45980000),(10,32,'2023-04-12 16:18:27',22990000),(12,25,'2023-04-12 16:52:53',184670000),(13,35,'2023-04-14 16:02:55',0);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_cart` AFTER INSERT ON `cart` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'cart'; 						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_cart` AFTER UPDATE ON `cart` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'cart';						SET @pk_d_old = CONCAT('<id>',OLD.`id`,'</id>');						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_cart` AFTER DELETE ON `cart` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'cart';						SET @pk_d = CONCAT('<id>',OLD.`id`,'</id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_cart_detail_product_variant_1` (`product_variant_id`) USING BTREE,
  KEY `fk_cart_detail_cart_1` (`cart_id`) USING BTREE,
  CONSTRAINT `fk_cart_detail_cart_1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_cart_detail_product_variant_1` FOREIGN KEY (`product_variant_id`) REFERENCES `product_variant` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_detail`
--

LOCK TABLES `cart_detail` WRITE;
/*!40000 ALTER TABLE `cart_detail` DISABLE KEYS */;
INSERT INTO `cart_detail` (`id`, `cart_id`, `quantity`, `create_date`, `product_variant_id`) VALUES (45,6,1,'2023-04-10 14:41:48',59),(46,6,1,'2023-04-10 14:41:48',57),(47,7,2,'2023-04-11 15:44:01',65),(60,2,1,'2023-04-12 15:07:56',68),(61,2,1,'2023-04-12 15:08:07',59),(68,10,1,'2023-04-12 16:18:37',65),(76,12,2,'2023-04-12 18:00:17',68),(99,12,3,'2023-04-15 02:17:01',56),(100,12,3,'2023-04-15 02:17:01',58);
/*!40000 ALTER TABLE `cart_detail` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_cart_detail` AFTER INSERT ON `cart_detail` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'cart_detail'; 						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_cart_detail` AFTER UPDATE ON `cart_detail` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'cart_detail';						SET @pk_d_old = CONCAT('<id>',OLD.`id`,'</id>');						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_cart_detail` AFTER DELETE ON `cart_detail` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'cart_detail';						SET @pk_d = CONCAT('<id>',OLD.`id`,'</id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `parent_id` int DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_category_category_1` (`parent_id`) USING BTREE,
  CONSTRAINT `fk_category_category_1` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`id`, `category_name`, `parent_id`, `create_date`, `update_date`) VALUES (1,'Điện thoại',NULL,NULL,'2023-04-10 15:52:22'),(2,'Laptop',NULL,'2023-04-08 14:49:06','2023-04-08 14:49:50'),(3,'PC',NULL,NULL,'2023-04-10 16:55:30'),(4,'Iphone (IOS)',1,'2023-04-08 14:49:12',NULL),(6,'Android',1,'2023-04-08 14:49:18',NULL),(7,'Laptop gaming',2,'2023-04-08 14:49:21',NULL),(8,'Laptop văn phòng',2,'2023-04-08 14:49:25',NULL),(36,'PC văn phòng',3,'2023-04-10 16:56:20','2023-04-10 16:56:20'),(39,'PC Gaming',3,'2023-04-10 17:22:07','2023-04-10 17:22:07'),(41,'PC live stream',3,'2023-04-10 17:32:08','2023-04-10 17:32:08'),(42,'Tai nghe',NULL,'2023-04-10 18:01:45','2023-04-10 18:01:45'),(43,'Tai nghe không dây',42,'2023-04-10 18:02:00','2023-04-10 18:02:00');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_category` AFTER INSERT ON `category` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'category'; 						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_category` AFTER UPDATE ON `category` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'category';						SET @pk_d_old = CONCAT('<id>',OLD.`id`,'</id>');						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_category` AFTER DELETE ON `category` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'category';						SET @pk_d = CONCAT('<id>',OLD.`id`,'</id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `color`
--

DROP TABLE IF EXISTS `color`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `color` (
  `id` int NOT NULL AUTO_INCREMENT,
  `color_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `color_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `color`
--

LOCK TABLES `color` WRITE;
/*!40000 ALTER TABLE `color` DISABLE KEYS */;
INSERT INTO `color` (`id`, `color_name`, `color_code`) VALUES (1,'Màu đen','#000'),(2,'Trắng','#fff'),(3,'Xanh','#00ff00'),(4,'Đỏ','#ff0000'),(5,'Vàng','#'),(6,'Cam','#'),(7,'Xanh lá','#'),(8,'Tím','#'),(9,'Hồng','#'),(10,'Bạc','#');
/*!40000 ALTER TABLE `color` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_color` AFTER INSERT ON `color` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'color'; 						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_color` AFTER UPDATE ON `color` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'color';						SET @pk_d_old = CONCAT('<id>',OLD.`id`,'</id>');						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_color` AFTER DELETE ON `color` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'color';						SET @pk_d = CONCAT('<id>',OLD.`id`,'</id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`next_val`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1271 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES (1270);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_hibernate_sequence` AFTER INSERT ON `hibernate_sequence` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'hibernate_sequence'; 						SET @pk_d = CONCAT('<next_val>',NEW.`next_val`,'</next_val>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_hibernate_sequence` AFTER UPDATE ON `hibernate_sequence` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'hibernate_sequence';						SET @pk_d_old = CONCAT('<next_val>',OLD.`next_val`,'</next_val>');						SET @pk_d = CONCAT('<next_val>',NEW.`next_val`,'</next_val>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_hibernate_sequence` AFTER DELETE ON `hibernate_sequence` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'hibernate_sequence';						SET @pk_d = CONCAT('<next_val>',OLD.`next_val`,'</next_val>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `history_store`
--

DROP TABLE IF EXISTS `history_store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history_store` (
  `timemark` timestamp NOT NULL,
  `table_name` tinytext COLLATE utf8mb3_bin NOT NULL,
  `pk_date_src` text COLLATE utf8mb3_bin NOT NULL,
  `pk_date_dest` text COLLATE utf8mb3_bin NOT NULL,
  `record_state` int NOT NULL,
  PRIMARY KEY (`table_name`(85),`pk_date_dest`(85))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history_store`
--

LOCK TABLES `history_store` WRITE;
/*!40000 ALTER TABLE `history_store` DISABLE KEYS */;
INSERT INTO `history_store` (`timemark`, `table_name`, `pk_date_src`, `pk_date_dest`, `record_state`) VALUES ('2023-04-14 21:58:29','cart','<id>11</id>','<id>11</id>',3),('2023-04-15 09:16:57','cart','<id>12</id>','<id>12</id>',2),('2023-04-14 23:02:50','cart','<id>13</id>','<id>13</id>',1),('2023-04-14 16:43:57','cart','<id>1</id>','<id>1</id>',2),('2023-04-14 22:34:22','cart','<id>2</id>','<id>2</id>',2),('2023-04-15 09:21:19','cart','<id>4</id>','<id>4</id>',3),('2023-04-15 09:16:56','cart_detail','<id>100</id>','<id>100</id>',1),('2023-04-14 22:34:21','cart_detail','<id>60</id>','<id>60</id>',2),('2023-04-14 22:33:51','cart_detail','<id>61</id>','<id>61</id>',2),('2023-04-15 09:21:13','cart_detail','<id>67</id>','<id>67</id>',3),('2023-04-14 21:58:21','cart_detail','<id>73</id>','<id>73</id>',3),('2023-04-13 14:51:28','cart_detail','<id>76</id>','<id>76</id>',2),('2023-04-15 09:16:55','cart_detail','<id>99</id>','<id>99</id>',1),('2023-04-15 09:44:21','hibernate_sequence','<next_val>1270</next_val>','<next_val>1214</next_val>',2),('2023-04-14 15:23:45','notification','<id>57</id>','<id>57</id>',3),('2023-04-14 15:23:45','notification','<id>58</id>','<id>58</id>',3),('2023-04-14 15:23:46','notification','<id>59</id>','<id>59</id>',3),('2023-04-14 15:15:13','notification','<id>73</id>','<id>73</id>',1),('2023-04-14 15:18:22','notification','<id>74</id>','<id>74</id>',1),('2023-04-14 15:18:47','notification','<id>75</id>','<id>75</id>',1),('2023-04-14 15:19:06','notification','<id>76</id>','<id>76</id>',1),('2023-04-14 15:25:14','notification','<id>77</id>','<id>77</id>',1),('2023-04-14 16:43:57','notification','<id>78</id>','<id>78</id>',1),('2023-04-13 17:53:53','opt_register','<username>123</username>','<username>123</username>',1),('2023-04-13 16:44:22','opt_register','<username></username>','<username></username>',2),('2023-04-14 22:42:01','opt_register','<username>string</username>','<username>string</username>',1),('2023-04-14 13:28:18','opt_register','<username>tainguyen</username>','<username>tainguyen</username>',1),('2023-04-14 21:58:07','order_detail','<id>40</id>','<id>40</id>',3),('2023-04-14 21:58:07','order_detail','<id>41</id>','<id>41</id>',3),('2023-04-13 15:05:35','order_detail','<id>43</id>','<id>43</id>',2),('2023-04-13 20:54:34','order_detail','<id>44</id>','<id>44</id>',1),('2023-04-13 20:56:46','order_detail','<id>45</id>','<id>45</id>',1),('2023-04-13 23:23:17','order_detail','<id>46</id>','<id>46</id>',1),('2023-04-13 23:23:17','order_detail','<id>47</id>','<id>47</id>',1),('2023-04-13 23:23:17','order_detail','<id>48</id>','<id>48</id>',1),('2023-04-14 14:38:17','order_detail','<id>49</id>','<id>49</id>',1),('2023-04-14 14:39:37','order_detail','<id>50</id>','<id>50</id>',1),('2023-04-14 14:43:02','order_detail','<id>51</id>','<id>51</id>',1),('2023-04-14 14:47:47','order_detail','<id>52</id>','<id>52</id>',1),('2023-04-14 14:49:08','order_detail','<id>53</id>','<id>53</id>',1),('2023-04-14 14:52:00','order_detail','<id>55</id>','<id>55</id>',1),('2023-04-14 14:53:13','order_detail','<id>56</id>','<id>56</id>',1),('2023-04-14 14:54:47','order_detail','<id>57</id>','<id>57</id>',1),('2023-04-14 14:59:39','order_detail','<id>58</id>','<id>58</id>',1),('2023-04-14 15:15:13','order_detail','<id>59</id>','<id>59</id>',1),('2023-04-14 15:18:22','order_detail','<id>60</id>','<id>60</id>',1),('2023-04-14 15:18:47','order_detail','<id>61</id>','<id>61</id>',1),('2023-04-14 15:19:05','order_detail','<id>62</id>','<id>62</id>',1),('2023-04-14 15:25:14','order_detail','<id>63</id>','<id>63</id>',1),('2023-04-14 16:43:56','order_detail','<id>64</id>','<id>64</id>',1),('2023-04-14 21:58:11','orders','<id>22</id>','<id>22</id>',3),('2023-04-14 22:26:18','orders','<id>23</id>','<id>23</id>',2),('2023-04-14 21:23:57','orders','<id>24</id>','<id>24</id>',2),('2023-04-13 23:01:02','orders','<id>25</id>','<id>25</id>',1),('2023-04-13 23:00:35','orders','<id>26</id>','<id>26</id>',1),('2023-04-14 21:23:59','orders','<id>27</id>','<id>27</id>',1),('2023-04-14 21:24:00','orders','<id>28</id>','<id>28</id>',1),('2023-04-14 21:24:03','orders','<id>29</id>','<id>29</id>',1),('2023-04-14 21:24:04','orders','<id>30</id>','<id>30</id>',1),('2023-04-14 21:24:05','orders','<id>31</id>','<id>31</id>',1),('2023-04-14 14:50:20','orders','<id>32</id>','<id>32</id>',1),('2023-04-14 21:24:06','orders','<id>33</id>','<id>33</id>',1),('2023-04-14 14:52:49','orders','<id>34</id>','<id>34</id>',1),('2023-04-14 14:53:28','orders','<id>35</id>','<id>35</id>',1),('2023-04-14 14:55:22','orders','<id>36</id>','<id>36</id>',1),('2023-04-14 15:01:44','orders','<id>37</id>','<id>37</id>',1),('2023-04-14 21:24:08','orders','<id>38</id>','<id>38</id>',1),('2023-04-14 21:24:11','orders','<id>39</id>','<id>39</id>',1),('2023-04-14 15:19:32','orders','<id>40</id>','<id>40</id>',1),('2023-04-14 15:23:19','orders','<id>41</id>','<id>41</id>',1),('2023-04-14 21:24:14','orders','<id>42</id>','<id>42</id>',1),('2023-04-14 16:43:56','orders','<id>43</id>','<id>43</id>',1),('2023-04-15 09:26:07','product','<id>100</id>','<id>100</id>',2),('2023-04-15 09:28:31','product','<id>103</id>','<id>103</id>',3),('2023-04-15 09:26:08','product','<id>104</id>','<id>104</id>',2),('2023-04-15 09:26:11','product','<id>105</id>','<id>105</id>',2),('2023-04-15 10:15:17','product','<id>109</id>','<id>109</id>',1),('2023-04-15 10:10:08','product','<id>93</id>','<id>93</id>',2),('2023-04-15 10:10:11','product','<id>94</id>','<id>94</id>',2),('2023-04-15 10:10:14','product','<id>95</id>','<id>95</id>',2),('2023-04-15 10:04:04','product','<id>96</id>','<id>96</id>',2),('2023-04-15 10:04:01','product','<id>97</id>','<id>97</id>',2),('2023-04-15 10:03:56','product','<id>98</id>','<id>98</id>',2),('2023-04-15 09:26:02','product','<id>99</id>','<id>99</id>',2),('2023-04-14 15:09:01','product_variant','<id>55</id>','<id>55</id>',2),('2023-04-14 14:36:19','product_variant','<id>56</id>','<id>56</id>',2),('2023-04-14 14:43:08','product_variant','<id>57</id>','<id>57</id>',2),('2023-04-14 14:39:53','product_variant','<id>65</id>','<id>65</id>',2),('2023-04-14 15:25:26','product_variant','<id>66</id>','<id>66</id>',2),('2023-04-14 15:18:30','product_variant','<id>67</id>','<id>67</id>',2),('2023-04-14 15:15:34','product_variant','<id>70</id>','<id>70</id>',2),('2023-04-15 09:30:33','product_variant','<id>71</id>','<id>71</id>',1),('2023-04-15 10:16:50','product_variant','<id>72</id>','<id>72</id>',1),('2023-04-15 09:25:05','promotion_product','<id>10</id>','<id>10</id>',3),('2023-04-15 09:25:05','promotion_product','<id>11</id>','<id>11</id>',3),('2023-04-14 21:23:33','promotion_product','<id>12</id>','<id>12</id>',3),('2023-04-15 10:01:28','promotion_product','<id>1</id>','<id>1</id>',2),('2023-04-15 10:02:38','promotion_product','<id>2</id>','<id>2</id>',2),('2023-04-15 10:02:42','promotion_product','<id>3</id>','<id>3</id>',2),('2023-04-15 10:12:29','promotion_product','<id>4</id>','<id>4</id>',1),('2023-04-15 10:10:00','promotion_product','<id>5</id>','<id>5</id>',1),('2023-04-14 21:23:23','promotion_product','<id>6</id>','<id>6</id>',3),('2023-04-14 21:23:23','promotion_product','<id>7</id>','<id>7</id>',3),('2023-04-15 09:25:05','promotion_product','<id>8</id>','<id>8</id>',3),('2023-04-15 09:25:05','promotion_product','<id>9</id>','<id>9</id>',3),('2023-04-13 23:22:25','promotion_user','<id>2</id>','<id>2</id>',2),('2023-04-14 21:55:35','promotion_user','<id>3</id>','<id>3</id>',2),('2023-04-14 21:55:37','promotion_user','<id>4</id>','<id>4</id>',2),('2023-04-14 21:55:38','promotion_user','<id>6</id>','<id>6</id>',2),('2023-04-14 21:55:41','promotion_user','<id>7</id>','<id>7</id>',2),('2023-04-14 15:20:35','rating','<id>41</id>','<id>41</id>',1),('2023-04-14 16:06:02','rating','<id>42</id>','<id>42</id>',1),('2023-04-14 21:58:36','refreshtoken','<id>1205</id>','<id>1205</id>',3),('2023-04-14 21:58:36','refreshtoken','<id>1209</id>','<id>1209</id>',3),('2023-04-13 14:09:36','refreshtoken','<id>1210</id>','<id>1210</id>',3),('2023-04-13 14:46:50','refreshtoken','<id>1211</id>','<id>1211</id>',3),('2023-04-14 21:58:37','refreshtoken','<id>1212</id>','<id>1212</id>',3),('2023-04-13 11:01:55','refreshtoken','<id>1213</id>','<id>1213</id>',3),('2023-04-13 12:04:45','refreshtoken','<id>1214</id>','<id>1214</id>',3),('2023-04-15 09:27:43','refreshtoken','<id>1268</id>','<id>1268</id>',1),('2023-04-15 09:44:21','refreshtoken','<id>1269</id>','<id>1269</id>',1),('2023-04-14 22:11:41','user','<id>1</id>','<id>1</id>',2),('2023-04-15 09:22:17','user','<id>22</id>','<id>22</id>',3),('2023-04-15 00:28:47','user','<id>35</id>','<id>35</id>',1),('2023-04-15 09:22:10','wishlist','<id>43</id>','<id>43</id>',3);
/*!40000 ALTER TABLE `history_store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `id` int NOT NULL AUTO_INCREMENT,
  `heading` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `subtitle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` (`id`, `heading`, `title`, `path`, `subtitle`, `timestamp`) VALUES (73,'Thông báo đơn hàng','Khách hàng Nhật Phú đã đặt hàng!','order','Số lượng sản phẩm: 1','2023-04-14 15:15:18'),(74,'Thông báo đơn hàng','Khách hàng Nhật Phú đã đặt hàng!','order','Số lượng sản phẩm: 1','2023-04-14 15:18:26'),(75,'Thông báo đơn hàng','Khách hàng Nhật Phú đã đặt hàng!','order','Số lượng sản phẩm: 1','2023-04-14 15:18:52'),(76,'Thông báo đơn hàng','Khách hàng Nhật Phú đã đặt hàng!','order','Số lượng sản phẩm: 1','2023-04-14 15:19:10'),(77,'Thông báo đơn hàng','Khách hàng Nhật Phú đã đặt hàng!','order','Số lượng sản phẩm: 1','2023-04-14 15:25:19'),(78,'Thông báo đơn hàng','Khách hàng Nhật Phú đã đặt hàng!','order','Số lượng sản phẩm: 1','2023-04-14 16:44:01');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_notification` AFTER INSERT ON `notification` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'notification'; 						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_notification` AFTER UPDATE ON `notification` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'notification';						SET @pk_d_old = CONCAT('<id>',OLD.`id`,'</id>');						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_notification` AFTER DELETE ON `notification` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'notification';						SET @pk_d = CONCAT('<id>',OLD.`id`,'</id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `opt_register`
--

DROP TABLE IF EXISTS `opt_register`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `opt_register` (
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `full_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `otp_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `time_expire` datetime DEFAULT NULL,
  `is_verified` bit(1) DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opt_register`
--

LOCK TABLES `opt_register` WRITE;
/*!40000 ALTER TABLE `opt_register` DISABLE KEYS */;
INSERT INTO `opt_register` (`username`, `password`, `email`, `full_name`, `phone`, `otp_code`, `time_expire`, `is_verified`) VALUES ('','$2a$10$9t0k6JIDExocnTNrWxhVK.6446QuGMYQRr.7YsR2JvTLAA2gdZvHK','','','','1301','2023-04-13 09:49:26',_binary '\0'),('123','$2a$10$wxS4H.pGQ1jwwDKlEHZFFez.7OaqY.QurXBQhrd02ZBxGklHPPaTe','nguyenquoctai872@gmail.com','Nguyễn Quốc Tài ','123','3213','2023-04-13 10:58:58',_binary '\0'),('hieu2510','$2a$10$hz/md.d9B8kcxCvc0uWFEu9KKX2/i59JQc6xnWNuxUgp5iKSciPgu','hieuhvps19146@fpt.edu.vn','Hiếu','776274144','4863','2023-04-09 13:07:19',_binary '\0'),('hieuadmin','$2a$10$3O8XABuCK17cTTca7.3GCu/1t8ZFDBDIPt.owz/eNmTtRLe43dKC6','hieuhvps19146@fpt.edu.vn','Hoàng Văn Hiếu','0776274144','9924','2023-04-08 14:44:19',_binary '\0'),('string','$2a$10$VJlMmh/nROvhKX.pytRf8evggBdHNK3XV5vHvjDFfWkzLGTiK7NAq','synhatphu2@gmail.com','string','0344963174','2000','2023-04-14 22:47:05',_binary '\0'),('tainguyen','$2a$10$EXHjc5fVnuiieiK8YoLTP.qfDTYgrWfKcuZQp3guBgcydVylrHmp2','nguyenquoctai872@gmail.com','Nguyễn Quốc Tài ','0987654322','4832','2023-04-14 06:33:22',_binary '\0'),('Test','$2a$10$kB5IHCCbFASSlvgmh1Q4ZeH52HF2b98YO7hAtnF3uafX4F6J0/20.','testguestagain@gmail.com','Test','','7319','2023-04-10 14:46:18',_binary '\0'),('user1','$2a$10$.nONdRp5kmD8wc/YgF6aQeL3EkIF2ahL7te4/ANO8rb2vwvJH1eAO','hieuhoang25102001td@gmail.com','Hiếu','776274144','1265','2023-04-11 01:10:42',_binary '\0'),('vongadmin','$2a$10$oR47K4q.wUNug1s6aP98QOGjfFznLZeMJn9cvdED10naSlx4AfOw.','vong.huynh@aegona.com','vanvong','0987132367','5047','2023-04-08 11:55:37',_binary '\0'),('vonghuynh','$2a$10$quJTv4RH2pqYjFZTRuOC1OQaYaAnNVYjAHsBa08/62rwHL.MlMA5.','ho chi minh','Huỳnh Văn Vọng','987132367','1782','2023-04-12 16:01:57',_binary '\0');
/*!40000 ALTER TABLE `opt_register` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_opt_register` AFTER INSERT ON `opt_register` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'opt_register'; 						SET @pk_d = CONCAT('<username>',NEW.`username`,'</username>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_opt_register` AFTER UPDATE ON `opt_register` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'opt_register';						SET @pk_d_old = CONCAT('<username>',OLD.`username`,'</username>');						SET @pk_d = CONCAT('<username>',NEW.`username`,'</username>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_opt_register` AFTER DELETE ON `opt_register` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'opt_register';						SET @pk_d = CONCAT('<username>',OLD.`username`,'</username>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_order_detail_orders_1` (`order_id`) USING BTREE,
  KEY `fk_order_detail_product_variant_1` (`product_variant_id`) USING BTREE,
  CONSTRAINT `fk_order_detail_orders_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_order_detail_product_variant_1` FOREIGN KEY (`product_variant_id`) REFERENCES `product_variant` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` (`id`, `order_id`, `product_variant_id`, `create_date`, `price_sum`, `promotion_value`, `quantity`) VALUES (42,23,66,'2023-04-13 00:17:29',12990000,6495000,1),(43,24,67,'2023-04-12 17:18:36',8790000,1758000,1),(44,25,68,'2023-04-13 13:54:38',136000000,3400000,1),(45,26,66,'2023-04-13 13:56:50',12990000,6495000,1),(46,27,66,'2023-04-13 23:23:22',12990000,6495000,1),(47,27,56,'2023-04-13 23:23:22',23890000,11945000,1),(48,27,57,'2023-04-13 23:23:22',23890000,11945000,1),(49,28,65,'2023-04-14 14:38:22',22990000,0,1),(50,29,65,'2023-04-14 14:39:42',22990000,0,1),(51,30,57,'2023-04-14 14:43:06',23890000,0,1),(52,31,67,'2023-04-14 14:47:52',8790000,0,1),(53,32,59,'2023-04-14 14:49:13',20390000,0,1),(55,34,67,'2023-04-14 14:52:05',8790000,0,1),(56,35,59,'2023-04-14 14:53:18',20390000,0,1),(57,36,66,'2023-04-14 14:54:52',12990000,0,1),(58,37,58,'2023-04-14 14:59:44',15000000,0,1),(59,38,70,'2023-04-14 15:15:18',29900000,0,1),(60,39,67,'2023-04-14 15:18:26',8790000,0,1),(61,40,57,'2023-04-14 15:18:51',23890000,0,1),(62,41,68,'2023-04-14 15:19:10',34000000,0,1),(63,42,66,'2023-04-14 15:25:19',12990000,0,1),(64,43,65,'2023-04-14 16:44:01',22990000,2299000,1);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_order_detail` AFTER INSERT ON `order_detail` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'order_detail'; 						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_order_detail` AFTER UPDATE ON `order_detail` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'order_detail';						SET @pk_d_old = CONCAT('<id>',OLD.`id`,'</id>');						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_order_detail` AFTER DELETE ON `order_detail` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'order_detail';						SET @pk_d = CONCAT('<id>',OLD.`id`,'</id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `order_status`
--

DROP TABLE IF EXISTS `order_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_status`
--

LOCK TABLES `order_status` WRITE;
/*!40000 ALTER TABLE `order_status` DISABLE KEYS */;
INSERT INTO `order_status` (`id`, `name`, `title`) VALUES (1,'Chờ xác nhận','Chờ người bán xác nhận đơn hàng'),(2,'Đang giao','Đang giao hàng'),(3,'Hoàn thành','Đơn hàng đã được giao thành công'),(4,'Đã hủy','Đã hủy bởi bạn');
/*!40000 ALTER TABLE `order_status` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_order_status` AFTER INSERT ON `order_status` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'order_status'; 						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_order_status` AFTER UPDATE ON `order_status` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'order_status';						SET @pk_d_old = CONCAT('<id>',OLD.`id`,'</id>');						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_order_status` AFTER DELETE ON `order_status` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'order_status';						SET @pk_d = CONCAT('<id>',OLD.`id`,'</id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
  `district` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `address_line` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `postal_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_orders_promotion_user_1` (`promotion_id`) USING BTREE,
  KEY `fk_orders_order_status_1` (`status`) USING BTREE,
  KEY `fk_orders_payment_method_1` (`payment_id`) USING BTREE,
  CONSTRAINT `fk_orders_order_status_1` FOREIGN KEY (`status`) REFERENCES `order_status` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_orders_payment_method_1` FOREIGN KEY (`payment_id`) REFERENCES `payment_method` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_orders_promotion_user_1` FOREIGN KEY (`promotion_id`) REFERENCES `promotion_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`id`, `user_id`, `created_date`, `is_pay`, `payment_id`, `status`, `is_cancelled`, `promotion_id`, `district`, `address_line`, `province`, `postal_id`) VALUES (23,25,'2023-04-13 00:17:29',_binary '',2,2,NULL,NULL,'string','string','string','string'),(24,1,'2023-04-12 17:18:36',_binary '\0',3,4,NULL,2,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(25,1,'2023-04-13 13:54:38',_binary '',3,3,NULL,NULL,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(26,1,'2023-04-13 13:56:50',_binary '',3,3,NULL,NULL,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(27,1,'2023-04-13 23:23:22',_binary '\0',3,4,NULL,2,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(28,1,'2023-04-14 14:38:22',_binary '\0',3,4,NULL,NULL,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(29,1,'2023-04-14 14:39:42',_binary '\0',3,4,NULL,NULL,NULL,'undefined, undefined',NULL,'161294'),(30,1,'2023-04-14 14:43:06',_binary '\0',3,4,NULL,NULL,NULL,'undefined, undefined',NULL,'598141'),(31,1,'2023-04-14 14:47:52',_binary '\0',3,4,NULL,NULL,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(32,1,'2023-04-14 14:49:13',_binary '',3,3,NULL,NULL,NULL,'undefined, undefined',NULL,'411829'),(33,1,'2023-04-14 14:51:13',_binary '\0',3,4,NULL,NULL,NULL,'undefined, undefined',NULL,'559589'),(34,1,'2023-04-14 14:52:05',_binary '',3,3,NULL,NULL,NULL,'undefined, undefined',NULL,'939053'),(35,1,'2023-04-14 14:53:18',_binary '',3,3,NULL,NULL,NULL,'undefined, undefined',NULL,'602233'),(36,1,'2023-04-14 14:54:52',_binary '',3,3,NULL,NULL,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(37,1,'2023-04-14 14:59:44',_binary '',3,3,NULL,NULL,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(38,1,'2023-04-14 15:15:18',_binary '\0',3,4,NULL,NULL,NULL,'undefined, undefined',NULL,'773065'),(39,1,'2023-04-14 15:18:26',_binary '\0',3,4,NULL,NULL,NULL,'undefined, undefined',NULL,'230897'),(40,1,'2023-04-14 15:18:51',_binary '',3,3,NULL,NULL,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000'),(41,1,'2023-04-14 15:19:10',_binary '',3,3,NULL,NULL,NULL,'undefined, undefined',NULL,'963267'),(42,1,'2023-04-14 15:25:19',_binary '\0',3,4,NULL,NULL,NULL,'undefined, undefined',NULL,'691738'),(43,1,'2023-04-14 16:44:01',_binary '\0',3,1,NULL,2,'Quận 12','đường, Tân chánh hiệp','Hồ Chí Minh','00000000');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_orders` AFTER INSERT ON `orders` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'orders'; 						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_orders` AFTER UPDATE ON `orders` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'orders';						SET @pk_d_old = CONCAT('<id>',OLD.`id`,'</id>');						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_orders` AFTER DELETE ON `orders` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'orders';						SET @pk_d = CONCAT('<id>',OLD.`id`,'</id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `payment_method`
--

DROP TABLE IF EXISTS `payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_method` (
  `id` int NOT NULL AUTO_INCREMENT,
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_method`
--

LOCK TABLES `payment_method` WRITE;
/*!40000 ALTER TABLE `payment_method` DISABLE KEYS */;
INSERT INTO `payment_method` (`id`, `method`) VALUES (1,'VISA_CARD'),(2,'MOMO'),(3,'CASH');
/*!40000 ALTER TABLE `payment_method` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_payment_method` AFTER INSERT ON `payment_method` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'payment_method'; 						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_payment_method` AFTER UPDATE ON `payment_method` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'payment_method';						SET @pk_d_old = CONCAT('<id>',OLD.`id`,'</id>');						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_payment_method` AFTER DELETE ON `payment_method` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'payment_method';						SET @pk_d = CONCAT('<id>',OLD.`id`,'</id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `is_delete` bit(1) DEFAULT NULL,
  `brand_id` int DEFAULT NULL,
  `promotion_id` int DEFAULT NULL,
  `type` int DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_product_category_1` (`category_id`) USING BTREE,
  KEY `fk_product_brand_1` (`brand_id`) USING BTREE,
  KEY `fk_promotion_product_product_1` (`promotion_id`) USING BTREE,
  CONSTRAINT `fk_product_brand_1` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_product_category_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_promotion_product_product_1` FOREIGN KEY (`promotion_id`) REFERENCES `promotion_product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id`, `product_name`, `description`, `create_date`, `update_date`, `category_id`, `is_delete`, `brand_id`, `promotion_id`, `type`, `image`) VALUES (93,'Iphone 14 promax','iPhone 14 Pro Max là mẫu flagship nổi bật nhất của Apple trong lần trở lại năm 2022 với nhiều cải tiến về công nghệ cũng như vẻ ngoài cao cấp, sang chảnh hợp với gu thẩm mỹ đại chúng. Những chiếc điện thoại đến từ nhà Táo Khuyết nhận được rất nhiều sự kỳ vọng của thị trường ngay từ khi chưa ra mắt. Vậy liệu những chiếc flagship đến từ công ty công nghệ hàng đầu thế giới này có làm bạn thất vọng? Cùng khám phá những điều thú vị về iPhone 14 Pro Max ở bài viết dưới đây nhé.','2023-04-08 08:15:15','2023-04-09 15:15:02',4,_binary '\0',2,5,NULL,'product-93.png'),(94,'Samsung S20 Ultra','Samsung Galaxy S20 là chiếc điện thoại với thiết kế màn hình tràn viền không khuyết điểm, camera sau ấn tượng, hiệu năng khủng cùng nhiều những đột phá công nghệ nổi bật, dẫn đầu thế giới.\n','2023-04-08 08:18:06','2023-04-08 08:18:06',6,_binary '\0',1,5,NULL,'product-94.png'),(95,'Samsung Galaxy S23 Ultra','Samsung Galaxy S23 Ultra là điện thoại cao cấp của hãng điện thoại Samsung được ra mắt vào đầu năm 2023. Điện thoại Samsung S23 series mới này sở hữu camera độ phân giải 200MP ấn tượng cùng một khung viền vuông vức sang trọng. Cấu hình máy cũng là một điểm nổi bật với con chip Snapdragon 8 Gen 2 mạnh mẽ, bộ nhớ RAM 8GB mang lại hiệu suất xử lý vượt trội.','2023-04-08 09:40:55','2023-04-08 09:40:55',6,_binary '\0',1,5,NULL,'product-95.png'),(96,'Samsung Galaxy A34 5G','Galaxy A34 5G sở hữu thiết kế đẹp và hiện đại với mặt lưng nhẵn làm từ nhựa cao cấp, màn hình lớn và cụm camera được bố trí theo một hàng dọc bắt mắt. Do sở hữu thiết kế từ nhựa nên máy sẽ có trọng lượng nhẹ hơn giúp mang lại cảm giác cầm nắm nhẹ nhàng.','2023-04-09 07:46:54','2023-04-09 07:52:19',6,_binary '\0',1,4,NULL,'product-96.png'),(97,'Laptop Apple MacBook Air M1 2020','Chiếc MacBook này được trang bị con chip Apple M1 được sản xuất độc quyền bởi Nhà Táo trên tiến trình 5 nm, 8 lõi bao gồm 4 lõi tiết kiệm điện và 4 lõi hiệu suất cao, mang đến một hiệu năng kinh ngạc, xử lý mọi tác vụ văn phòng một cách mượt mà như Word, Excel, Powerpoint,... thực hiện tốt các nhiệm vụ chỉnh sửa hình ảnh, kết xuất 2D trên các phần mềm Photoshop, AI,... máy còn hỗ trợ tiết kiệm được điện năng cao.','2023-04-09 07:51:20','2023-04-09 07:51:20',8,_binary '\0',2,4,NULL,'product-97.png'),(98,'Samsung Galaxy S21 FE 5G','Galaxy S21 FE 5G thiết kế mỏng nhẹ với độ dày 7.9 mm, khối lượng chỉ 177 gram, các góc cạnh bo tròn cho cảm giác hài hòa, mềm mại, kết hợp các tông màu thời thượng gồm tím, xanh lá, xám và trắng giúp bạn dễ dàng tạo nên phong cách riêng đầy cá tính.','2023-04-09 07:54:06','2023-04-09 15:14:51',6,_binary '\0',1,4,NULL,'product-98.png'),(99,'Samsung Galaxy S20 FE','Camera trên S20 FE là 3 cảm biến chất lượng nằm gọn trong mô đun chữ nhật độc đáo ở mặt lưng bao gồm: Camera chính 12 MP cho chất lượng ảnh sắc nét, camera góc siêu rộng 12 MP cung cấp góc chụp tối đa và cuối cùng camera tele 8 MP hỗ trợ zoom quang học 3X.','2023-04-09 07:57:59','2023-04-09 07:57:59',6,_binary '\0',1,4,NULL,'product-99.png'),(100,'Iphone 14','iPhone 14 128GB được xem là mẫu smartphone bùng nổ của nhà táo trong năm 2022, ấn tượng với ngoại hình trẻ trung, màn hình chất lượng đi kèm với những cải tiến về hệ điều hành và thuật toán xử lý hình ảnh, giúp máy trở thành cái tên thu hút được đông đảo người dùng quan tâm tại thời điểm ra mắt.','2023-04-09 08:00:07','2023-04-09 08:00:07',4,_binary '\0',2,4,NULL,'product-100.png'),(104,'Iphone 11','iPhone 11 sở hữu hiệu năng khá mạnh mẽ, ổn định trong thời gian dài nhờ được trang bị chipset A13 Bionic. Màn hình LCD 6.1 inch sắc nét cùng chất lượng hiển thị Full HD của máy cho trải nghiệm hình ảnh mượt mà và có độ tương phản cao. Hệ thống camera hiện đại được tích hợp những tính năng công nghệ mới kết hợp với viên Pin dung lượng 3110mAh, giúp nâng cao trải nghiệm của người dùng.','2023-04-09 20:09:06','2023-04-09 20:09:06',4,_binary '\0',2,4,NULL,'product-104.png'),(105,'Apple Macbook Air M2 2022','Thiết kế sang trọng, lịch lãm - siêu mỏng 11.3mm, chỉ 1.24kg\nHiệu năng hàng đầu - Chip Apple m2, 8 nhân GPU, hỗ trợ tốt các phần mềm như Word, Axel, Adoble Premier\nĐa nhiệm mượt mà - Ram 16GB, SSD 256GB cho phép vừa làm việc, vừa nghe nhạc\nMàn hình sắc nét - Độ phân giải 2560 x 1664 cùng độ sáng 500 nits\nÂm thanh sống động - 4 loa tramg bị công nghệ dolby atmos và âm thanh đa chiều','2023-04-09 13:21:47','2023-04-09 13:21:47',8,_binary '\0',2,4,NULL,'product-105.png'),(109,'OPPO Find N2 Flip','OPPO Find N2 Flip, chiếc điện thoại gập đầu tiên của OPPO được giới thiệu chính thức vào tháng 03/2023. Với cấu hình mạnh mẽ bao gồm con chip Dimensity 9000+ và bộ camera nổi trội, đây được xem là một trong những mẫu điện thoại đáng chú ý ở thời điểm hiện tại khi sở hữu bộ cấu hình tốt trong tầm giá.','2023-04-15 03:15:22','2023-04-15 03:15:22',6,_binary '\0',77,1,NULL,'product-109.png');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_product` AFTER INSERT ON `product` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'product'; 						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_product` AFTER UPDATE ON `product` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'product';						SET @pk_d_old = CONCAT('<id>',OLD.`id`,'</id>');						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_product` AFTER DELETE ON `product` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'product';						SET @pk_d = CONCAT('<id>',OLD.`id`,'</id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `product_attribute`
--

DROP TABLE IF EXISTS `product_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_attribute` (
  `id` int NOT NULL AUTO_INCREMENT,
  `attribute_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `attribute_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_product_attribute_product` (`product_id`) USING BTREE,
  CONSTRAINT `fk_product_attribute_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_attribute`
--

LOCK TABLES `product_attribute` WRITE;
/*!40000 ALTER TABLE `product_attribute` DISABLE KEYS */;
INSERT INTO `product_attribute` (`id`, `attribute_name`, `attribute_value`, `product_id`) VALUES (114,'Pin','3850',93),(115,'Màn hình','Retina',95),(116,'Kích thước màn hình','6.1 inches',95),(117,'Camera sau','Camera kép 12MP: - Camera góc rộng: ƒ/1.8 aperture - Camera siêu rộng: ƒ/2.4 aperture',95),(118,'Pin','4750',94),(119,'Loại card đồ họa','8 nhân GPU, 16 nhân Neural Engine',105),(120,'Dung lượng RAM','16GB',105),(121,'Công nghệ màn hình','Liquid Retina Display',105);
/*!40000 ALTER TABLE `product_attribute` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_product_attribute` AFTER INSERT ON `product_attribute` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'product_attribute'; 						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_product_attribute` AFTER UPDATE ON `product_attribute` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'product_attribute';						SET @pk_d_old = CONCAT('<id>',OLD.`id`,'</id>');						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_product_attribute` AFTER DELETE ON `product_attribute` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'product_attribute';						SET @pk_d = CONCAT('<id>',OLD.`id`,'</id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `product_variant`
--

DROP TABLE IF EXISTS `product_variant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_variant` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sku_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `display_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `color_id` int DEFAULT NULL,
  `storage_id` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `product_id` (`product_id`,`color_id`,`storage_id`) USING BTREE,
  KEY `fk_product_variant_product_1` (`product_id`) USING BTREE,
  KEY `fk_color_product` (`color_id`) USING BTREE,
  KEY `fk_storage_product` (`storage_id`) USING BTREE,
  CONSTRAINT `fk_color_product` FOREIGN KEY (`color_id`) REFERENCES `color` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_product_variant_product_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_storage_product` FOREIGN KEY (`storage_id`) REFERENCES `storage` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_variant`
--

LOCK TABLES `product_variant` WRITE;
/*!40000 ALTER TABLE `product_variant` DISABLE KEYS */;
INSERT INTO `product_variant` (`id`, `sku_name`, `quantity`, `price`, `status`, `product_id`, `image`, `display_name`, `color_id`, `storage_id`) VALUES (55,'IP14PRM',10,29900000,_binary '',93,'productVariant-55','Iphone 14 promax yellow',5,2),(56,'SSU23P',14,23890000,_binary '',95,'productVariant-56','Samsung Galaxy S23 Ultra 256GB',4,3),(57,'SSU23W',52,23890000,_binary '',95,'productVariant-57','Samsung Galaxy S23 Ultra 256GB',2,3),(58,'SSU20B',15,15000000,_binary '',94,'productVariant-58','Samsung S20 Ulra 126GB',1,2),(59,'IP14R',100,20390000,_binary '',100,'productVariant-59','iPhone 14 128GB',4,2),(60,'IP14R256',100,22990000,_binary '',100,'productVariant-60','iPhone 14 256GB',4,3),(61,'IP14R256',100,27990000,_binary '',100,'productVariant-61','iPhone 14 256GB',4,4),(62,'SSA53128',100,8490000,_binary '',96,'productVariant-62','Samsung Galaxy A34 5G 128GB',1,2),(63,'SSA53G128',20,8490000,_binary '',96,'productVariant-63','Samsung Galaxy A34 5G 128GB',3,2),(64,'SSAGR53128',200,8490000,_binary '',96,'productVariant-64','Samsung Galaxy A34 5G 128GB',10,2),(65,'MGND3SA/A',22,22990000,_binary '',97,'productVariant-65','MacBook Air M1 2020 8GB/256GB/7-core GPU (MGND3SA/A)',5,3),(66,'SS21FEGRE',23,12990000,_binary '',98,'productVariant-66','Samsung Galaxy S21 FE 5G',7,2),(67,'SS20FEGRE',23,8790000,_binary '',99,'productVariant-67','Samsung Galaxy S20 FE',7,2),(68,'AM2BAC',15,34000000,_binary '',105,'productVariant-68','Apple Macbook Air M2 2022 16GB 256GB',10,2),(70,'IP14PRMB',11,29900000,_binary '',93,'productVariant-70','Iphone 14 Pro Max Black',1,2),(71,'IP11W128GB',15,12090000,_binary '',104,'productVariant-71','iPhone 11 128GB',2,2),(72,'OOPFF',10,19900000,_binary '',109,'productVariant-72','OPPO Find N2 Flip',1,3);
/*!40000 ALTER TABLE `product_variant` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_product_variant` AFTER INSERT ON `product_variant` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'product_variant'; 						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_product_variant` AFTER UPDATE ON `product_variant` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'product_variant';						SET @pk_d_old = CONCAT('<id>',OLD.`id`,'</id>');						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_product_variant` AFTER DELETE ON `product_variant` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'product_variant';						SET @pk_d = CONCAT('<id>',OLD.`id`,'</id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `maximum_price` double DEFAULT NULL,
  `activate` bit(1) DEFAULT NULL,
  `is_percent` tinyint(1) DEFAULT '1',
  `discount_amount` double DEFAULT '0',
  `discount` double DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion_product`
--

LOCK TABLES `promotion_product` WRITE;
/*!40000 ALTER TABLE `promotion_product` DISABLE KEYS */;
INSERT INTO `promotion_product` (`id`, `expiration_date`, `created_date`, `name`, `updated_date`, `maximum_price`, `activate`, `is_percent`, `discount_amount`, `discount`) VALUES (1,'2023-04-15 23:59:17','2023-04-12 12:26:14','-20%','2023-04-15 10:22:37',200000,_binary '',1,20,20),(2,'2023-04-15 23:59:00','2023-04-10 12:26:06','-10%','2023-04-15 00:00:00',300000,_binary '',1,10,10),(3,'2023-04-15 23:59:07','2023-04-10 16:57:58','Giảm 50%','2023-04-15 00:00:58',0,_binary '',1,50,50),(4,'2023-04-15 23:12:14','2023-04-15 09:25:37','-15%','2023-04-15 09:25:40',NULL,_binary '',1,15,NULL),(5,'2023-04-15 23:09:34',NULL,NULL,'2023-04-15 10:09:54',NULL,_binary '',1,20,NULL);
/*!40000 ALTER TABLE `promotion_product` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_promotion_product` AFTER INSERT ON `promotion_product` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'promotion_product'; 						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_promotion_product` AFTER UPDATE ON `promotion_product` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'promotion_product';						SET @pk_d_old = CONCAT('<id>',OLD.`id`,'</id>');						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_promotion_product` AFTER DELETE ON `promotion_product` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'promotion_product';						SET @pk_d = CONCAT('<id>',OLD.`id`,'</id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `promotion_type`
--

DROP TABLE IF EXISTS `promotion_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotion_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name_promotion_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `condition_minimum` int DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `is_limited` bit(1) DEFAULT NULL,
  `descriptions` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `limited_amount` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion_type`
--

LOCK TABLES `promotion_type` WRITE;
/*!40000 ALTER TABLE `promotion_type` DISABLE KEYS */;
INSERT INTO `promotion_type` (`id`, `name_promotion_type`, `condition_minimum`, `amount`, `is_limited`, `descriptions`, `limited_amount`) VALUES (1,'Giảm theo đơn hàng',NULL,NULL,_binary '\0',NULL,0),(2,'Giảm 10% cho khách mới',NULL,NULL,_binary '\0',NULL,0),(3,'Khách hàng thân thiết',NULL,NULL,_binary '\0',NULL,0),(4,'Voucher tháng 4',25000000,100,_binary '',NULL,0),(5,'Giảm 20% cho đơn hàng trên 1 triệu',1000000,NULL,_binary '\0',NULL,0),(6,'Ưu đãi tháng 3 giảm 100k/đơn hàng',100000,NULL,_binary '',NULL,0),(7,'Sale 15-3',NULL,NULL,_binary '\0',NULL,0),(9,'Hoàn tiền 20% cho mỗi đơn hàng',10000000,0,_binary '\0','Hoàn tiền 20% cho đơn hàng trên 10 triệu',0);
/*!40000 ALTER TABLE `promotion_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_promotion_type` AFTER INSERT ON `promotion_type` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'promotion_type'; 						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_promotion_type` AFTER UPDATE ON `promotion_type` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'promotion_type';						SET @pk_d_old = CONCAT('<id>',OLD.`id`,'</id>');						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_promotion_type` AFTER DELETE ON `promotion_type` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'promotion_type';						SET @pk_d = CONCAT('<id>',OLD.`id`,'</id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `promotion_user`
--

DROP TABLE IF EXISTS `promotion_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotion_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name_promotion_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `discount_value` double DEFAULT '0',
  `is_used` tinyint(1) DEFAULT '0',
  `create_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `expiration_date` datetime DEFAULT NULL,
  `promotion_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `promotion_type` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `is_percent` tinyint(1) DEFAULT '1',
  `is_deleted` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_promotion_user_user_1` (`user_id`) USING BTREE,
  KEY `fk_promotion_user_promotion_type_1` (`promotion_type`) USING BTREE,
  CONSTRAINT `fk_promotion_user_promotion_type_1` FOREIGN KEY (`promotion_type`) REFERENCES `promotion_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_promotion_user_user_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion_user`
--

LOCK TABLES `promotion_user` WRITE;
/*!40000 ALTER TABLE `promotion_user` DISABLE KEYS */;
INSERT INTO `promotion_user` (`id`, `name_promotion_user`, `discount_value`, `is_used`, `create_date`, `start_date`, `expiration_date`, `promotion_code`, `promotion_type`, `user_id`, `is_percent`, `is_deleted`) VALUES (1,'Giảm theo đơn',500000,0,'2023-03-04 12:00:00','2023-01-04 12:00:00','2023-03-06 12:00:00','ORDERSALE5',1,25,0,0),(2,'Ưu đãi khách hàng mới',10,0,'2023-03-04 12:00:00','2023-01-30 12:00:00','2023-06-10 12:00:00','NEWCUSTOMER',2,1,1,0),(3,'Ưu đãi khách hàng mới',10,0,'2023-03-04 12:00:00','2023-03-04 12:00:00','2023-06-10 12:00:00','NEWCUSTOMER',2,NULL,1,0),(4,'Voucher tháng 4',20,0,'2023-03-04 12:00:00','2023-04-01 12:00:00','2023-04-30 23:59:59','APRILSALE',4,NULL,1,0),(5,'Sale 15-3',2000000,0,'2023-03-04 12:00:00','2023-03-04 12:00:00','2023-03-30 12:00:00','MARCH15',7,25,1,1),(6,'Sale 15-4',2000000,0,'2023-03-04 12:00:00','2023-04-01 12:00:00','2023-04-30 12:00:00','HAPPYAPRIL15',4,NULL,0,0),(7,'Hoàn tiền 20%',20,0,'2023-03-04 12:00:00','2023-03-04 12:00:00','2023-09-04 12:00:00','REDEEM20',9,NULL,1,0);
/*!40000 ALTER TABLE `promotion_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_promotion_user` AFTER INSERT ON `promotion_user` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'promotion_user'; 						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_promotion_user` AFTER UPDATE ON `promotion_user` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'promotion_user';						SET @pk_d_old = CONCAT('<id>',OLD.`id`,'</id>');						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_promotion_user` AFTER DELETE ON `promotion_user` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'promotion_user';						SET @pk_d = CONCAT('<id>',OLD.`id`,'</id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `order_detail_id` (`order_detail_id`),
  KEY `fk_rating_product_1` (`product_id`) USING BTREE,
  KEY `fk_rating_order_detail_1` (`order_detail_id`) USING BTREE,
  KEY `fk_rating_user_1` (`user_id`) USING BTREE,
  CONSTRAINT `fk_rating_order_detail_1` FOREIGN KEY (`order_detail_id`) REFERENCES `order_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_rating_product_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_rating_user_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
INSERT INTO `rating` (`id`, `point`, `created_date`, `user_id`, `order_detail_id`, `content`, `product_id`) VALUES (41,5,'2023-04-14 15:20:40',1,57,'',98),(42,5,'2023-04-14 16:06:08',1,61,'',95);
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_rating` AFTER INSERT ON `rating` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'rating'; 						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_rating` AFTER UPDATE ON `rating` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'rating';						SET @pk_d_old = CONCAT('<id>',OLD.`id`,'</id>');						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_rating` AFTER DELETE ON `rating` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'rating';						SET @pk_d = CONCAT('<id>',OLD.`id`,'</id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `refreshtoken`
--

DROP TABLE IF EXISTS `refreshtoken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refreshtoken` (
  `id` int NOT NULL AUTO_INCREMENT,
  `expiry_date` datetime NOT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `tokenToAccount` (`user_id`) USING BTREE,
  CONSTRAINT `refreshtoken_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=1270 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refreshtoken`
--

LOCK TABLES `refreshtoken` WRITE;
/*!40000 ALTER TABLE `refreshtoken` DISABLE KEYS */;
INSERT INTO `refreshtoken` (`id`, `expiry_date`, `token`, `user_id`) VALUES (1268,'2023-05-15 02:27:48','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwaHUiLCJleHAiOjE2ODQxMTc2Njh9.y-Qz1hwd4OB1qtBTc5UDPRE4F_uQm6paQSV-UmhXprA',1),(1269,'2023-05-15 02:44:27','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsb25nIiwiZXhwIjoxNjg0MTE4NjY2fQ.0WQOt1shL38xtQDR3zgJQ2kswtOPi2YJxg2O3NfNjbs',26);
/*!40000 ALTER TABLE `refreshtoken` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_refreshtoken` AFTER INSERT ON `refreshtoken` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'refreshtoken'; 						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_refreshtoken` AFTER UPDATE ON `refreshtoken` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'refreshtoken';						SET @pk_d_old = CONCAT('<id>',OLD.`id`,'</id>');						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_refreshtoken` AFTER DELETE ON `refreshtoken` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'refreshtoken';						SET @pk_d = CONCAT('<id>',OLD.`id`,'</id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `unique_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`unique_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`unique_id`, `role_name`) VALUES (1,'USER'),(2,'ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_role` AFTER INSERT ON `role` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'role'; 						SET @pk_d = CONCAT('<unique_id>',NEW.`unique_id`,'</unique_id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_role` AFTER UPDATE ON `role` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'role';						SET @pk_d_old = CONCAT('<unique_id>',OLD.`unique_id`,'</unique_id>');						SET @pk_d = CONCAT('<unique_id>',NEW.`unique_id`,'</unique_id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_role` AFTER DELETE ON `role` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'role';						SET @pk_d = CONCAT('<unique_id>',OLD.`unique_id`,'</unique_id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `storage`
--

DROP TABLE IF EXISTS `storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `storage` (
  `id` int NOT NULL AUTO_INCREMENT,
  `storage_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storage`
--

LOCK TABLES `storage` WRITE;
/*!40000 ALTER TABLE `storage` DISABLE KEYS */;
INSERT INTO `storage` (`id`, `storage_name`) VALUES (1,'64GB'),(2,'128GB'),(3,'256GB'),(4,'512GB'),(5,'1TB');
/*!40000 ALTER TABLE `storage` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_storage` AFTER INSERT ON `storage` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'storage'; 						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_storage` AFTER UPDATE ON `storage` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'storage';						SET @pk_d_old = CONCAT('<id>',OLD.`id`,'</id>');						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_storage` AFTER DELETE ON `storage` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'storage';						SET @pk_d = CONCAT('<id>',OLD.`id`,'</id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `full_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `email`, `full_name`, `phone`, `create_date`, `update_date`) VALUES (1,'synhatphu3@gmail.com','Phú','0985746756','2023-04-08 14:53:31','2023-04-14 22:11:45'),(25,'rifiweb707@dogemn.com','Pham Nhat Minh','0901234569','2023-04-09 04:25:04','2023-04-09 04:25:04'),(26,'truonghoanglong1308@gmail.com','Truong Hoang Long','0969777741','2023-04-09 04:25:04','2023-04-09 04:25:04'),(28,'testguestagain@gmail.com','Test Guest Again',NULL,'2023-04-10 14:41:46','2023-04-10 14:41:46'),(29,'ngocsamyd@gmail.com','Sâm Nguyễn Thị Ngọc',NULL,'2023-04-11 13:53:28','2023-04-11 13:53:28'),(32,'hieuhoang25102001td@gmail.com','Hieu Hoang',NULL,'2023-04-12 16:18:27','2023-04-12 16:18:27'),(35,'synhatphu2@gmail.com','Sỳ Nhật Phú','0365768578','2023-04-14 16:02:55','2023-04-15 00:28:51');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_user` AFTER INSERT ON `user` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'user'; 						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_user` AFTER UPDATE ON `user` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'user';						SET @pk_d_old = CONCAT('<id>',OLD.`id`,'</id>');						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_user` AFTER DELETE ON `user` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'user';						SET @pk_d = CONCAT('<id>',OLD.`id`,'</id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uq_user_prod` (`product_id`,`user_id`) USING BTREE,
  KEY `fk_wishlist_product_1` (`product_id`) USING BTREE,
  KEY `fk_wishlist_user_1` (`user_id`) USING BTREE,
  CONSTRAINT `fk_wishlist_product_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_wishlist_user_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
INSERT INTO `wishlist` (`id`, `product_id`, `user_id`, `update_date`, `create_date`) VALUES (45,97,29,'2023-04-11 15:45:01','2023-04-11 15:45:01');
/*!40000 ALTER TABLE `wishlist` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_i_wishlist` AFTER INSERT ON `wishlist` FOR EACH ROW BEGIN 						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'cvb'; 						SET @tbl_name = 'wishlist'; 						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>'); 						SET @rec_state = 1;						UPDATE `history_store` SET `pk_date_dest` = `pk_date_src` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d AND (`record_state` = 2 OR `record_state` = 1); 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_dest` = @pk_d; 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`,`record_state` ) 						VALUES (@time_mark, @tbl_name, @pk_d, @pk_d, @rec_state); 						END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_u_wishlist` AFTER UPDATE ON `wishlist` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25195 SECOND); 						SET @tbl_name = 'wishlist';						SET @pk_d_old = CONCAT('<id>',OLD.`id`,'</id>');						SET @pk_d = CONCAT('<id>',NEW.`id`,'</id>');						SET @rec_state = 2;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d_old, @rec_state );						ELSE 						UPDATE `history_store` SET `timemark` = @time_mark, `pk_date_src` = @pk_d WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d_old;						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `a_d_wishlist` AFTER DELETE ON `wishlist` FOR EACH ROW BEGIN						SET @time_mark = DATE_ADD(NOW(), INTERVAL 25196 SECOND); 						SET @tbl_name = 'wishlist';						SET @pk_d = CONCAT('<id>',OLD.`id`,'</id>');						SET @rec_state = 3;						SET @rs = 0;						SELECT `record_state` INTO @rs FROM `history_store` WHERE  `table_name` = @tbl_name AND `pk_date_src` = @pk_d;						IF @rs = 1 THEN 						DELETE FROM `history_store` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs > 1 THEN 						UPDATE `history_store` SET `timemark` = @time_mark, `record_state` = 3, `pk_date_src` = `pk_date_dest` WHERE `table_name` = @tbl_name AND `pk_date_src` = @pk_d; 						END IF; 						IF @rs = 0 THEN 						INSERT INTO `history_store`( `timemark`, `table_name`, `pk_date_src`,`pk_date_dest`, `record_state` ) VALUES (@time_mark, @tbl_name, @pk_d,@pk_d, @rec_state ); 						END IF; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
CREATE DEFINER=`root`@`%` PROCEDURE `deleteAllTokenByUserId`(IN userId INT)
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
CREATE DEFINER=`root`@`%` PROCEDURE `order_history`(in id_order_status int, in user_id int,`limit` INTEGER,in `offset` INTEGER)
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
CREATE DEFINER=`root`@`%` PROCEDURE `sp_checkCurrentInvetory`(in variant_id int, reQty int, out checked int)
begin
	set  checked = 404;
	if exists (select * from product_variant v where v.id = variant_id )
    then
		set @inventory := -1;
        select quantity into @inventory from product_variant v where v.id = variant_id;
        select case
                when @inventory = 0 then 0
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
CREATE DEFINER=`root`@`%` PROCEDURE `sp_sumTotalInCart`(In cartId int,
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
/*!50003 DROP PROCEDURE IF EXISTS `tinhTong` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `tinhTong`()
BEGIN
    DECLARE a INT (11) DEFAULT 0;
    DECLARE b INT (11) DEFAULT 0;
    DECLARE tong INT (11) DEFAULT 0;
    SET a = 200;
    SET b = 300;
    SET tong = a + b;
    SELECT tong;
END ;;
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

-- Dump completed on 2023-04-15 10:44:14
