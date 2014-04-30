package com.rbac.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbac.dao.ActionDao;
import com.rbac.entity.SysAction;

@Service("actionService")
public class ActionService {

	@Autowired
	private ActionDao actionDao;
	
	/**
	 * 根据权限名称和权限路径查找权限列表
	 * @param name
	 * @param url
	 * @return
	 */
	public List<SysAction> getSysActionList(String name, String url){
		return actionDao.getSysActionList(name, url);
	}
	
	/**
	 * 删除权限
	 * @param actionId
	 * @param modifierId
	 */
	public void deleteAction(Long actionId, Long modifierId){
		SysAction action = this.getActionById(actionId);
		action.setIsDeleted(1);
		action.setModifierId(modifierId);
		action.setModifyTime(new Date());
		actionDao.saveOrUpdate(action);
	}
	
	/**
	 * 根据权限id查找权限
	 * @param actionId
	 * @return
	 */
	public SysAction getActionById(Long actionId){
		return actionDao.findById(SysAction.class, actionId);
	}
	
	/**
	 * 保存权限实体
	 * @param action
	 */
	public void saveOrUpdateAction(SysAction action){
		actionDao.saveOrUpdate(action);
	}
}
