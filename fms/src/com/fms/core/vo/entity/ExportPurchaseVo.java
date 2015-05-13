package com.fms.core.vo.entity;

import java.util.Date;

import com.fms.core.entity.Material;
import com.fms.core.entity.Scmcoc;

/**
 * 导出采购单临时类
 * 
 * @author Administrator
 * 
 */
public class ExportPurchaseVo {
	private int serialNo;// 序号
	private Scmcoc scmcoc;// 供应商
	private Material material;// 物料
	private Double qty;// 数量
	private String purchaseNo;// 采购单号
	private Double price;// 单价
	private Date purchaseDate;// 采购日期
	private Date deliveryDate;// 交货日期
	private String note;// 备注

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
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

	public String getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
