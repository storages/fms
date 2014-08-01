package com.fms.core.entity;

import java.util.Date;

import com.fms.base.entity.BaseEntity;

/**
 * �û���Ϣ
 *
 * @author Administrator
 *
 */
public class AclUser extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ��½����
	 */
	private String loginName = null;
	/**
	 * ����
	 */
	private String password = null;
	/**
	 * �û�����
	 */
	private String userName = null;
	/**
	 * ����¼����
	 */
	private Date lastlogin = null;
	/**
	 * "L"Ϊ�����û�;"S"ʱΪ����Ա;"P"Ϊ��ͨ�û�
	 */
	private String userFlag = null;
	/**
	 * �Ƿ����
	 */
	private Boolean isForbid = false;
	
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}
	public String getUserFlag() {
		return userFlag;
	}
	public void setUserFlag(String userFlag) {
		this.userFlag = userFlag;
	}
	public Boolean getIsForbid() {
		return isForbid;
	}
	public void setIsForbid(Boolean isForbid) {
		this.isForbid = isForbid;
	}
	
	public String getUserFlagCN() {
        if ((userFlag != null) && userFlag.trim().equals("S")) {
            return "�����û�";
        } else {
            return "��ͨ�û�";
        }
    }
}
	