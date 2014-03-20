package com.rbac.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.rbac.common.BaseDaoSupport;
import com.rbac.common.SscConstant;
import com.rbac.entity.SysAccount;

@Component("loginDao")
public class LoginDao extends BaseDaoSupport {

	public SysAccount getSysAccountByUsername(String username) {
		List list = super.getSession().createCriteria(SysAccount.class).add(
				Restrictions.eq("isDeleted", SscConstant.isNotDeleted)).add(
				Restrictions.eq("username", username)).list();
		if (list.size() > 0) {
			return (SysAccount) list.get(0);
		}
		return null;
	}
}
