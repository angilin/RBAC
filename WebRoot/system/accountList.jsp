<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>用户管理</title>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="accountList.do">
			<html:hidden property="deleteIds" />
			<h1>
				用户列表
			</h1>
			<ul>
				<li>
					用户登录名
					<html:text property="usernameQry" />
				</li>
				<li>
					用户显示名
					<html:text property="realnameQry" />
				</li>
			</ul>
			<html:submit property="query" value="查询" />
			<html:submit property="del" value="批量删除" style="display:none" />
			<input type="button" value="新增"
				onclick="window.location.href='accountModify.do'" />
			<table border="1" width="80%">
				<thead>
					<tr>
						<th>
							用户登录名
						</th>
						<th>
							用户显示名
						</th>
						<th width="8%">
							编辑
						</th>
						<th width="8%">
							删除
						</th>
					</tr>
				</thead>
				<c:if test="${not empty accountList}">
					<c:forEach items="${accountList}" var="account">
						<tr>
							<td>
								<c:out value="${account.username }" />
							</td>
							<td>
								<c:out value="${account.realname }" />
							</td>
							<td align="center">
								<a href="accountModify.do?id=${account.id }" align="">编辑</a>
							</td>
							<td align="center">
								<c:if test="${account.username ne 'admin'}">
									<a
										href="javascript:if(confirm('是否确定删除这条记录？')){document.forms[0].deleteIds.value='${account.id}';document.forms[0].del.click();}">删除</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</html:form>
	</body>
</html>
