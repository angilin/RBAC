package com.rbac.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.rbac.common.BaseDaoSupport;
import com.rbac.entity.SysAccount;

@Component("accountDao")
public class AccountDao extends BaseDaoSupport {

	
	public List<SysAccount> getSysAccountList(String username, String realname){
		Criteria crit = super.getSession().createCriteria(SysAccount.class);
		crit.add(Restrictions.eq("isDeleted", 0));
		if(StringUtils.isNotEmpty(username)){
			crit.add(Restrictions.ilike("username", username, MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotEmpty(realname)){
			crit.add(Restrictions.ilike("realname", realname, MatchMode.ANYWHERE));
		}
		return crit.list();
	}
	
}