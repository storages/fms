package com.fms.core.entity;

import com.fms.base.entity.BaseEntity;

/**
 * BOM表版本号(用于同种成品，使用不同的物料生产)
 * @author Administrator
 *
 */
public class BomVersion extends BaseEntity {
	
	/**
	 * 成品
	 */
	private Material exgBom;
	/**
	 * 原料
	 */
	private Material imgBom;
	/**
	 * 版本编号
	 */
	private Integer versionNo;
	/**
	 * 备注
	 */
	private String note;
	public Material getExgBom() {
		return exgBom;
	}
	public void setExgBom(Material exgBom) {
		this.exgBom = exgBom;
	}
	public Integer getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Material getImgBom() {
		return imgBom;
	}
	public void setImgBom(Material imgBom) {
		this.imgBom = imgBom;
	}
	
	
}
