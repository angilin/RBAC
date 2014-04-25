package com.rbac.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbac.dao.RoleDao;
import com.rbac.entity.SysRole;

@Service("roleService")
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	
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
	}
}
