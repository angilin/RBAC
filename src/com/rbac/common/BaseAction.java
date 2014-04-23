package com.rbac.common;

import javax.servlet.http.HttpServletRequest;

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
	
	/**
	 * 得到当前登录用户id
	 * @param request
	 * @return
	 */
	protected Long getCurrentAccountId(HttpServletRequest request){
		UserDetail user = (UserDetail)request.getSession(true).getAttribute(MvcConstant.USER);
		if(user!=null && user.getAccount()!=null){
			return user.getAccount().getId();
		}
		return null;
	}

}

