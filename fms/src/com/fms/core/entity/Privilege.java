package com.fms.core.entity;

import java.util.HashSet;
import java.util.Set;

import com.fms.base.entity.BaseEntity;
/**
 * 权限
 * @author Administrator
 *
 */
public class Privilege extends BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 编码
	 */
	private String code;
	
	/**
	 * 名称
	 */
	private String  name;
	/**
	 * url 地址
	 */
	private String url;
	/**
	 * URL说明
	 */
	private String remakes;
	
	private Set<RolePrivilege> rolePrivileges=new HashSet<RolePrivilege>();
	
	
	

	public Set<RolePrivilege> getRolePrivileges() {
		return rolePrivileges;
	}
	public void setRolePrivileges(Set<RolePrivilege> rolePrivileges) {
		this.rolePrivileges = rolePrivileges;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRemakes() {
		return remakes;
	}
	public void setRemakes(String remakes) {
		this.remakes = remakes;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
