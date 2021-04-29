DROP TABLE IF EXISTS `sys_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` json DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq` (`name`) USING BTREE
)