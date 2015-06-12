package com.fms.temp;

import java.io.Serializable;

/**
 * 出库导入临时类
 * 
 * @author Administrator
 * 
 */
public class TempOutStorage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorInfo;
	private String imgExgFlag;// 物料标记
	private String expFlag;// 出库类型
	private String outStorageNo;// 出库单号
	private String orderNo;// 订单号
	private String purchaseNo;// 采购单号
	private String hsCode;// 物料编码
	private String specQty;// 每包装数量
	private String expQty;// 出库数量
	private String stockName;// 仓库名称
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

	public String getExpFlag() {
		return expFlag;
	}

	public void setExpFlag(String expFlag) {
		this.expFlag = expFlag;
	}

	public String getOutStorageNo() {
		return outStorageNo;
	}

	public void setOutStorageNo(String outStorageNo) {
		this.outStorageNo = outStorageNo;
	}

	public String getExpQty() {
		return expQty;
	}

	public void setExpQty(String expQty) {
		this.expQty = expQty;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

}
