package com.fms.core.vo.entity;

import java.io.Serializable;
import java.util.Date;

import com.fms.core.entity.Scmcoc;

public class PurchaseReportVo implements Serializable {

	/**
	 * 
	 */
	private static long serialVersionUID = 1L;
	private Integer serialNo;// 流水号
	private String purchaseNo;// 采购单号
	private Scmcoc scmcoc;// 供应商
	private Double totalAmount;// 采购总金额
	private Date purchDate;// 采购日期
	private String specialNote;// 备注
	private String purchStatus;// 是否完结

	public String getPurchStatus() {
		return purchStatus;
	}

	public void setPurchStatus(String purchStatus) {
		this.purchStatus = purchStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}

	public String getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	public Scmcoc getScmcoc() {
		return scmcoc;
	}

	public void setScmcoc(Scmcoc scmcoc) {
		this.scmcoc = scmcoc;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getPurchDate() {
		return purchDate;
	}

	public void setPurchDate(Date purchDate) {
		this.purchDate = purchDate;
	}

	public String getSpecialNote() {
		return specialNote;
	}

	public void setSpecialNote(String specialNote) {
		this.specialNote = specialNote;
	}
}
