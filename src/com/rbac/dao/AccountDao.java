package com.rbac.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.rbac.common.BaseDaoSupport;
import com.rbac.entity.SysAccount;
import com.rbac.util.CommonUtils;

@Component("accountDao")
public class AccountDao extends BaseDaoSupport {

	
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
}