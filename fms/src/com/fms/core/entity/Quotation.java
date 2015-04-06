package com.fms.core.entity;

import java.util.Date;

import com.fms.annotation.CnFileName;
import com.fms.base.entity.BaseEntity;

/**
 * 报价单
 * @author Administrator
 *
 */
@CnFileName(name="报价单")
public class Quotation extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//流水号
	@CnFileName(name="流水号")
	private Integer serialNo;
	//供应商实体
	@CnFileName(name="供应商")
	private Scmcoc scmcoc;
	//物料实体
	@CnFileName(name="物料")
	private Material material;
	//单价
	@CnFileName(name="单价")
	private Double price;
	//生效日期
	@CnFileName(name="生效日期")
	private Date effectDate;
	//备注
	@CnFileName(name="备注")
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
	public Date getEffectDate() {
		return effectDate;
	}
	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	


}
