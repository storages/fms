package com.fms.core.entity;

import java.util.Date;

import com.fms.annotation.CnFileName;
import com.fms.base.entity.BaseEntity;
import com.fms.commons.AppBillStatus;

/**
 * 请购单表体
 * @author Administrator
 *
 */
@CnFileName(name="请购单详细 ")
public class AppBillItem extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 申请单状态(appStatus)   0、未申请  1、待审批  2、审批通过   3、审批不通过 【默认未申请】
	 * 常量类【AppBillStatus】
	 */
	@CnFileName(name="申请单状态 ")
	private String appStatus = AppBillStatus.UNAPPLY;
	/**
	 * 供应商(实体ScmCoc)
	 */
	private Scmcoc scmcoc;
	/**
	 * 物料(实体Material)
	 */
	private Material material;
	/**
	 * 单价(来源报价单)
	 */
	@CnFileName(name="单价 ")
	private Double price;
	/**
	 * 申请数量(totalQty)
	 */
	@CnFileName(name="申请数量 ")
	private Double totalQty;
	/**
	 * 金额(amount)
	 */
	@CnFileName(name="金额 ")
	private Double amount;
	/**
	 * 申请日期(appDate)
	 */
	@CnFileName(name="申请日期 ")
	private Date appDate;
	
	/**
	 * 审批日期
	 */
	@CnFileName(name="审批日期 ")
	private Date verifyDate;
	/**
	 * 审批人
	 */
	@CnFileName(name="审批人")
	private String verifyUser;
	
	/**
	 * 不通过原因
	 */
	@CnFileName(name="不通过原因")
	private String noPassReason;
	/**
	 * 申请单表头(实体AppBillHead)
	 */
	private AppBillHead head;
	/**
	 * 备注(note) 
	 */
	@CnFileName(name="备注")
	private String note;
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
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
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(Double totalQty) {
		this.totalQty = totalQty;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Date getAppDate() {
		return appDate;
	}
	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public AppBillHead getHead() {
		return head;
	}
	public void setHead(AppBillHead head) {
		this.head = head;
	}
	public Date getVerifyDate() {
		return verifyDate;
	}
	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}
	public String getVerifyUser() {
		return verifyUser;
	}
	public void setVerifyUser(String verifyUser) {
		this.verifyUser = verifyUser;
	}
	public String getNoPassReason() {
		return noPassReason;
	}
	public void setNoPassReason(String noPassReason) {
		this.noPassReason = noPassReason;
	}
	
	
	
}
