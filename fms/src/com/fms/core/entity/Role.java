package com.fms.core.entity;

import java.util.HashSet;
import java.util.Set;

import com.fms.base.entity.BaseEntity;

/**
 * 角色
 * @author Administrator
 *
 */
public class Role extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name ;
	/**
	 * 用户角色
	 */
	private Set<UserRole> userRoles=new HashSet<UserRole>();
	
	/**
	 * 备注信息
	 */
	private String desc;
	
	/**
	 * 角色权限
	 */
    private Set<RolePrivilege> rolePrivileges=new HashSet<RolePrivilege>();
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	public Set<RolePrivilege> getRolePrivileges() {
		return rolePrivileges;
	}
	public void setRolePrivileges(Set<RolePrivilege> rolePrivileges) {
		this.rolePrivileges = rolePrivileges;
	}
	
	
	
	

}
