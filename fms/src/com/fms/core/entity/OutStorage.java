package com.fms.core.entity;

import java.util.Date;

import com.fms.annotation.CnFileName;
import com.fms.base.entity.BaseEntity;

/**
 * 出库
 */
public class OutStorage extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 流水号(serialNo)
	 */
	@CnFileName(name = "流水号")
	private Integer serialNo;

	/**
	 * 出库单号(inStorageNo)
	 */
	@CnFileName(name = "出库单号")
	private String outStorageNo;
	/**
	 * 订单号(orderNo) 从订单表头中抓取订单号(下拉框)
	 */
	@CnFileName(name = "订单号")
	private String orderNo;

	/**
	 * 采购单号(purchaseNo) 从订单表头中抓取订单号(下拉框)
	 */
	@CnFileName(name = "采购单号")
	private String purchaseNo;

	/**
	 * 启用状态(useFlag) 0、启用 1、作废 系统默认启用
	 */
	@CnFileName(name = "启用状态")
	private String useFlag = "0";
	/**
	 * 物料清单对象
	 */
	@CnFileName(name = "物料清单对象")
	private Material material;
	/**
	 * 货物数量(expQty) 根据订单号从入库表中获取，然后检查出库数量不能大于入库数量
	 */
	@CnFileName(name = "货物数量")
	private Double expQty;
	/**
	 * 出库人(handling) 统自动生成(当前登录用户)
	 */
	@CnFileName(name = " 出库人")
	private String handling;
	/**
	 * 出库日期(expDate) 系统自动生成(当前系统时间)
	 */
	@CnFileName(name = "出库日期")
	private Date expDate;
	/**
	 * 出库类型(0:出货出库 1:退货出库 2:外发出库 3:其它出库)
	 */
	@CnFileName(name = "出库类型")
	private String expFlag;

	/**
	 * 仓库对象
	 */
	@CnFileName(name = "仓库对象")
	private Stock stock;
	/**
	 * 每件包装数量
	 */
	@CnFileName(name = "每件包装数量")
	private Double specQty;
	/**
	 * 件数
	 */
	@CnFileName(name = "件数")
	private Double pkgs;
	/**
	 * 物料类型
	 */
	@CnFileName(name = "物料类型")
	private String imgExgFlag;

	/**
	 * 客户供应商对象
	 */
	@CnFileName(name = "客户供应商")
	private Scmcoc scmcoc;
	/**
	 * 备注
	 */
	@CnFileName(name = "备注")
	private String note;

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Double getExpQty() {
		return expQty;
	}

	public void setExpQty(Double expQty) {
		this.expQty = expQty;
	}

	public String getHandling() {
		return handling;
	}

	public void setHandling(String handling) {
		this.handling = handling;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public String getExpFlag() {
		return expFlag;
	}

	public void setExpFlag(String expFlag) {
		this.expFlag = expFlag;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOutStorageNo() {
		return outStorageNo;
	}

	public void setOutStorageNo(String outStorageNo) {
		this.outStorageNo = outStorageNo;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Scmcoc getScmcoc() {
		return scmcoc;
	}

	public void setScmcoc(Scmcoc scmcoc) {
		this.scmcoc = scmcoc;
	}

	public String getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	public String getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}

}
