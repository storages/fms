package com.fms.core.entity;

import com.fms.base.entity.BaseEntity;

public class OperateLogs extends BaseEntity {
	public final static String CREATE_OPERATE= "创建";
	public final static String EDIT_OPERATE= "修改";
	public final static String REMOVE_OPERATE= "删除";
	
	private String orgType;
	
	private String msg;
	
	private AclUser orgUser;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public AclUser getOrgUser() {
		return orgUser;
	}
	public void setOrgUser(AclUser orgUser) {
		this.orgUser = orgUser;
	}
	public static String getCreateOperate() {
		return CREATE_OPERATE;
	}
	public static String getEditOperate() {
		return EDIT_OPERATE;
	}
	public static String getRemoveOperate() {
		return REMOVE_OPERATE;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	
	
	
	
}
