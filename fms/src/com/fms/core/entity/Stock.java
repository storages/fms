﻿package com.fms.core.entity;

import com.fms.annotation.CnFileName;
import com.fms.base.entity.BaseEntity;

@CnFileName(name="仓库")
public class Stock extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 仓库编码
	 */
	@CnFileName(name="仓库编码")
	private String code;
	/**
	 * 仓库名称
	 */
	@CnFileName(name="名称")
	private String name;
	/**
	 * 备注
	 */
	@CnFileName(name="备注")
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
