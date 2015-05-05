package com.fms.core.entity;

import com.fms.annotation.CnFileName;
import com.fms.base.entity.BaseEntity;

/**
 * 订单表体
 * 
 * @author Administrator
 * 
 */
@CnFileName(name = "订单详细")
public class OrderItem extends BaseEntity {

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
	 * 商品编码(hsCode)
	 */
	@CnFileName(name = "商品编码")
	private String hsCode;
	/**
	 * 商品名称(hsName)
	 */
	@CnFileName(name = "商品名称")
	private String hsName;
	/**
	 * 规格型号(hsModel)
	 */
	@CnFileName(name = "规格型号")
	private String hsModel;
	/**
	 * 订单数量(qty)
	 */
	@CnFileName(name = "订单数量")
	private Double qty;
	/**
	 * 计量单位实体(Unit)
	 */
	@CnFileName(name = "计量单位")
	private Unit unit;
	/**
	 * 单价(price)
	 */
	@CnFileName(name = "单价")
	private Double price;
	/**
	 * 总价(amount)
	 */
	@CnFileName(name = "总价")
	private Double amount;
	/**
	 * 备注
	 */
	@CnFileName(name = "备注")
	private String note;

	/**
	 * 订单表头对象
	 */
	@CnFileName(name = "订单表头对象")
	private OrderHead orderHead;

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

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
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

	public OrderHead getOrderHead() {
		return orderHead;
	}

	public void setOrderHead(OrderHead orderHead) {
		this.orderHead = orderHead;
	}

}
