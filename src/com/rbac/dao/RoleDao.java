package com.rbac.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.rbac.common.BaseDaoSupport;
import com.rbac.entity.SysRole;
import com.rbac.util.CommonUtils;

@Component("roleDao")
public class RoleDao extends BaseDaoSupport {

	/**
	 * 根据角色名称和角色描述查找角色列表
	 * @param roleName
	 * @param roleDesc
	 * @return
	 */
	public List<SysRole> getSysRoleList(String roleName, String roleDesc){
		Criteria crit = super.getSession().createCriteria(SysRole.class);
		crit.add(Restrictions.eq("isDeleted", 0));
		if(CommonUtils.isNotEmpty(roleName)){
			crit.add(Restrictions.ilike("roleName", roleName, MatchMode.ANYWHERE));
		}
		if(CommonUtils.isNotEmpty(roleDesc)){
			crit.add(Restrictions.ilike("roleDesc", roleDesc, MatchMode.ANYWHERE));
		}
		return crit.list();
	}
}
