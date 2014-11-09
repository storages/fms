package com.fms.core.vo.entity;

import java.io.Serializable;

/**
 * 采购按月统计临时类
 * @author Administrator
 *
 */
public class PurchaseReportByMonth implements Serializable{

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
	private String months;
	/**
	 * 物料类型(materialTypeName)
	 */
	private String materialTypeName;
	/**
	 * 物料编码(hsCode)
	 */
	private String hsCode;
	/**
	 * 物料名称(hsName)
	 */
	private String hsName;
	/**
	 * 规格型号(hsModel)
	 */
	private String hsModel;
	/**
	 * 采购数量(purQty)
	 */
	private Double purQty;
	/**
	 * 采购金额(amount)  注：加总所有对应的金额
	 */
	private Double amount;
	/**
	 * 入库数量(impQty)
	 */
	private Double impQty;
	
	/**
	 * 未入库数(unImpQty)
	 */
	private Double unImpQty;
	/**
	 * 物料是否收齐(isCollect)
	 */
	private Boolean isCollect;
	public Integer getNumberice() {
		return numberice;
	}
	public void setNumberice(Integer numberice) {
		this.numberice = numberice;
	}
	public String getMonths() {
		return months;
	}
	public void setMonths(String months) {
		this.months = months;
	}
	public String getMaterialTypeName() {
		return materialTypeName;
	}
	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
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
	public Double getPurQty() {
		return purQty;
	}
	public void setPurQty(Double purQty) {
		this.purQty = purQty;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getImpQty() {
		return impQty;
	}
	public void setImpQty(Double impQty) {
		this.impQty = impQty;
	}
	public Double getUnImpQty() {
		return unImpQty;
	}
	public void setUnImpQty(Double unImpQty) {
		this.unImpQty = unImpQty;
	}
	public Boolean getIsCollect() {
		return isCollect;
	}
	public void setIsCollect(Boolean isCollect) {
		this.isCollect = isCollect;
	}
	
	
	
}
