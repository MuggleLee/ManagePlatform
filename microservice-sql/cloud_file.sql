/*
Navicat MySQL Data Transfer

Source Server         : local-docker-mysql
Source Server Version : 50646
Source Host           : 127.0.0.1:12345
Source Database       : cloud_file

Target Server Type    : MYSQL
Target Server Version : 50646
File Encoding         : 65001

Date: 2019-11-04 00:04:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info` (
  `id` varchar(32) NOT NULL COMMENT '文件md5',
  `name` varchar(128) NOT NULL COMMENT '文件名',
  `isImg` tinyint(1) NOT NULL COMMENT '是否是图片',
  `contentType` varchar(128) NOT NULL COMMENT '文件类型',
  `size` int(11) NOT NULL COMMENT '文件大小',
  `path` varchar(255) DEFAULT NULL COMMENT '物理路径',
  `url` varchar(1024) NOT NULL COMMENT '文件网络url',
  `source` varchar(32) NOT NULL COMMENT '文件存储地方',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `createTime` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件信息表';

-- ----------------------------
-- Records of file_info
-- ----------------------------
INSERT INTO `file_info` VALUES ('88655cb64e3398fbd82ac4623cbffced', 'headPicture.jpg', '1', 'image/jpeg', '26087', '/uploadPicture/Microserver-file/2019/11/02/88655cb64e3398fbd82ac4623cbffced.jpg', 'http://mugglelee.nat300.top/api-f/statics/2019/11/02/88655cb64e3398fbd82ac4623cbffced.jpg', 'LOCAL', '2019-11-02 19:27:32');
