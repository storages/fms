package com.fms.core.entity;

import java.util.Date;

import com.fms.annotation.CnFileName;
import com.fms.base.entity.BaseEntity;

/**
 * 采购单详细内容
 * @author Administrator
 *
 */
@CnFileName(name="采购单详细")
public class PurchaseItem extends BaseEntity {
	
	/**
	 * 物料(实体Material)   由申请单【审核】后系统抓取申请单
	 */
	@CnFileName(name="物料")
	private Material material;
	/**
	 * 采购数量(qty)   由申请单【审核】后系统抓取申请单
	 */
	@CnFileName(name="采购数量")
	private Double qty;
	/**
	 * 单价(price)  由申请单【审核】后系统抓取申请单
	 */
	@CnFileName(name="单价")
	private Double price;
	/**
	 * 金额(amount)  由申请单【审核】后系统抓取申请单
	 */
	@CnFileName(name="金额")
	private Double amount;
	/**
	 * 采购时间(purchaseDate)
	 */
	@CnFileName(name="采购时间")
	private Date purchaseDate;
	/**
	 * 交货日期(deliveryDate)
	 */
	@CnFileName(name="交货日期")
	private Date deliveryDate;
	/**
	 * 是否已下单
	 */
	@CnFileName(name="是否已下单")
	private Boolean isBuy = Boolean.FALSE;
	/**
	 * 关联申请单表体id
	 */
	private String linkAppBillItemId;
	/**
	 * 采购单表头对象
	 */
	private PurchaseBill purchaseBill;
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
	public PurchaseBill getPurchaseBill() {
		return purchaseBill;
	}
	public void setPurchaseBill(PurchaseBill purchaseBill) {
		this.purchaseBill = purchaseBill;
	}
	public Boolean getIsBuy() {
		return isBuy;
	}
	public void setIsBuy(Boolean isBuy) {
		this.isBuy = isBuy;
	}
	public String getLinkAppBillItemId() {
		return linkAppBillItemId;
	}
	public void setLinkAppBillItemId(String linkAppBillItemId) {
		this.linkAppBillItemId = linkAppBillItemId;
	}
	
	
}
