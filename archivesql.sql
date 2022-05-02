

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`file_manager` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `file_manager`;

/*Table structure for table `administrators` */

DROP TABLE IF EXISTS `administrators`;

CREATE TABLE `administrators` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
  `adm_Name` varchar(64) DEFAULT NULL COMMENT '管理员名称',
  `created_Time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `pass_word` varchar(64) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `administrators` */

insert  into `administrators`(`id`,`adm_Name`,`created_Time`,`pass_word`) values 
(1,'郭腾跃','2021-04-22 21:45:12','123456'),

/*Table structure for table `dir_inf` */

DROP TABLE IF EXISTS `dir_inf`;

CREATE TABLE `dir_inf` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '文件夹编号',
  `dir_name` varchar(512) NOT NULL COMMENT '文件夹名称',
  `dir_type` int DEFAULT '0' COMMENT '文件夹类型（公共、小组）',
  `group_id` int unsigned DEFAULT NULL COMMENT '组名',
  `parent_dir` int DEFAULT NULL COMMENT '父文件夹',
  `dir_path` varchar(1024) NOT NULL COMMENT '文件夹地址',
  `truedir_path` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '绝对地址',
  `dir_status` tinyint(1) DEFAULT '0' COMMENT '文件夹状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

/*Data for the table `dir_inf` */

insert  into `dir_inf`(`id`,`dir_name`,`dir_type`,`group_id`,`parent_dir`,`dir_path`,`truedir_path`,`dir_status`) values 
(1,'0',0,0,1,'http://localhost:8080/0','0',0),
(2,'1',0,0,2,'http://localhost:8080/1','1',0),
(6,'周报',0,0,1,'http://localhost:8080/0/周报','0/周报',0),
(10,'日志',0,0,1,'http://localhost:8080/0/日志','0/日志',0),
(11,'100',0,100,2,'http://localhost:8080/1/100','1/100',0);

/*Table structure for table `download_record` */

DROP TABLE IF EXISTS `download_record`;

CREATE TABLE `download_record` (
  `download_id` int NOT NULL AUTO_INCREMENT COMMENT '下载编号',
  `file_id` int DEFAULT NULL COMMENT '文件编号',
  `user_id` int DEFAULT NULL COMMENT '用户编号',
  `download_time` timestamp NULL DEFAULT NULL COMMENT '浏览时间',
  PRIMARY KEY (`download_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `download_record_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `download_record` */

/*Table structure for table `file_hits` */

DROP TABLE IF EXISTS `file_hits`;

CREATE TABLE `file_hits` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '点击编号',
  `file_id` int DEFAULT NULL COMMENT '文件编号',
  `hits_time` timestamp NULL DEFAULT NULL COMMENT '时间',
  `hits_users` int DEFAULT NULL COMMENT '用户编号',
  PRIMARY KEY (`id`),
  KEY `hits_users` (`hits_users`),
  KEY `file_hits_ibfk_1` (`file_id`),
  CONSTRAINT `file_hits_ibfk_1` FOREIGN KEY (`file_id`) REFERENCES `file_inf` (`id`),
  CONSTRAINT `file_hits_ibfk_2` FOREIGN KEY (`hits_users`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `file_hits` */

/*Table structure for table `file_inf` */

DROP TABLE IF EXISTS `file_inf`;

CREATE TABLE `file_inf` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '文件编号',
  `file_name` varchar(512) NOT NULL COMMENT '文件名',
  `cate_id` varchar(64) DEFAULT NULL COMMENT '类型编号',
  `dir_id` int DEFAULT NULL COMMENT '文件夹编号',
  `user_id` int DEFAULT NULL COMMENT '用户编号',
  `file_size` int NOT NULL COMMENT '文件大小(单位kb)',
  `file_upload_time` timestamp NULL DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `dir_id` (`cate_id`),
  KEY `file_inf2` (`dir_id`),
  CONSTRAINT `file_inf2` FOREIGN KEY (`dir_id`) REFERENCES `dir_inf` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;

/*Data for the table `file_inf` */

/*Table structure for table `study_group` */

DROP TABLE IF EXISTS `study_group`;

CREATE TABLE `study_group` (
  `group_id` int NOT NULL AUTO_INCREMENT COMMENT '组号',
  `num_of_stu` int DEFAULT '0' COMMENT '组员数目',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8;

/*Data for the table `study_group` */

insert  into `study_group`(`group_id`,`num_of_stu`) values 
(100,1);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `full_Name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `created_Time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `pass_word` varchar(50) DEFAULT NULL COMMENT '密码',
  `group_id` int DEFAULT NULL COMMENT '组号',
  `group_leader` tinyint(1) DEFAULT NULL COMMENT '是否是组长',
  PRIMARY KEY (`id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `study_group` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
DROP TABLE IF EXISTS `pro_ma`;

CREATE TABLE `pro_ma` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `bid` int NOT NULL,
  `userid` int NOT NULL,
  `cho` int NOT NULL,
  PRIMARY KEY (`id`)
) ;
