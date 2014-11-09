package com.fms.core.vo.entity;

import java.io.Serializable;

/**
 * 订单统按月份统计临时类
 * @author Administrator
 *
 */
public class OrderReportByMonth implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 序号(numberice)
	 */
	private Integer numberice;
	/**
	 * 月份(months)
	 */
	private Integer months;
	/**
	 * 订单份数(orderQty)
	 */
	private Integer orderQty;
	/**
	 * 订单总金额(totalAmount)
	 */
	private Double totalAmount;
	public Integer getNumberice() {
		return numberice;
	}
	public void setNumberice(Integer numberice) {
		this.numberice = numberice;
	}
	public Integer getMonths() {
		return months;
	}
	public void setMonths(Integer months) {
		this.months = months;
	}
	public Integer getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	


}
