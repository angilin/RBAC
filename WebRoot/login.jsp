<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>RBAC系统登录页面</title>
</head>
<body>
	<html:form action="login.do">
		<div style="margin:0px auto;width:300px;">
			<h1>RBAC系统</h1>
			<div><span style="width:100px">用户名：</span><html:text property="username"/></div>
			<div><span style="width:100px">密&nbsp;&nbsp;码：</span><html:password property="password"/></div>
			<center><html:submit property="submit" value="登录"/></center>
			<div><font color="red">${errormsg}</font></div>
		</div>
	</html:form>
</body>
</html>



