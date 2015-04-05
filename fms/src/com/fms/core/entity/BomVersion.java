package com.fms.core.entity;

import com.fms.base.entity.BaseEntity;

/**
 * 工厂BOM管理【版本资料】
 * 
 * @author Administrator
 * 
 */
public class BomVersion extends BaseEntity {

	/**
	 * 版本编号
	 */
	private Integer versionNo;

	/**
	 * 成品BOM
	 */
	private BomExg bomExg;
	/**
	 * 备注
	 */
	private String note;

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

	public BomExg getBomExg() {
		return bomExg;
	}

	public void setBomExg(BomExg bomExg) {
		this.bomExg = bomExg;
	}

}
