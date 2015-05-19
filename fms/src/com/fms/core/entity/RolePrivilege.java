package com.fms.core.entity;

import com.fms.base.entity.BaseEntity;

/**
 * 角色权限
 * @author Administrator
 *
 */
public class RolePrivilege extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Role role;
	private Privilege privilege;
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Privilege getPrivilege() {
		return privilege;
	}
	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}
	
	

}
