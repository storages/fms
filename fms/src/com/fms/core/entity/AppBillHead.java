package com.fms.core.entity;

import java.util.Date;

import com.fms.annotation.CnFileName;
import com.fms.base.entity.BaseEntity;
import com.fms.commons.AppBillStatus;

/**
 * 请购单表头
 * @author Administrator
 *
 */
@CnFileName(name="请购单 ")
public class AppBillHead extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 流水号   系统自动生成
	 */
	@CnFileName(name="流水号")
	private Integer serialNo;
	/**
	 * 申请单状态   0、未申请  1、待审批  2、审批通过   3、审批不通过 【默认未申请】常量类：AppBillStatus
	 */
	@CnFileName(name="状态")
	private String appStatus = AppBillStatus.UNAPPLY;
	/**
	 * 申请单号码    系统自动生成 格式：R+年月日时分秒例如：R20141107130822
	 */
	@CnFileName(name="申请单号码")
	private String appNo;
	/**
	 * 项数    系统自动计算AppBillItem中的条数
	 */
	@CnFileName(name="项数")
	private Integer itemQty=0;
	/**
	 * 总数量    系统自动计算AppBillItem中的数量加总
	 */
	@CnFileName(name="总数量")
	private Double totalQty=0d;
	/**
	 * 总金额    系统自动计算AppBillItem中的金额加总
	 */
	@CnFileName(name="总金额")
	private Double totalAmount=0d;
	/**
	 * 申请日期
	 */
	@CnFileName(name="申请日期")
	private Date appDate;
	/**
	 * 已审批数    系统自动计算AppBillItem中的已审批的条数加总
	 */
	@CnFileName(name="已审批数")
	private Double approvaledQty=0d;
	/**
	 * 未审批数     系统自动计算AppBillItem中的未审批的条数加总
	 */
	@CnFileName(name="未审批数 ")
	private Double unApprovalQty=0d;
	
	/**
	 * 申请人
	 */
	/*@CnFileName(name="申请人 ")*/
	private AclUser submitUser;
	/**
	 * 备注
	 */
	@CnFileName(name="申请人 ")
	private String note;
	
	public Integer getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public Integer getItemQty() {
		return itemQty;
	}
	public void setItemQty(Integer itemQty) {
		this.itemQty = itemQty;
	}
	public Double getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(Double totalQty) {
		this.totalQty = totalQty;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Date getAppDate() {
		return appDate;
	}
	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}
	public Double getApprovaledQty() {
		return approvaledQty;
	}
	public void setApprovaledQty(Double approvaledQty) {
		this.approvaledQty = approvaledQty;
	}
	public Double getUnApprovalQty() {
		return unApprovalQty;
	}
	public void setUnApprovalQty(Double unApprovalQty) {
		this.unApprovalQty = unApprovalQty;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public AclUser getSubmitUser() {
		return submitUser;
	}
	public void setSubmitUser(AclUser submitUser) {
		this.submitUser = submitUser;
	}
	
	

}
