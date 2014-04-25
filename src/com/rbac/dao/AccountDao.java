package com.rbac.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.rbac.common.BaseDaoSupport;
import com.rbac.entity.SysAccount;
import com.rbac.entity.SysAccountRole;
import com.rbac.util.CommonUtils;

@Component("accountDao")
public class AccountDao extends BaseDaoSupport {

	/**
	 * 根据用户登录名和用户显示名查找用户列表
	 * @param username
	 * @param realname
	 * @return
	 */
	public List<SysAccount> getSysAccountList(String username, String realname){
		Criteria crit = super.getSession().createCriteria(SysAccount.class);
		crit.add(Restrictions.eq("isDeleted", 0));
		if(CommonUtils.isNotEmpty(username)){
			crit.add(Restrictions.ilike("username", username, MatchMode.ANYWHERE));
		}
		if(CommonUtils.isNotEmpty(realname)){
			crit.add(Restrictions.ilike("realname", realname, MatchMode.ANYWHERE));
		}
		return crit.list();
	}
	
	/**
	 * 根据用户名查找用户，精确匹配，忽略传入的用户id对应的用户
	 * @param username
	 * @param ignoreAccountId
	 * @return
	 */
	public List<SysAccount> getSysAccountListByExactUsername(String username, Long ignoreAccountId){
		Criteria crit = super.getSession().createCriteria(SysAccount.class);
		crit.add(Restrictions.eq("isDeleted", 0));
		if(CommonUtils.isNotEmpty(username)){
			crit.add(Restrictions.eq("username", username));
		}
		if(ignoreAccountId!=null){
			crit.add(Restrictions.ne("id", ignoreAccountId));
		}
		return crit.list();
	}
	
	/**
	 * 根据用户id查找用户关联角色列表
	 * @param accountId
	 * @return
	 */
	public List<SysAccountRole> getSysAccountRoleByAccountId(Long accountId){
		Criteria crit = super.getSession().createCriteria(SysAccountRole.class);
		crit.add(Restrictions.eq("isDeleted", 0));
		if(accountId!=null){
			crit.add(Restrictions.eq("sysAccount", super.findById(SysAccount.class, accountId)));
		}
		else{
			return new ArrayList<SysAccountRole>();
		}
		return crit.list();
	}
}