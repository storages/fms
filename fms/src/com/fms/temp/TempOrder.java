package com.fms.temp;

import java.io.Serializable;
import java.util.Date;

/**
 * Order表导入临时类
 * 
 * @author Administrator
 * 
 */
public class TempOrder implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorInfo;
	private String orderNo;
	private String scmCocName;
	private Date placeOrderDate;
	private Date deliveryDate;
	private String salesman;
	private String hsCode;
	private Double qty;
	private Double price;
	private String note;// 备注

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getScmCocName() {
		return scmCocName;
	}

	public void setScmCocName(String scmCocName) {
		this.scmCocName = scmCocName;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getHsCode() {
		return hsCode;
	}

	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
