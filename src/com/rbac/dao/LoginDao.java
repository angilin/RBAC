package com.rbac.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.rbac.common.BaseDaoSupport;
import com.rbac.entity.SysAccount;
import com.rbac.entity.SysAction;
import com.rbac.entity.SysMenu;

@Component("loginDao")
public class LoginDao extends BaseDaoSupport {

	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	public SysAccount getSysAccountByUsername(String username) {
		List list = super.getSession().createCriteria(SysAccount.class).add(
				Restrictions.eq("isDeleted", 0)).add(
				Restrictions.eq("username", username)).list();
		if (list.size() > 0) {
			return (SysAccount) list.get(0);
		}
		return null;
	}
	
	/**
	 * 根据用户id查找菜单列表
	 * @param accountId
	 * @return
	 */
	public List getMenuListByAccountId(Long accountId){
		String hql = "from SysMenu m where m.isDeleted=0 and m.id in (select rm.sysMenu.id from SysRoleMenu rm where rm.isDeleted=0 and rm.sysRole.id in (select ar.sysRole.id from SysAccountRole ar where ar.isDeleted=0 and ar.sysAccount.id=:accountId))";
		return super.getSession().createQuery(hql).setLong("accountId", accountId).list();
		//使用sqlQuery会报空值错，是hibernate3.6.5之前版本的问题，见https://hibernate.atlassian.net/i#browse/HHH-2225
		//String sql = "select m.* from sys_menu m where m.is_deleted=0 and m.ID in (select rm.menu_id from sys_role_menu rm where rm.is_deleted=0 and rm.ROLE_ID in (select ar.role_id from sys_account_role ar where ar.is_deleted=0 and ar.account_id=:accountId))";
		//return super.getSession().createSQLQuery(sql).addEntity(SysMenu.class).setLong("accountId", accountId).list();
	}
	
	/**
	 * 根据用户id查找权限列表
	 * @param accountId
	 * @return
	 */
	public List getActionListByAccountId(Long accountId){
		String sql = "select a.* from sys_action a where a.is_deleted=0 and a.id in (select ma.action_id from sys_menu_action ma where ma.is_deleted=0 and ma.menu_id in (select rm.menu_id from sys_role_menu rm where rm.is_deleted=0 and rm.ROLE_ID in (select ar.role_id from sys_account_role ar where ar.is_deleted=0 and ar.account_id=:accountId)))";
		return super.getSession().createSQLQuery(sql).addEntity(SysAction.class).setLong("accountId", accountId).list();
	}
}
