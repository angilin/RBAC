package com.rbac.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbac.common.MenuTree;
import com.rbac.dao.MenuDao;
import com.rbac.dao.RoleDao;
import com.rbac.entity.SysMenu;
import com.rbac.entity.SysRole;
import com.rbac.entity.SysRoleMenu;
import com.rbac.util.CommonUtils;

@Service("roleService")
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private MenuDao menuDao;
	
	/**
	 * 根据角色名称和角色描述查找角色列表
	 * @param roleName
	 * @param roleDesc
	 * @return
	 */
	public List<SysRole> getSysRoleList(String roleName, String roleDesc){
		return roleDao.getSysRoleList(roleName, roleDesc);
	}
	
	/**
	 * 删除角色
	 * @param roleId
	 * @param modifierId
	 */
	public void deleteRole(Long roleId, Long modifierId){
		SysRole role = this.getRoleById(roleId);
		role.setIsDeleted(1);
		role.setModifierId(modifierId);
		role.setModifyTime(new Date());
		roleDao.saveOrUpdate(role);
	}
	
	/**
	 * 根据角色id查找角色
	 * @param roleId
	 * @return
	 */
	public SysRole getRoleById(Long roleId){
		return roleDao.findById(SysRole.class, roleId);
	}
	
	/**
	 * 保存角色实体
	 * @param role
	 */
	public void saveOrUpdateRole(SysRole role){
		roleDao.saveOrUpdate(role);
		String menuIds = role.getMenuIds();
		String[] menuIdArray = menuIds.split(",");
		List<SysRoleMenu> roleMenuList = roleDao.getSysRoleMenuByRoleId(role.getId());
		for(SysRoleMenu roleMenu : roleMenuList){
			roleMenu.setIsDeleted(1);
			roleMenu.setModifierId(role.getModifierId());
			roleMenu.setModifyTime(new Date());
			roleDao.saveOrUpdate(roleMenu);
		}
		for(String menuId : menuIdArray){
			if(CommonUtils.isBlank(menuId)){
				continue;
			}
			SysRoleMenu roleMenu = new SysRoleMenu();
			roleMenu.setIsDeleted(0);
			roleMenu.setCreatorId(role.getModifierId());
			roleMenu.setCreateTime(new Date());
			roleMenu.setSysRole(role);
			SysMenu menu = roleDao.findById(SysMenu.class, CommonUtils.parseLong(menuId));
			roleMenu.setSysMenu(menu);
			roleDao.saveOrUpdate(roleMenu);
		}
	}
	
	/**
	 * 根据角色id查找角色关联菜单列表
	 * @param roleId
	 * @return
	 */
	public List<SysRoleMenu> getSysRoleMenuByRoleId(Long roleId){
		return roleDao.getSysRoleMenuByRoleId(roleId);
	}
	
	/**
	 * 根据角色得到菜单复选树，不需要反绑数据时，role传null即可
	 * @param roleId
	 * @return
	 */
	public String getCheckedMenuTree(Long roleId){
		List<SysMenu> menuList = menuDao.getSysMenuList(null, null);
		if(roleId!=null){
			List<SysRoleMenu> roleMenuList = roleDao.getSysRoleMenuByRoleId(roleId);
			for(SysRoleMenu roleMenu : roleMenuList){
				for(SysMenu menu : menuList){
					if(roleMenu.getSysMenu().getId().equals(menu.getId())){
						menu.setChecked(true);
					}
				}
			}
		}
		return MenuTree.getMenuJsonString(menuList, true);
	}
}
