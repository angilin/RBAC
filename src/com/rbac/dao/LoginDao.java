package com.rbac.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.rbac.common.BaseDaoSupport;
import com.rbac.entity.SysAccount;

@Component("loginDao")
public class LoginDao extends BaseDaoSupport{

	public SysAccount login(String username, String password){
		List list = super.getSession().createCriteria(SysAccount.class).add(Restrictions.eq("username", username)).add(Restrictions.eq("password", password)).list();
		if(list.size()>0){
			return (SysAccount)list.get(0);
		}
		return null;
	}
}
