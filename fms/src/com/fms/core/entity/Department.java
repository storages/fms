package com.fms.core.entity;

import com.fms.base.entity.BaseEntity;

/**
 * ������
 * @author Administrator
 *
 */
public class Department extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *  ��������
	 */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
