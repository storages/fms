package com.fms.core.vo.entity;

import java.io.Serializable;

/**
 * 采购按供应商统计临时类
 * @author Administrator
 *
 */
public class PurchaseReportByScmCoc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 序号(numberice)
	 */
	private Integer numberice;
	
	/**
	 * 供应商编码(code)
	 */
	private String code;
	
	/**
	 * 供应商名称(name)
	 */
	private String name;
	/**
	 * 联系电话(linkPhone)
	 */
	private String linkPhone;
	/**
	 * 采购数量(purQty)
	 */
	private Double purQty;
	
	/**
	 * 采购金额(amount)  注：加总所有对应的金额
	 */
	private Double amount;
	
	/**
	 * 已送货数量(sendQty)
	 */
	private Double sendQty;
	
	/**
	 * 未送货数量(unSendQty)
	 */
	private Double unSendQty;
	
	/**
	 * 物料是否送齐(isHand)
	 */
	private Boolean isHand;

	public Integer getNumberice() {
		return numberice;
	}

	public void setNumberice(Integer numberice) {
		this.numberice = numberice;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
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

	public Double getSendQty() {
		return sendQty;
	}

	public void setSendQty(Double sendQty) {
		this.sendQty = sendQty;
	}

	public Double getUnSendQty() {
		return unSendQty;
	}

	public void setUnSendQty(Double unSendQty) {
		this.unSendQty = unSendQty;
	}

	public Boolean getIsHand() {
		return isHand;
	}

	public void setIsHand(Boolean isHand) {
		this.isHand = isHand;
	}

	

}
