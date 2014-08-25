package com.fms.core.entity;

import com.fms.base.entity.BaseEntity;

/**
 * 物料
 * @author Administrator
 *
 */
public class Material extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//物料名称
	private String hsName;
	//颜色
	private String color;
	//物料类别
	private MaterialType materialType;
	//计量单位
	private Unit unit;
	//包装规格
	private String spec;
	//数量
	private Double qty;
	//件数(数量/包装规格)
	private Double pkgs;
	//成品或原料标记("I"原料，"E"成品)
	private String imgExgFlag;
	//批次号
	private String batchNO;
	//最低库存
	private Double lowerQty;
	//备注
	private String note;
	public String getHsName() {
		return hsName;
	}
	public void setHsName(String hsName) {
		this.hsName = hsName;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public MaterialType getMaterialType() {
		return materialType;
	}
	public void setMaterialType(MaterialType materialType) {
		this.materialType = materialType;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public Double getQty() {
		return qty;
	}
	public void setQty(Double qty) {
		this.qty = qty;
	}
	public Double getPkgs() {
		return pkgs;
	}
	public void setPkgs(Double pkgs) {
		this.pkgs = pkgs;
	}
	public String getImgExgFlag() {
		return imgExgFlag;
	}
	public void setImgExgFlag(String imgExgFlag) {
		this.imgExgFlag = imgExgFlag;
	}
	public String getBatchNO() {
		return batchNO;
	}
	public void setBatchNO(String batchNO) {
		this.batchNO = batchNO;
	}
	public Double getLowerQty() {
		return lowerQty;
	}
	public void setLowerQty(Double lowerQty) {
		this.lowerQty = lowerQty;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}