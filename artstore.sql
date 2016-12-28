-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: artstore
-- ------------------------------------------------------
-- Server version	5.7.15-log

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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `user_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  KEY `user_id_idx` (`user_id`),
  KEY `product_id_idx` (`product_id`),
  CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `img` varchar(45) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_img_id_idx` (`user_id`),
  CONSTRAINT `user_img_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(245) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `img` varchar(45) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Pen','A 15 pack of BIC pens.',1.49,'http://i.imgur.com/QdIzFgh.jpg','sketching');
INSERT INTO `products` VALUES (2,'Pencil','A 24 pack of standard #2 Ticonderoga pencils.',2.99,'http://i.imgur.com/vzvhjmf.jpg','sketching');
INSERT INTO `products` VALUES (3,'Colored Pencils','A 24 pack of pre-sharpened Crayola colored pencils',4.99,'http://i.imgur.com/xslUzmy.jpg','sketching');
INSERT INTO `products` VALUES (4,'Markers','A 10 pack of Crayola markers with classic colors.',3.99,'http://i.imgur.com/8iq2uMn.jpg','sketching');
INSERT INTO `products` VALUES (5,'Crayons','A 24 pack of Crayola crayons with standard colors.',1.99,'http://i.imgur.com/ORVTpRG.jpg','sketching');
INSERT INTO `products` VALUES (6,'Paint Brush','7 different paint brushes that will cover a large variety of your painting needs.',9.99,'http://i.imgur.com/Y3kVE52.jpg','painting');
INSERT INTO `products` VALUES (7,'Paint Palette','A medium sized paint palette that can hold several different colors.',6.99,'http://i.imgur.com/c3rCRyZ.jpg','painting');
INSERT INTO `products` VALUES (8,'Oil Paints','A 24 pack of a variety of oil paints that will allow you to achieve any color you need.',19.99,'http://i.imgur.com/3l8Wnfv.jpg','painting');
INSERT INTO `products` VALUES (9,'Acrylic Paints','5 of your most basic acrylic paint colors.',9.99,'http://i.imgur.com/9Pa3B02.jpg','painting');
INSERT INTO `products` VALUES (10,'Finger Paints','10 different colors that make finger painting easy.',9.99,'http://i.imgur.com/9Pa3B02.jpg','painting');
INSERT INTO `products` VALUES (11,'Paper','200 sheets of lined notebook paper for your basic sketching needs.',3.99,'http://i.imgur.com/07ixINv.jpg','surfaces');
INSERT INTO `products` VALUES (12,'Notebook','A notebook of paper so you can sketch on the go.',5.99,'http://i.imgur.com/8ohA2wz.jpg','surfaces');
INSERT INTO `products` VALUES (13,'Canvas','A medium sized canvas for you to paint on with some of your recently purchased paints. Does not come with the easel.',9.99,'http://i.imgur.com/4UjNuGi.jpg','surfaces');
INSERT INTO `products` VALUES (14,'Easel','An easel to hold your canvases for you while you paint',39.99,'http://i.imgur.com/jzA5hle.jpg','surfaces');
INSERT INTO `products` VALUES (15,'Tile','A 7 inch by 7 inch tile for small painting projects.',2.99,'http://i.imgur.com/uVW5pen.jpg','surfaces');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviews` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `image_id` int(11) DEFAULT NULL,
  `review` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `review_product_id_idx` (`product_id`),
  KEY `review_img_id_idx` (`image_id`),
  CONSTRAINT `review_img_id` FOREIGN KEY (`image_id`) REFERENCES `images` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `review_product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (2,1,NULL,'Amazing purchase! Writes well on notebook paper. Perfect for taking notes!');
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `address` varchar(256) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'bob','bob','123 bob street','bob@gmail.com','password'),(2,'bob2','bob2','234 bob street','bob2@gmail.com','password2'),(3,'bob2','bob2','234 bob street','bob2@gmail.com','password2');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-28 17:49:43
