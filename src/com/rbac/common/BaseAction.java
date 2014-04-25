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
		UserDetail userDetail = this.getCurrentUserDetail(request);
		if(userDetail!=null && userDetail.getAccount()!=null){
			return userDetail.getAccount().getId();
		}
		return null;
	}
	
	/**
	 * 得到当前登录用户信息
	 * @param request
	 * @return
	 */
	protected UserDetail getCurrentUserDetail(HttpServletRequest request){
		UserDetail userDetail = (UserDetail)request.getSession(true).getAttribute(MvcConstant.USER);
		if(userDetail!=null){
			return userDetail;
		}
		return null;
	}

}

