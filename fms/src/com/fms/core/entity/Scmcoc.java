package com.fms.core.entity;

import java.util.Date;

import com.fms.base.entity.BaseEntity;

/**
 * ��Ӧ����
 * @author GDC
 *
 */
public class Scmcoc extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ����
	 */
	private String code;
	/**
	 * ����
	 */
	private String name;
	/**
	 * ��Ӧ����ϵ�绰
	 */
	private String linkPhone;
	/**
	 * ��Ӧ��������ϵ��ʽ
	 */
	private String networkLink;
	/**
	 * ��Ӧ�̵�ַ
	 */
	private String address;
	/**
	 * ��Ӧ����ϵ��
	 */
	private String linkMan;
	/**
	 * ���˽�ֹ����
	 */
	private String endDate;
	/**
	 *	�Ƿ��ǿͻ� 
	 *	true ��ʾ�ǿͻ�
	 *	false ��ʾ�ǹ�Ӧ��
	 *
	 */
	public Boolean isCustom;
	/**
	 * ��ע
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
