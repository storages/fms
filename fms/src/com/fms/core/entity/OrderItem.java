package com.fms.core.entity;

import com.fms.base.entity.BaseEntity;

/**
 * 订单表体
 * @author Administrator
 *
 */
public class OrderItem extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 流水号(serialNo)   系统自动生成累加1
	 */ 
	private Integer serialNo;
	/**
	 * 商品编码(hsCode)
	 */
	private String hsCode;
	/**
	 * 商品名称(hsName)
	 */
	private String hsName;
	/**
	 * 规格型号(hsModel)
	 */
	private String hsModel;
	/**
	 * 订单数量(qty)
	 */
	private String qty;
	/**
	 * 计量单位实体(Unit)
	 */
	private Unit unit;
	/**
	 * 单价(price)
	 */
	private Double price;
	/**
	 * 总价(amount)
	 */
	private Double amount;
	/**
	 * 备注
	 */
	private String note;
	
	public Integer getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	public String getHsCode() {
		return hsCode;
	}
	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}
	public String getHsName() {
		return hsName;
	}
	public void setHsName(String hsName) {
		this.hsName = hsName;
	}
	public String getHsModel() {
		return hsModel;
	}
	public void setHsModel(String hsModel) {
		this.hsModel = hsModel;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
}
