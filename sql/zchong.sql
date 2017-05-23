# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.18)
# Database: zchong
# Generation Time: 2017-05-23 10:18:44 +0000
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
  `c_id` int(2) NOT NULL,
  `power_id` varchar(32) DEFAULT '',
  `isEmpty` int(1) NOT NULL,
  `p_lock` int(1) NOT NULL,
  `updateTime` varchar(32) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `m_id` (`m_id`),
  CONSTRAINT `m_power_ibfk_1` FOREIGN KEY (`m_id`) REFERENCES `machine` (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `m_power` WRITE;
/*!40000 ALTER TABLE `m_power` DISABLE KEYS */;

INSERT INTO `m_power` (`id`, `m_id`, `c_id`, `power_id`, `isEmpty`, `p_lock`, `updateTime`)
VALUES
	(16,'1CSb5BSoG5SaiNKQIgKnWBjKR8TkEVdV',1,'',0,1,'1493629626'),
	(18,'1CSb5BSoG5SaiNKQIgKnWBjKR8TkEVdV',2,'kcf7HEBlSUHuZffZ',0,1,'1493629710'),
	(19,'1CSb5BSoG5SaiNKQIgKnWBjKR8TkEVdV',3,'',0,1,'1493629729'),
	(20,'1CSb5BSoG5SaiNKQIgKnWBjKR8TkEVdV',4,'oih1JCfb3KQ47eHy',0,1,'1493629745'),
	(21,'1CSb5BSoG5SaiNKQIgKnWBjKR8TkEVdV',5,'y8sS8aaR3sYIobRW',0,1,'1493629763'),
	(24,'1CSb5BSoG5SaiNKQIgKnWBjKR8TkEVdV',6,'KwR2j6OZoI4TrscY',0,1,'1495524532134'),
	(25,'sqcdJwlkKNCe6Sdt2hxqvPh0lsRglrog',1,'ywFNTa6EV8wV6TR4',0,1,'1495533460329'),
	(26,'sqcdJwlkKNCe6Sdt2hxqvPh0lsRglrog',2,'XAEC6cz0V59pApq7',0,1,'1495533460329'),
	(27,'sqcdJwlkKNCe6Sdt2hxqvPh0lsRglrog',3,'UIKxoOFQo1RH3VN3',0,1,'1495533460329'),
	(28,'sqcdJwlkKNCe6Sdt2hxqvPh0lsRglrog',4,'dZhyuCXvt2UmLvLE',0,1,'1495533460329'),
	(29,'sqcdJwlkKNCe6Sdt2hxqvPh0lsRglrog',5,'hlAj9o2MwJuRxVwZ',0,1,'1495533460329'),
	(30,'sqcdJwlkKNCe6Sdt2hxqvPh0lsRglrog',6,'',0,1,'1495533460329');

/*!40000 ALTER TABLE `m_power` ENABLE KEYS */;
UNLOCK TABLES;


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
  `initTime` varchar(32) NOT NULL DEFAULT '',
  `updateTime` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`m_id`),
  KEY `id` (`id`),
  KEY `sta_id` (`sta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `machine` WRITE;
/*!40000 ALTER TABLE `machine` DISABLE KEYS */;

INSERT INTO `machine` (`m_id`, `sta_id`, `m_state`, `m_wifi`, `m_4G`, `id`, `initTime`, `updateTime`)
VALUES
	('1CSb5BSoG5SaiNKQIgKnWBjKR8TkEVdV','',1,1,0,9,'',NULL),
	('sqcdJwlkKNCe6Sdt2hxqvPh0lsRglrog','',0,0,0,10,'',NULL);

/*!40000 ALTER TABLE `machine` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table order_list
# ------------------------------------------------------------

DROP TABLE IF EXISTS `order_list`;

CREATE TABLE `order_list` (
  `order_id` varchar(32) NOT NULL DEFAULT '',
  `c_id` varchar(32) DEFAULT '0',
  `power_id` varchar(32) NOT NULL DEFAULT '',
  `userId` varchar(32) NOT NULL DEFAULT '',
  `out_time` varchar(32) NOT NULL DEFAULT '',
  `back_time` varchar(32) DEFAULT '',
  `total_fee` int(11) NOT NULL DEFAULT '0',
  `order_state` int(1) NOT NULL DEFAULT '0',
  `is_change` int(1) NOT NULL DEFAULT '0',
  `m_id` varchar(32) NOT NULL DEFAULT '',
  `id` int(11) DEFAULT NULL,
  `isPay` int(1) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `order_list` WRITE;
/*!40000 ALTER TABLE `order_list` DISABLE KEYS */;

INSERT INTO `order_list` (`order_id`, `c_id`, `power_id`, `userId`, `out_time`, `back_time`, `total_fee`, `order_state`, `is_change`, `m_id`, `id`, `isPay`)
VALUES
	('1493659527477152',NULL,'2EwrcAGYnYTWGGTd','o5UR3xFIif1N2qtNNc4HHsYxMohg','1493659527477',NULL,0,1,0,'1CSb5BSoG5SaiNKQIgKnWBjKR8TkEVdV',2,1),
	('149550445922190','3','KwR2j6OZoI4TrscY','o5UR3xFIif1N2qtNNc4HHsYxMohg','1495504474086',NULL,5,1,0,'1CSb5BSoG5SaiNKQIgKnWBjKR8TkEVdV',NULL,1),
	('1495525758921579','4','oih1JCfb3KQ47eHy','o5UR3xFIif1N2qtNNc4HHsYxMohg','1495525758921',NULL,0,2,1,'1CSb5BSoG5SaiNKQIgKnWBjKR8TkEVdV',NULL,1);

/*!40000 ALTER TABLE `order_list` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table pay_list
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pay_list`;

CREATE TABLE `pay_list` (
  `transaction_id` varchar(32) NOT NULL,
  `userId` varchar(32) NOT NULL DEFAULT '',
  `trade_type` varchar(11) NOT NULL,
  `bank_type` varchar(11) NOT NULL DEFAULT '',
  `total_fee` int(11) NOT NULL,
  `settlement_total_fee` int(11) NOT NULL,
  `cash_fee` int(11) NOT NULL,
  `out_trade_no` varchar(32) NOT NULL DEFAULT '',
  `time_end` varchar(14) NOT NULL DEFAULT '',
  `result_code` varchar(16) DEFAULT NULL,
  `pay_source` int(11) DEFAULT NULL,
  `device_info` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table power
# ------------------------------------------------------------

DROP TABLE IF EXISTS `power`;

CREATE TABLE `power` (
  `power_id` varchar(32) NOT NULL DEFAULT '',
  `is_enable` int(1) NOT NULL DEFAULT '0',
  `p_quantity` int(11) NOT NULL,
  `p_count` int(11) NOT NULL,
  `is_back` int(1) NOT NULL DEFAULT '0',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `c_id` int(2) DEFAULT NULL,
  PRIMARY KEY (`power_id`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `power` WRITE;
/*!40000 ALTER TABLE `power` DISABLE KEYS */;

INSERT INTO `power` (`power_id`, `is_enable`, `p_quantity`, `p_count`, `is_back`, `id`, `c_id`)
VALUES
	('',0,60,10,0,48,NULL),
	('2EwrcAGYnYTWGGTd',1,45,10,1,3,1),
	('dZhyuCXvt2UmLvLE',0,60,10,0,46,NULL),
	('E6FTSlIBIGyxifGZ',1,50,3,0,6,NULL),
	('hlAj9o2MwJuRxVwZ',0,60,10,0,47,NULL),
	('kcf7HEBlSUHuZffZ',1,85,100,1,4,2),
	('KwR2j6OZoI4TrscY',1,95,33,1,5,3),
	('oih1JCfb3KQ47eHy',1,90,59,1,1,4),
	('UIKxoOFQo1RH3VN3',0,60,10,0,45,NULL),
	('XAEC6cz0V59pApq7',0,60,10,0,44,NULL),
	('y8sS8aaR3sYIobRW',1,50,11,1,2,5),
	('ywFNTa6EV8wV6TR4',0,60,10,0,43,NULL);

/*!40000 ALTER TABLE `power` ENABLE KEYS */;
UNLOCK TABLES;


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
  `end_time` varchar(11) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '',
  `sta_name` varchar(32) NOT NULL DEFAULT '',
  `phone` varchar(12) DEFAULT '',
  `sta_logo` varchar(100) DEFAULT '',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` varchar(11) DEFAULT '',
  PRIMARY KEY (`sta_id`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sta_list` WRITE;
/*!40000 ALTER TABLE `sta_list` DISABLE KEYS */;

INSERT INTO `sta_list` (`sta_id`, `sta_adress`, `end_time`, `sta_name`, `phone`, `sta_logo`, `id`, `start_time`)
VALUES
	('210001','','22:00','','','',2,'10:00');

/*!40000 ALTER TABLE `sta_list` ENABLE KEYS */;
UNLOCK TABLES;


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
  `headimgurl` varchar(255) DEFAULT NULL,
  `isBlack` int(11) DEFAULT NULL,
  `creditScore` int(11) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`userId`, `origin`, `nickname`, `balance`, `sex`, `phone`, `zmxy`, `isChange`, `password`, `headimgurl`, `isBlack`, `creditScore`)
VALUES
	('o5UR3xEUrLw3uqh6XfYxmpRva3pA','','奇小怪',80,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	('o5UR3xFIif1N2qtNNc4HHsYxMohg','','C君',95,NULL,'',NULL,NULL,NULL,'http://wx.qlogo.cn/mmopen/hgXWbMaaqmDahBttlaYckrprYryu5e7TvbRMuJeXeHZiaIMsDyiafoP67z2wO7TCcmZCtq2Jgtt6DRn0S2W2KuQMwibEq8icHEQp/0',NULL,NULL);

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
