<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>密码修改</title>
		<style type="text/css">
		</style>
		<script type="text/javascript" src="extjs/ext-all.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript">
			function checkPwdModify(){
				with(document.forms[0]){
					if(isBlank(id.value)){
						if(isBlank(password.value)){
							alert('密码必须填写！');
							return false;
						}
					}
					if((isNotBlank(password.value) ||　isNotBlank(passwordRepeat.value)) && password.value.trim()!=passwordRepeat.value.trim()){
						alert('密码和重复密码必须一致！');
						return false;
					}
				}
				document.getElementById('submit').click();
				return true;
			}
						
		</script>
	</head>
	<body onload="if('${msg }'!=''){alert('${msg}');}">
		<html:form action="pwdModify.do">
			<h1>密码修改</h1>
			<html:submit property="submit" value="保存" onclick="return checkPwdModify();"/>
			<html:hidden property="id"/>
			<ul>
				<li>
					用户登录名：<c:out value="${account.username}"/>
				</li>
				<li>
					用户显示名：<c:out value="${account.realname}"/>
				</li>
				<li>
					密码
					<span style="color:red" title="必填">(*)</span>
					<html:password property="password" />
				</li>
				<li>
					请在输入一次密码
					<span style="color:red" title="必填">(*)</span>
					<html:password property="passwordRepeat" />
				</li>
			</ul>
		</html:form>
	</body>
</html>
