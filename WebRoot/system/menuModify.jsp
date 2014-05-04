<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>菜单维护</title>
		<style type="text/css">
		</style>
		<script type="text/javascript" src="extjs/ext-all.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript">
			function checkMenuModify(){
				with(document.forms[0]){
					if(isBlank(name.value)){
						alert('菜单名称必须填写！');
						return false;
					}
					if(isNotBlank(orderSeq.value) && (isNaN(orderSeq.value) || !/^[1-9]\d*$/.test(orderSeq.value))){
						alert('菜单顺序必须是正整数！');
						return false;
					}
				}
				document.getElementById('submit').click();
				return true;
			}
			
		</script>
	</head>
	<body>
		<html:form action="menuModify.do">
			<h1>菜单维护</h1>
			<input type="button" value="返回" onclick="history.go(-1);" />
			<html:submit property="submit" value="保存" onclick="return checkMenuModify();"/>
			<html:hidden property="id"/>
			<ul>
				<li>
					菜单名称<span style="color:red" title="必填">(*)</span>
					<html:text property="name"/>
				</li>
				<li>
					菜单路径
					<html:text property="url" />
				</li>
				<li>
					菜单顺序
					<html:text property="orderSeq" />
				</li>
				<li>
					上级菜单
					<html:select property="parentId">
						<html:option value=""></html:option>
						<html:options collection="menuList" property="id" labelProperty="name"/>
					</html:select>
				</li>
			</ul>
		</html:form>
	</body>
</html>
