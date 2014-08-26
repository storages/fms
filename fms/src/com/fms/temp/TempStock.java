package com.fms.temp;

import com.fms.core.entity.Stock;

/**
 * 临时类，辅助Excel导入功能（因为导入时要显示错误的信息，但这一属性不需要保存到数据库，故出现此临时类）
 * @author guodacai 2014-8-26 上午10:46:19
 *
 */
public class TempStock extends Stock {

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
