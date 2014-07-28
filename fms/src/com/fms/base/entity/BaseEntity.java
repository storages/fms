package com.fms.base.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 父类公共实体
 * @author Administrator
 *
 */
public class BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 实体类ID
	 */
	private String id;
	
	/**
	 * 创建日期
	 */
	private Date createDate;
	
	/**
	 * 修改日期
	 */
	private Date modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	
	
}
