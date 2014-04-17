package com.rbac.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.collection.PersistentBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbac.dao.AccountDao;
import com.rbac.entity.SysAccount;

@Service("accountService")
public class AccountService {
	
	@Autowired
	private AccountDao accountDao;

	public void saveOrUpdateAccount(SysAccount account){
		if(account.getRealname().equals("系统管理员")){
			account.setRealname("bbbb");
		}
		else{
			account.setRealname("admin");
		}
		accountDao.getHibernateTemplate().update(account);
		
		if(account.getRealname().equals("bbbb")){
			account.setRealname("系统管理员");
		}
		else{
			account.setRealname("bbbb");
		}
		accountDao.saveOrUpdate(account);
	}
	
	public SysAccount getAccountById(Long accountId){
		return accountDao.findById(SysAccount.class, accountId);
	}
}
