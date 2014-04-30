<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>RBAC系统登录页面</title>
<script type="text/javascript">
	function checkKeyCode13(e,type){
		if(window.event) // IE
	  	{
	  		keynum = e.keyCode
	  	}
		else if(e.which) // Netscape/Firefox/Opera
  		{
  			keynum = e.which
  		}
		if(keynum==13 && type==0){
			document.getElementById('password').focus();
		}
		if(keynum==13 && type==1){
			document.forms[0].submit();
		}
	}
	
	function relocation(){
		if(window.top!=window && '${error_msg}'!=''){
			parent.location.href=window.location.href;
		}
	}
</script>
</head>
<body onload="relocation();">
	<html:form action="login.do">
		<div style="margin:0px auto;width:300px;">
			<h1>RBAC系统</h1>
			<div><span style="width:100px">用户名：</span><html:text property="username" onkeypress="checkKeyCode13(event,0);"/></div>
			<div><span style="width:100px">密&nbsp;&nbsp;码：</span><html:password property="password" onkeypress="checkKeyCode13(event,1);"/></div>
			<center><html:submit property="submit" value="登录"/></center>
			<div><font color="red"><c:out value="${error_msg}"/></font></div>
		</div>
	</html:form>
</body>
</html>



