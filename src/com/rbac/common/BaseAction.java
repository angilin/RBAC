package com.rbac.common;

import org.apache.struts.action.Action;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class BaseAction extends Action {
	

	protected Object getBean(String beanName) {
		return getApplicationContext().getBean(beanName);
	}

	private ApplicationContext getApplicationContext() {
		return WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServlet()
						.getServletContext());
	}

} // end BaseAction

