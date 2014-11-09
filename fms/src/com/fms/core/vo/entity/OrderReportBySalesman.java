package com.fms.core.vo.entity;

import java.io.Serializable;

/**
 * 订单按业务员统计临时类
 * 
 * @author Administrator
 * 
 */
public class OrderReportBySalesman implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 序号(numberice)
	 */
	private Integer numberice;
	/**
	 * 业务员(salesman)
	 */
	private String salesman;
	/**
	 * 供应商编码(code)
	 */
	private String code;
	/**
	 * 供应商名称(name)
	 */
	private String name;
	/**
	 * 订单份数(orderQty) 注：该业务员接单的份数(也就是订单表头条数)
	 */
	private Integer orderQty;

	/**
	 * 订单金额(totalAmount) 注：加总所有对应的金额
	 */
	private Double totalAmount;

	public Integer getNumberice() {
		return numberice;
	}

	public void setNumberice(Integer numberice) {
		this.numberice = numberice;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
