/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.rbac.action.system;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.rbac.common.BaseAction;
import com.rbac.entity.SysAction;
import com.rbac.form.system.ActionModifyForm;
import com.rbac.service.ActionService;
import com.rbac.util.CommonUtils;

/** 
 * MyEclipse Struts
 * Creation date: 04-30-2014
 * 
 * XDoclet definition:
 * @struts.action path="/actionModify" name="actionModifyForm" input="/system/actionModify.jsp" scope="request" validate="true"
 * @struts.action-forward name="success" path="/system/actionList.jsp"
 */
public class ActionModifyAction extends BaseAction {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionModifyForm actionModifyForm = (ActionModifyForm) form;
		ActionService actionService = (ActionService) super.getBean("actionService");
		if(CommonUtils.isNotBlank(actionModifyForm.getSubmit())){
			SysAction action = new SysAction();
			if(CommonUtils.isNotBlank(actionModifyForm.getId())){
				Long id = CommonUtils.parseLong(actionModifyForm.getId());
				action = actionService.getActionById(id);
				action.setModifierId(super.getCurrentAccountId(request));
				action.setModifyTime(new Date());
			}
			else{
				action.setCreatorId(super.getCurrentAccountId(request));
				action.setCreateTime(new Date());
			}
			action.setName(actionModifyForm.getName());
			action.setUrl(actionModifyForm.getUrl());
			actionService.saveOrUpdateAction(action);
			return mapping.findForward("success");
		}
		return mapping.getInputForward();
	}
}