package com.fms.core.entity;

import com.fms.base.entity.BaseEntity;

/**
 * 物料类别
 * @author Administrator
 *
 */
public class MaterialType extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//类型名称
	private String typeName;
	
	//备注
	private String note;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	

}
