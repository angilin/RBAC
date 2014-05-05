<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>角色维护</title>
		<!-- 复选树功能参考extjs4.1.1中examples/tree/check-tree.html -->
		<link rel="stylesheet" type="text/css" href="extjs/resources/css/ext-all.css" />
		<style type="text/css">
		</style>
		<script type="text/javascript" src="extjs/ext-all.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript">
			function checkRoleModify(){
				with(document.forms[0]){
					if(isBlank(roleName.value)){
						alert('角色名称必须填写！');
						return false;
					}
					var records = treePanel.getView().getChecked();
                    menuIdArray = [];
                    Ext.Array.each(records, function(rec){
                        menuIdArray.push(rec.get('id'));
                    });
                    
					menuIds.value = menuIdArray.join(',');
				}
				document.getElementById('submit').click();
				return true;
			}
			
			var treePanel;
			
	Ext.require([
	    'Ext.tree.*',
	    'Ext.data.*'
	]);

	Ext.onReady(function() {
		var store = Ext.create("Ext.data.TreeStore", {				
			model : "ctreemodel",
			root: {id:"0",text:"菜单",expanded: true ,children:${menuJson}} 
		});
	
		treePanel = Ext.create('Ext.tree.Panel', {
		        id: 'tree',
		        width: 300,
		        autoScroll: true,
		        renderTo: 'menuTree',
		        store:  store
		    });
		  
		treePanel.expandAll();
	});
			
		</script>
	</head>
	<body>
		<html:form action="roleModify.do">
			<h1>角色维护</h1>
			<input type="button" value="返回" onclick="history.go(-1);" />
			<html:submit property="submit" value="保存" onclick="return checkRoleModify();"/>
			<html:hidden property="id"/>
			<ul>
				<li>
					角色名称<span style="color:red" title="必填">(*)</span>
					<html:text property="roleName"/>
				</li>
				<li>
					角色描述
					<html:text property="roleDesc" />
				</li>
				<li>
					<div id="menuTree"></div>
					<html:hidden property="menuIds"/>
				</li>
			</ul>
		</html:form>
	</body>
</html>
