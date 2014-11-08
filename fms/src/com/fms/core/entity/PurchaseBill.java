package com.fms.core.entity;

import java.util.Date;

import com.fms.base.entity.BaseEntity;

/**
 * 采购单
 * @author Administrator
 *
 */
public class PurchaseBill extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 流水号(serialNo)   系统自动生成累加1
	 */
	private Integer serialNo;
	
	/**
	 * 采购单号(purchaseNo)  系统自动生成累加1 格式：C+年月日+0001   例如：C201411070001
	 */
	private String purchaseNo;
	/**
	 * 采购单状态(purchStatus)   0、未生效   1、生效   系统默认未生效
	 */
	private String purchStatus;
	/**
	 * 申请单号(appBillNo) 由申请单【审核】后系统生成
	 */
	private String appBillNo;
	/**
	 * 供应商(实体ScmCoc)  由申请单【审核】后系统生成
	 */
	private Scmcoc scmcoc;
	/**
	 * 物料(实体Material)   由申请单【审核】后系统生成
	 */
	private Material material;
	/**
	 * 采购数量(qty)   由申请单【审核】后系统生成
	 */
	private Double qty;
	/**
	 * 单价(price)  由申请单【审核】后系统生成
	 */
	private Double price;
	/**
	 * 金额(amount)  由申请单【审核】后系统生成
	 */
	private Double amount;
	/**
	 * 采购时间(purchaseDate)
	 */
	private Date purchaseDate;
	/**
	 * 交货日期(deliveryDate)
	 */
	private Date deliveryDate;
	/**
	 * 打印次数(printCount)  点击打印后系统自动加1
	 */
	private Integer printCount;
	/**
	 * 是否完结(isComplete) 当仓库收货收齐后，系统自动反写成已完结  0、未完结   1、已完结  默认未完结
	 */
	private Boolean isComplete;
	/**
	 * 特别说明(specialNote)
	 */
	private String specialNote;
	
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
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public Double getQty() {
		return qty;
	}
	public void setQty(Double qty) {
		this.qty = qty;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
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
	
	
}
