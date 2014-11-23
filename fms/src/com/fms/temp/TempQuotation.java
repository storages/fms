package com.fms.temp;

import java.io.Serializable;

/**
 * 报价单Excel导入临时类
 * @author Administrator
 *
 */
public class TempQuotation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorInfo;
	private String scmcocCode;
	private String scmcocName;
	private String hsCode;
	private String hsName;
	private String price;
	

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getScmcocCode() {
		return scmcocCode;
	}

	public void setScmcocCode(String scmcocCode) {
		this.scmcocCode = scmcocCode;
	}

	public String getScmcocName() {
		return scmcocName;
	}

	public void setScmcocName(String scmcocName) {
		this.scmcocName = scmcocName;
	}

	public String getHsCode() {
		return hsCode;
	}

	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}

	public String getHsName() {
		return hsName;
	}

	public void setHsName(String hsName) {
		this.hsName = hsName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	
	
	
}
