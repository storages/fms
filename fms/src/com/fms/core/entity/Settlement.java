package com.fms.core.entity;

import com.fms.base.entity.BaseEntity;

/**
 * 结算方式
 * @author Administrator
 *
 */
public class Settlement extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 结算方式名称
	 */
	private String name;
	/**
	 * 备注
	 */
	private String note;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	

}
