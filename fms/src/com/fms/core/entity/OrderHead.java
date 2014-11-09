package com.fms.core.entity;

import java.util.Date;

import com.fms.base.entity.BaseEntity;

/**
 * 订单表头
 * @author Administrator
 *
 */
public class OrderHead extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 流水号(serialNo)   系统自动生成累加1
	 */
	private Integer serialNo;
	
	/**
	 * 订单号(orderNo) 系统自动生成  格式：O+年月日+0001   例：O201411090001
	 */
	private String orderNo;
	/**
	 * 客户实体(ScmCoc)
	 */
	private Scmcoc scmcoc;
	
	/**
	 * 项数(itemQty) 系统自动计算OrderItem中的条数
	 */ 
	private Integer itemQty;
	/**
	 * 订单总数(totalQty) 系统自动计算OrderItem中的数量加总
	 */
	private Double totalQty;
	/**
	 * 总金额(totalAmount) 系统自动计算OrderItem中的金额加总
	 */
	private Double totalAmount;
	/**
	 * 下单日期(placeOrderDate) 
	 */
	private Date placeOrderDate;
	/**
	 * 交货期(deliveryDate)
	 */
	private Date deliveryDate;
	/**
	 * 业务员(salesman)  从用户表中列出是业务部门的所有人，下拉选择
	 */
	private String salesman;
	/**
	 * 备注(note)
	 */
	private String note;
	
	
	public Integer getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Scmcoc getScmcoc() {
		return scmcoc;
	}
	public void setScmcoc(Scmcoc scmcoc) {
		this.scmcoc = scmcoc;
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
	public Date getPlaceOrderDate() {
		return placeOrderDate;
	}
	public void setPlaceOrderDate(Date placeOrderDate) {
		this.placeOrderDate = placeOrderDate;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
}
