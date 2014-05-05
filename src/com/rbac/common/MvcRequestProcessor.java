package com.rbac.common;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.RequestProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.rbac.util.CommonUtils;


public class MvcRequestProcessor extends RequestProcessor{

	private ApplicationContext getCtx(HttpServletRequest request) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getSession()
						.getServletContext());
		return ctx;
	}
	
	/**
	 * struts控制器，用于权限验证
	 */
	public boolean processRoles(HttpServletRequest request,
			HttpServletResponse response, ActionMapping mapping)
			throws IOException, ServletException {
		HttpSession session = request.getSession();

		// 如果访问的路径是登陆、登出则不需要权限验证
		if (mapping.getPath().equals("/login")
				|| mapping.getPath().equals("/logout")) {
			return true;
		}

		// 如果没有登录，进行提示并返回到错误页面
		if (session.getAttribute(MvcConstant.USER) == null) {
			request.setAttribute(MvcConstant.ERROR_MSG, "请先登录系统");
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
			return false;
		}
		
		//检查登录用户是否有权限访问该路径
		UserDetail user = (UserDetail)session.getAttribute(MvcConstant.USER);
		Set<String> permitActionSet = user.getPermitActionSet();
		if(CommonUtils.isBlank(mapping.getPath()) || !permitActionSet.contains(mapping.getPath().substring(1)+".do")){
			request.setAttribute(MvcConstant.ERROR_MSG, "你没有权限访问该功能");
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
			return false;
		}

		return true;
	}
}
