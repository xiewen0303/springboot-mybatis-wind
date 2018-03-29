/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : testdb_a

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2018-03-27 18:31:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_a
-- ----------------------------
DROP TABLE IF EXISTS `user_a`;
CREATE TABLE `user_a` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1009 DEFAULT CHARSET=utf8;
