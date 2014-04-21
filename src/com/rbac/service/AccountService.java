package com.rbac.service;

import java.util.List;

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
	
	public SysAccount getAccountById(Long accountId){
		return accountDao.findById(SysAccount.class, accountId);
	}
	
	public List<SysAccount> getSysAccountList(String username, String realname){
		return accountDao.getSysAccountList(username, realname);
	}
	
	public void deleteAccount(Long accountId){
		SysAccount account = accountDao.findById(SysAccount.class, accountId);
		account.setIsDeleted(1);
		account.setModifierId(1L);
		accountDao.saveOrUpdate(account);
	}
}
