package com.fms.core.entity;

import com.fms.base.entity.BaseEntity;

/**
 * 工厂物料BOM表
 * @author Administrator
 *
 */
public class MaterialBom extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	/**
	 * 单项用量
	 */
	private Double unitDosage = 0.0; 
	
	/**
	 * BOM版本号
	 */
	private BomVersion bomVersion;
	
	/**
	 * 备注
	 */
	private String note;


	public Double getUnitDosage() {
		return unitDosage;
	}

	public void setUnitDosage(Double unitDosage) {
		this.unitDosage = unitDosage;
	}

	public BomVersion getBomVersion() {
		return bomVersion;
	}

	public void setBomVersion(BomVersion bomVersion) {
		this.bomVersion = bomVersion;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	

}
