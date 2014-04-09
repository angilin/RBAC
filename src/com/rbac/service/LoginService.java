package com.rbac.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbac.common.UserDetail;
import com.rbac.dao.LoginDao;
import com.rbac.entity.SysAccount;
import com.rbac.entity.SysAction;
import com.rbac.entity.SysMenu;
import com.rbac.entity.SysMenuVo;
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
		if (account != null && StringUtils.isNotBlank(account.getPassword())
				&& StringUtils.isNotBlank(account.getSalt())) {
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
				userDetail.setMenuJsonString(this.getMenuJsonString(loginDao
						.getMenuListByAccountId(account.getId())));
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
	

	/**
	 * 菜单转json字符串
	 * @param menuList
	 * @return
	 */
	private String getMenuJsonString(List<SysMenu> menuList) {
		List<SysMenuVo> levelOneMenuList = new ArrayList<SysMenuVo>();
		List<SysMenuVo> todoMenuList = new ArrayList<SysMenuVo>();
		for (SysMenu menu : menuList) {
			SysMenuVo menuVo = new SysMenuVo();
			menuVo.setId(menu.getId());
			menuVo.setOrderSeq(menu.getOrderSeq());
			menuVo.setText(menu.getName());
			menuVo.setUrl(menu.getUrl());
			menuVo.setParentId(menu.getParentId());
			if (menu.getParentId() == null
					|| menu.getParentId().longValue() == 0L) {
				levelOneMenuList.add(menuVo);
			} else {
				todoMenuList.add(menuVo);
			}
		}
		Collections.sort(levelOneMenuList);
		for (SysMenuVo menuVo : levelOneMenuList) {
			setMenuChilren(menuVo, todoMenuList);
		}

		// 菜单json模板 
		// {id:"0",text:"菜单",expanded: true ,children:[{id:"2",text:"子菜单1",leaf: true}]}
		JSONArray array = new JSONArray();
		for (SysMenuVo menuVo : levelOneMenuList) {
			array.put(objToJson(menuVo));
		}
		return array.toString();
	}

	/**
	 * 菜单转json字符串，递归处理子菜单
	 * @param menu
	 * @param todoMenuList
	 */
	private void setMenuChilren(SysMenuVo menu, List<SysMenuVo> todoMenuList) {
		ListIterator<SysMenuVo> it = todoMenuList.listIterator();
		while (it.hasNext()) {
			SysMenuVo todoMenu = it.next();
			if (todoMenu.getParentId() != null
					&& todoMenu.getParentId().equals(menu.getId())) {
				menu.getChildren().add(todoMenu);
				it.remove();
				setMenuChilren(todoMenu, todoMenuList);
			}
		}
		Collections.sort(menu.getChildren());
	}

	/**
	 * 菜单实体转json对象
	 * @param menuVo
	 * @return
	 */
	private JSONObject objToJson(SysMenuVo menuVo) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("id", menuVo.getId());
			obj.put("leaf", menuVo.getLeaf());
			obj.put("text", menuVo.getText());
			obj.put("url", menuVo.getUrl());
			if (menuVo.getChildren().size() > 0) {
				JSONArray childArray = new JSONArray();
				for (SysMenuVo child : menuVo.getChildren()) {
					childArray.put(objToJson(child));
				}
				obj.put("children", childArray);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
