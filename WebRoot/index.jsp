<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>登录页面</title>
</head>
<body>
	用户登录名:<c:out value="${sessionScope['user'].username}"></c:out>
	<br/>
	用户显示名:<c:out value="${sessionScope['user'].realname}"></c:out>
</body>
</html>



