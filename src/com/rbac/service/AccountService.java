package com.rbac.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbac.dao.AccountDao;
import com.rbac.entity.SysAccount;

@Service("accountService")
public class AccountService {
	
	@Autowired
	private AccountDao accountDao;

	/**
	 * 保存用户
	 * @param account
	 */
	public void saveOrUpdateAccount(SysAccount account){
		accountDao.saveOrUpdate(account);
	}
	
	/**
	 * 根据id查找用户实体
	 * @param accountId
	 * @return
	 */
	public SysAccount getAccountById(Long accountId){
		return accountDao.findById(SysAccount.class, accountId);
	}
	
	/**
	 * 根据用户登录名和用户显示名查找用户列表
	 * @param username
	 * @param realname
	 * @return
	 */
	public List<SysAccount> getSysAccountList(String username, String realname){
		return accountDao.getSysAccountList(username, realname);
	}
	
	/**
	 * 删除用户
	 * @param accountId
	 */
	public void deleteAccount(Long accountId, Long modifierId){
		SysAccount account = this.getAccountById(accountId);
		account.setIsDeleted(1);
		account.setModifierId(modifierId);
		account.setModifyTime(new Date());
		accountDao.saveOrUpdate(account);
	}
	
	/**
	 * 检查用户登录名是否已经存在
	 * @param username
	 * @param ignoreId
	 * @return
	 */
	public boolean checkExistAccountName(String username, Long ignoreAccountId){
		List list = accountDao.getSysAccountListByExactUsername(username, ignoreAccountId);
		if(list.size()>0){
			return true;
		}
		return false;
	}
}
