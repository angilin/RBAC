package com.rbac.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbac.dao.AccountDao;
import com.rbac.entity.SysAccount;
import com.rbac.entity.SysAccountRole;
import com.rbac.entity.SysRole;
import com.rbac.util.CommonUtils;

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
		String roleIds = account.getRoleIds();
		String[] roleIdArray = roleIds.split(",");
		//删除旧的用户角色管理
		List<SysAccountRole> accountRoleList = accountDao.getSysAccountRoleByAccountId(account.getId());
		for(SysAccountRole accountRole : accountRoleList){
			accountRole.setIsDeleted(1);
			accountRole.setModifierId(account.getModifierId());
			accountRole.setModifyTime(new Date());
			accountDao.saveOrUpdate(accountRole);
		}
		//添加新的用户角色管理
		for(String roleIdStr : roleIdArray){
			Long roleId = CommonUtils.parseLong(roleIdStr);
			SysRole role = accountDao.findById(SysRole.class, roleId);
			SysAccountRole accountRole = new SysAccountRole();
			accountRole.setCreatorId(account.getModifierId());
			accountRole.setCreateTime(new Date());
			accountRole.setSysAccount(account);
			accountRole.setSysRole(role);
			accountDao.saveOrUpdate(accountRole);
		}
		
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
	
	/**
	 * 根据用户id查找用户关联角色列表
	 * @param accountId
	 * @return
	 */
	public List<SysAccountRole> getSysAccountRoleByAccountId(Long accountId){
		return accountDao.getSysAccountRoleByAccountId(accountId);
	}
}
