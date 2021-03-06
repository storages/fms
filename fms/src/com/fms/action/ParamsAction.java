package com.fms.action;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.ParameterSet;
import com.fms.logic.ParamsLogic;

public class ParamsAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ParamsLogic paramsLogic;

	private String id;

	// 数量保留小数位数
	private Integer qtyBits;

	// 重量保留小数位数
	private Integer weightBits;

	// 单价保留小数位数
	private Integer priceBits;

	// 金额保留小数位数
	private Integer amountBits;

	// 采购单打印次数
	private Integer printCount;

	/**
	 * 查询参数设置的值
	 * 
	 * @return
	 */
	public String getParameterValue() {
		try {
			ParameterSet params = paramsLogic.getParameterValue(getLoginUser());
			request.put("params", params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.SUCCESS;
	}

	/**
	 * 保存参数
	 * 
	 * @return
	 */
	public String saveParamsValue() {
		ParameterSet ps = new ParameterSet(qtyBits, weightBits, priceBits, amountBits, printCount);
		if (id != null && !"".equals(id)) {
			ps.setId(id);
		}
		this.paramsLogic.saveParameterValue(getLoginUser(), ps);
		return "save";
	}

	public ParamsLogic getParamsLogic() {
		return paramsLogic;
	}

	public void setParamsLogic(ParamsLogic paramsLogic) {
		this.paramsLogic = paramsLogic;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getPrintCount() {
		return printCount;
	}

	public void setPrintCount(Integer printCount) {
		this.printCount = printCount;
	}

}
