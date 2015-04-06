package com.fms.temp;

import java.io.Serializable;

/**
 * 物料信息临时类
 * 
 * @author Administrator
 * 
 */
public class TempMater implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorInfo;
	// 物料编码
	private String hsCode;
	// 物料名称
	private String hsName;
	// 颜色
	private String color;
	// 物料类别
	private String materialType;
	// 计量单位
	private String unit;
	// 规格
	private String model;
	// 成品或原料标记("I"原料，"E"成品)
	private String imgExgFlag;
	// 批次号
	private String batchNO;
	// 最低库存
	private Double lowerQty;
	// 备注
	private String note;

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getHsCode() {
		return hsCode;
	}

	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}

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

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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
