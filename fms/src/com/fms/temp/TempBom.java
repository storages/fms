package com.fms.temp;

import java.io.Serializable;

/**
 * Bom表导入临时类
 * 
 * @author Administrator
 * 
 */
public class TempBom implements Serializable {
	private String errorInfo;
	private String imgCode;// 原料编码
	private String imgName;// 原料名称
	private String imgModel;// 原料规格
	private String imgBetchNo;// 原料批次号
	private Double consumption;// 单耗
	private Double wastage;// 损耗
	private Double singleDose;// 单项用量
	private String note;// 备注

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getImgCode() {
		return imgCode;
	}

	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getImgModel() {
		return imgModel;
	}

	public void setImgModel(String imgModel) {
		this.imgModel = imgModel;
	}

	public String getImgBetchNo() {
		return imgBetchNo;
	}

	public void setImgBetchNo(String imgBetchNo) {
		this.imgBetchNo = imgBetchNo;
	}

	public Double getConsumption() {
		return consumption;
	}

	public void setConsumption(Double consumption) {
		this.consumption = consumption;
	}

	public Double getWastage() {
		return wastage;
	}

	public void setWastage(Double wastage) {
		this.wastage = wastage;
	}

	public Double getSingleDose() {
		return singleDose;
	}

	public void setSingleDose(Double singleDose) {
		this.singleDose = singleDose;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
