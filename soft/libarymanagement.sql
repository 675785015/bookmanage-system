/*
Navicat MySQL Data Transfer

Source Server         : localMysql
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : libarymanagement

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-05-15 11:31:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_book
-- ----------------------------
DROP TABLE IF EXISTS `tb_book`;
CREATE TABLE `tb_book` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '书名',
  `preview` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '简介',
  `author` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '作者',
  `isbn` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '书号',
  `press` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '出版社',
  `category_id` bigint(11) NOT NULL COMMENT '直接分类id',
  `category_field` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '所属分类',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `cover_path` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '封面图片地址',
  `on_shelf` int(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `isbn` (`isbn`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '分类名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` int(1) DEFAULT NULL COMMENT '是否删除 0未删除 1已删除',
  `parent_id` bigint(11) DEFAULT NULL COMMENT '上级分类  为null时是顶级分类',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tb_member
-- ----------------------------
DROP TABLE IF EXISTS `tb_member`;
CREATE TABLE `tb_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_number` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `college_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `true_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `register_datetime` datetime DEFAULT NULL,
  `logout_datetime` datetime DEFAULT NULL,
  `logout_reason` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `mobile` varchar(12) COLLATE utf8_bin DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ord_number` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `ord_member_id` bigint(20) DEFAULT NULL,
  `ord_book_id` bigint(20) DEFAULT NULL,
  `ord_borrow_time` datetime DEFAULT NULL,
  `ord_return_time` datetime DEFAULT NULL,
  `ord_plan_time` datetime DEFAULT NULL,
  `ord_status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(11) NOT NULL,
  `username` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
