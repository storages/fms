package com.fms.temp;

import com.fms.core.entity.Currencies;

public class TempCurr extends Currencies {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorInfo;

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	
	
}
