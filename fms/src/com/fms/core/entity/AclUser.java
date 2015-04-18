package com.fms.core.entity;

import java.util.Date;

import com.fms.annotation.CnFileName;
import com.fms.base.entity.BaseEntity;

/**
 * 用户信息
 *
 * @author Administrator
 *
 */
 @CnFileName(name="登陆用户")
public class AclUser extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 登陆名称
	 */
	@CnFileName(name="登陆名")
	private String loginName = null;
	/**
	 * 密码
	 */
	@CnFileName(name="密码")
	private String password = null;
	/**
	 * 用户名称
	 */
	@CnFileName(name="用户名称")
	private String userName = null;
	/**
	 * 员工
	 */
	private Employee   employee;
	/**
	 * 最后登录日期
	 */
	private Date lastlogin = null;
	/**
	 * "L"为超级用户;"S"时为管理员;"P"为普通用户
	 */
	@CnFileName(name="权限")
	private String userFlag = null;
	/**
	 * 是否禁用;true为禁用； false为正常使用；　默认是false
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
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
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
            return "超级用户";
        } else {
            return "普通用户";
        }
    }
}
	