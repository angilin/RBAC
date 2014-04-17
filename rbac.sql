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

-- 正在导出表  rbac.sys_account 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `sys_account` DISABLE KEYS */;
INSERT INTO `sys_account` (`ID`, `USERNAME`, `PASSWORD`, `REALNAME`, `SALT`, `IS_DELETED`, `CREATOR_ID`, `CREATE_TIME`, `MODIFIER_ID`, `MODIFY_TIME`) VALUES
	(1, 'admin', 'bb84f339b1cfb72a933809dd1a55edb4deb326fd78975554', '系统管理员', '3fc91ab38271c27ed4531999670716fb2a3c7e59d02293de', 0, NULL, NULL, NULL, '2014-03-20 16:59:51');
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

-- 正在导出表  rbac.sys_account_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_account_role` DISABLE KEYS */;
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

-- 正在导出表  rbac.sys_action 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_action` DISABLE KEYS */;
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
  `ORDER_SEQ` INT(10) NULL DEFAULT NULL COMMENT '排序字段',
  PRIMARY KEY  (`ID`),
  KEY `FK_sys_menu_sys_menu` (`PARENT_ID`),
  CONSTRAINT `FK_sys_menu_sys_menu` FOREIGN KEY (`PARENT_ID`) REFERENCES `sys_menu` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- 正在导出表  rbac.sys_menu 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
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

-- 正在导出表  rbac.sys_menu_action 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_menu_action` DISABLE KEYS */;
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

-- 正在导出表  rbac.sys_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
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

-- 正在导出表  rbac.sys_role_menu 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
