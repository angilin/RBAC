-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.0.41-community-nt - MySQL Community Edition (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 rbac 的数据库结构
CREATE DATABASE IF NOT EXISTS `rbac` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `rbac`;


-- 导出  表 rbac.sys_account 结构
CREATE TABLE IF NOT EXISTS `sys_account` (
  `ID` bigint(20) NOT NULL auto_increment COMMENT '主键',
  `USERNAME` varchar(20) NOT NULL COMMENT '用户登录名',
  `PASSWORD` varchar(100) NOT NULL COMMENT '密码',
  `REALNAME` varchar(20) default NULL COMMENT '用户显示名',
  `SALT` varchar(100) NOT NULL COMMENT '盐',
  `IS_DELETED` int(11) NOT NULL default '0' COMMENT '删除标识位',
  `CREATOR_ID` bigint(20) default NULL COMMENT '创建者id',
  `CREATE_TIME` timestamp NULL default NULL COMMENT '创建时间',
  `MODIFIER_ID` bigint(20) default NULL COMMENT '修改者id',
  `MODIFY_TIME` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 正在导出表  rbac.sys_account 的数据：~9 rows (大约)
/*!40000 ALTER TABLE `sys_account` DISABLE KEYS */;
INSERT INTO `sys_account` (`ID`, `USERNAME`, `PASSWORD`, `REALNAME`, `SALT`, `IS_DELETED`, `CREATOR_ID`, `CREATE_TIME`, `MODIFIER_ID`, `MODIFY_TIME`) VALUES
	(1, 'admin', 'b8bbe0f553f9255cc60cb78be320de82835a51ab248a4ddc', '系统管理员', 'caf4596a842145983bdb98ae5a0545bf358ad2f38ce480bd', 0, 1, '2014-03-21 18:14:33', 1, '2014-04-30 14:09:29');
/*!40000 ALTER TABLE `sys_account` ENABLE KEYS */;


-- 导出  表 rbac.sys_account_role 结构
CREATE TABLE IF NOT EXISTS `sys_account_role` (
  `ID` bigint(20) NOT NULL auto_increment COMMENT '主键',
  `ACCOUNT_ID` bigint(20) default NULL COMMENT '用户id',
  `ROLE_ID` bigint(20) default NULL COMMENT '角色id',
  `IS_DELETED` int(11) NOT NULL default '0' COMMENT '删除标识位',
  `CREATOR_ID` bigint(20) default NULL COMMENT '创建者id',
  `CREATE_TIME` timestamp NULL default NULL COMMENT '创建时间',
  `MODIFIER_ID` bigint(20) default NULL COMMENT '修改者id',
  `MODIFY_TIME` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY  (`ID`),
  KEY `FK_sys_account_role_sys_account` (`ACCOUNT_ID`),
  KEY `FK_sys_account_role_sys_role` (`ROLE_ID`),
  CONSTRAINT `FK_sys_account_role_sys_account` FOREIGN KEY (`ACCOUNT_ID`) REFERENCES `sys_account` (`ID`),
  CONSTRAINT `FK_sys_account_role_sys_role` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账号角色表';

-- 正在导出表  rbac.sys_account_role 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `sys_account_role` DISABLE KEYS */;
INSERT INTO `sys_account_role` (`ID`, `ACCOUNT_ID`, `ROLE_ID`, `IS_DELETED`, `CREATOR_ID`, `CREATE_TIME`, `MODIFIER_ID`, `MODIFY_TIME`) VALUES
	(1, 1, 1, 0, 1, '2014-03-21 18:15:00', 1, '2014-03-21 18:15:05');
/*!40000 ALTER TABLE `sys_account_role` ENABLE KEYS */;


-- 导出  表 rbac.sys_action 结构
CREATE TABLE IF NOT EXISTS `sys_action` (
  `ID` bigint(20) NOT NULL auto_increment,
  `NAME` varchar(20) default NULL COMMENT '权限名称',
  `URL` varchar(50) default NULL COMMENT '权限url',
  `NEED_CHECK` int(11) default '1' COMMENT '是否需要验证',
  `IS_DELETED` int(11) NOT NULL default '0' COMMENT '删除标识位',
  `CREATOR_ID` bigint(20) default NULL COMMENT '创建者id',
  `CREATE_TIME` timestamp NULL default NULL COMMENT '创建时间',
  `MODIFIER_ID` bigint(20) default NULL COMMENT '修改者id',
  `MODIFY_TIME` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- 正在导出表  rbac.sys_action 的数据：~6 rows (大约)
/*!40000 ALTER TABLE `sys_action` DISABLE KEYS */;
INSERT INTO `sys_action` (`ID`, `NAME`, `URL`, `NEED_CHECK`, `IS_DELETED`, `CREATOR_ID`, `CREATE_TIME`, `MODIFIER_ID`, `MODIFY_TIME`) VALUES
	(1, '用户维护', 'accountModify.do', 1, 0, NULL, NULL, 1, '2014-05-05 10:39:13'),
	(2, '角色维护', 'roleModify.do', 1, 0, NULL, NULL, NULL, '2014-05-05 10:39:15'),
	(3, '菜单维护', 'menuModify.do', 1, 0, NULL, NULL, NULL, '2014-05-05 10:39:18'),
	(4, '权限维护', 'actionModify.do', 1, 0, NULL, NULL, NULL, '2014-05-05 10:39:20');
/*!40000 ALTER TABLE `sys_action` ENABLE KEYS */;


-- 导出  表 rbac.sys_menu 结构
CREATE TABLE IF NOT EXISTS `sys_menu` (
  `ID` bigint(20) NOT NULL auto_increment,
  `NAME` varchar(20) default NULL COMMENT '菜单显示名',
  `URL` varchar(50) default NULL COMMENT '菜单的url路径',
  `PARENT_ID` bigint(20) default NULL COMMENT '父菜单id',
  `NEED_CHECK` int(11) NOT NULL default '1' COMMENT '是否需要权限才能访问',
  `IS_SHOW` int(11) NOT NULL default '1' COMMENT '是否显示该菜单',
  `IS_DELETED` int(11) NOT NULL default '0' COMMENT '删除标识位',
  `CREATOR_ID` bigint(20) default NULL COMMENT '创建者id',
  `CREATE_TIME` timestamp NULL default NULL COMMENT '创建时间',
  `MODIFIER_ID` bigint(20) default NULL COMMENT '修改者id',
  `MODIFY_TIME` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改日期',
  `ORDER_SEQ` int(10) default NULL COMMENT '排序字段',
  PRIMARY KEY  (`ID`),
  KEY `FK_sys_menu_sys_menu` (`PARENT_ID`),
  CONSTRAINT `FK_sys_menu_sys_menu` FOREIGN KEY (`PARENT_ID`) REFERENCES `sys_menu` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- 正在导出表  rbac.sys_menu 的数据：~7 rows (大约)
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` (`ID`, `NAME`, `URL`, `PARENT_ID`, `NEED_CHECK`, `IS_SHOW`, `IS_DELETED`, `CREATOR_ID`, `CREATE_TIME`, `MODIFIER_ID`, `MODIFY_TIME`, `ORDER_SEQ`) VALUES
	(1, '系统管理', '', NULL, 1, 1, 0, 1, '2014-03-21 18:17:17', 1, '2014-05-04 17:14:04', 9),
	(2, '个人设置', '', NULL, 1, 1, 0, 1, '2014-03-21 18:17:19', 1, '2014-03-21 18:29:44', 10),
	(11, '用户管理', 'accountList.do', 1, 1, 1, 0, 1, '2014-03-21 18:29:33', 1, '2014-05-05 11:26:04', 1),
	(12, '角色管理', 'roleList.do', 1, 1, 1, 0, 1, '2014-04-11 10:41:58', 1, '2014-05-05 10:38:53', 2),
	(13, '菜单管理', 'menuList.do', 1, 1, 1, 0, 1, '2014-04-30 14:43:52', 1, '2014-05-05 10:38:56', 3),
	(14, '权限管理', 'actionList.do', 1, 1, 1, 0, 1, '2014-04-30 14:44:45', 1, '2014-05-05 10:38:58', 4),
	(22, '修改密码', 'pwdModify.do', 2, 1, 1, 0, 1, '2014-03-21 18:23:12', 1, '2014-05-05 10:39:01', 1);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;


-- 导出  表 rbac.sys_menu_action 结构
CREATE TABLE IF NOT EXISTS `sys_menu_action` (
  `ID` bigint(20) NOT NULL auto_increment,
  `ACTION_ID` bigint(20) default NULL COMMENT '权限id',
  `MENU_ID` bigint(20) default NULL COMMENT '菜单id',
  `IS_DELETED` int(11) NOT NULL default '0' COMMENT '删除标识位',
  `CREATOR_ID` bigint(20) default NULL COMMENT '创建者id',
  `CREATE_TIME` timestamp NULL default NULL COMMENT '创建时间',
  `MODIFIER_ID` bigint(20) default NULL COMMENT '修改者id',
  `MODIFY_TIME` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY  (`ID`),
  KEY `FK_sys_action_relate_sys_action` (`ACTION_ID`),
  KEY `FK_sys_action_relate_sys_menu` (`MENU_ID`),
  CONSTRAINT `FK_sys_action_relate_sys_action` FOREIGN KEY (`ACTION_ID`) REFERENCES `sys_action` (`ID`),
  CONSTRAINT `FK_sys_action_relate_sys_menu` FOREIGN KEY (`MENU_ID`) REFERENCES `sys_menu` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

-- 正在导出表  rbac.sys_menu_action 的数据：~6 rows (大约)
/*!40000 ALTER TABLE `sys_menu_action` DISABLE KEYS */;
INSERT INTO `sys_menu_action` (`ID`, `ACTION_ID`, `MENU_ID`, `IS_DELETED`, `CREATOR_ID`, `CREATE_TIME`, `MODIFIER_ID`, `MODIFY_TIME`) VALUES
	(2, 2, 12, 0, NULL, NULL, NULL, '2014-04-25 14:16:26'),
	(3, 3, 13, 0, NULL, NULL, NULL, '2014-04-30 14:47:04'),
	(4, 4, 14, 0, NULL, NULL, NULL, '2014-04-30 14:47:14'),
	(6, 1, 11, 0, 1, '2014-05-05 11:26:04', NULL, '2014-05-05 11:26:04');
/*!40000 ALTER TABLE `sys_menu_action` ENABLE KEYS */;


-- 导出  表 rbac.sys_role 结构
CREATE TABLE IF NOT EXISTS `sys_role` (
  `ID` bigint(20) NOT NULL auto_increment COMMENT '主键',
  `ROLE_NAME` varchar(50) default NULL COMMENT '角色名',
  `ROLE_DESC` varchar(200) default NULL COMMENT '角色描述',
  `IS_DELETED` int(11) NOT NULL default '0' COMMENT '删除标识位',
  `CREATOR_ID` bigint(20) default NULL COMMENT '创建者id',
  `CREATE_TIME` timestamp NULL default NULL COMMENT '创建时间',
  `MODIFIER_ID` bigint(20) default NULL COMMENT '修改者id',
  `MODIFY_TIME` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- 正在导出表  rbac.sys_role 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` (`ID`, `ROLE_NAME`, `ROLE_DESC`, `IS_DELETED`, `CREATOR_ID`, `CREATE_TIME`, `MODIFIER_ID`, `MODIFY_TIME`) VALUES
	(1, '系统管理员角色', '系统管理员', 0, 1, '2014-03-21 18:14:18', 1, '2014-05-06 17:41:03');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;


-- 导出  表 rbac.sys_role_menu 结构
CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `ID` bigint(20) NOT NULL auto_increment,
  `ROLE_ID` bigint(20) default NULL COMMENT '角色id',
  `MENU_ID` bigint(20) default NULL COMMENT '菜单id',
  `IS_DELETED` int(11) NOT NULL default '0' COMMENT '删除标识位',
  `CREATOR_ID` bigint(20) default NULL COMMENT '创建者id',
  `CREATE_TIME` timestamp NULL default NULL COMMENT '创建时间',
  `MODIFIER_ID` bigint(20) default NULL COMMENT '修改者id',
  `MODIFY_TIME` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY  (`ID`),
  KEY `FK_sys_role_action_sys_role` (`ROLE_ID`),
  KEY `FK_sys_role_action_sys_menu` (`MENU_ID`),
  CONSTRAINT `FK_sys_role_action_sys_menu` FOREIGN KEY (`MENU_ID`) REFERENCES `sys_menu` (`ID`),
  CONSTRAINT `FK_sys_role_action_sys_role` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- 正在导出表  rbac.sys_role_menu 的数据：~20 rows (大约)
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` (`ID`, `ROLE_ID`, `MENU_ID`, `IS_DELETED`, `CREATOR_ID`, `CREATE_TIME`, `MODIFIER_ID`, `MODIFY_TIME`) VALUES
	(1, 1, 1, 0, 1, '2014-03-21 18:30:31', 1, '2014-03-21 18:30:32'),
	(2, 1, 2, 0, 1, '2014-03-21 18:30:48', 1, '2014-03-21 18:30:50'),
	(3, 1, 11, 0, 1, '2014-03-21 18:31:08', 1, '2014-03-21 18:31:11'),
	(4, 1, 22, 0, 1, '2014-03-21 18:31:31', 1, '2014-03-21 18:31:32'),
	(5, 1, 12, 0, 1, '2014-04-11 10:42:56', 1, '2014-04-11 10:42:59'),
	(6, 1, 13, 0, 1, '2014-04-30 14:45:15', 1, '2014-04-30 14:45:17'),
	(7, 1, 14, 0, 1, '2014-04-30 14:45:30', 1, '2014-04-30 14:45:32');
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
