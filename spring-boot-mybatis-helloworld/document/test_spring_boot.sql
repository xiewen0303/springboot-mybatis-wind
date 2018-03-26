/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : test_spring_boot

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2018-03-26 19:02:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1009 DEFAULT CHARSET=utf8;
