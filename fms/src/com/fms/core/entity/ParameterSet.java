package com.fms.core.entity;

import com.fms.annotation.CnFileName;
import com.fms.base.entity.BaseEntity;

/**
 * 系统参数设置
 * 
 * @author Administrator
 * 
 */
@CnFileName(name = "系统参数")
public class ParameterSet extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 无参构造
	 */
	public ParameterSet() {
	}

	/**
	 * 满参构造
	 * 
	 * @param qtyBits
	 * @param weightBits
	 * @param priceBits
	 * @param amountBits
	 * @param printCount
	 */
	public ParameterSet(Integer qtyBits, Integer weightBits, Integer priceBits, Integer amountBits, Integer printCount) {
		super();
		this.qtyBits = qtyBits == null ? 3 : qtyBits;
		this.weightBits = weightBits == null ? 3 : weightBits;
		this.priceBits = priceBits == null ? 3 : priceBits;
		this.amountBits = amountBits == null ? 3 : amountBits;
		this.printCount = printCount == null ? 3 : printCount;
	}

	// 数量保留小数位数
	private Integer qtyBits = 3;

	// 重量保留小数位数
	private Integer weightBits = 3;

	// 单价保留小数位数
	private Integer priceBits = 3;

	// 金额保留小数位数
	private Integer amountBits = 3;

	// 采购单打印次数
	private Integer printCount = 1;

	public Integer getPrintCount() {
		return printCount;
	}

	public void setPrintCount(Integer printCount) {
		this.printCount = printCount;
	}

	public Integer getQtyBits() {
		return qtyBits;
	}

	public void setQtyBits(Integer qtyBits) {
		this.qtyBits = qtyBits;
	}

	public Integer getWeightBits() {
		return weightBits;
	}

	public void setWeightBits(Integer weightBits) {
		this.weightBits = weightBits;
	}

	public Integer getPriceBits() {
		return priceBits;
	}

	public void setPriceBits(Integer priceBits) {
		this.priceBits = priceBits;
	}

	public Integer getAmountBits() {
		return amountBits;
	}

	public void setAmountBits(Integer amountBits) {
		this.amountBits = amountBits;
	}

}
