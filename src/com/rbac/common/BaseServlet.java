package com.rbac.common;

import javax.servlet.http.HttpServlet;

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

}
