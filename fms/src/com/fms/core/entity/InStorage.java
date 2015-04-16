package com.fms.core.entity;

import java.util.Date;

import com.fms.annotation.CnFileName;
import com.fms.base.entity.BaseEntity;

/**
 * 入库
 * 
 * @author Administrator
 * 
 */
@CnFileName(name = "入库")
public class InStorage extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 流水号
	 */
	@CnFileName(name = "流水号")
	private Integer serialNo;

	/**
	 * 入库单号(inStorageNo)
	 */
	@CnFileName(name = "入库单号")
	private String inStorageNo;

	/**
	 * 采购单号(purchaseNo) 从采购单中获取，然后下拉框选择
	 */
	@CnFileName(name = "采购单号")
	private String purchaseNo;
	/**
	 * 订单号(orderNo)
	 */
	@CnFileName(name = "订单号")
	private String orderNo;

	/**
	 * 物料清单对象
	 */
	@CnFileName(name = "物料清单对象")
	private Material material;
	/**
	 * 入库数量(impQty)
	 */
	@CnFileName(name = "数量")
	private Double inQty;
	/**
	 * 入库人(handling) 统自动生成(当前登录用户)
	 */
	@CnFileName(name = "入库人")
	private String handling;
	/**
	 * 入库日期(impDate) 系统自动生成(当前系统时间)
	 */
	@CnFileName(name = "入库日期")
	private Date impDate;

	/**
	 * 客户供应商对象
	 */
	@CnFileName(name = "客户供应商")
	private Scmcoc scmcoc;
	/**
	 * 仓库对象
	 */
	@CnFileName(name = "仓库对象")
	private Stock stock;
	/**
	 * 启用状态(useFlag) 0、启用 1、作废 系统默认启用
	 */
	@CnFileName(name = "启用状态")
	private String useFlag;

	/**
	 * 每件包装数量
	 */
	@CnFileName(name = "每件包装数量")
	private Double specQty;
	/**
	 * 件数(入库数量/每包装数量,向上取整)系统自动计算
	 */
	@CnFileName(name = "件数")
	private Double pkgs;
	/**
	 * 货物标志(imgExgFlag)(原料、成品)下拉框选择
	 */
	@CnFileName(name = "货物标志")
	private String imgExgFlag;
	/**
	 * 入库类型 (0:采购入库 1:退货入库 2:其它入库)下拉框选择
	 */
	@CnFileName(name = "入库类型")
	private String impFlag;
	/**
	 * 物料类型(materialTypeName)(取物料类型表中的名称，下拉框选择)
	 */
	@CnFileName(name = "物料类型")
	private String materialTypeName;
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

	public String getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Double getInQty() {
		return inQty;
	}

	public void setInQty(Double inQty) {
		this.inQty = inQty;
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

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public String getInStorageNo() {
		return inStorageNo;
	}

	public void setInStorageNo(String inStorageNo) {
		this.inStorageNo = inStorageNo;
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

}
