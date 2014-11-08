package com.fms.core.entity;

import java.util.Date;

import com.fms.base.entity.BaseEntity;

public class InStorage extends BaseEntity {

	/**
	 * 入库
	 */
	private static final long serialVersionUID = 1L;
	//流水号
	private Integer serialNo;
	// 物料
	private Material material;
	// 每包装数量
	private Integer specQty;
	// 件数(入库数量/每包装数量,向上取整)
	private Double pkgs;
	// 供应商名称
	private Scmcoc scmcoc;
	// 入库数量
	private Double inQty;
	// 入库类型(0:采购入库    1:退货入库     2:其它入库)
	private String impFlag;
	// 入库日期
	private Date impDate;
	//物料类型
	private MaterialType materialType;
	//备注
	private String note;
	//进出库标志
	private String impExpFalg;
	
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
	
	public Double getInQty() {
		return inQty;
	}
	public void setInQty(Double inQty) {
		this.inQty = inQty;
	}
	public String getImpFlag() {
		return impFlag;
	}
	public void setImpFlag(String impFlag) {
		this.impFlag = impFlag;
	}
	public Date getImpDate() {
		return impDate;
	}
	public void setImpDate(Date impDate) {
		this.impDate = impDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getImpExpFalg() {
		return impExpFalg;
	}
	public void setImpExpFalg(String impExpFalg) {
		this.impExpFalg = impExpFalg;
	}
	public MaterialType getMaterialType() {
		return materialType;
	}
	public void setMaterialType(MaterialType materialType) {
		this.materialType = materialType;
	}
	
	

}
