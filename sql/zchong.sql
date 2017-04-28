# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.18)
# Database: zchong
# Generation Time: 2017-04-28 13:18:09 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table admin
# ------------------------------------------------------------

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table cabin
# ------------------------------------------------------------

DROP TABLE IF EXISTS `cabin`;

CREATE TABLE `cabin` (
  `c_id` varchar(32) NOT NULL DEFAULT '',
  `c_state` int(1) NOT NULL,
  `d_lock` int(1) NOT NULL,
  `p_lock` int(1) NOT NULL,
  `isEmpty` int(1) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`c_id`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table feedback
# ------------------------------------------------------------

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table m_power
# ------------------------------------------------------------

DROP TABLE IF EXISTS `m_power`;

CREATE TABLE `m_power` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `m_id` varchar(32) NOT NULL DEFAULT '',
  `c_id` varchar(32) NOT NULL DEFAULT '',
  `power_id` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `m_id` (`m_id`),
  KEY `power_id` (`power_id`),
  KEY `c_id` (`c_id`),
  CONSTRAINT `m_power_ibfk_1` FOREIGN KEY (`m_id`) REFERENCES `machine` (`m_id`),
  CONSTRAINT `m_power_ibfk_2` FOREIGN KEY (`power_id`) REFERENCES `power` (`power_id`),
  CONSTRAINT `m_power_ibfk_3` FOREIGN KEY (`c_id`) REFERENCES `cabin` (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table machine
# ------------------------------------------------------------

DROP TABLE IF EXISTS `machine`;

CREATE TABLE `machine` (
  `m_id` varchar(32) NOT NULL DEFAULT '',
  `sta_id` varchar(32) NOT NULL DEFAULT '',
  `m_state` int(1) NOT NULL,
  `m_wifi` int(1) NOT NULL,
  `m_4G` int(1) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`m_id`),
  KEY `id` (`id`),
  KEY `sta_id` (`sta_id`),
  CONSTRAINT `machine_ibfk_1` FOREIGN KEY (`sta_id`) REFERENCES `sta_list` (`sta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table order_list
# ------------------------------------------------------------

DROP TABLE IF EXISTS `order_list`;

CREATE TABLE `order_list` (
  `order_id` varchar(32) NOT NULL DEFAULT '',
  `c_id` varchar(32) NOT NULL DEFAULT '',
  `power_id` varchar(32) NOT NULL DEFAULT '',
  `userId` varchar(32) NOT NULL DEFAULT '',
  `out_time` date NOT NULL,
  `back_time` date NOT NULL,
  `total_fee` int(11) NOT NULL,
  `order_state` int(1) NOT NULL,
  `is_change` int(1) NOT NULL,
  `m_id` varchar(32) NOT NULL DEFAULT '',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`order_id`),
  KEY `id` (`id`),
  KEY `userId` (`userId`),
  KEY `m_id` (`m_id`),
  KEY `c_id` (`c_id`),
  CONSTRAINT `order_list_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  CONSTRAINT `order_list_ibfk_2` FOREIGN KEY (`m_id`) REFERENCES `machine` (`m_id`),
  CONSTRAINT `order_list_ibfk_3` FOREIGN KEY (`c_id`) REFERENCES `cabin` (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table power
# ------------------------------------------------------------

DROP TABLE IF EXISTS `power`;

CREATE TABLE `power` (
  `power_id` varchar(32) NOT NULL DEFAULT '',
  `is_enable` int(1) NOT NULL,
  `p_quantity` int(11) NOT NULL,
  `p_count` int(11) NOT NULL,
  `is_back` int(1) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`power_id`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table recharge_list
# ------------------------------------------------------------

DROP TABLE IF EXISTS `recharge_list`;

CREATE TABLE `recharge_list` (
  `recharge_id` varchar(32) NOT NULL DEFAULT '',
  `user_id` varchar(32) NOT NULL DEFAULT '',
  `re_time` date NOT NULL,
  `total_fee` int(11) NOT NULL,
  `rech_state` int(1) NOT NULL,
  `pay_type` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`recharge_id`),
  KEY `id` (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `recharge_list_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table refund_list
# ------------------------------------------------------------

DROP TABLE IF EXISTS `refund_list`;

CREATE TABLE `refund_list` (
  `refund_id` int(11) NOT NULL,
  `user_id` varchar(32) NOT NULL DEFAULT '',
  `s_time` date NOT NULL,
  `e_time` date DEFAULT NULL,
  `refund_fee` int(11) NOT NULL,
  `ref_state` int(1) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`refund_id`),
  KEY `id` (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `refund_list_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sta_list
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sta_list`;

CREATE TABLE `sta_list` (
  `sta_id` varchar(32) NOT NULL DEFAULT '',
  `sta_adress` varchar(100) NOT NULL DEFAULT '',
  `start_time` date DEFAULT NULL,
  `end_time` date DEFAULT NULL,
  `sta_name` varchar(32) NOT NULL DEFAULT '',
  `phone` varchar(12) DEFAULT '',
  `sta_logo` varchar(100) DEFAULT '',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`sta_id`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userId` varchar(32) NOT NULL DEFAULT '',
  `origin` varchar(11) NOT NULL DEFAULT '',
  `nickname` varchar(32) DEFAULT NULL,
  `balance` int(11) DEFAULT NULL,
  `sex` tinyint(4) DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `zmxy` int(11) DEFAULT NULL,
  `isChange` int(1) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `headimgurl` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`userId`, `origin`, `nickname`, `balance`, `sex`, `phone`, `zmxy`, `isChange`, `password`, `headimgurl`)
VALUES
	('o5UR3xFIif1N2qtNNc4HHsYxMohg','','C君',100,NULL,'',NULL,NULL,NULL,'http://wx.q');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
