package com.fms.core.entity;

import java.util.Date;

import com.fms.base.entity.BaseEntity;

/**
 * 供应商类
 * @author GDC
 *
 */
public class Scmcoc extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 供应商联系电话
	 */
	private String linkPhone;
	/**
	 * 供应商网络联系方式
	 */
	private String networkLink;
	/**
	 * 供应商地址
	 */
	private String address;
	/**
	 * 供应商联系人
	 */
	private String linkMan;
	/**
	 * 结账截止日期
	 */
	private String endDate;
	/**
	 *	是否是客户 
	 *	true 表示是客户
	 *	false 表示是供应商
	 *
	 */
	public Boolean isCustom;
	/**
	 * 备注
	 */
	private String note;
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
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public Boolean getIsCustom() {
		return isCustom;
	}
	public void setIsCustom(Boolean isCustom) {
		this.isCustom = isCustom;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getLinkPhone() {
		return linkPhone;
	}
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	public String getNetworkLink() {
		return networkLink;
	}
	public void setNetworkLink(String networkLink) {
		this.networkLink = networkLink;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	
	
	
}
