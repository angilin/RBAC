package com.rbac.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;

import com.rbac.entity.SysMenu;
import com.rbac.entity.SysMenuVo;

public class MenuTree {
	
	/**
	 * 菜单转json字符串
	 * @param menuList
	 * @param check	是否有复选框
	 * @return
	 */
	public static String getMenuJsonString(List<SysMenu> menuList, Boolean check) {
		List<SysMenuVo> levelOneMenuList = new ArrayList<SysMenuVo>();
		List<SysMenuVo> todoMenuList = new ArrayList<SysMenuVo>();
		for (SysMenu menu : menuList) {
			SysMenuVo menuVo = new SysMenuVo();
			menuVo.setId(menu.getId());
			menuVo.setOrderSeq(menu.getOrderSeq());
			menuVo.setText(menu.getName());
			menuVo.setUrl(menu.getUrl());
			menuVo.setParentId(menu.getParentId());
			if(check){
				menuVo.setChecked(menu.getChecked());
			}
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
		// checked:true 可选，是否有复选框
		JSONArray array = new JSONArray();
		for (SysMenuVo menuVo : levelOneMenuList) {
			array.put(objToJson(menuVo, check));
		}
		return array.toString();
	}

	/**
	 * 菜单转json字符串，递归处理子菜单
	 * @param menu
	 * @param todoMenuList
	 */
	private static void setMenuChilren(SysMenuVo menu, List<SysMenuVo> todoMenuList) {
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
	 * @param check	是否有复选框
	 * @return
	 */
	private static JSONObject objToJson(SysMenuVo menuVo, Boolean check) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("id", menuVo.getId());
			obj.put("leaf", menuVo.getLeaf());
			obj.put("text", menuVo.getText());
			obj.put("url", menuVo.getUrl());
			if(check){
				obj.put("checked", menuVo.getChecked());
			}
			if (menuVo.getChildren().size() > 0) {
				JSONArray childArray = new JSONArray();
				for (SysMenuVo child : menuVo.getChildren()) {
					childArray.put(objToJson(child, check));
				}
				obj.put("children", childArray);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
