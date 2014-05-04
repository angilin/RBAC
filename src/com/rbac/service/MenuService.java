package com.rbac.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbac.dao.MenuDao;
import com.rbac.entity.SysMenu;

@Service("menuService")
public class MenuService {

	@Autowired
	private MenuDao menuDao;
	
	/**
	 * 根据菜单名称和菜单路径查找菜单列表
	 * @param name
	 * @param url
	 * @return
	 */
	public List<SysMenu> getSysMenuList(String name, String url){
		return menuDao.getSysMenuList(name, url);
	}
		
	/**
	 * 删除菜单
	 * @param menuId
	 * @param modifierId
	 */
	public void deleteMenu(Long menuId, Long modifierId){
		SysMenu menu = this.getMenuById(menuId);
		menu.setIsDeleted(1);
		menu.setModifierId(modifierId);
		menu.setModifyTime(new Date());
		menuDao.saveOrUpdate(menu);
	}
	
	/**
	 * 根据菜单id查找菜单
	 * @param menuId
	 * @return
	 */
	public SysMenu getMenuById(Long menuId){
		return menuDao.findById(SysMenu.class, menuId);
	}
	
	/**
	 * 保存菜单实体
	 * @param menu
	 */
	public void saveOrUpdateMenu(SysMenu menu){
		menuDao.saveOrUpdate(menu);
	}
}
