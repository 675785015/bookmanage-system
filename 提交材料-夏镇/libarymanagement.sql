/*
Navicat MySQL Data Transfer

Source Server         : localMysql
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : libarymanagement

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-05-15 15:17:10
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
-- Records of tb_book
-- ----------------------------
INSERT INTO `tb_book` VALUES ('1', '软件的艺术3333', '2423423423大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模', '夏镇233232', '1616841656222', '春蚕出版社23232', '25', '文学理论', '2018-04-22 11:03:15', '2018-05-14 10:55:02', '/image/20180424164737082599.jpg', '1');
INSERT INTO `tb_book` VALUES ('2', '文学的艺术', '顶替顶替顶替顶替顶替顶替顶替顶替顶替在要人夫 都是废物 夫w', '麦子', '1616351313', '冬夏出版社', '25', '文学理论', '2018-04-22 10:51:48', '2018-04-22 10:51:48', '/image/20180422105143429027.jpg', '1');
INSERT INTO `tb_book` VALUES ('3', 'first java book', 'sfsgewgwgewgwgsgewgewtewtewsdgsdgsgssfsgewgwgewgwgsgewgewtewtewsdgsdgsgssfsgewgwgewgwgsgewgewtewtewsdgsdgsgssfsgewgwgewgwgsgewgewtewtewsdgsdgsgs', 'jone', '146168136131516', 'kafapriesdf dfwojf', '25', '文学理论', '2018-04-22 10:55:03', '2018-04-22 10:55:03', '/image/20180422105332708189.jpg', '1');
INSERT INTO `tb_book` VALUES ('4', '完美世界', '杜伟杜伟杜伟杜伟顶替杜伟顶替杜伟顶替杜伟顶替杜伟顶替杜伟顶替杜伟顶替杜伟顶替杜伟顶替杜伟顶替杜伟顶替杜伟顶替杜伟顶替', '夏镇', '16113464613', '中国出版社', '25', '文学理论', '2018-04-22 10:56:38', '2018-04-22 10:56:38', '/image/20180422105636751407.jpg', '1');
INSERT INTO `tb_book` VALUES ('5', '生存之道', '村都是废物 村都是废物 村都是废物 村都是废物 村都是废物 村都是废物 村都是废物', 'tom', '136496813346468', '中国邮电出版社', '30', '大学月', '2018-04-23 15:40:01', '2018-05-15 11:30:59', '/20180515113057143242.jpg', '0');
INSERT INTO `tb_book` VALUES ('7', '软件的艺术', '枯春 有为大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模', '夏镇', '16168416562', '春蚕出版社', '25', '文学理论', '2018-04-24 16:35:11', '2018-04-24 16:35:11', '/image/20180424163500354380.jpg', '0');
INSERT INTO `tb_book` VALUES ('8', '软件的艺术', '枯春 有为大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模', '夏镇', '16168416562sss', '春蚕出版社', '25', '文学理论', '2018-04-24 16:35:23', '2018-04-24 16:35:23', '/image/20180424163500354380.jpg', '0');
INSERT INTO `tb_book` VALUES ('9', '软件的艺术22', '2222222大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模枯春 有为大规模', '夏镇222', '161684165622', '春蚕出版社22', '25', '文学理论', '2018-04-24 16:45:03', '2018-04-24 16:45:03', '/image/20180424164215750544.jpg', '0');

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
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES ('1', '文艺', '2018-03-02 17:43:07', '2018-03-02 17:43:07', '1', null);
INSERT INTO `tb_category` VALUES ('2', '小说', '2018-03-08 10:55:26', '2018-03-08 10:55:26', '1', null);
INSERT INTO `tb_category` VALUES ('3', '童书', '2018-03-08 10:56:17', '2018-03-08 10:56:17', '1', null);
INSERT INTO `tb_category` VALUES ('4', '经营', '2018-03-08 10:56:22', '2018-03-08 10:56:22', '1', null);
INSERT INTO `tb_category` VALUES ('8', '人文社科', '2018-03-08 11:32:10', '2018-03-08 11:32:10', '1', null);
INSERT INTO `tb_category` VALUES ('9', '中国当代小说', '2018-03-08 11:33:14', '2018-03-13 14:03:56', '1', '2');
INSERT INTO `tb_category` VALUES ('10', '文学', '2018-03-09 13:52:12', '2018-03-13 14:09:58', '1', '1');
INSERT INTO `tb_category` VALUES ('11', '传记', '2018-03-09 13:52:24', '2018-03-13 14:21:42', '1', '1');
INSERT INTO `tb_category` VALUES ('12', null, '2018-03-09 13:52:27', '2018-03-09 14:00:03', '0', null);
INSERT INTO `tb_category` VALUES ('13', null, '2018-03-09 13:52:30', '2018-03-09 14:00:06', '0', null);
INSERT INTO `tb_category` VALUES ('14', null, '2018-03-09 13:52:31', '2018-03-09 14:00:11', '0', null);
INSERT INTO `tb_category` VALUES ('15', null, '2018-03-09 13:56:19', '2018-03-09 14:00:07', '0', null);
INSERT INTO `tb_category` VALUES ('16', null, '2018-03-09 13:56:20', '2018-03-09 14:00:08', '0', null);
INSERT INTO `tb_category` VALUES ('17', null, '2018-03-09 13:56:21', '2018-03-09 14:00:09', '0', null);
INSERT INTO `tb_category` VALUES ('18', null, '2018-03-09 13:56:22', '2018-03-09 14:00:10', '0', null);
INSERT INTO `tb_category` VALUES ('19', null, '2018-03-09 13:56:26', '2018-03-09 14:00:12', '0', null);
INSERT INTO `tb_category` VALUES ('20', null, '2018-03-09 13:58:35', '2018-03-09 14:00:12', '0', null);
INSERT INTO `tb_category` VALUES ('21', '生活', '2018-03-13 13:56:57', '2018-03-13 14:52:31', '1', null);
INSERT INTO `tb_category` VALUES ('22', '科技', '2018-03-13 14:02:04', '2018-03-13 14:02:04', '1', null);
INSERT INTO `tb_category` VALUES ('23', '文学', '2018-03-13 14:21:23', '2018-03-13 14:21:23', '1', '1');
INSERT INTO `tb_category` VALUES ('24', '文集', '2018-03-13 14:22:14', '2018-04-13 17:26:00', '1', '23');
INSERT INTO `tb_category` VALUES ('25', '文学理论', '2018-03-13 14:22:21', '2018-03-13 14:22:21', '1', '23');
INSERT INTO `tb_category` VALUES ('26', 'java', '2018-04-13 17:04:58', '2018-04-13 17:04:58', '1', '22');
INSERT INTO `tb_category` VALUES ('27', 'maodun', '2018-04-13 17:09:32', '2018-04-13 17:09:32', '1', '1');
INSERT INTO `tb_category` VALUES ('28', 'maodunsss', '2018-04-13 17:10:06', '2018-04-13 17:10:06', '1', '1');
INSERT INTO `tb_category` VALUES ('29', '大学', '2018-04-13 17:11:17', '2018-04-13 17:11:17', '1', '1');
INSERT INTO `tb_category` VALUES ('30', '大学月', '2018-04-13 17:11:27', '2018-04-13 17:11:27', '1', '23');
INSERT INTO `tb_category` VALUES ('31', '文艺', '2018-05-08 10:15:37', '2018-05-08 10:15:37', '1', null);

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
-- Records of tb_member
-- ----------------------------
INSERT INTO `tb_member` VALUES ('1', '201215 de1', '北京 机械工程学院', '夏镇', '2018-05-04 16:05:42', null, null, '15910757156', '1');
INSERT INTO `tb_member` VALUES ('2', null, null, null, '2018-05-07 17:09:37', '2018-05-08 09:31:14', '学生已离校', null, '0');

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
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('1', '20180507174355555905', '1', '3', '2018-05-07 17:43:55', '2018-05-14 11:46:37', null, '1');
INSERT INTO `tb_order` VALUES ('2', '20180507180226156682', '1', '4', '2018-05-07 18:02:26', null, '2018-05-08 00:00:00', '0');
INSERT INTO `tb_order` VALUES ('3', '20180508090210854773', '1', '3', '2018-05-08 09:02:10', '2018-05-08 09:02:56', '2018-05-08 00:00:00', '1');

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

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e');
