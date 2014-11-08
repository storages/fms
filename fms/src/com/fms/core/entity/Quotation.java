package com.fms.core.entity;

import java.util.Date;

import com.fms.base.entity.BaseEntity;

/**
 * 报价单
 * @author Administrator
 *
 */
public class Quotation extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//流水号
	private Integer serialNo;
	//供应商实体
	private Scmcoc scmcoc;
	//物料实体
	private Material material;
	//单价
	private Double price;
	//生效日期
	private Date effect;
	//备注
	private String note;
	public Integer getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Integer serialNo) {
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getEffect() {
		return effect;
	}
	public void setEffect(Date effect) {
		this.effect = effect;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	


}
