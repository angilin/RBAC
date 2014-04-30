package com.rbac.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.rbac.common.BaseDaoSupport;
import com.rbac.entity.SysAction;
import com.rbac.entity.SysRole;
import com.rbac.util.CommonUtils;

@Component("actionDao")
public class ActionDao extends BaseDaoSupport {

	/**
	 * 根据权限名称和权限路径查找权限列表
	 * @param name
	 * @param url
	 * @return
	 */
	public List<SysAction> getSysActionList(String name, String url){
		Criteria crit = super.getSession().createCriteria(SysAction.class);
		crit.add(Restrictions.eq("isDeleted", 0));
		if(CommonUtils.isNotEmpty(name)){
			crit.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));
		}
		if(CommonUtils.isNotEmpty(url)){
			crit.add(Restrictions.ilike("url", url, MatchMode.ANYWHERE));
		}
		return crit.list();
	}
}
