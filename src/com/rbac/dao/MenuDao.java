package com.rbac.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.rbac.common.BaseDaoSupport;
import com.rbac.entity.SysMenu;
import com.rbac.entity.SysMenuAction;
import com.rbac.util.CommonUtils;

@Component("menuDao")
public class MenuDao extends BaseDaoSupport {

	/**
	 * 根据菜单名称和菜单路径查找菜单列表
	 * @param name
	 * @param url
	 * @return
	 */
	public List<SysMenu> getSysMenuList(String name, String url){
		Criteria crit = super.getSession().createCriteria(SysMenu.class);
		crit.add(Restrictions.eq("isDeleted", 0));
		if(CommonUtils.isNotEmpty(name)){
			crit.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));
		}
		if(CommonUtils.isNotEmpty(url)){
			crit.add(Restrictions.ilike("url", url, MatchMode.ANYWHERE));
		}
		crit.addOrder(Order.asc("parentId")).addOrder(Order.asc("orderSeq"));
		return crit.list();
	}
	
	/**
	 * 根据菜单id查找菜单关联权限列表
	 * @param menuId
	 * @return
	 */
	public List<SysMenuAction> getSysMenuActionByMenuId(Long menuId){
		Criteria crit = super.getSession().createCriteria(SysMenuAction.class);
		crit.add(Restrictions.eq("isDeleted", 0));
		if(menuId!=null){
			crit.add(Restrictions.eq("sysMenu", super.findById(SysMenu.class, menuId)));
		}
		else{
			return new ArrayList<SysMenuAction>();
		}
		return crit.list();
	}
}
