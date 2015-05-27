package com.fms.temp;

import java.io.Serializable;

/**
 * 入库导入临时类
 * 
 * @author Administrator
 * 
 */
public class TempInStorage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorInfo;
	private String imgExgFlag;// 物料标记
	private String impFlag;// 入库类型
	private String inStorageNo;// 入库单号
	private String orderNo;// 订单号
	private String purchaseNo;// 采购单号
	private String hsCode;// 物料编码
	private String specQty;// 每包装数量
	private Integer pkgs;// 件数
	private String note;// 备注

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getImgExgFlag() {
		return imgExgFlag;
	}

	public void setImgExgFlag(String imgExgFlag) {
		this.imgExgFlag = imgExgFlag;
	}

	public String getImpFlag() {
		return impFlag;
	}

	public void setImpFlag(String impFlag) {
		this.impFlag = impFlag;
	}

	public String getInStorageNo() {
		return inStorageNo;
	}

	public void setInStorageNo(String inStorageNo) {
		this.inStorageNo = inStorageNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	public String getHsCode() {
		return hsCode;
	}

	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}

	public String getSpecQty() {
		return specQty;
	}

	public void setSpecQty(String specQty) {
		this.specQty = specQty;
	}

	public Integer getPkgs() {
		return pkgs;
	}

	public void setPkgs(Integer pkgs) {
		this.pkgs = pkgs;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
