package com.fms.core.entity;

import com.fms.annotation.CnFileName;
import com.fms.base.entity.BaseEntity;

/**
 * 计量单位
 * 
 * @author Administrator
 * 
 */
@CnFileName(name="计量单位")
public class Unit extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 编码
	@CnFileName(name="编码")
	private String code;
	// 名称
	@CnFileName(name="名称")
	private String name;
	// 备注
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

	public Unit() {
	}

	public Unit(String code, String name, String note) {
		super();
		this.code = code;
		this.name = name;
		this.note = note;
	}

}
