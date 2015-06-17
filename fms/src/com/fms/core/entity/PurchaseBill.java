package com.fms.core.entity;

import java.util.Date;

import com.fms.annotation.CnFileName;
import com.fms.base.entity.BaseEntity;

/**
 * 采购单
 * 
 * @author Administrator
 * 
 */
@CnFileName(name = "采购单")
public class PurchaseBill extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 流水号(serialNo) 系统自动生成累加1
	 */
	@CnFileName(name = "流水号")
	private Integer serialNo;

	/**
	 * 采购单号(purchaseNo) 系统自动生成 格式：C+年月日时分秒
	 */
	@CnFileName(name = "采购单号")
	private String purchaseNo;
	/**
	 * 采购单状态(purchStatus) 0、未生效 1、生效 系统默认未生效
	 */
	@CnFileName(name = "采购单状态")
	private String purchStatus = "0";
	/**
	 * 申请单号(appBillNo) 由申请单【审核】后系统抓取申请单
	 */
	@CnFileName(name = "申请单号")
	private String appBillNo;
	/**
	 * 采购单项数
	 */
	@CnFileName(name = "采购单项数")
	private Integer itemNo;

	/**
	 * 采购人
	 */
	@CnFileName(name = "采购人")
	private AclUser purchaseUser;

	/**
	 * 供应商(实体ScmCoc) 由申请单【审核】后系统抓取申请单
	 */
	@CnFileName(name = "供应商")
	private Scmcoc scmcoc;
	/**
	 * 采购单总金额
	 */
	@CnFileName(name = "采购单总金额")
	private Double totalAmount;
	/**
	 * 采购时间
	 */
	@CnFileName(name = "采购时间")
	private Date purchDate;
	/**
	 * 打印次数(printCount) 点击打印后系统自动加1
	 */
	@CnFileName(name = "打印次数")
	private Integer printCount = 0;
	/**
	 * 是否完结(isComplete) 当仓库收货收齐后，系统自动反写成已完结 false、未完结 true、已完结 默认未完结
	 */
	@CnFileName(name = "是否完结")
	private Boolean isComplete = Boolean.FALSE;
	/**
	 * 特别说明(specialNote)
	 */
	@CnFileName(name = "特别说明")
	private String specialNote;

	/**
	 * 订单号
	 */
	@CnFileName(name = "订单号")
	private String orderNo;

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

	public String getPurchStatus() {
		return purchStatus;
	}

	public void setPurchStatus(String purchStatus) {
		this.purchStatus = purchStatus;
	}

	public String getAppBillNo() {
		return appBillNo;
	}

	public void setAppBillNo(String appBillNo) {
		this.appBillNo = appBillNo;
	}

	public Scmcoc getScmcoc() {
		return scmcoc;
	}

	public void setScmcoc(Scmcoc scmcoc) {
		this.scmcoc = scmcoc;
	}

	public Integer getPrintCount() {
		return printCount;
	}

	public void setPrintCount(Integer printCount) {
		this.printCount = printCount;
	}

	public Boolean getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(Boolean isComplete) {
		this.isComplete = isComplete;
	}

	public String getSpecialNote() {
		return specialNote;
	}

	public void setSpecialNote(String specialNote) {
		this.specialNote = specialNote;
	}

	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public AclUser getPurchaseUser() {
		return purchaseUser;
	}

	public void setPurchaseUser(AclUser purchaseUser) {
		this.purchaseUser = purchaseUser;
	}

}
