/*
Navicat MySQL Data Transfer

Source Server         : 10.6.1.70_mysql
Source Server Version : 50538
Source Host           : 10.6.1.70:3306
Source Database       : itmanager

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2015-10-13 17:56:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for busi_info
-- ----------------------------
DROP TABLE IF EXISTS `busi_info`;
CREATE TABLE `busi_info` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `busi_code` varchar(20) DEFAULT NULL,
  `busi_desc` varchar(30) DEFAULT NULL,
  `sys_id` int(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for busi_info_tmp
-- ----------------------------
DROP TABLE IF EXISTS `busi_info_tmp`;
CREATE TABLE `busi_info_tmp` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `busi_code` varchar(20) DEFAULT NULL,
  `busi_desc` varchar(30) DEFAULT NULL,
  `sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for busi_info_user_group_ref
-- ----------------------------
DROP TABLE IF EXISTS `busi_info_user_group_ref`;
CREATE TABLE `busi_info_user_group_ref` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `busi_info_id` int(11) DEFAULT NULL,
  `user_group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for busi_sys_ref
-- ----------------------------
DROP TABLE IF EXISTS `busi_sys_ref`;
CREATE TABLE `busi_sys_ref` (
  `busi_id` int(32) DEFAULT NULL,
  `sys_id` int(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for earlywarn_busi_ref
-- ----------------------------
DROP TABLE IF EXISTS `earlywarn_busi_ref`;
CREATE TABLE `earlywarn_busi_ref` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id,主键',
  `sys_id` int(32) DEFAULT NULL,
  `busi_id` int(32) DEFAULT NULL,
  `ew_id` int(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预警配置类与业务的中间表';

-- ----------------------------
-- Table structure for earlywarn_sys_ref
-- ----------------------------
DROP TABLE IF EXISTS `earlywarn_sys_ref`;
CREATE TABLE `earlywarn_sys_ref` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id,主键',
  `sys_id` int(32) DEFAULT NULL,
  `ew_id` int(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='预警配置类与系统的中间表';

-- ----------------------------
-- Table structure for mail_config_info
-- ----------------------------
DROP TABLE IF EXISTS `mail_config_info`;
CREATE TABLE `mail_config_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mail_server` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `mail_sender` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `mail_nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `mail_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `mail_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `used_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mes_all_process_req
-- ----------------------------
DROP TABLE IF EXISTS `mes_all_process_req`;
CREATE TABLE `mes_all_process_req` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL,
  `warn_count` int(11) DEFAULT NULL,
  `error_count` int(11) DEFAULT NULL,
  `created_time` varchar(32) DEFAULT NULL,
  `process_status` char(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1801 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mes_busi_process_req
-- ----------------------------
DROP TABLE IF EXISTS `mes_busi_process_req`;
CREATE TABLE `mes_busi_process_req` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `busi_warn_count` int(11) DEFAULT NULL,
  `busi_err_count` int(11) DEFAULT NULL,
  `sys_id` int(11) DEFAULT NULL,
  `busi_code` varchar(20) DEFAULT NULL,
  `sys_code` varchar(20) DEFAULT NULL,
  `created_time` varchar(20) DEFAULT NULL,
  `err_id` text,
  `err_mess_id` text,
  `warn_mess_id` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mes_early_warn_info
-- ----------------------------
DROP TABLE IF EXISTS `mes_early_warn_info`;
CREATE TABLE `mes_early_warn_info` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id,主键',
  `description` varchar(40) DEFAULT NULL COMMENT '描述',
  `flag` char(2) DEFAULT '0' COMMENT '是否有效,0.有效,1.无效',
  `status` char(2) DEFAULT '1' COMMENT '状态,0.启动,1.关闭',
  `warn_count_min` int(32) DEFAULT '1' COMMENT '异常警告发送邮件或短信的触发条数',
  `sys_id` int(32) DEFAULT '2' COMMENT '异常提醒发送方式,1.短信,2.邮件,3.短信+邮件',
  `reserved1` varchar(50) DEFAULT NULL,
  `reserved2` varchar(50) DEFAULT NULL,
  `reserved3` varchar(50) DEFAULT NULL,
  `reserved4` varchar(50) DEFAULT NULL,
  `busi_id` int(32) DEFAULT '1' COMMENT '预警等级,1.警告,2.异常',
  `error_count_min` int(32) DEFAULT NULL COMMENT '异常数量允许最小值',
  `send_type` char(3) DEFAULT NULL,
  `error_count_max` int(32) DEFAULT NULL COMMENT '异常数量允许最大值',
  `warn_count_max` int(32) DEFAULT '1' COMMENT '异常警告发送邮件或短信的最大值',
  `sys_child_active_threshold_max_count` int(5) DEFAULT '6' COMMENT '服务器下的实例允许的最小数量',
  `sys_req_apdex_threshold_min` int(5) DEFAULT '3' COMMENT 'apdex的阀值',
  `sys_child_active_threshold_min_count` int(5) DEFAULT '3' COMMENT '服务器下的实例允许的最小数量',
  `sys_req_apdex_threshold_max` int(5) DEFAULT '12' COMMENT 'apdex的阀值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='异常信息发送规则配置基础类';

-- ----------------------------
-- Table structure for mes_err_process_req
-- ----------------------------
DROP TABLE IF EXISTS `mes_err_process_req`;
CREATE TABLE `mes_err_process_req` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `sys_id` int(32) DEFAULT NULL,
  `busi_id` int(32) DEFAULT NULL,
  `sys_code` varchar(20) DEFAULT NULL,
  `busi_code` varchar(20) DEFAULT NULL,
  `err_code` varchar(20) DEFAULT NULL,
  `err_err_count` int(32) DEFAULT NULL,
  `err_warn_count` int(32) DEFAULT NULL,
  `created_time` varchar(30) DEFAULT NULL,
  `process_status` char(3) DEFAULT NULL,
  `all_code` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mes_process_track
-- ----------------------------
DROP TABLE IF EXISTS `mes_process_track`;
CREATE TABLE `mes_process_track` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(24) DEFAULT NULL COMMENT '用户编号',
  `err_id` text COMMENT '异常编号,可以是一个，也可以是一串',
  `process_status` char(3) DEFAULT NULL COMMENT '处理状态 0.未处理，1.正在处理，2.处理完成',
  `created_time` varchar(30) DEFAULT NULL COMMENT '创建日期 yyyy-MM-dd hh:mm:ss',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mes_sys_process_req
-- ----------------------------
DROP TABLE IF EXISTS `mes_sys_process_req`;
CREATE TABLE `mes_sys_process_req` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_warn_count` int(11) DEFAULT NULL,
  `sys_err_count` int(11) DEFAULT NULL,
  `sys_code` varchar(20) DEFAULT NULL,
  `created_time` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=307 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for PCDS_SYMENU
-- ----------------------------
DROP TABLE IF EXISTS `PCDS_SYMENU`;
CREATE TABLE `PCDS_SYMENU` (
  `ID` varchar(36) NOT NULL,
  `PID` varchar(36) DEFAULT NULL COMMENT '父ID',
  `TEXT` varchar(100) NOT NULL COMMENT '菜单名称',
  `ICONCLS` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `SRC` varchar(200) DEFAULT NULL COMMENT '菜单地址',
  `SEQ` decimal(22,0) NOT NULL COMMENT '顺序',
  PRIMARY KEY (`ID`),
  KEY `FKBAFBF1E219A69B63` (`PID`),
  CONSTRAINT `FKBAFBF1E219A69B63` FOREIGN KEY (`PID`) REFERENCES `PCDS_SYMENU` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Table structure for PCDS_SYPORTAL
-- ----------------------------
DROP TABLE IF EXISTS `PCDS_SYPORTAL`;
CREATE TABLE `PCDS_SYPORTAL` (
  `ID` varchar(36) NOT NULL,
  `TITLE` varchar(100) NOT NULL COMMENT '标题',
  `SRC` varchar(200) NOT NULL COMMENT '地址',
  `HEIGHT` decimal(22,0) DEFAULT NULL COMMENT '高度',
  `CLOSABLE` varchar(1) DEFAULT NULL COMMENT '是否显示关闭按钮',
  `COLLAPSIBLE` varchar(1) DEFAULT NULL COMMENT '是否显示折叠按钮',
  `SEQ` decimal(22,0) DEFAULT NULL COMMENT '顺序',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for PCDS_SYRESOURCES
-- ----------------------------
DROP TABLE IF EXISTS `PCDS_SYRESOURCES`;
CREATE TABLE `PCDS_SYRESOURCES` (
  `ID` varchar(36) NOT NULL,
  `PID` varchar(36) DEFAULT NULL COMMENT '父ID',
  `TEXT` varchar(100) DEFAULT NULL COMMENT '资源名',
  `SEQ` decimal(22,0) NOT NULL COMMENT '顺序',
  `SRC` varchar(200) DEFAULT NULL COMMENT '资源地址',
  `DESCRIPT` varchar(100) DEFAULT NULL COMMENT '描述',
  `ONOFF` varchar(1) DEFAULT NULL COMMENT '1.开启验证，0.不开启',
  PRIMARY KEY (`ID`),
  KEY `FK93E6436240DB7ED7` (`PID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
-- Table structure for PCDS_SYROLE
-- ----------------------------
DROP TABLE IF EXISTS `PCDS_SYROLE`;
CREATE TABLE `PCDS_SYROLE` (
  `ID` varchar(36) NOT NULL,
  `PID` varchar(36) DEFAULT NULL COMMENT '父ID',
  `TEXT` varchar(100) DEFAULT NULL COMMENT '角色名',
  `SEQ` decimal(22,0) NOT NULL COMMENT '顺序',
  `DESCRIPT` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`ID`),
  KEY `FKBAFE5CF919A9067A` (`PID`),
  CONSTRAINT `FKBAFE5CF919A9067A` FOREIGN KEY (`PID`) REFERENCES `PCDS_SYROLE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for PCDS_SYROLE_SYMENUS
-- ----------------------------
DROP TABLE IF EXISTS `PCDS_SYROLE_SYMENUS`;
CREATE TABLE `PCDS_SYROLE_SYMENUS` (
  `ID` varchar(36) NOT NULL,
  `ROLE_ID` varchar(36) NOT NULL COMMENT '角色ID',
  `MENU_ID` varchar(36) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`ID`),
  KEY `FKFAE41EE8524A55D3` (`MENU_ID`),
  KEY `FKFAE41EE86BB8EA73` (`ROLE_ID`),
  CONSTRAINT `FKFAE41EE8524A55D3` FOREIGN KEY (`MENU_ID`) REFERENCES `PCDS_SYMENU` (`ID`),
  CONSTRAINT `FKFAE41EE86BB8EA73` FOREIGN KEY (`ROLE_ID`) REFERENCES `PCDS_SYROLE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关系表';

-- ----------------------------
-- Table structure for PCDS_SYROLE_SYRESOURCES
-- ----------------------------
DROP TABLE IF EXISTS `PCDS_SYROLE_SYRESOURCES`;
CREATE TABLE `PCDS_SYROLE_SYRESOURCES` (
  `ID` varchar(36) NOT NULL,
  `ROLE_ID` varchar(36) NOT NULL COMMENT '角色ID',
  `RESOURCES_ID` varchar(36) NOT NULL COMMENT '资源ID',
  PRIMARY KEY (`ID`),
  KEY `FK804524196BB8EA73` (`ROLE_ID`),
  KEY `FK8045241929FE92C1` (`RESOURCES_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源关系表';

-- ----------------------------
-- Table structure for PCDS_SYUSER
-- ----------------------------
DROP TABLE IF EXISTS `PCDS_SYUSER`;
CREATE TABLE `PCDS_SYUSER` (
  `ID` int(36) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL COMMENT '登录名',
  `PASSWORD` varchar(100) NOT NULL COMMENT '密码',
  `CREATEDATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `MODIFYDATETIME` datetime DEFAULT NULL COMMENT '最后修改时间',
  `FF` char(200) DEFAULT NULL,
  `dd` int(11) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮件',
  `tel` varchar(50) DEFAULT NULL COMMENT '电话号码',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for PCDS_SYUSER_SYROLE
-- ----------------------------
DROP TABLE IF EXISTS `PCDS_SYUSER_SYROLE`;
CREATE TABLE `PCDS_SYUSER_SYROLE` (
  `ID` varchar(36) NOT NULL,
  `SYROLE_ID` varchar(36) NOT NULL COMMENT '角色ID',
  `SYUSER_ID` int(36) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`ID`),
  KEY `FK7DE9C0EDC50FC12D` (`SYUSER_ID`),
  KEY `FK7DE9C0ED1FE4FD4D` (`SYROLE_ID`),
  CONSTRAINT `FK7DE9C0ED1FE4FD4D` FOREIGN KEY (`SYROLE_ID`) REFERENCES `PCDS_SYROLE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

-- ----------------------------
-- Table structure for sms_config_info
-- ----------------------------
DROP TABLE IF EXISTS `sms_config_info`;
CREATE TABLE `sms_config_info` (
  `sms_send_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `used_status` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_info`;
CREATE TABLE `sys_info` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `sys_code` varchar(20) NOT NULL,
  `sys_desc` varchar(30) DEFAULT NULL,
  `sys_name` varchar(30) DEFAULT NULL COMMENT '系统名称',
  `sys_child_active_threshold_max_count` int(5) DEFAULT '6' COMMENT '服务器下的实例允许的最小数量',
  `sys_req_apdex_threshold_min` int(5) DEFAULT '3' COMMENT 'apdex的阀值',
  `sys_child_active_threshold_min_count` int(5) DEFAULT '3' COMMENT '服务器下的实例允许的最小数量',
  `sys_req_apdex_threshold_max` int(5) DEFAULT '12' COMMENT 'apdex的阀值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_info_user_group_ref
-- ----------------------------
DROP TABLE IF EXISTS `sys_info_user_group_ref`;
CREATE TABLE `sys_info_user_group_ref` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sys_info_id` int(11) DEFAULT NULL,
  `user_group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) DEFAULT NULL,
  `group_desc` varchar(500) DEFAULT NULL,
  `send_type` int(11) DEFAULT NULL COMMENT '0.邮件\r\n1.短信\r\n2.短信+邮件',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for user_group_user_info_ref
-- ----------------------------
DROP TABLE IF EXISTS `user_group_user_info_ref`;
CREATE TABLE `user_group_user_info_ref` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_group_id` int(11) DEFAULT NULL,
  `user_info_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(20) DEFAULT NULL,
  `user_Name` varchar(30) DEFAULT NULL,
  `busi_id` int(32) DEFAULT NULL,
  `sys_id` int(32) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zk_node_class_library_info
-- ----------------------------
DROP TABLE IF EXISTS `zk_node_class_library_info`;
CREATE TABLE `zk_node_class_library_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_name` varchar(40) DEFAULT NULL,
  `node_name` varchar(60) DEFAULT NULL,
  `node_desc` varchar(60) DEFAULT NULL,
  `class_path` text,
  `library_path` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=381 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zk_node_data_info
-- ----------------------------
DROP TABLE IF EXISTS `zk_node_data_info`;
CREATE TABLE `zk_node_data_info` (
  `id` varchar(40) NOT NULL,
  `node_desc` varchar(60) DEFAULT NULL,
  `node_ip` varchar(30) DEFAULT NULL,
  `update_time` varchar(30) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `root_name` varchar(30) DEFAULT NULL,
  `parent_name` varchar(30) DEFAULT NULL,
  `node_name` varchar(30) DEFAULT NULL,
  `node_data` text,
  `created_time_format` varchar(40) DEFAULT NULL,
  `node_count` int(5) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `created_time_idx` (`created_time`),
  KEY `parent_name_idx` (`parent_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
