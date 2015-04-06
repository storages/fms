package com.fms.core.entity;

import com.fms.annotation.CnFileName;
import com.fms.base.entity.BaseEntity;

/**
 * 部门类
 * @author Administrator
 *
 */
@CnFileName(name="部门")
public class Department extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 部门编码
	 */
	@CnFileName(name="部门编码")
	private String code;
	/**
	 *  部门名称
	 */
	@CnFileName(name="部门名称")
	private String name;
	
	/**
	 * 备注
	 */
	@CnFileName(name="备注")
	private String note;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
}
