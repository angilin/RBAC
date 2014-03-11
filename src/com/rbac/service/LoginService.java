package com.rbac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbac.dao.LoginDao;
import com.rbac.entity.SysAccount;

@Service("loginService")
public class LoginService {

	@Autowired
	private LoginDao loginDao;
		
	public SysAccount login(String username, String password){
		return loginDao.login(username, password);
	}
}
