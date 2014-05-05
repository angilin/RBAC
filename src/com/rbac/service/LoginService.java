package com.rbac.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbac.common.MenuTree;
import com.rbac.common.UserDetail;
import com.rbac.dao.LoginDao;
import com.rbac.entity.SysAccount;
import com.rbac.entity.SysAction;
import com.rbac.entity.SysMenu;
import com.rbac.entity.SysMenuVo;
import com.rbac.util.CommonUtils;
import com.rbac.util.PasswordHash;

@Service("loginService")
public class LoginService {

	@Autowired
	private LoginDao loginDao;

	/**
	 * 登录功能
	 * @param username
	 * @param password
	 * @return UserDetail
	 */
	public UserDetail login(String username, String password) {
		// 就算根据用户名没找到用户，也要去调用验证密码方法，防止别人根据返回时间猜测用户名
		SysAccount account = loginDao.getSysAccountByUsername(username);
		String userhash = "11";
		String usersalt = "11";
		if (account != null && CommonUtils.isNotBlank(account.getPassword())
				&& CommonUtils.isNotBlank(account.getSalt())) {
			userhash = account.getPassword();
			usersalt = account.getSalt();
		}
		StringBuilder s = new StringBuilder();
		s.append(PasswordHash.PBKDF2_ITERATIONS).append(":").append(usersalt)
				.append(":").append(userhash);
		try {
			if (PasswordHash.validatePassword(password, s.toString())) {
				UserDetail userDetail = new UserDetail();
				userDetail.setAccount(account);
				userDetail.setMenuJsonString(MenuTree.getMenuJsonString(loginDao
						.getMenuListByAccountId(account.getId()),false));
				userDetail.setPermitActionSet(this.getPermitActionSet(account.getId()));
				return userDetail;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 获取用户权限，包括菜单自身权限和菜单相关权限
	 * @param accountId
	 * @return
	 */
	private Set<String> getPermitActionSet(Long accountId){
		Set<String> permitActionSet = new HashSet<String>();
		List<SysMenu> menuList = loginDao.getMenuListByAccountId(accountId);
		List<SysAction> actionList = loginDao.getActionListByAccountId(accountId);
		for(SysMenu menu : menuList){
			permitActionSet.add(menu.getUrl());
		}
		for(SysAction action : actionList){
			permitActionSet.add(action.getUrl());
		}
		return permitActionSet;
	}
}
