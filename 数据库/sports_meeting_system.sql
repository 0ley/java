/*
Navicat MySQL Data Transfer

Source Server         : sports_meeting_system
Source Server Version : 50612
Source Host           : localhost:3306
Source Database       : sports_meeting_system

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2019-12-25 16:06:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `academy_admin`
-- ----------------------------
DROP TABLE IF EXISTS `academy_admin`;
CREATE TABLE `academy_admin` (
  `id` int(25) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(25) CHARACTER SET utf8 NOT NULL,
  `password` varchar(25) CHARACTER SET utf8 NOT NULL,
  `telephone` varchar(25) CHARACTER SET utf8 DEFAULT NULL,
  `career` varchar(25) CHARACTER SET utf8 DEFAULT NULL,
  `academy` varchar(25) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of academy_admin
-- ----------------------------
INSERT INTO `academy_admin` VALUES ('1', 'user_academy1', '123456', '18178367892', 'academy', 'computer_science');
INSERT INTO `academy_admin` VALUES ('2', 'user_academy2', '123456', '13577790834', 'academy', 'medical_science');
INSERT INTO `academy_admin` VALUES ('3', 'user_academy3', '123456', '18565425966', 'academey', 'education_science');
INSERT INTO `academy_admin` VALUES ('13', '2', '2', '2', 'education_science', 'education_science');

-- ----------------------------
-- Table structure for `athletes`
-- ----------------------------
DROP TABLE IF EXISTS `athletes`;
CREATE TABLE `athletes` (
  `inner_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(25) NOT NULL,
  `name` varchar(25) CHARACTER SET utf8 NOT NULL,
  `sex` varchar(25) CHARACTER SET utf8 NOT NULL,
  `academy` varchar(25) CHARACTER SET utf8 NOT NULL,
  `telephone` varchar(25) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`inner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of athletes
-- ----------------------------
INSERT INTO `athletes` VALUES ('58', '1', 'Amy', 'female', 'computer_science', '18346377884');
INSERT INTO `athletes` VALUES ('59', '2', 'Bob', 'male', 'computer_science', '18267890352');
INSERT INTO `athletes` VALUES ('60', '3', 'Linda', 'female', 'computer_science', '18545629374');
INSERT INTO `athletes` VALUES ('61', '4', 'Jack', 'male', 'computer_science', '13789464833');
INSERT INTO `athletes` VALUES ('62', '5', 'Nino', 'male', 'medical_science', '13789378263');
INSERT INTO `athletes` VALUES ('63', '6', 'Jean', 'male', 'medical_science', '19374958349');
INSERT INTO `athletes` VALUES ('64', '7', 'Quella', 'female', 'medical_science', '16382937485');
INSERT INTO `athletes` VALUES ('65', '8', 'Lily', 'female', 'medical_science', '15453234567');
INSERT INTO `athletes` VALUES ('66', '9', 'Gaga', 'male', 'education_science', '16782736478');
INSERT INTO `athletes` VALUES ('67', '10', 'chloe', 'female', 'education_science', '15273878291');
INSERT INTO `athletes` VALUES ('68', '11', 'hyt18', 'female', 'education_science', '17689345345');
INSERT INTO `athletes` VALUES ('69', '12', 'Ceilin', 'female', 'education_science', '15672839372');
INSERT INTO `athletes` VALUES ('70', '13', 'hyt01', 'male', 'education_science', '178289283722');
INSERT INTO `athletes` VALUES ('71', '14', 'xzh01', 'female', 'education_science', '16787678263');
INSERT INTO `athletes` VALUES ('72', '15', 'hyt02', 'male', 'education_science', '15677882765');
INSERT INTO `athletes` VALUES ('73', '16', 'hyt03', 'female', 'education_science', '15673823739');
INSERT INTO `athletes` VALUES ('74', '17', 'hyt04', 'male', 'education_science', '16782927838');
INSERT INTO `athletes` VALUES ('75', '18', 'lxj01', 'female', 'education_science', '16792763833');
INSERT INTO `athletes` VALUES ('76', '19', 'lxj02', 'male', 'education_science', '13543455643');
INSERT INTO `athletes` VALUES ('77', '20', 'wlt03', 'male', 'education_science', '16782763749');
INSERT INTO `athletes` VALUES ('78', '21', 'wlt02', 'male', 'medical_science', '189034750352');
INSERT INTO `athletes` VALUES ('79', '22', 'wlt01', 'male', 'medical_science', '189093549025');
INSERT INTO `athletes` VALUES ('80', '23', 'zkl02', 'male', 'medical_science', '17903842043');
INSERT INTO `athletes` VALUES ('81', '24', 'zkl01', 'male', 'medical_science', '17892387434');
INSERT INTO `athletes` VALUES ('82', '25', 'zyx02', 'male', 'medical_science', '19830978023');
INSERT INTO `athletes` VALUES ('83', '26', 'zyx01', 'female', 'medical_science', '19829309842');
INSERT INTO `athletes` VALUES ('84', '27', 'hyt08', 'female', 'medical_science', '18902840482');
INSERT INTO `athletes` VALUES ('85', '28', 'fzh07', 'female', 'medical_science', '18298732897');
INSERT INTO `athletes` VALUES ('86', '29', 'xzh06', 'female', 'medical_science', '16729872937');
INSERT INTO `athletes` VALUES ('87', '30', 'xzh05', 'female', 'medical_science', '15677878633');
INSERT INTO `athletes` VALUES ('88', '31', 'xzh04', 'female', 'computer_science', '1657238647');
INSERT INTO `athletes` VALUES ('89', '32', 'xzh03', 'female', 'computer_science', '1789343589');
INSERT INTO `athletes` VALUES ('90', '33', 'syc04', 'female', 'computer_science', '1548903945');
INSERT INTO `athletes` VALUES ('91', '34', 'syc03', 'female', 'computer_science', '10974238972');
INSERT INTO `athletes` VALUES ('92', '35', 'syc02', 'female', 'computer_science', '18975834543');
INSERT INTO `athletes` VALUES ('93', '36', 'syc01', 'male', 'computer_science', '18903485454');
INSERT INTO `athletes` VALUES ('94', '37', 'fzh03', 'male', 'computer_science', '17893723438');
INSERT INTO `athletes` VALUES ('95', '38', 'fzh02', 'male', 'computer_science', '17923749423');
INSERT INTO `athletes` VALUES ('96', '39', 'fzh01', 'male', 'computer_science', '16898327404');
INSERT INTO `athletes` VALUES ('97', '40', 'lxj03', 'male', 'computer_science', '16789237493');
INSERT INTO `athletes` VALUES ('98', '41', 'yxt01', 'male', 'computer_science', '16787364823');
INSERT INTO `athletes` VALUES ('99', '42', 'yxt02', 'female', 'computer_science', '17893749534');
INSERT INTO `athletes` VALUES ('100', '43', 'yxt03', 'male', 'computer_science', '17389723895');
INSERT INTO `athletes` VALUES ('101', '44', 'czh01', 'female', 'computer_science', '13789742343');
INSERT INTO `athletes` VALUES ('102', '45', 'gxm01', 'male', 'computer_science', '17368923847');
INSERT INTO `athletes` VALUES ('103', '46', 'gql02', 'female', 'computer_science', '17893745894');
INSERT INTO `athletes` VALUES ('104', '47', 'lxj09', 'female', 'medical_science', '19787328974');
INSERT INTO `athletes` VALUES ('105', '48', 'xzh13', 'female', 'medical_science', '10989030492');
INSERT INTO `athletes` VALUES ('106', '49', 'hyt12', 'male', 'medical_science', '18978923473');
INSERT INTO `athletes` VALUES ('107', '50', 'fzh11', 'male', 'medical_science', '16789238743');
INSERT INTO `athletes` VALUES ('108', '51', 'fzh09', 'female', 'education_science', '17389623983');
INSERT INTO `athletes` VALUES ('109', '52', 'gxm08', 'female', 'education_science', '17573846583');
INSERT INTO `athletes` VALUES ('110', '53', 'gql06', 'male', 'education_science', '17893749823');
INSERT INTO `athletes` VALUES ('111', '54', 'gql05', 'male', 'education_science', '17893274234');
INSERT INTO `athletes` VALUES ('112', '55', '55', 'male', 'education_science', '1111111111');
INSERT INTO `athletes` VALUES ('113', '57', '56', 'male', 'education_science', '56');
INSERT INTO `athletes` VALUES ('118', '66', '66', 'male', 'education_science', '1234565');
INSERT INTO `athletes` VALUES ('119', '100', '100', 'male', 'computer_science', '1234156');

-- ----------------------------
-- Table structure for `score`
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `inner_id` int(25) NOT NULL AUTO_INCREMENT,
  `student_id` int(25) NOT NULL,
  `sports_item` varchar(25) CHARACTER SET utf8 NOT NULL,
  `score` double(10,2) NOT NULL,
  PRIMARY KEY (`inner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('1', '1', '50m', '6.00');
INSERT INTO `score` VALUES ('2', '2', '50m', '8.55');
INSERT INTO `score` VALUES ('3', '3', '50m', '8.12');
INSERT INTO `score` VALUES ('4', '4', '50m', '5.08');
INSERT INTO `score` VALUES ('5', '5', '50m', '7.19');
INSERT INTO `score` VALUES ('6', '6', '50m', '7.18');
INSERT INTO `score` VALUES ('7', '7', '50m', '7.17');
INSERT INTO `score` VALUES ('8', '8', '50m', '7.16');
INSERT INTO `score` VALUES ('9', '9', '50m', '7.15');
INSERT INTO `score` VALUES ('10', '10', '50m', '7.14');
INSERT INTO `score` VALUES ('11', '11', '50m', '7.13');
INSERT INTO `score` VALUES ('12', '12', '50m', '7.12');
INSERT INTO `score` VALUES ('13', '13', '50m', '7.11');
INSERT INTO `score` VALUES ('14', '14', '50m', '7.09');
INSERT INTO `score` VALUES ('15', '15', '50m', '7.08');
INSERT INTO `score` VALUES ('16', '16', '50m', '7.07');
INSERT INTO `score` VALUES ('17', '17', '50m', '7.06');
INSERT INTO `score` VALUES ('18', '18', '50m', '7.02');
INSERT INTO `score` VALUES ('19', '19', '100m', '15.12');
INSERT INTO `score` VALUES ('20', '20', '100m', '15.13');
INSERT INTO `score` VALUES ('21', '21', '100m', '15.14');
INSERT INTO `score` VALUES ('22', '22', '100m', '15.07');
INSERT INTO `score` VALUES ('23', '23', '3000m', '20.33');
INSERT INTO `score` VALUES ('24', '24', '3000m', '19.56');
INSERT INTO `score` VALUES ('25', '25', '3000m', '24.23');
INSERT INTO `score` VALUES ('26', '26', '50m', '7.03');
INSERT INTO `score` VALUES ('27', '27', '50m', '7.56');
INSERT INTO `score` VALUES ('28', '28', '100m', '16.75');
INSERT INTO `score` VALUES ('29', '29', '3000m', '23.59');
INSERT INTO `score` VALUES ('30', '30', '3000m', '21.12');
INSERT INTO `score` VALUES ('31', '31', '50m', '6.98');
INSERT INTO `score` VALUES ('32', '32', '50m', '6.78');
INSERT INTO `score` VALUES ('33', '33', '100m', '14.34');
INSERT INTO `score` VALUES ('34', '34', '100m', '14.01');
INSERT INTO `score` VALUES ('35', '35', '100m', '14.02');
INSERT INTO `score` VALUES ('36', '36', '50m', '8.67');
INSERT INTO `score` VALUES ('37', '37', '100m', '14.03');
INSERT INTO `score` VALUES ('38', '38', '100m', '14.04');
INSERT INTO `score` VALUES ('39', '39', '100m', '14.05');
INSERT INTO `score` VALUES ('40', '40', '3000m', '23.34');
INSERT INTO `score` VALUES ('41', '41', '3000m', '25.78');
INSERT INTO `score` VALUES ('42', '42', '3000m', '25.13');
INSERT INTO `score` VALUES ('43', '43', '50m', '9.23');
INSERT INTO `score` VALUES ('44', '44', '50m', '9.56');
INSERT INTO `score` VALUES ('45', '45', '100m', '15.56');
INSERT INTO `score` VALUES ('46', '46', '100m', '12.01');
INSERT INTO `score` VALUES ('47', '47', '50m', '9.38');
INSERT INTO `score` VALUES ('48', '48', '100m', '13.34');
INSERT INTO `score` VALUES ('49', '49', '100m', '13.09');
INSERT INTO `score` VALUES ('50', '50', '3000m', '19.23');
INSERT INTO `score` VALUES ('51', '51', '100m', '14.98');
INSERT INTO `score` VALUES ('52', '52', '3000m', '24.41');
INSERT INTO `score` VALUES ('53', '53', '100m', '13.34');
INSERT INTO `score` VALUES ('54', '54', '3000m', '23.51');
INSERT INTO `score` VALUES ('55', '55', '3000m', '55.50');
INSERT INTO `score` VALUES ('57', '57', '3000m', '666.00');
INSERT INTO `score` VALUES ('58', '66', '50m', '7.77');
INSERT INTO `score` VALUES ('59', '100', '100m', '100.00');

-- ----------------------------
-- Table structure for `system_admin`
-- ----------------------------
DROP TABLE IF EXISTS `system_admin`;
CREATE TABLE `system_admin` (
  `id` varchar(25) CHARACTER SET utf8 NOT NULL,
  `user_name` varchar(25) CHARACTER SET utf8 NOT NULL,
  `password` varchar(25) CHARACTER SET utf8 NOT NULL,
  `telephone` varchar(25) CHARACTER SET utf8 DEFAULT NULL,
  `career` varchar(25) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of system_admin
-- ----------------------------
INSERT INTO `system_admin` VALUES ('system01', '1', '1', '18158159271', 'system_admin');
INSERT INTO `system_admin` VALUES ('system02', 'user_system2', '123456', '13922278372', 'system_admin');
INSERT INTO `system_admin` VALUES ('system03', 'user_system3', '123456', '13788762829', 'system_admin');
