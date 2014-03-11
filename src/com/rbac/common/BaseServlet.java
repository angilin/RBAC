package com.rbac.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@SuppressWarnings("serial")
public abstract class BaseServlet extends HttpServlet {

	protected Object getBean(String beanName) {
		return getApplicationContext().getBean(beanName);
	}

	private ApplicationContext getApplicationContext() {
		return WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
	}

	@Override
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		executeLogic(arg0, arg1);
	}

	@Override
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		executeLogic(arg0, arg1);
	}

	protected abstract void executeLogic(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;
}
