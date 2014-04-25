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
	
	public List<SysRole> getSysRoleList(String roleName, String roleDesc){
		return roleDao.getSysRoleList(roleName, roleDesc);
	}
	
	public void deleteRole(Long roleId){
		SysRole role = this.getRoleById(roleId);
		role.setIsDeleted(1);
		role.setModifierId(1L);
		role.setModifyTime(new Date());
		roleDao.saveOrUpdate(role);
	}
	
	public SysRole getRoleById(Long roleId){
		return roleDao.findById(SysRole.class, roleId);
	}
	
	public void saveOrUpdateRole(SysRole role){
		roleDao.saveOrUpdate(role);
	}
}
