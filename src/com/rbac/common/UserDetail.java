package com.rbac.common;

import java.util.Set;

import com.rbac.entity.SysAccount;

public class UserDetail {

	//用户
	private SysAccount account;
	
	//菜单
	private String menuJsonString;
	
	//用户的所有权限
	private Set<String> permitActionSet;

	public SysAccount getAccount() {
		return account;
	}

	public void setAccount(SysAccount account) {
		this.account = account;
	}

	public String getMenuJsonString() {
		return menuJsonString;
	}

	public void setMenuJsonString(String menuJsonString) {
		this.menuJsonString = menuJsonString;
	}

	public Set<String> getPermitActionSet() {
		return permitActionSet;
	}

	public void setPermitActionSet(Set<String> permitActionSet) {
		this.permitActionSet = permitActionSet;
	}
}
