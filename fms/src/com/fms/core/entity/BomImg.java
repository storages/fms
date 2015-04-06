package com.fms.core.entity;

import com.fms.annotation.CnFileName;
import com.fms.base.entity.BaseEntity;

/**
 * 工厂BOM管理【料件资料】
 * 
 * @author Administrator
 * 
 */
@CnFileName(name="BOM料件")
public class BomImg extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// BOM版本对象
	private BomVersion bomVersion;
	// 物料清单
	private Material material;
	// 单耗
	@CnFileName(name="单耗")
	private Double unitConsume = 0.0;
	// 原料批次号
	@CnFileName(name="原料批次号")
	private String imgBetchNo;
	// 损耗
	@CnFileName(name="损耗")
	private Double wastRate = 0.0;
	// 单项用量(单项用量 单项用量=单耗/(1-损耗))
	@CnFileName(name="单项用量")
	private Double unitDosage = 0.0;
	// 备注
	@CnFileName(name="备注")
	private String note;

	public BomVersion getBomVersion() {
		return bomVersion;
	}

	public void setBomVersion(BomVersion bomVersion) {
		this.bomVersion = bomVersion;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Double getUnitConsume() {
		return unitConsume;
	}

	public void setUnitConsume(Double unitConsume) {
		this.unitConsume = unitConsume;
	}

	public Double getWastRate() {
		return wastRate;
	}

	public void setWastRate(Double wastRate) {
		this.wastRate = wastRate;
	}

	public Double getUnitDosage() {
		return unitDosage;
	}

	public void setUnitDosage(Double unitDosage) {
		this.unitDosage = unitDosage;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getImgBetchNo() {
		return imgBetchNo;
	}

	public void setImgBetchNo(String imgBetchNo) {
		this.imgBetchNo = imgBetchNo;
	}

}
