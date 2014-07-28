package com.fms.core.entity;

import com.fms.base.entity.BaseEntity;

/**
 * 部门类
 * @author Administrator
 *
 */
public class Department extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *  部门名称
	 */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
