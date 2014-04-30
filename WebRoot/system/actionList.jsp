<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>权限管理</title>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="actionList.do">
			<html:hidden property="deleteIds" />
			<h1>
				权限列表
			</h1>
			<ul>
				<li>
					权限名称
					<html:text property="nameQry" />
				</li>
				<li>
					权限路径
					<html:text property="urlQry" />
				</li>
			</ul>
			<html:submit property="query" value="查询" />
			<html:submit property="del" value="批量删除" style="display:none" />
			<input type="button" value="新增"
				onclick="window.location.href='actionModify.do'" />
			<table border="1" width="80%">
				<thead>
					<tr>
						<th>
							权限名称
						</th>
						<th>
							权限路径
						</th>
						<th width="8%">
							编辑
						</th>
						<th width="8%">
							删除
						</th>
					</tr>
				</thead>
				<c:if test="${not empty actionList}">
					<c:forEach items="${actionList}" var="action">
						<tr>
							<td>
								<c:out value="${action.name }" />
							</td>
							<td>
								<c:out value="${action.url }" />
							</td>
							<td align="center">
								<a href="actionModify.do?id=${action.id }">编辑</a>
							</td>
							<td align="center">
									<a
										href="javascript:if(confirm('是否确定删除这条记录？')){document.forms[0].deleteIds.value='${action.id}';document.forms[0].del.click();}">删除</a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</html:form>
	</body>
</html>
