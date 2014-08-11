package com.fms.core.entity;

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
	 * 权限URL 
	 */
	Set<PrivilegeLocation> privilegeLocations;
	

}
