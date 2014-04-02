package com.rbac.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.rbac.common.BaseDaoSupport;
import com.rbac.entity.SysAccount;
import com.rbac.entity.SysMenu;

@Component("loginDao")
public class LoginDao extends BaseDaoSupport {

	public SysAccount getSysAccountByUsername(String username) {
		List list = super.getSession().createCriteria(SysAccount.class).add(
				Restrictions.eq("isDeleted", 0)).add(
				Restrictions.eq("username", username)).list();
		if (list.size() > 0) {
			return (SysAccount) list.get(0);
		}
		return null;
	}
	
	public List getMenuListByAccountId(Long accountId){
		String sql = "select m.* from sys_menu m where m.ID in (select rm.menu_id from sys_role_menu rm where rm.ROLE_ID in (select ar.role_id from sys_account_role ar where ar.account_id=:accountId))";
		return super.getSession().createSQLQuery(sql).addEntity(SysMenu.class).setLong("accountId", accountId).list();
	}
}
