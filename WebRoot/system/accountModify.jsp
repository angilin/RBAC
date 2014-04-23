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
		<script type="text/javascript" src="extjs/ext-all.js"></script>
		<script type="text/javascript">
			function checkAccountModify(){
				with(document.forms[0]){
					if(isBlank(username.value)){
						alert('用户登录名必须填写！');
						return false;
					}
					if(isBlank(realname.value)){
						alert('用户显示名必须填写！');
						return false;
					}
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
					//检查用户登录名和用户显示名是否重复
					if(isNotBlank(document.getElementById('usernameError').innerText)){
						alert(document.getElementById('usernameError').innerText);
						return false;
					}
				}
				document.getElementById('submit').click();
				return true;
			}
			
			String.prototype.trim = function(){  
			    // 用正则表达式将前后空格  
			    // 用空字符串替代
			    return this.replace(/(^\s*)|(\s*$)/g, "");  
			}
			
			function isBlank(str){
				return !isNotBlank(str);
			}
						
			function isNotBlank(str){
				str = str.trim();
				if(str==undefined || str==''){
					return false;
				}
				return true;
			}
			
			function checkAccountName(){
				var username = document.forms[0].username.value.trim();
				if(isNotBlank(username)){
					eval("var params = {username:'"+encodeURIComponent(username)+"',ignoreId:'"+document.forms[0].id.value+"'}");
					Ext.Ajax.request({
							method : 'POST',
							url: 'servlet/CheckAccountNameServlet',
							success:checkAccountNameAndBack,
							failure:function(){},
			      			params:params
			      	});
			    }
			}
		 
			function checkAccountNameAndBack(request){
				if(request.responseText){
					var isExist = request.responseText;
					if(isExist=='1'){
						document.getElementById('usernameError').innerText='该用户名已被占用！';
					}
					else if(isExist=='0'){
						document.getElementById('usernameError').innerText='';
					}
				}
			}
		</script>
	</head>
	<body onload="checkAccountName();">
		<html:form action="accountModify.do">
			<h1>用户维护</h1>
			<input type="button" value="返回" onclick="history.go(-1);" />
			<html:submit property="submit" value="保存" onclick="return checkAccountModify();"/>
			<html:hidden property="id"/>
			<ul>
				<li>
					用户登录名<span style="color:red" title="必填">(*)</span>
					<html:text property="username" onblur="checkAccountName();"/>
					<span id="usernameError" style="color:red"></span>
				</li>
				<li>
					用户显示名<span style="color:red" title="必填">(*)</span>
					<html:text property="realname" />
				</li>
				<li>
					密码
					<c:if test="${empty requestScope['accountModifyForm'].id}">
						<span style="color:red" title="必填">(*)</span>
					</c:if>
					<html:password property="password" />
				</li>
				<li>
					请在输入一次密码
					<c:if test="${empty requestScope['accountModifyForm'].id}">
						<span style="color:red" title="必填">(*)</span>
					</c:if>
					<html:password property="passwordRepeat" />
				</li>
			</ul>
		</html:form>
	</body>
</html>
