<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>权限维护</title>
		<style type="text/css">
		</style>
		<script type="text/javascript" src="extjs/ext-all.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript">
			function checkActionModify(){
				with(document.forms[0]){
					if(isBlank(name.value)){
						alert('权限名称必须填写！');
						return false;
					}
					if(isBlank(url.value)){
						alert('权限路径必须填写！');
						return false;
					}
				}
				document.getElementById('submit').click();
				return true;
			}
			
		</script>
	</head>
	<body>
		<html:form action="actionModify.do">
			<h1>权限维护</h1>
			<input type="button" value="返回" onclick="history.go(-1);" />
			<html:submit property="submit" value="保存" onclick="return checkActionModify();"/>
			<html:hidden property="id"/>
			<ul>
				<li>
					权限名称<span style="color:red" title="必填">(*)</span>
					<html:text property="name"/>
				</li>
				<li>
					权限路径<span style="color:red" title="必填">(*)</span>
					<html:text property="url" />
				</li>
			</ul>
		</html:form>
	</body>
</html>
