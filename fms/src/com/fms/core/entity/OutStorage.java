package com.fms.core.entity;

import java.util.Date;

import com.fms.base.entity.BaseEntity;

public class OutStorage extends BaseEntity {

	/**
	 * 出库
	 */
	private static final long serialVersionUID = 1L;
	// 物料
	private Material material;
	// 每包装数量
	private Integer specQty;
	// 件数(出库数量/每包装数量,向上取整)
	private Double pkgs;
	// 客户名称
	private Scmcoc scmcoc;
	// 出库数量
	private Double outQty;
	// 出库类型(0:出货出库    1:退货出库     2:其它出库)
	private String expFlag;
	// 出库日期
	private Date expDate;
	
	//备注
	private String note;
	
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public Integer getSpecQty() {
		return specQty;
	}
	public void setSpecQty(Integer specQty) {
		this.specQty = specQty;
	}
	public Double getPkgs() {
		return pkgs;
	}
	public void setPkgs(Double pkgs) {
		this.pkgs = pkgs;
	}
	public Scmcoc getScmcoc() {
		return scmcoc;
	}
	public void setScmcoc(Scmcoc scmcoc) {
		this.scmcoc = scmcoc;
	}
	public Double getOutQty() {
		return outQty;
	}
	public void setOutQty(Double outQty) {
		this.outQty = outQty;
	}
	public String getExpFlag() {
		return expFlag;
	}
	public void setExpFlag(String expFlag) {
		this.expFlag = expFlag;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	

}
