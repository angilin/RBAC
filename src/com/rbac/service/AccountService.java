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
		accountDao.saveOrUpdate(account);
	}
	
	
}
