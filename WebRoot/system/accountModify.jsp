<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>用户维护</title>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="accountModify.do">
			<h1>用户维护</h1>
			<input type="button" value="返回" onclick="history.go(-1);" />
			<html:submit property="submit" value="保存"/>
			<ul>
				<li>
					用户登录名
					<html:text property="username" />
				</li>
				<li>
					用户显示名
					<html:text property="realname" />
				</li>
				<li>
					密码
					<html:password property="password" />
				</li>
				<li>
					请在输入一次密码
					<html:password property="passwordRepeat" />
				</li>
			</ul>
		</html:form>
	</body>
</html>
