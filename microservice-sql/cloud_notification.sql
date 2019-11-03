/*
Navicat MySQL Data Transfer

Source Server         : local-docker-mysql
Source Server Version : 50646
Source Host           : 127.0.0.1:12345
Source Database       : cloud_notification

Target Server Type    : MYSQL
Target Server Version : 50646
File Encoding         : 65001

Date: 2019-11-04 00:04:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sms
-- ----------------------------
DROP TABLE IF EXISTS `t_sms`;
CREATE TABLE `t_sms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(16) NOT NULL COMMENT '手机号码',
  `signName` varchar(128) DEFAULT NULL COMMENT '阿里云短信签名',
  `templateCode` varchar(128) DEFAULT NULL COMMENT '阿里云模板code',
  `params` varchar(500) DEFAULT NULL COMMENT '参数',
  `bizId` varchar(128) DEFAULT NULL COMMENT '阿里云返回的',
  `code` varchar(64) DEFAULT NULL COMMENT '阿里云返回的code',
  `message` varchar(128) DEFAULT NULL COMMENT '阿里云返回的',
  `day` date NOT NULL COMMENT '日期（冗余字段,便于统计某天的发送次数）',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `phone` (`phone`),
  KEY `day` (`day`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COMMENT='发短信记录表';

-- ----------------------------
-- Records of t_sms
-- ----------------------------
INSERT INTO `t_sms` VALUES ('10', '18320628585', 'MuggleLee', 'SMS_172883578', '{\"code\":\"047953\"}', '838221470636676961^0', 'OK', 'OK', '2019-10-09', '2019-10-09 23:57:48', '2019-10-09 23:57:48');
INSERT INTO `t_sms` VALUES ('11', '18320628585', 'MuggleLee', 'SMS_172883578', '{\"code\":\"652994\"}', '122200572023568218^0', 'OK', 'OK', '2019-10-26', '2019-10-26 01:12:44', '2019-10-26 01:12:45');
INSERT INTO `t_sms` VALUES ('12', '18320628585', 'MuggleLee', 'SMS_172883578', '{\"code\":\"073442\"}', '410300872063768292^0', 'OK', 'OK', '2019-10-26', '2019-10-26 12:22:43', '2019-10-26 12:22:44');
INSERT INTO `t_sms` VALUES ('13', '18320628585', 'MuggleLee', 'SMS_172883578', '{\"code\":\"228170\"}', '748104372145444049^0', 'OK', 'OK', '2019-10-27', '2019-10-27 11:03:51', '2019-10-27 11:03:58');
INSERT INTO `t_sms` VALUES ('14', '18320628585', 'MuggleLee', 'SMS_172883578', '{\"code\":\"759339\"}', '268007072145908692^0', 'OK', 'OK', '2019-10-27', '2019-10-27 11:11:42', '2019-10-27 11:11:43');
INSERT INTO `t_sms` VALUES ('15', '18320628585', 'MuggleLee', 'SMS_172883578', '{\"code\":\"013044\"}', '418321772277374456^0', 'OK', 'OK', '2019-10-28', '2019-10-28 23:42:47', '2019-10-28 23:42:48');
INSERT INTO `t_sms` VALUES ('16', '18320628585', 'MuggleLee', 'SMS_172883578', '{\"code\":\"525733\"}', '321216572277519995^0', 'OK', 'OK', '2019-10-28', '2019-10-28 23:45:13', '2019-10-28 23:45:13');
INSERT INTO `t_sms` VALUES ('17', '18320628585', 'MuggleLee', 'SMS_172883578', '{\"code\":\"410544\"}', '773818172277917823^0', 'OK', 'OK', '2019-10-28', '2019-10-28 23:51:51', '2019-10-28 23:51:51');
INSERT INTO `t_sms` VALUES ('18', '13242817418', 'MuggleLee', 'SMS_172883578', '{\"code\":\"232909\"}', '719714772277958062^0', 'OK', 'OK', '2019-10-28', '2019-10-28 23:52:31', '2019-10-28 23:52:31');
INSERT INTO `t_sms` VALUES ('19', '13242817418', 'MuggleLee', 'SMS_172883578', '{\"code\":\"389502\"}', '943821072278009174^0', 'OK', 'OK', '2019-10-28', '2019-10-28 23:53:22', '2019-10-28 23:53:22');
INSERT INTO `t_sms` VALUES ('20', '18320628585', 'MuggleLee', 'SMS_172883578', '{\"code\":\"150815\"}', '960210972694438500^0', 'OK', 'OK', '2019-11-02', '2019-11-02 19:33:48', '2019-11-02 19:33:49');
INSERT INTO `t_sms` VALUES ('21', '13242817418', 'MuggleLee', 'SMS_172883578', '{\"code\":\"053167\"}', '698801872705076886^0', 'OK', 'OK', '2019-11-02', '2019-11-02 22:31:04', '2019-11-02 22:31:06');
INSERT INTO `t_sms` VALUES ('22', '18320628585', 'MuggleLee', 'SMS_172883578', '{\"code\":\"555276\"}', '667209772705117211^0', 'OK', 'OK', '2019-11-02', '2019-11-02 22:31:45', '2019-11-02 22:31:46');
