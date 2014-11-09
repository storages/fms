package com.fms.core.entity;

import java.util.Date;

import com.fms.base.entity.BaseEntity;

/**
 * 入库
 * @author Administrator
 *
 */
public class InStorage extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 流水号
	 */
	private Integer serialNo;
	/**
	 * 采购单号(purchaseNo)  从采购单中获取，然后下拉框选择
	 */
	private String purchaseNo;
	/**
	 * 物料编码(hsCode) 
	 */
	private String hsCode;
	/**
	 * 订单号(orderNo)
	 */
	private String orderNo;
	/**
	 * 商品编码(ptCode)
	 */
	private String ptCode;
	/**
	 * 货物名称(hsName)
	 */
	private String hsName;
	/**
	 * 货物规格型号(hsModel)
	 */
	private String hsModel;
	/**
	 * 颜色
	 */
	private String colors;
	/**
	 * 入库数量(impQty)
	 */
	private Double inQty;
	/**
	 * 批次号(betchNo)
	 */
	private String betchNo;
	/**
	 * 入库人(handling)  统自动生成(当前登录用户)
	 */
	private String handling;
	/**
	 * 入库日期(impDate)  系统自动生成(当前系统时间)
	 */
	private Date impDate;
	/**
	 * 启用状态(useFlag)  0、启用   1、作废  系统默认启用
	 */
	private String  useFlag;
	
	/**
	 * 每件包装数量
	 */
	private Double specQty;
	/**
	 *  件数(入库数量/每包装数量,向上取整)系统自动计算
	 */
	private Double pkgs;
	/**
	 * 货物标志(imgExgFlag)(原料、成品)下拉框选择
	 */
	private String imgExgFlag;
	/**
	 * 入库类型  (0:采购入库    1:退货入库     2:其它入库)下拉框选择
	 */
	private String impFlag;
	/**
	 * 物料类型(materialTypeName)(取物料类型表中的名称，下拉框选择)
	 */
	private String materialTypeName;
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
	public String getPurchaseNo() {
		return purchaseNo;
	}
	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	public String getHsCode() {
		return hsCode;
	}
	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPtCode() {
		return ptCode;
	}
	public void setPtCode(String ptCode) {
		this.ptCode = ptCode;
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
	public Double getInQty() {
		return inQty;
	}
	public void setInQty(Double inQty) {
		this.inQty = inQty;
	}
	public String getBetchNo() {
		return betchNo;
	}
	public void setBetchNo(String betchNo) {
		this.betchNo = betchNo;
	}
	public String getHandling() {
		return handling;
	}
	public void setHandling(String handling) {
		this.handling = handling;
	}
	public Date getImpDate() {
		return impDate;
	}
	public void setImpDate(Date impDate) {
		this.impDate = impDate;
	}
	public String getUseFlag() {
		return useFlag;
	}
	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}
	public Double getSpecQty() {
		return specQty;
	}
	public void setSpecQty(Double specQty) {
		this.specQty = specQty;
	}
	public Double getPkgs() {
		return pkgs;
	}
	public void setPkgs(Double pkgs) {
		this.pkgs = pkgs;
	}
	public String getImgExgFlag() {
		return imgExgFlag;
	}
	public void setImgExgFlag(String imgExgFlag) {
		this.imgExgFlag = imgExgFlag;
	}
	public String getImpFlag() {
		return impFlag;
	}
	public void setImpFlag(String impFlag) {
		this.impFlag = impFlag;
	}
	public String getMaterialTypeName() {
		return materialTypeName;
	}
	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getColors() {
		return colors;
	}
	public void setColors(String colors) {
		this.colors = colors;
	}
	

}
